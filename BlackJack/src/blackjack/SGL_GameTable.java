/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Duong Steven
 */
public class SGL_GameTable {
    
    private static SGL_GameTable INSTANCE = null;
    /**
     * SGL_GameTable constructor
     * Ask the number of player
     */
    private SGL_GameTable(){
    SGL_Frame.GUI().displayInCenter("****************************************");
    SGL_Frame.GUI().displayInCenter("*              BLACKJACK               *");
    SGL_Frame.GUI().displayInCenter("****************************************");
    NumberOfPlayer = Integer.parseInt(getData("enter number of players:", 0));
    }
    
    /**
     * Entry point of singleton object 
     * @return uniq SGL_GameTable
     */
    public static SGL_GameTable Instance(){
        if (INSTANCE == null) INSTANCE = new SGL_GameTable();
        return INSTANCE;
    }
    
    /**
     * Enum used as transition in the game machine state
     */
    private enum GAME_STATE{
        INIT,
        START,
        END_BET,
        END_DEAL,
        END_PLAYER,
        END_DEALER,
        END_ROUND,
        END_GAME
    }
        
    public static int NumberOfPlayer = 0;
    public CLS_Player[] Players;                 // reference all players
    private GAME_STATE State = GAME_STATE.INIT;  // starting state
    
