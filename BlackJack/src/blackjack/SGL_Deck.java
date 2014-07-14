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
public class SGL_Deck {
private final int NumberOfDeck = 1;
private Random rnd;    
private static ArrayList<CLS_CARD.CARD> CardsInDeck;
private static EnumMap<CLS_CARD.CARD, Integer> CardsOutDeck;
private static SGL_Deck INSTANCE = null;

// constructor
private SGL_Deck(){resetDeck();};

/**
 * Entry point of singleton object
 * @return uniq SGL_Deck
 */
public static SGL_Deck instance(){
    if (INSTANCE == null) INSTANCE = new SGL_Deck();
    return INSTANCE;
}

public enum HAND_STATE{
    NONE(0),
    SPLITTED(0),    
    DOUBLE(0),
    PENDING(20),
    BLACKJACK(21),
    SURRENDER(22),
    BUST(22),
    WIN(1),
    LOOSE(2),
    TIE(3);
    
    HAND_STATE(int val){ this.value = val; }
    private final int value;
    public int value(){ return value; }
}
    
/**
 * Used to shuffle deck
 */
public final void resetDeck(){
    long seed = new Date().getTime();
    rnd = new Random(seed);
    CardsInDeck = new ArrayList(Arrays.asList(CLS_CARD.CARD.values()));
    CardsOutDeck = new EnumMap<>(CLS_CARD.CARD.class);
}

/**
 * Used to generate a random card
 * @return 
 */
public CLS_CARD getCard(){
    // get a random card off the deck
    int randomNumber = rnd.nextInt(CardsInDeck.size());
    CLS_CARD.CARD cardpicked = CardsInDeck.get(randomNumber);
    int numberOut = (CardsOutDeck.get(cardpicked) != null)? CardsOutDeck.get(cardpicked):0;
    CardsOutDeck.put(cardpicked, ++numberOut);
    
    // remove the card of the deck if the number is out of limit
    if (numberOut == NumberOfDeck*4) CardsInDeck.remove(randomNumber);
    return new CLS_CARD(cardpicked); 
}

/**
 * Used to generate a hidden card 
 * @return 
 */
public CLS_CARD getHideCard(){
    CLS_CARD cardpicked = getCard();
    cardpicked.Hide = CLS_CARD.HIDE;
    return cardpicked;
}

}
