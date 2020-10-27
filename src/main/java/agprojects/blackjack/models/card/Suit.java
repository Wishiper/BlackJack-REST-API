package agprojects.blackjack.models.card;
import lombok.Getter;

/**
 * This enum holds all the possible Suits that are in a deck of cards
 */
@Getter
public enum Suit {
    CLUBS("CLUBS"),
    DIAMONDS("DIAMONDS"),
    HEARTS("HEARTS"),
    SPADES("SPADES");

    /**
     * Short string representation of the suit (one letter symbol).
     */
    private final String name;

    Suit(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the suit.
     */
    @Override
    public String toString() {
        return name;
    }
}