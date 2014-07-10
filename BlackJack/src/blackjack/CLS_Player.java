/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;
import java.io.*;
import java.util.*;
        
/**
 * @author Duong Steven
 */
public class CLS_Player {
    private static int  Number = 0;
    private String Name = "Player"+Number;
    public ArrayList<SGL_Deck.CARD> Hand;
    public ArrayList<Integer> TotalSum;
    
    public CLS_Player(){
        Number++;      
        Name = getCommand("#Player " + Number + " enter your name :" );  
        Hand = new ArrayList<>();
        TotalSum = new ArrayList<>();
    }
    
    /**
     * User interface method used to retrive user command
     * @param str message to display before retrieving input
     * @return input command
     */
    public final String getCommand(String str){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                // use the user-interface
                System.out.println(str);
                return in.readLine();
            }catch(IOException ex){
                System.out.println("Invalid input retry");
            }
        }while(true);
    }
    
    public void display(String str){
        System.out.println(str);
    }
    
    public void calculateHand(){
        TotalSum.clear();
        int sum = 0;
        
        for(SGL_Deck.CARD HandCard : Hand){
            sum += HandCard.value();
        }
        
        TotalSum.add(sum);
        if (Hand.contains(SGL_Deck.CARD.ACE) && ((sum + 10) <= SGL_Deck.instance().BLACKJACK)){
            TotalSum.add(sum + 10);
            
            // sort in decreasing order to get the bigger sum at the top  
            TotalSum.sort(Collections.reverseOrder());
        }
    }
    
    public void clear(){
        Hand.clear();
    }
}
