package agprojects.blackjack.models.card;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Wrapper class used to store information about a card.
 */
@Entity
@Table(name = "cards")
@NoArgsConstructor
public final class Card {
    /**
     * The id of the card. Used for persistence.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    /**
     * The type of card.
     *
     * @see CardType
     */
    @Getter
    @Enumerated(EnumType.STRING)
    private CardType type;

    public Card(CardType type) {
        this.type = type;
    }
}