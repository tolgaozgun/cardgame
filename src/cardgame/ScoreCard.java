package cardgame;

import java.util.Arrays;

/**
 * 
 * Maintains one integer score per player, for any number of players Caution:
 * invalid player numbers result in run-time exception!
 * 
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 2.0
 * @date 21/02/2021
 *
 */

public class ScoreCard {
	// Properties
	int[] scores;

	// Constructors
	/**
	 * Creates a new score card with given number of players
	 * 
	 * @param noOfPlayers Number of players.
	 */
	public ScoreCard( int noOfPlayers ) {
		scores = new int[ noOfPlayers ];

		// Initialize all scores to be zero.
		for ( int i = 0; i < scores.length; i++ ) {
			scores[ i ] = 0;
		}
	}

	// Methods
	/**
	 * Returns the score of a player with the given index value Returns -1 for an
	 * non-existing index value.
	 * 
	 * @param playerNo Integer index value of a player.
	 * @return The score of the specified player.
	 */
	public int getScore( int playerNo ) {
		if ( playerNo > scores.length || playerNo < 0 ) {
			return -1;
		}
		return scores[ playerNo ];
	}

	/**
	 * Updates the score of a player. Doesn't do anything for an non-existing index
	 * value.
	 * 
	 * @param playerNo Integer index value of a player.
	 * @param amount   The score to be added to this player.
	 */
	public void update( int playerNo, int amount ) {
		if ( playerNo < scores.length && playerNo >= 0 ) {
			scores[ playerNo ] += amount;
		}
	}

	/**
	 * Gets the current score card as a String.
	 * 
	 * @return String value of the current score card.
	 */
	@Override
	public String toString() {
		String s;
		s = "\n" + "_____________\n" + "\nPlayer\tScore\n" + "_____________\n";

		for ( int playerNo = 0; playerNo < scores.length; playerNo++ ) {
			s = s + ( playerNo + 1 ) + "\t" + scores[ playerNo ] + "\n";
		}

		s += "_____________\n";
		return s;
	}

	/**
	 * Get the winners of the game. Called at the end of the game. Returns index
	 * values of players.
	 * 
	 * @return Integer array containing winner player(s)'s index.
	 */
	public int[] getWinners() {
		int maxValue;
		int[] returnArray;
		int returnSize;

		maxValue = scores[ 0 ];
		returnArray = new int[ scores.length ];
		returnSize = 0;

		// Iterates over each player's score.
		// If a new maximum is found, return array is cleared and
		// the new player is put inside the array. Max value is
		// changed to the found value.
		// If the current value is equal to prior maximum value,
		// current player is added to winners array.
		for ( int i = 0; i < scores.length; i++ ) {
			if ( scores[ i ] > maxValue ) {
				maxValue = scores[ i ];
				returnArray = new int[ scores.length - i ];
				returnArray[ 0 ] = i;
				returnSize = 1;
			} else if ( scores[ i ] == maxValue ) {
				returnArray[ returnSize ] = i;
				returnSize++;
			}
		}
		// Concats the current array to the corresponding size
		// while returning.
		return Arrays.copyOf( returnArray, returnSize );
	}
}
