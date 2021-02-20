package cardgame;

import java.util.ArrayList;

// Cardgame
// author:
// date:
public class CardGame {
    // properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;
    
    // constructors
    public CardGame( Player p1, Player p2, Player p3, Player p4 ) {
    	players = new ArrayList<Player>();
        players.add( p1 );
        players.add( p2 );
        players.add( p3 );
        players.add( p4 );
        
        fullPack = new Cards( true );
        fullPack.shuffle();
        
        for ( int i = 0; i < 13 ; i++ ) {
        	players.get( 0 ).add( fullPack.getTopCard() );
        	players.get( 1 ).add( fullPack.getTopCard() );
        	players.get( 2 ).add( fullPack.getTopCard() );
        	players.get( 3 ).add( fullPack.getTopCard() );
        }
        
        scoreCard = new ScoreCard( players.size() );
        cardsOnTable = new Cards[ players.size() ];
        for( int i = 0; i < players.size(); i++ ) {
        	cardsOnTable[i] = new Cards(false);
        }
        turnOfPlayer = 0;
        roundNo = 0;
    }
    
    // methods
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
    
    public boolean isTurnOf( Player p ) {
        return players.indexOf( p ) == turnOfPlayer;
    }
    
    public boolean isGameOver() {
        return roundNo > 12;
    }
     
    public int getScore( int playerNumber ) {
        return scoreCard.getScore( playerNumber );
    }
    
    public String getName( int playerNumber ) {
    	Player player;
    	player = players.get( playerNumber );
        return player.getName();
    }
    
    public int getRoundNo() {
        return roundNo + 1;
    }
    
    public int getTurnOfPlayerNo() {
        return turnOfPlayer + 1;
    }
    
    private void updateScores() {
    	int[] winners;
    	int max;
    	int winnerSize;
    	Player player;
		Card c;
    	
    	max = 0;
    	winners = new int[4];
    	winnerSize = 0;
    	for( int i = 0; i < 4; i++ ) {
    		c = cardsOnTable[i].getTopCard();
    		if( c.getFaceValue() == max ) {
    			winners[winnerSize] = i;
    			winnerSize++;
    		} else if (c.getFaceValue() > max ){
    			max = c.getFaceValue();
    			winners = new int[4];
    			winners[0] = i;
    			winnerSize = 1;
    		}
    	}
    	
        for( int i = 0; i < winnerSize; i++ ) {
        	player = players.get(  winners[i] );
        	System.out.print( player.getName() );
        	if( i != winnerSize - 1) {
        		System.out.print( ", " );
        	}
        	scoreCard.update(winners[i], 1);
        }
        System.out.println( " won this round! " );
    }
    
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
    
    public String showScoreCard() {
        return scoreCard.toString();
    }
    
}