    /**
     * Methode used to retrieve input from players
     * @param str : message to be displayed before user input 
     * @param mode : used to activate different filter on the user input
     * @return 
     */
    private String getData(String str, int mode){
        do {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                System.out.println(str);
                String inStr = in.readLine();
                switch(mode){
                   case 0: // max number of player is 5
                        if (inStr.matches("[1-5]")) return inStr;
                        System.out.println(" max number is 5");
                        break;
                   case 1: // player available actions (1) Yes / (2) No
                       if (inStr.matches("[1-2]")) return inStr;
                       System.out.println(" unavailable cmd, enter the number cmd !");
                       break;
                   default:
                       break;
                }
            }catch(IOException ex){
                System.out.println("Invalid input retry");
            }
        }while(true);
    }
    
    /**
     * Method to instanciate player's profile and add it to the Players array
     */
    private void addPlayers(){
        Players = new CLS_Player[NumberOfPlayer];
        
        // Create a new player
        for (int i = 0; i < NumberOfPlayer; i++){
            Players[i] = new CLS_Player();
        }
    }
    
    /**
     * Reset players hands
     * @param player
     */
    public void clear(CLS_Player player){
        player.Hands.clear();
        player.numberOfHands = 1;
    }
    
    /**
     * Deal cards to player's hand
     * @param player: player to be assigned a card
     * @param currhand: player's hand to be assigned card (a player may has several hand) 
     */
    private void dealCard(CLS_Player player, CLS_Hand currhand){
        currhand.CardInHand.add(SGL_Deck.instance().getCard());
    }
    
    /**
     * Method to split a pair card hand in two  
     * @param player : owner of the hand
     * @param currhand : hand to be splitted
     */
    private void splitHand(CLS_Player player, CLS_Hand currhand){
        // create a new hand with same bet
        int lastPosition = player.Hands.size();
        player.Hands.add(lastPosition, new CLS_Hand(player,currhand.Bet));
        CLS_CARD pairCard = currhand.CardInHand.remove(0);
        player.Hands.get(lastPosition).CardInHand.add(pairCard);
        
        // deal cards to both hand once it has been splitted
        dealCard(player, currhand);
        dealCard(player, player.Hands.get(lastPosition));
        currhand.State = SGL_Deck.HAND_STATE.SPLITTED;
        player.Hands.get(lastPosition).State = SGL_Deck.HAND_STATE.SPLITTED;
    }
    
    /**
     * 
     * @param player
     * @param currhand 
     */
    private void doubleBet(CLS_Player player, CLS_Hand currhand){
        player.Account -= currhand.Bet;
        currhand.Bet *= 2;
        currhand.CardInHand.add(SGL_Deck.instance().getHideCard());
        currhand.State = SGL_Deck.HAND_STATE.DOUBLE;
    }
    
    /**
     * 
     * @param player
     * @param currhand 
     */
    private void surrender(CLS_Player player, CLS_Hand currhand){
         currhand.State = SGL_Deck.HAND_STATE.SURRENDER;
    }
    
    /**
     * 
     * @param hand 
     */
    private void calculateHand(CLS_Hand hand){
        // keep last card hiding if player choose to double
        int sum = 0;
        
        hand.TotalSum.clear();
        
        // calulate sum of open cards in the hand 
        for(CLS_CARD card : hand.CardInHand){
            if (!card.Hide) sum += card.Card.value();
        }
        
        hand.TotalSum.add(sum);
        
        // Calculate other possible hand's value if it contains an ACE
        if (hand.CardInHand.contains(new CLS_CARD(CLS_CARD.CARD.ACE)) && !(hand.CardInHand.get(hand.CardInHand.indexOf(new CLS_CARD(CLS_CARD.CARD.ACE)))).Hide && ((sum + 10) <= SGL_Deck.HAND_STATE.BLACKJACK.value())){
            hand.TotalSum.add(sum + 10);
            // sort in decreasing order to get the bigger sum at the top
            hand.TotalSum.sort(Collections.reverseOrder());
        }
        // Check if hand is busted
        if (hand.TotalSum.get(0) >= SGL_Deck.HAND_STATE.BUST.value()){
            hand.State =  SGL_Deck.HAND_STATE.BUST;
            
            // Check if initial hand is Blackjack
        }else if ((hand.State != SGL_Deck.HAND_STATE.SPLITTED) && (hand.CardInHand.size() == 2) && hand.CardInHand.contains(new CLS_CARD(CLS_CARD.CARD.ACE)) && (hand.CardInHand.contains(new CLS_CARD(CLS_CARD.CARD.JACK))
                                                                                                                                                             ||  hand.CardInHand.contains(new CLS_CARD(CLS_CARD.CARD.QUEEN))
                                                                                                                                                             ||  hand.CardInHand.contains(new CLS_CARD(CLS_CARD.CARD.KING)))){
            hand.State = SGL_Deck.HAND_STATE.BLACKJACK;
            
            // Check if the hand can be hitted
        }else if (hand.State == SGL_Deck.HAND_STATE.NONE){
            hand.State = SGL_Deck.HAND_STATE.NONE;
        }else{
             hand.State = SGL_Deck.HAND_STATE.PENDING;
        }
    }
    
    /**
     * 
     * @param player 
     */
    public void calculateAllHands(CLS_Player player){
        for(CLS_Hand hand : player.Hands){
            calculateHand(hand);
        }
    }
    
    /**
     * Method to process user input
     * @param player
     * @param hand
     * @param cmd 
     */
    private void process(CLS_Player player, CLS_Hand hand, int cmd){
        //command number: (1) to hit , (2) to stand, (3) to double, (4) to surrender, (5)to split
        switch(cmd){
            case 1:
                hand.CardInHand.add(SGL_Deck.instance().getCard());
                calculateHand(hand);
                break;
            case 2:
                hand.State = SGL_Deck.HAND_STATE.PENDING;
                break;
            case 3:
                surrender(player, hand);
                break;
            case 4:
                doubleBet(player, hand);
                break;
            case 5:
                splitHand(player, hand);
                break;
            default:
                System.out.println("Error unknown command !! ");
                break;
        }
    }
    
    /**
     * Method used to check if it is a winning hand
     * @param hand 
     */
    private void CheckFinal(CLS_Hand hand){
        if ((SGL_Dealer.instance().Hand.State == SGL_Deck.HAND_STATE.BUST) && (hand.State == SGL_Deck.HAND_STATE.PENDING)){
            // dealer is busted
            hand.State = SGL_Deck.HAND_STATE.WIN;
        }else if((SGL_Dealer.instance().Hand.State != SGL_Deck.HAND_STATE.BUST) && (hand.State != SGL_Deck.HAND_STATE.BUST)){
            
            // player is still in game
            if(SGL_Dealer.instance().Hand.TotalSum.get(0) < hand.TotalSum.get(0)){
                
                hand.State = SGL_Deck.HAND_STATE.WIN;
                
            }else if(SGL_Dealer.instance().Hand.TotalSum.get(0) == hand.TotalSum.get(0)){
                
                if(SGL_Dealer.instance().Hand.State == hand.State){
                    hand.State = SGL_Deck.HAND_STATE.TIE;
                }else if(SGL_Dealer.instance().Hand.State == SGL_Deck.HAND_STATE.BLACKJACK){
                    hand.State = SGL_Deck.HAND_STATE.LOOSE;
                }
                
            }else if (hand.State != SGL_Deck.HAND_STATE.SURRENDER){
                hand.State = SGL_Deck.HAND_STATE.LOOSE;
            }
        }
    }
    
    /**
     * Method used to calculate the total gain including initial bet
     * @param hand
     * @return 
     */
    private int CalculateGain(CLS_Hand hand){
        int gain = 0;
        if (hand.State == SGL_Deck.HAND_STATE.BLACKJACK){
            
            // if BlackJack pay at 3:2
            gain += (int)(hand.Bet * 2.5);
            
        }else if (hand.State == SGL_Deck.HAND_STATE.WIN) {
            
            // if win get the bet doubled  
            gain += hand.Bet * 2;
            
        }else if(hand.State == SGL_Deck.HAND_STATE.TIE){
            
            // if tie get the bet back
            gain += hand.Bet;
            
        }else if(hand.State == SGL_Deck.HAND_STATE.SURRENDER){
            // if surrender get the half of bet
            gain += hand.Bet/2 + hand.Bet%2;
        }
        return gain;
    } 
    
    /**
     * State machine used to process the game 
     */
    public void State_Machine(){
        while (State != GAME_STATE.END_GAME){
            switch(State){
                case INIT: // Add Players
                    
                    addPlayers();
                    State = GAME_STATE.START;
                    break;
                    
                case START: // Ask Bet
                    
                    SGL_Frame.GUI().displayFrame();
                    for(CLS_Player player : Players){
                        int bet = Integer.parseInt(player.getData("enter your bet", 1));
                        player.Hands.add(new CLS_Hand(player,bet));
                    }
                    State = GAME_STATE.END_BET;
                    SGL_Frame.GUI().displayFrame();
                    break;
                    
                case END_BET: // Deal cards
                    
                    for(int round = 1; round <= 2; round++){
                        for(CLS_Player player : Players){
                            player.Hands.get(0).CardInHand.add(SGL_Deck.instance().getCard());
                            calculateAllHands(player);
                        }
                        if (round == 1){
                            // first card is open 
                            SGL_Dealer.instance().Hand.CardInHand.add(SGL_Deck.instance().getCard());
                        }else if (round == 2){
                            // second card is hide unless the hand is a Blackjack
                            SGL_Dealer.instance().Hand.CardInHand.add(SGL_Deck.instance().getHideCard()); 
                            calculateHand(SGL_Dealer.instance().Hand);
                            if (SGL_Dealer.instance().Hand.State == SGL_Deck.HAND_STATE.BLACKJACK){
                                SGL_Dealer.instance().Hand.openHand();
                                calculateHand(SGL_Dealer.instance().Hand);
                                System.out.println("Dealer has Blackjack");
                                State = GAME_STATE.END_DEALER;
                                SGL_Frame.GUI().displayFrame();
                                break;
                            }
                            
                        }
                    }
                    State = GAME_STATE.END_DEAL;
                     SGL_Frame.GUI().displayFrame();
                    break;
                    
                case END_DEAL: // Ask actions
                    // variable used to reiterate in Hands list if it as been modified after a split hand
                    boolean redo;
                    for(CLS_Player player : Players){
                        do {
                            // Iterate through all players hand
                            redo = false;
                            ListIterator handIterator = player.Hands.listIterator();
                            while(handIterator.hasNext()){
                                CLS_Hand hand = (CLS_Hand)handIterator.next();
                                while (hand.State == SGL_Deck.HAND_STATE.NONE || (hand.State == SGL_Deck.HAND_STATE.SPLITTED && !redo)){ // 
                                    
                                    if(hand.CardInHand.get(0).equals(hand.CardInHand.get(1)) && player.Account >= hand.Bet){
                                        // a pair hand
                                        String str = " choose command number: (1) to hit , (2) to stand, (3) to surrender , (4) to double, (5)to split";
                                        int cmd = Integer.parseInt(player.getData(str, 2));
                                        process(player, hand, cmd);
                                         if (hand.State == SGL_Deck.HAND_STATE.SPLITTED) {
                                             redo = true;
                                         }
                                        
                                    }else if( player.Account >= hand.Bet){
                                        // can still double
                                        String str = " choose command number: (1) to hit , (2) to stand, (3) to surrender , (4) to double";
                                        int cmd = Integer.parseInt(player.getData(str, 3));
                                        process(player, hand, cmd);
                                    }else{
                                        // can still double
                                        String str = " choose command number: (1) to hit , (2) to stand, (3)to surrender";
                                        int cmd = Integer.parseInt(player.getData(str, 3));
                                        process(player, hand, cmd);
                                    }
                                    SGL_Frame.GUI().displayFrame();
                                }
                                // if Hands has been modified exit while loop and redo it
                                if (hand.State == SGL_Deck.HAND_STATE.SPLITTED && redo) {
                                    break;
                                }
                            }
                        }while(redo);
                    }
                    State = GAME_STATE.END_PLAYER;
                    SGL_Frame.GUI().displayFrame();
                    break;
                    
                case END_PLAYER: // Dealer's turn
                    
                    SGL_Dealer.instance().Hand.openHand();
                    calculateHand(SGL_Dealer.instance().Hand);
                    SGL_Frame.GUI().displayFrame();
                    
                    // check if still have hands in game if not directely go to check winner 
                    boolean pendingHands = true;
                    
                    for(CLS_Player player : Players){
                        // check all players hand
                        if ( player.Hands.contains((new CLS_Hand(SGL_Deck.HAND_STATE.PENDING))) 
                          || player.Hands.contains((new CLS_Hand(SGL_Deck.HAND_STATE.DOUBLE)))){
                             pendingHands = true;
                        }else{
                            //no pending hands in game, directely go to check result
                            State = SGL_GameTable.GAME_STATE.END_ROUND;
                            break;
                        }
                    }
                    
                    // Still have hands which are waiting for dealer's actions
                    while((SGL_Dealer.instance().Hand.TotalSum.get(0) < SGL_Dealer.instance().SumLimit) && pendingHands){
                        SGL_Dealer.instance().Hand.CardInHand.add(SGL_Deck.instance().getCard());
                        calculateHand(SGL_Dealer.instance().Hand);
                        SGL_Frame.GUI().displayFrame();
                    }
                    State = SGL_GameTable.GAME_STATE.END_DEALER;
                    break;
                    
                case END_DEALER:
                    HashMap<CLS_Player, Integer> playerGain = new HashMap<>();
                    for (CLS_Player player : Players){
                        playerGain.put(player, 0);
                        for (CLS_Hand hand : player.Hands){
                            //reveal last card if hand is doubled
                            if (hand.State == SGL_Deck.HAND_STATE.DOUBLE){
                                hand.openHand();
                                hand.State = SGL_Deck.HAND_STATE.PENDING;
                                calculateHand(hand);
                            }
                            
                            // Check the hand result
                            CheckFinal(hand);
                            
                            // Calculate gain of the hand
                            int newgain = playerGain.get(player) + CalculateGain(hand);
                            playerGain.put(player, newgain);
                        }
                    }
                    
                    SGL_Frame.GUI().displayFrame();
                    for (CLS_Player player : Players){
                        // display player gain
                        int gain = playerGain.get(player);
                        player.Account += gain;
                        if (gain > 0) player.display("Win " + gain);
                    }
                    SGL_Frame.GUI().formatPlayersAccount();
                    
                    State = SGL_GameTable.GAME_STATE.END_ROUND;
                    break;
                    
                case END_ROUND:
                    
                    int cmd = Integer.parseInt(getData("do you want to continue ? (1) Yes / (2) No", 1));
                    State =(cmd == 2)? SGL_GameTable.GAME_STATE.END_GAME : SGL_GameTable.GAME_STATE.START;
                    for (CLS_Player player : Players){ clear(player); }
                    SGL_Dealer.instance().clear();
                    break;
                    
                case END_GAME:
                    
                    SGL_Frame.GUI().displayInCenter("  GAME OVER ");
                    
                default:
                    
                    System.out.println("Error in State machine");
                    State = SGL_GameTable.GAME_STATE.END_GAME;
                    break;
            }
        }
    }
}

