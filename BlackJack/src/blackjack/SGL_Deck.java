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
public final int BLACKJACK = 21;
private final int NumberOfDeck = 1;
private Random rnd;    
private static ArrayList<CARD> CardsInDeck;
private static EnumMap<CARD, Integer> CardsOutDeck;
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

public enum CARD{
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),        
    KING(10);
    
    CARD(int val){ this.value = val; }
    private final int value;
    public int value(){ return value; }
    public String toString(){
        String name = "";
        switch (ordinal()) {
            case 0:
                name = "ACE";
                break;
            case 1:
                name = "TWO";
                break;
            case 2:
                name = "THREE";
                break;
            case 3:
                name = "FOUR";
                break;
            case 4:
                name = "FIVE";
                break;
            case 5:
                name = "SIX";
                break;
            case 6:
                name = "SEVEN";
                break;
            case 7:
                name = "EIGHT";
                break;
            case 8:
                name = "NINE";
                break;
            case 9:
                name = "TEN";
                break;
            case 10:
                name = "JACK";
                break;
            case 11:
                name = "QUEEN";
                break;
            case 12:
                name = "KING";
                break;
            default:
                name = "NOTHING";
                break;
        }
        return name;
    }
    }
    

public final void resetDeck(){
    long seed = new Date().getTime();
    rnd = new Random(seed);
    CardsInDeck = new ArrayList(Arrays.asList(CARD.values()));
    CardsOutDeck = new EnumMap<>(CARD.class);
}

public CARD getCard(){
    // get a random card off the deck
    int randomNumber = rnd.nextInt(CardsInDeck.size());
    CARD cardpicked = CardsInDeck.get(randomNumber);
    int numberOut = (CardsOutDeck.get(cardpicked) != null)? CardsOutDeck.get(cardpicked):0;
    CardsOutDeck.put(cardpicked, ++numberOut);
    
    // remove the card of the deck if the number is out of limit
    if (numberOut == NumberOfDeck*4) CardsInDeck.remove(randomNumber);
    return cardpicked; 
}

}
