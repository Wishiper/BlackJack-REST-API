package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a Hand in the BlackJack - could be a dealer or a player's hand.
 */
@Entity
@Table(name = "hands")
public final class Hand {

    public Hand() {
        this.cardsInHand = new ArrayList<>();
    }

    public Hand(Card... cards){
        this.cardsInHand = new ArrayList<>();
        this.cardsInHand.addAll(Arrays.asList(cards));
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "handId")
    private int handId;

    @Column(name = "handValue")
    private String handValue;

    @Column(name = "isBlackJack")
    private boolean isBlackJack = false;

    @Column(name = "isSplitable")
    private boolean isSplittable = false;

    @Column(name = "isBust")
    private boolean isBust = false;

    @Column(name = "isFinished")
    private boolean isFinished = false;

    @Column(name = "numberOfAces")
    private int numberOfAces = 0;

    /**
     * List of cards in the current Hand, a player could have multiple hands if he splits for example.
     */
    @Column(name = "cardsInHand", length = 1024)
    private final ArrayList<Card> cardsInHand;

    public int getHandId() {
        return handId;
    }

    public void setHandId(int handId) {
        this.handId = handId;
    }

    public String getHandValue() {
        return handValue;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }

    public boolean isSplittable(){
        return isSplittable;
    }

    public boolean isBust() {
        return isBust;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Adds a card to the Hand.
     * @param card Card object.
     */
    public void  addCard(Card card){
        cardsInHand.add(card);
    }

    /**
     * Evaluates hand value of the Hand, considering soft Aces and multiple Aces
     */
    public void evaluateHand(){
        int tempValue = evaluateTempHandValue();
        checkIfSplittable();
        boolean softAceUsed = false;

        //This Adjusts the value of the soft Ace in cases where aces are more than two
        if(numberOfAces>= 2){
            tempValue+=10;
        }
        // This while makes every Ace value to 1 if over 21 else if under 21 - its a soft ace
        while (numberOfAces>0){
            if(tempValue>21){
                tempValue -= 10;
                numberOfAces -= 1;
            }else {
                softAceUsed = true;
                break;
            }
        }
        //Returns the normal value of the hand without if its under 21 or equal and there isn't a soft Ace
        if(tempValue <= 21 && !softAceUsed){
            handValue = Integer.toString(tempValue);
        }
        // Returns the soft Ace value - 4/14 for example if cards are Ace and Three
        if(tempValue < 21 && softAceUsed){
            handValue = tempValue - 10 + "/" + tempValue;
        }
        // If there is only two cards and tempValue is 21 then it is a BlackJack
        if(tempValue == 21 && cardsInHand.size() == 2){
            isBlackJack = true;
            isFinished = true;
            handValue = tempValue + " - BlackJack";
        }
        // If over 21 - Hand is a BUST
        else if (tempValue > 21){
            isBust = true;
            isFinished = true;
            handValue = tempValue + " - Bust";
        }
    }

    /**
     * Adds up all the hands values into a temporaryResult and counts the number of Aces
     * @return Combined number of all cardsInHand values
     */
    private int evaluateTempHandValue(){
        int tempHandValue = 0;

        for (Card card: cardsInHand) {
            if(card.isAce()){
                numberOfAces++;
            }
            tempHandValue = tempHandValue + card.getRank();
        }


        return tempHandValue;
    }

    /**
     * Checks if the current Hand is splittable - true if first two cards are of same value
     */
    private void checkIfSplittable(){
        if(cardsInHand.size()==2 && cardsInHand.get(0) != null && cardsInHand.get(1) != null){
            isSplittable = cardsInHand.get(0).getRank() == cardsInHand.get(1).getRank();
        }else if(cardsInHand.size()>2){
            isSplittable = false;
        }
    }

    public Hand split() {
        Card cardToSplit = this.cardsInHand.remove(1);
        return new Hand(cardToSplit);

    }
}
