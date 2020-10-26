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
    private final CardType type;

    public Card(CardType type) {
        this.type = type;
    }

    public CardType getType() {
        return type;
    }

    public int getRank() {
        return type.getRank().getCardRank();
    }

    public String getSuit() {
        return type.getSuit().getName();
    }

    public String getName() {
        return type.toString();
    }

    public boolean isAce(){
        return type.getRank().getCardRank() == 11;
    }
}