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
    public String Name = "Player"+Number; 
    public int Account = 100;
    public ArrayList<CLS_Hand> Hands;
    public int numberOfHands = 0; 

    public CLS_Player(){
        Number++;      
        Name = getData("enter your name :", 0);  
        Hands = new ArrayList<>();
    }
    
    /**
     * User interface method used to retrive user command
     * @param str message to display before retrieving input
     * @return input command
     */
    public final String getData(String str, int type){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                // use the user-interface
                System.out.println("#" + this.Name + " : " + str);
                String inStr = in.readLine();
                
                // filter input
                switch(type){
                    case 0: // player name must be only letters with maximum of 8 char
                        if (inStr.matches("[a-zA-Z]{1,8}")) return inStr;
                        System.out.println("player name must be only letters with minimum of 1 char and maximum of 8 char !");
                        break;
                    case 1: // player can only bet less than chips he has
                        if (inStr.matches("\\d+") && (Integer.parseInt(inStr) > 0) && (Integer.parseInt(inStr) <= Account)){
                            return inStr;
                        }
                        System.out.println(" player can only bet number less or equal to chips he has !");
                        break;
                    case 2: // player available actions (1) to hit , (2) to stand, (3) to surrender , (4) to double, (5)to split
                         if (inStr.matches("[1-5]")) return inStr;
                         System.out.println(" unavailable cmd, enter the number cmd !");
                        break;
                    case 3: // player available actions (1) to hit , (2) to stand, (3) to surrender , (4) to double
                         if (inStr.matches("[1-4]")) return inStr;
                         System.out.println(" unavailable cmd, enter the number cmd !");
                        break;
                    case 4: // player available actions (1) to hit , (2) to stand, (3) to surrender
                        if (inStr.matches("[1-3]")) return inStr;
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
     * display msg to player UI
     * @param str 
     */
    public void display(String str){
        System.out.println("#" + this.Name + " " + str);
    }
}
