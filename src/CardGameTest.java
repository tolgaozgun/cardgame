import cardgame.Card;
import cardgame.CardGame;
import cardgame.Cards;
import cardgame.Player;
import cardgame.ScoreCard;

/**
 * Test class for this card game. Tests every functionality
 * in the code if it works optimally.
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 1.07
 * @date 21/02/2021
 *
 */

public class CardGameTest {
	
    public static void main( String[] args ) {
        System.out.println( "Start of CardGameTest\n" );
        
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
        System.out.println( "Turn: " + game.getTurnOfPlayerNo() + " Round: " + game.getRoundNo() );
        game.playTurn( player1, card );
        System.out.println( "Is turn of Player 2? " + game.isTurnOf( player2 ) );
        System.out.println( "Turn: " + game.getTurnOfPlayerNo() + " Round: " + game.getRoundNo() );
        game.playTurn( player2, card );
        System.out.println( "Turn: " + game.getTurnOfPlayerNo() + " Round: " + game.getRoundNo() );
        game.playTurn( player3, card );
        System.out.println( "Turn: " + game.getTurnOfPlayerNo() + " Round: " + game.getRoundNo() );
        game.playTurn( player4, card );
        
        System.out.println( "\nEnd of CardGameTest\n" );
    }
    
} // end of class CardGameTest
