package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "hands")
public final class Hand {

    public Hand() {
        this.cardsInHand = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "handId")
    private int handId;
    /**
     * List of cards in the current Hand, a player could have multiple hands if he splits for example.
     */
    @Column(name = "cardsInHand", length = 1024)
    private final ArrayList<Card> cardsInHand;

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public int getHandId() {
        return handId;
    }

    /**
     * Adds a card to the Hand.
     * @param card Card object.
     */
    public void  addCard(Card card){
        cardsInHand.add(card);
    }
}