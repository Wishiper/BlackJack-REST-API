package agprojects.blackjack.models.card;


import lombok.Getter;

/**
 * This enum holds all the possible ranks that are in a deck of cards.
 */
public enum Rank {
    DEUCE(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    /**
     * Short string representation of the rank.
     */
    private final int cardRank;

    Rank(int cardRank) {
        this.cardRank = cardRank;
    }

    public int getCardRank() {
        return cardRank;
    }
}
