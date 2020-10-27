package agprojects.blackjack.models.card;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Wrapper class used to store information about a card.
 */
public final class Card implements Serializable {


    /**
     * The type of card (Rank and Suit).
     *
     * @see CardType
     */
    @Enumerated(EnumType.STRING)
    private final CardType name;

    public Card(CardType name) {
        this.name = name;
    }

    public CardType getName() {
        return name;
    }

    public int getRank() {
        return name.getRank().getCardRank();
    }

    public String getSuit() {
        return name.getSuit().getName();
    }

    public boolean isAce(){
        return name.getRank().getCardRank() == 11;
    }
}