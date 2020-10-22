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

    @Column(name = "handValue")
    private String handValue;
    /**
     * List of cards in the current Hand, a player could have multiple hands if he splits for example.
     */
    @Column(name = "cardsInHand", length = 1024)
    private final ArrayList<Card> cardsInHand;

    public int getHandId() {
        return handId;
    }

    public String getHandValue() {
        return handValue;
    }
    //TODO implement this
    public void setHandValue() {
        int handValueSum = 0;
        for (Card card: cardsInHand) {
            handValueSum += card.getType().getRank().getCardRank();

        }

        this.handValue = handValueSum + "";
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * Adds a card to the Hand.
     * @param card Card object.
     */
    public void  addCard(Card card){
        cardsInHand.add(card);
    }

    public boolean hasBlackjack(){
//        if(getSecondHandValue() == 21 && myCards.size() == 2)
//            return true;
        return false;
    }
}
