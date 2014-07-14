/*
 * This program and the accompanying materials
 * are made available for the coding challenge 
 * organized by Insight.
 */

package blackjack;

import java.io.IOException;
//import testmodule.SGL_Tests;

/**
 *
 * @author Duong Steven
 */
public class BlackJack {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        SGL_GameTable.Instance().State_Machine();
    }
      
}
