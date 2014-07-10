/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

/**
 * @author Duong Steven
 */
public class SGL_GameTable {
    
    private static SGL_GameTable INSTANCE = null;
    private SGL_GameTable(){}
    
    /**
     * Entry point of singleton object 
     * @return uniq SGL_GameTable
     */
    public static SGL_GameTable Instance(){
        if (INSTANCE == null) INSTANCE = new SGL_GameTable();
        return INSTANCE;
    }
    
    private enum GAME_STATE{
        START,
        END_BET,
        END_DEAL,
        END_ROUND,
        END_GAME
    }
        
    public static int MaxPlayer = 1;
    public static int NumberOfPlayer = 0;
    private  static CLS_Player[] Players;   // reference all players
    private GAME_STATE State = GAME_STATE.START;
    
    private void addPlayers(){
        Players = new CLS_Player[NumberOfPlayer];
        
        // Create a new player
        for (int i = 0; i < NumberOfPlayer; i++){
            Players[i] = new CLS_Player();
        }
    }
    
    private void dealCard(CLS_Player player){
        player.Hand.add(SGL_Deck.instance().getCard());
    }
}
