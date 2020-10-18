package agprojects.blackjack.models.card;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Wrapper class used to store information about a card.
 */
public final class Card implements Serializable {

    /**
     * The type of card.
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
}