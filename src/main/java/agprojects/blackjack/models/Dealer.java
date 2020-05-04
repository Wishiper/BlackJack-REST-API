package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;
import org.springframework.stereotype.Component;

/**
 * This class represents the dealer in BlackJack.
 */
@Component
public final class Dealer {


    /**
     * The dealers deck which constists of 312 cards (6 decks)
     */
    private Deck deck;

    public Dealer(){
        this.deck = new Deck();
    }

    public Card draw(){
        return deck.getCard();

    }

    public Deck getDeck() {
        return deck;
    }


}
