package cardgame;

/**
 * 
 * A single playing Card class that holds suit and face value properties. Has
 * methods for comparing current card against an another card or printing out a
 * card's values.
 * 
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 2.0
 * @date 21/02/2021
 * 
 */

public class Card {
	// Constants
	final String[] SUITS = { "Hearts", "Diamonds", "Spades", "Clubs" };
	final String[] FACES = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	final int NUM_CARDS_IN_SUIT = 13;

	// Properties
	int cardNo;

	// Constructors

	/**
	 * Initiate Card object with a face value and a suit value.
	 * 
	 * @param faceValue The number written on the card
	 * @param suit      The integer value of the card's suit.
	 */
	public Card( int faceValue, int suit ) {
		cardNo = faceValue + suit * NUM_CARDS_IN_SUIT;
	}

	/**
	 * Initiate Card object with a integer of card number. Each suit moves card's
	 * index by 13. Suits are ordered as following: Hearts Diamonds Spades Clubs
	 * 
	 * @param cardNumber The integer value of cards index.
	 */
	public Card( int cardNumber ) {
		cardNo = cardNumber;
	}

	/**
	 * Returns the value of card without taking suits into consideration. This
	 * returns an integer from 0 to 12.
	 * 
	 * @return Integer value of the card's number (from 0 to 12 inclusive).
	 */
	public int getFaceValue() {
		return cardNo % NUM_CARDS_IN_SUIT;
	}

	/**
	 * Returns the suit value of card. This returns an integer from 0 to 3. Suits
	 * are ordered as following: 0 - Hearts 1 - Diamonds 2 - Spades 3 - Clubs
	 * 
	 * @return Integer value of the card's suit (from 0 to 3 inclusive).
	 */
	public int getSuit() {
		return cardNo / NUM_CARDS_IN_SUIT;
	}

	/**
	 * Returns the String value of the card. This String includes card's suit and
	 * face value.
	 * 
	 * @return String value of the card.
	 */
	@Override
	public String toString() {
		return FACES[ getFaceValue() ] + " of " + SUITS[ getSuit() ];
	}

	/**
	 * Checks if a card has the same face value and suit with this card.
	 * 
	 * @param c The card to compare this card to.
	 * @return Boolean whether these card's values are equal.
	 */
	public boolean equals( Card c ) {
		return c.getFaceValue() == getFaceValue() && c.getSuit() == getSuit();
	}

	/**
	 * Compares a given card's face value with this card's face value. Returns: > 1
	 * if this card's face value is bigger. > 0 if both of the cards' face values
	 * are equal. > -1 if other card has a bigger face value.
	 * 
	 * @param c Card to compare this card against.
	 * @return Integer value of -1, 0, 1 depending on the result.
	 */
	public int compareTo( Card c ) {
		return Integer.compare( getFaceValue(), c.getFaceValue() );
	}
}