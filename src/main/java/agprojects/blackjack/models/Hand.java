package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "hands")
public final class Hand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int handId;

    @Column(name = "cards_in_hand")
    private ArrayList<Card> cardsInHand;

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public int getHandId() {
        return handId;
    }
}
