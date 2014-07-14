/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package testmodule;
import blackjack.*;
/**
 * @author Duong Steven
 */
public class SGL_Tests {
    private static SGL_Tests INSTANCE = null;
    private SGL_Tests(){}
    public static SGL_Tests Instance(){
        if (INSTANCE == null) INSTANCE = new SGL_Tests();
        return INSTANCE;
    }
    
    public void CardRandomness(){
        for (int i = 0; i < 52; i++){
        System.out.println("card: " + SGL_Deck.instance().getCard().toString());
        }
    }
    /*
    public void HandSum(){
        CLS_Player dummy = new CLS_Player();
        dummy.Hands.add(new CLS_Hand());
        
        //----------- 13 -------------------
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.TWO);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.SIX);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.instance().getHideCard());
        SGL_GameTable.Instance().calculateAllHands(dummy);
        System.out.println("player hand is: ");
        String str = "";
        for(SGL_Deck.CARD card : dummy.Hands.get(0).CardInHand){
          str += card.toString();
        }
        System.out.println(str);
        for(int tot : dummy.Hands.get(0).TotalSum){ System.out.println(tot + " "); }
        SGL_GameTable.Instance().clear(dummy);
        dummy.Hands.add(new CLS_Hand());
        
        //----------- 15 -------------------
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.JACK);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.FIVE);
        SGL_GameTable.Instance().calculateAllHands(dummy);
        System.out.println("player hand is: ");
        for(int tot : dummy.Hands.get(0).TotalSum){ System.out.println(tot + " "); }
        SGL_GameTable.Instance().clear(dummy);
        dummy.Hands.add(new CLS_Hand());
        
        //----------- 20 -------------------
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.QUEEN);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.KING);
        SGL_GameTable.Instance().calculateAllHands(dummy);
        System.out.println("player hand is: ");
        for(int tot : dummy.Hands.get(0).TotalSum){ System.out.println(tot + " "); }
        SGL_GameTable.Instance().clear(dummy);
        dummy.Hands.add(new CLS_Hand());
        
        //----------- 11/21 -------------------
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.QUEEN);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.ACE);
        SGL_GameTable.Instance().calculateAllHands(dummy);
        System.out.println("player hand is: ");
        for(int tot : dummy.Hands.get(0).TotalSum){ System.out.println(tot + " "); }
        SGL_GameTable.Instance().clear(dummy);
        dummy.Hands.add(new CLS_Hand());
        
        //----------- 16 -------------------
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.QUEEN);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.ACE);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.FOUR);
        dummy.Hands.get(0).CardInHand.add(SGL_Deck.CARD.ACE);
        SGL_GameTable.Instance().calculateAllHands(dummy);
        System.out.println("player hand is: ");
        for(int tot : dummy.Hands.get(0).TotalSum){ System.out.println(tot + " "); }
        SGL_GameTable.Instance().clear(dummy);
        dummy.Hands.add(new CLS_Hand());
    }*/
    
}
