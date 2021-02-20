package cardgame;

import java.util.Random;

/**
 * Maintains a collection of zero or more playing cards.
 * Provides facilities to create a full pack of 52 cards
 * and to shuffle the cards.
 * 
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 1.06
 * @date 21/02/2021
 * 
 */

public class Cards {
	
	// Constants
    final int NUM_CARDS_IN_FULLPACK = 52;
    
    // Properties
    Card[] cards;
    int    valid;  // number of cards currently in collection
    
    // Constructors
    /**
     * Constructor for Cards class. Creates a full pack of cards
     * for this instance if the parameter is set to true.
     * @param fullPack Whether it should create a full pack of cards.
     */
    public Cards( boolean fullPack ) {
        cards = new Card[ NUM_CARDS_IN_FULLPACK ];
        valid = 0;
        
        if ( fullPack ) {
            createFullPackOfCards();
        }
    }
    
    // Methods
    /**
     * If current deck is not empty, takes a card from top,
     * removes it from the cards array and returns the card.
     * @return The Card object with highest index in the cards array.
     */
    public Card getTopCard() {
        Card tmp;
        if ( valid <= 0 ) {
            return null;
        } else {
            valid--;
            tmp = cards[valid];
            cards[valid] = null;
            return tmp;
        }
    }
    
    /**
     * If the current deck is not full adds a given card
     * to the top of the current deck.
     * @param c The card to be added
     * @return Whether the operation is successful.
     */
    public boolean addTopCard( Card c ) {
        if ( valid < cards.length ) {
            cards[valid] = c;   // should this be cloned?
            valid++;
            return true;
        }
        return false;
    }
    
    /**
     * Creates a full pack of cards for this card array.
     */
    private void createFullPackOfCards() {
    	for( int i = 0; i < NUM_CARDS_IN_FULLPACK; i++ ) {
    		addTopCard( new Card(i) );
    	}
    }

    /**
     * Shuffles the current deck of cards.
     * 
     * Iterates over each card in this deck and chooses
     * a random new position for each card. Swaps the 
     * cards in both positions.
     * 
     * This also could have been done using a method
     * from List interface. However, it would require
     * conversion from array to list and back to array.
     * Method from the list interface:
     * 
     * Collections.shuffle(cards);
     */
	public void shuffle() {
		Random random;
		Card temp;
		int newIndex;
		random = new Random();
		for( int i = 0; i < cards.length; i++ ) {
			newIndex = random.nextInt( cards.length );
			temp = cards[newIndex];
			cards[newIndex] = cards[i];
			cards[i] = temp;
		}
	}    
}
