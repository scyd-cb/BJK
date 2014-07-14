/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

import java.util.*;

/**
 * @author Duong Steven
 */
public class CLS_Hand {
    public int Bet = 0;
    public ArrayList<CLS_CARD> CardInHand;
    public ArrayList<Integer> TotalSum;
    public SGL_Deck.HAND_STATE State = SGL_Deck.HAND_STATE.NONE;
    
    /**
     * constructor
     * @param player
     * @param bet
     */
    public CLS_Hand(CLS_Player player, int bet){
        CardInHand = new ArrayList<>();
        TotalSum = new ArrayList<>();
        player.numberOfHands++;
        player.Account -= bet;
        SGL_Frame.GUI().formatPlayersAccount();
        Bet = bet;
    }
    
    public CLS_Hand(){
        CardInHand = new ArrayList<>();
        TotalSum = new ArrayList<>();
    }
    
    public CLS_Hand(SGL_Deck.HAND_STATE state){
        CardInHand = new ArrayList<>();
        TotalSum = new ArrayList<>();
        State = state;
    }
    
    /**
     * Unhide all card of the hand
     */
    public void openHand(){
        for(CLS_CARD card : CardInHand){
            card.Hide = CLS_CARD.UNHIDE;
        }
    }
    
    /**
     * Display all cards in the hand
     * @return 
     */
    public String getHand(){
        String str = "";
        if (!CardInHand.isEmpty()){
            for(CLS_CARD card : CardInHand){
                str += card.toString();
            }
        }
    return str;
    }
    
    @Override
     public boolean equals(Object o) {
         if (o instanceof CLS_Hand) {
             if (State.equals(((CLS_Hand) o).State)){
                 return true;
             }
         }
         return false;
     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.State);
        return hash;
    }
}
