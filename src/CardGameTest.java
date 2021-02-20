import java.util.Scanner;
import cardgame.*;

// CardGameTest
// author:
// date:
public class CardGameTest
{
    public static void main( String[] args)
    {
        Scanner scan = new Scanner( System.in );
        System.out.println( "Start of CardGameTest\n");
        
        // CONSTANTS
        
        // VARIABLES
        Card       card;
        Cards      cards;
        ScoreCard  scores;
        Player     player1;
        Player     player2;
        Player     player3;
        Player     player4;
        
        
        CardGame   game;
        
        // PROGRAM CODE
        
        // test Card class
        card = new Card( 0 );
        System.out.println( card );
        System.out.println();
        
        // test Cards class
        cards = new Cards( true );
        cards.addTopCard( card );
        cards.testOnlyPrint();  // remove method after testing!
        
        // test ScoreCard class
        scores = new ScoreCard( 4 );
        scores.update( 3, 1 );
        scores.update( 1, 2 );
        System.out.println( "\n" + scores );
        
        // test Player class
        player1 = new Player( "PlayerName" );
        player1.add( card );
        System.out.println( player1.getName() + " plays " + player1.playCard() );
        
        // test CardGame class too?
        player2 = new Player( "Player2" );
        player3 = new Player( "Player3" );
        player4 = new Player( "Player4" );
        game = new CardGame ( player1, player2, player3, player4 );
        player1.add( card );
        player2.add( card );
        player3.add( card );
        player4.add( card );
        System.out.println( "Player 1 plays " + game.playTurn( player1, card ) );
        game.playTurn( player2, card );
        
        // Once you have all the bits working, complete the MyCardGame program
        // that provides a menu allowing any of the players to play their card,
        // an option to see the score card, and one to quit the game at any time.
        // When the game is over it should print out the winners.
        
        System.out.println( "\nEnd of CardGameTest\n" );
    }
    
} // end of class CardGameTest
