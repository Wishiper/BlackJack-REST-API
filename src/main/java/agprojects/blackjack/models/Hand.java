package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    @Getter
    private int handId;

    private ArrayList<Card> cardsInHand;

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }
}
