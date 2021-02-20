package cardgame;

import java.util.ArrayList;

/**
 * CardGame class that manages the game. Creates and holds all player objects,
 * creates the deck and deals it to the players. Also handles player's play
 * turn requests.
 *  
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 1.06
 * @date 21/02/2021
 */

public class CardGame {
	// Constants
	final int         MAX_ROUNDS = 13;
	final int         NUM_PLAYERS = 4;
	
    // Properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;
    
    // Constructors
    /**
     * Initiates a CardGame instance with four Player objects.
     * @param p1 First player
     * @param p2 Second player
     * @param p3 Third player
     * @param p4 Fourth player
     */
    public CardGame( Player p1, Player p2, Player p3, Player p4 ) {
    	players = new ArrayList<Player>();
        players.add( p1 );
        players.add( p2 );
        players.add( p3 );
        players.add( p4 );
        
        fullPack = new Cards( true );
        fullPack.shuffle();
        
        for ( int i = 0; i < MAX_ROUNDS ; i++ ) {
        	players.get( 0 ).add( fullPack.getTopCard() );
        	players.get( 1 ).add( fullPack.getTopCard() );
        	players.get( 2 ).add( fullPack.getTopCard() );
        	players.get( 3 ).add( fullPack.getTopCard() );
        }
        
        scoreCard = new ScoreCard( NUM_PLAYERS );
        cardsOnTable = new Cards[ NUM_PLAYERS ];
        for( int i = 0; i < NUM_PLAYERS; i++ ) {
        	cardsOnTable[i] = new Cards(false);
        }
        turnOfPlayer = 0;
        roundNo = 1;
    }
    
    /**
     * This method plays a card for a player.
     * Returns false if
     * - Game is over
     * - It is not specified player's turn.
     * @param p Player to play
     * @param c Card to play
     * @return Boolean whether the card could be played.
     */
    public boolean playTurn( Player p, Card c ) {
    	if( isGameOver() ) {
    		return false;
    	}
    	
    	if( !isTurnOf( p ) ) {
    		return false;
    	}
    	cardsOnTable[ players.indexOf( p ) ].addTopCard( c );
    	System.out.println( "-----------------------------" );
    	System.out.println( p.getName() + " plays " + c );
    	System.out.println( "-----------------------------" );
    	if( turnOfPlayer != 3 ) {
    		turnOfPlayer++;
    	} else {
    		updateScores();
    		System.out.println( showScoreCard() );
        	turnOfPlayer = 0;
        	roundNo++;
    	}
    	return true;
    }
    
    /**
     * Returns a boolean whether it is the specified player's turn.
     * @param p The player to check
     * @return Boolean whether it is the specified player's turn.
     */
    public boolean isTurnOf( Player p ) {
        return players.indexOf( p ) == turnOfPlayer;
    }
    
    /**
     * Returns a boolean regarding if the game is over
     * @return Boolean if the game is over.
     */
    public boolean isGameOver() {
        return roundNo > MAX_ROUNDS;
    }
     
    /**
     * Gets the score of a player with the specified index value.
     * Index value should be between 0-3 inclusive.
     * Returns 0 for undefined players
     * @param playerNumber The integer value of the index of a player.
     * @return The score of the specified player.
     */
    public int getScore( int playerNumber ) {
    	if( playerNumber >= NUM_PLAYERS || playerNumber < 0 ) {
    		return 0;
    	}
        return scoreCard.getScore( playerNumber );
    }
    
    /**
     * Gets the name of a player with the specified index value.
     * Index value should be between 0-3 inclusive.
     * @param playerNumber The integer value of the index of a player.
     * @return The score of the specified player.
     */
    public String getName( int playerNumber ) {
    	if( playerNumber >= NUM_PLAYERS || playerNumber < 0 ) {
    		return null;
    	}
        return players.get( playerNumber ).getName();
    }
    
    /**
     * Gets the current round number.
     * Round numbers go from 1 to 13 inclusive.
     * @return Integer value of current round number.
     */
    public int getRoundNo() {
        return roundNo;
    }

    /**
     * Gets the index value of player whose turn it is.
     * Turn numbers go from 0 to 3 inclusive.
     * @return Integer value of index of the player to play.
     */
    public int getTurnOfPlayerNo() {
        return turnOfPlayer + 1;
    }
    
    /**
     * Updates the score card. This method is called at the end
     * of each round to check for cards on the table and increment
     * the winner player(s)'s value by one.
     */
    private void updateScores() {
    	int[] winners;
    	int max;
    	int winnerSize;
    	Player player;
    	Card c;
    	
    	max = 0;
    	winners = new int[ NUM_PLAYERS ];
    	winnerSize = 0;
    	// Iterates over each player's top card on the table.
    	// If a new maximum is found, winner array is cleared and
    	// the new player is put inside the array. Max value is 
    	// changed to the found value.
    	// If the current value is equal to prior maximum value,
    	// current player is added to winners array.
    	for( int i = 0; i < NUM_PLAYERS; i++ ) {
    		c = cardsOnTable[ i ].getTopCard();
    		if( c.getFaceValue() == max ) {
    			winners[ winnerSize ] = i;
    			winnerSize++;
    		} else if (c.getFaceValue() > max ){
    			max = c.getFaceValue();
    			winners = new int[ NUM_PLAYERS ];
    			winners[ 0 ] = i;
    			winnerSize = 1;
    		}
    	}
    	
    	// Iterates over each winner and gets the player
    	// object from their index value. Then prints
    	// their name and announces them as winners.
        for( int i = 0; i < winnerSize; i++ ) {
        	player = players.get(  winners[ i ] );
        	System.out.print( player.getName() );
        	
        	if( i != winnerSize - 1) {
        		System.out.print( ", " );
        	}
        	
        	scoreCard.update( winners[i], 1 );
        }
        System.out.println( " won this round! " );
    }

    /**
     * Returns the winning players of this game. This method is called
     * at the end of the game.
     * @return Player array of winner(s).
     */
    public Player[] getWinners() {
    	Player[] winnerPlayers;
        int[] winnerIntegers;
        
        winnerIntegers = scoreCard.getWinners();
        winnerPlayers = new Player[ winnerIntegers.length ];
        
        for( int i = 0; i < winnerIntegers.length; i++ ) {
        	winnerPlayers[i] = players.get( winnerIntegers[i] );
        }
        return winnerPlayers;
    }
    
    /**
     * This method returns the current score card.
     * @return String value of current score card.
     */
    public String showScoreCard() {
        return scoreCard.toString();
    }   
}