package cardgame;

/**
 * Simple card game player with name and hand of cards
 * 
 * @author Tolga Ozgun, Deniz Gokcen, Burcu Kaplan
 * @version 1.02
 * @date 20/02/2021
 *
 */

public class Player {
    // Properties
    String name;
    Cards hand;
    
    // Constructors
    /**
     * Default constructor that accepts a name.
     * @param name The name of this player.
     */
    public Player( String name ) {
    	this.name = name;
    	hand = new Cards( false ); 
    }
    
    // Methods
    /**
     * Returns the name of this player
     * @return Name of this player
     */
    public String getName() {
        return name;
    }
    
    /**
     * Adds the given card to the top of
     * this player's deck.
     * @param c The card to be added
     */
    public void add( Card c ) {
        hand.addTopCard( c );
    }

    /**
     * Gets the top card from this player's deck.
     * @return Top card of this player.
     */
	public Card playCard() {
		Card card;
		card = hand.getTopCard();
		return card;
	}  
} 
