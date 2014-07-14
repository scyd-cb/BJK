/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

/**
 * @author Duong Steven
 */
public class SGL_Frame {
    private static SGL_Frame INSTANCE = null;
    private SGL_Frame(){
        for(int i = 0; i < 25; i++){
            ClearPage += "\n";
        }
    }
    
    private String ClearPage = "";
    private static String  SPACE = "                    ";
    private int namepad = 0;
    private String PlayersName = "";
    private String PlayersAccount = "";
    private String PlayersHand = "";
    
    public static SGL_Frame GUI(){
        if (INSTANCE == null) INSTANCE = new SGL_Frame();
        return INSTANCE;
    }
    
    /**
     * clear terminal
     */
    public void clear(){
        System.out.println(ClearPage);
    }
    
    /**
     * display string in center
     * @param str 
     */
    public void displayInCenter(String str){
        String pad = "%"+ (67 + str.length()/2) +"s";
        System.out.println(String.format(pad, str));
    }
    
    /**
     * display in terminal 
     * @param str 
     */
    public void display(String str){
        System.out.println(str);
    }
    
    /**
     * used to center players' name
     */
    private void formatPlayersName(){
        PlayersName = "";
        int count = 1;
         for (CLS_Player player : SGL_GameTable.Instance().Players){   
           PlayersName += player.Name;
            if (count != SGL_GameTable.NumberOfPlayer) PlayersName += SPACE;        
            count++;
        } 
         namepad = (67 + PlayersName.length()/2);
    }
   
    /**
     * used to center players' account
     */
    public void formatPlayersAccount(){
        PlayersAccount = "";
        String currAccount;
        String prevAccount = "";
        String currName;
        String prevName = "";
         int count = 1;
         
         for (CLS_Player player : SGL_GameTable.Instance().Players){   
            currAccount = "<$" + Integer.toString(player.Account) + ">";
            currName = player.Name;
            if (count != 1){
                int ctr = currName.length() + prevName.length() - currAccount.length() - prevAccount.length();
                int nbspace = ctr/2 + ctr%2 + SPACE.length();
              PlayersAccount += String.format("%"+nbspace+"s", ""); 
            }
            PlayersAccount += currAccount;
            prevAccount = currAccount;
            prevName = currName;
            count++;
        }
         int nbpad = namepad + prevAccount.length()/2 - prevName.length()/2;
         PlayersAccount = String.format("%"+nbpad+"s", PlayersAccount);
    }
    
    /**
     * 
     */
    private void displayPlayerInfo(){
        if (PlayersName.length() == 0) formatPlayersName();
        if (PlayersAccount.length() == 0) formatPlayersAccount();
        displayInCenter(PlayersName);
        display(PlayersAccount);
        displayPlayerHands();
    }
    
    /**
     * 
     */
    private void displayPlayerHands(){
        int hand = 0;
        int maxNumberOfHand = 1;
        String currName;
        String prevName = "";
        String currHand;
        String prevHand = "";
        int count = 1;
        
        while (hand < maxNumberOfHand){
            PlayersHand = "";
            for (CLS_Player player : SGL_GameTable.Instance().Players){
                if (maxNumberOfHand < player.numberOfHands) maxNumberOfHand = player.numberOfHands;
                
                if (hand < player.Hands.size()){
                    String sumStr = "";
                    int num = 1;
                    for(int sum : player.Hands.get(hand).TotalSum){
                        if (num != 1) sumStr += "/";
                        sumStr += sum;
                        num++;
                    }
                    currHand = "$" + Integer.toString(player.Hands.get(hand).Bet) + " " + player.Hands.get(hand).getHand() + " (" + sumStr + ")";
                }else{
                    currHand = "";
                }
                currName = player.Name;
                
                if (count != 1){
                    int ctr = currName.length() + prevName.length() - currHand.length() - prevHand.length();
                    int nbspace = ctr/2 + ctr%2 + SPACE.length();
                    PlayersHand += String.format("%"+nbspace+"s", "");
                }
                PlayersHand += currHand;
                prevHand = currHand;
                prevName = currName;
                count++;
            }
             int nbpad = namepad + prevHand.length()/2 - prevName.length()/2;
             PlayersHand = String.format("%"+nbpad+"s", PlayersHand);
             System.out.println(PlayersHand);
            hand++;
        }
    }
    
    /**
     *  display fixed frame
     */
    public void displayFrame(){
        clear();
        displayInCenter(" __________ ");
        displayInCenter("|  DEALER  |");
        displayInCenter("|__________|");
        String sumStr = "";
        int num = 1;
        for(int sum : SGL_Dealer.instance().Hand.TotalSum){
            if (num != 1) sumStr += "/";
            sumStr += sum;
            num++;
        }
        if (!SGL_Dealer.instance().Hand.CardInHand.isEmpty()){
            displayInCenter(SGL_Dealer.instance().Hand.getHand() + " (" + sumStr + ")");
        }
        System.out.println("\n\n\n\n");
        displayPlayerInfo();
        System.out.println("\n\n\n\n\n\n\n\n\n");
    }
    
    
}
