import java.util.Scanner;
import cardgame.*;

/**
 * Provides a menu allowing any of the players to play their card, an option to
 * see the score card, and one to quit the game at any time. When the game is
 * over it displays the winners.
 * 
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 2.2
 * @date 21/02/2020
 */

public class MyCardGame {

	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );

		// CONSTANTS
		final int MENU_EXIT = 0;
		final int MENU_PLAY_P1 = 1;
		final int MENU_PLAY_P2 = 2;
		final int MENU_PLAY_P3 = 3;
		final int MENU_PLAY_P4 = 4;
		final int MENU_SCORES = 5;

		// VARIABLES
		Player p1, p2, p3, p4;
		CardGame game;
		int selection;

		// PROGRAM CODE

		// create players...
		p1 = new Player( "p1" );
		p2 = new Player( "p2" );
		p3 = new Player( "p3" );
		p4 = new Player( "p4" );

		// create game with the 4 players...
		game = new CardGame( p1, p2, p3, p4 );

		// display menu, get and process selection, until exit
		do {
			// display menu
			System.out.println();
			System.out.println( "MyCardGame   Round: " + game.getRoundNo()
					 + "\t TurnOfPlayer: " + game.getTurnOfPlayerNo() );
			System.out.println();
			System.out.println( MENU_PLAY_P1 + " - Player " + MENU_PLAY_P1 + " plays" );
			System.out.println( MENU_PLAY_P2 + " - Player " + MENU_PLAY_P2 + " plays" );
			System.out.println( MENU_PLAY_P3 + " - Player " + MENU_PLAY_P3 + " plays" );
			System.out.println( MENU_PLAY_P4 + " - Player " + MENU_PLAY_P4 + " plays" );
			System.out.println( MENU_SCORES + " - Show scores" );

			// ask for and get selection
			System.out.println();
			System.out.println( "Selection (" + MENU_EXIT + " to exit): " );
			selection = scan.nextInt();

			// process selection
			if ( selection == MENU_PLAY_P1 ) {
				play( p1, game );
			} else if ( selection == MENU_PLAY_P2 ) {
				play( p2, game );
			} else if ( selection == MENU_PLAY_P3 ) {
				play( p3, game );
			} else if ( selection == MENU_PLAY_P4 ) {
				play( p4, game );
			} else if ( selection == MENU_SCORES ) {
				System.out.println( game.showScoreCard() );
			} else if ( selection != MENU_EXIT ) {
				System.out.println( "Invalid selection! \n" );
			}
		} while ( selection != MENU_EXIT && !game.isGameOver() );
		scan.close();

		if ( game.isGameOver() ) {
			System.out.print( "Game is over! " );
		} else {
			System.out.print( "You ended the game! ");
		}
		System.out.println( "Winners are:" );
		for ( Player winner : game.getWinners() ) {
			System.out.println( winner.getName() );
		}
		game.showScoreCard();
		
	}

	/**
	 * Plays the card for player in the current card game instance. If it is not
	 * successful, returns the card to player with a warning message.
	 * 
	 * @param p    The player to be played for.
	 * @param game Current CardGame instance.
	 * @return Boolean value whether it was successful.
	 */
	private static boolean play( Player p, CardGame game ) {
		Card c;
		boolean accepted;

		c = p.playCard();
		accepted = game.playTurn( p, c );
		if ( !accepted ) {
			System.out.println( "-----------------------------" );
			System.out.println( "It is not " + p.getName() + "'s turn!" );
			System.out.println( "-----------------------------" );
			p.add( c );
		}
		return accepted;
	}
}
