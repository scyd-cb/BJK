/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

/**
 * @author Duong Steven
 */
public class SGL_Dealer {
    private static SGL_Dealer INSTANCE = null;
    private SGL_Dealer(){ Hand = new CLS_Hand(); }
    public CLS_Hand Hand;
    public int SumLimit = 17; 
    public static SGL_Dealer instance(){
        if (INSTANCE == null) INSTANCE = new SGL_Dealer();
        return INSTANCE;
    }
    
    /**
     * reset dealer's hand
     */
    public void clear(){
        Hand.CardInHand.clear();
        Hand.TotalSum.clear();
        Hand.State = SGL_Deck.HAND_STATE.NONE;
    }
}
