package cardgame;

import java.util.Arrays;

// ScoreCard - Maintains one integer score per player, for any number of players
//             Caution: invalid playernumbers result in run-time exception!
// author:
// date:
public class ScoreCard {
    // properties
    int[] scores;
    
    // constructors
    public ScoreCard( int noOfPlayers ) {
        scores = new int[ noOfPlayers ];
        
        // init all scores to zero
        for ( int i = 0; i < scores.length; i++) {
            scores[i] = 0;
        }
    }
    
    // methods
    public int getScore( int playerNo ) {
        return scores[ playerNo ];
    }
    
    public void update( int playerNo, int amount ) {
        scores[ playerNo ] += amount;
    }
    
    public String toString() {
        String s;
        s = "\n"
            + "_____________\n"
            + "\nPlayer\tScore\n"
            + "_____________\n";
        
        for ( int playerNo = 0; playerNo < scores.length; playerNo++ ){
            s = s + ( playerNo + 1 ) + "\t" + scores[ playerNo ] + "\n";
        }
        
        s += "_____________\n";
        return s;
    }
    
	public int[] getWinners() {
		int maxValue;
		int[] returnArray;
		int returnSize;
		
		maxValue = scores[ 0 ];
		returnArray = new int[ scores.length ];
		returnSize = 0;
		
		/*
		 * 
		// 4 6 5 6
		
		// max: integers[0]
		// returnArray: int[] { 0 } 
		
		// max: 4
		// returnArray: { 1 }
		
		// returnArray: { 1, 2 }
		
		// max: 1
		// returnArray: { 0 }
		
		int[] integers = { 1, 4, 4, 7 };
		 */
		
		for( int i = 0; i < scores.length; i++ ) {
			if( scores[ i ] > maxValue ) {
				maxValue = scores[ i ];
				returnArray = new int[ scores.length - i ];
				returnArray[ 0 ] = i;
				returnSize = 1;
			} else if( scores[ i ] == maxValue ) {
				returnArray[returnSize] = i;
				returnSize++;
			}
		}
		return Arrays.copyOf(returnArray, returnSize);
	}
} // end class ScoreCard
