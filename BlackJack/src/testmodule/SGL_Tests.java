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
    
    public void HandSum(){
        CLS_Player dummy = new CLS_Player();
        //----------- 13 -------------------
        dummy.Hand.add(SGL_Deck.CARD.TWO);
        dummy.Hand.add(SGL_Deck.CARD.SIX);
        dummy.Hand.add(SGL_Deck.CARD.FIVE);
        dummy.calculateHand();
        System.out.println("player hand is: ");
        for(int tot : dummy.TotalSum){ System.out.println(tot + " "); }
        dummy.clear();
        
        //----------- 15 -------------------
        dummy.Hand.add(SGL_Deck.CARD.JACK);
        dummy.Hand.add(SGL_Deck.CARD.FIVE);
        dummy.calculateHand();
        System.out.println("player hand is: ");
        for(int tot : dummy.TotalSum){ System.out.println(tot + " "); }
        dummy.clear();
        
        //----------- 20 -------------------
        dummy.Hand.add(SGL_Deck.CARD.QUEEN);
        dummy.Hand.add(SGL_Deck.CARD.KING);
        dummy.calculateHand();
        System.out.println("player hand is: ");
        for(int tot : dummy.TotalSum){ System.out.println(tot + " "); }
        dummy.clear();
        
        //----------- 11/21 -------------------
        dummy.Hand.add(SGL_Deck.CARD.QUEEN);
        dummy.Hand.add(SGL_Deck.CARD.ACE);
        dummy.calculateHand();
        System.out.println("player hand is: ");
        for(int tot : dummy.TotalSum){ System.out.println(tot + " "); }
        dummy.clear();
        
        //----------- 16 -------------------
        dummy.Hand.add(SGL_Deck.CARD.QUEEN);
        dummy.Hand.add(SGL_Deck.CARD.ACE);
        dummy.Hand.add(SGL_Deck.CARD.FOUR);
        dummy.Hand.add(SGL_Deck.CARD.ACE);
        dummy.calculateHand();
        System.out.println("player hand is: ");
        for(int tot : dummy.TotalSum){ System.out.println(tot + " "); }
        dummy.clear();
    }

}
