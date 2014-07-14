/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

import java.util.Objects;

/**
 * @author Duong Steven
 */
public class CLS_CARD {
    /**
     * Constructor with card type 
     * @param cardType 
     */
    public CLS_CARD(CARD cardType){
    Card = cardType;
    }
    
    public CLS_CARD(boolean hide, CARD cardType){
        Hide = hide;
        Card = cardType;
    }
    public CARD Card;
    public static final boolean HIDE = true;
    public static final boolean UNHIDE = false;
    public boolean Hide = UNHIDE;
    
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
       
    }
    
    /**
     * display the card
     * @return 
     */
     public String toString(){
            String name = "";
            if (!Hide){
                switch (Card.ordinal()) {
                    case 0:
                        name = "[A]";
                        break;
                    case 1:
                        name = "[2]";
                        break;
                    case 2:
                        name = "[3]";
                        break;
                    case 3:
                        name = "[4]";
                        break;
                    case 4:
                        name = "[5]";
                        break;
                    case 5:
                        name = "[6]";
                        break;
                    case 6:
                        name = "[7]";
                        break;
                    case 7:
                        name = "[8]";
                        break;
                    case 8:
                        name = "[9]";
                        break;
                    case 9:
                        name = "[10]";
                        break;
                    case 10:
                        name = "[J]";
                        break;
                    case 11:
                        name = "[Q]";
                        break;
                    case 12:
                        name = "[K]";
                        break;
                    default:
                        break;
                }
            }else{ name = "[?]";}
            return name;
        }
     
     @Override
     public boolean equals(Object o) {
         if (o instanceof CLS_CARD) {
             if (Card.equals(((CLS_CARD) o).Card)){
                 return true;
             }
         }
         return false;
     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.Card);
        return hash;
    }
}
