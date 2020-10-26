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

    @Column(name = "isBlackJack")
    private boolean isBlackJack = false;

    @Column(name = "isSplitable")
    private boolean isSplitable = false;

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

    public String getHandValue() {
        return handValue;
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

    public boolean isBlackJack() {
        return isBlackJack;
    }

    public void setBlackJack(boolean blackJack) {
        isBlackJack = blackJack;
    }

    public boolean isSplitable(){
        return isSplitable;
    }

    public void setSplitable(boolean splitable) {
        isSplitable = splitable;
    }





    public void evaluateHand(){
        int tempValue = evaluateTempHandValue();
        checkIfSplittable();
        boolean softAceUsed = false;
        while (numberOfAces>0){
            if(tempValue>21){
                tempValue -= 10;
                numberOfAces -= 1;
            }else {
                softAceUsed = true;
                break;
            }
        }
        if(tempValue <= 21 && !softAceUsed){
            handValue = Integer.toString(tempValue);
        }
        if(tempValue < 21 && softAceUsed){
            handValue = tempValue - 10 + "/" + tempValue;
        }
        if(tempValue == 21 && cardsInHand.size() == 2){
            isBlackJack = true;
            handValue = tempValue + " - BlackJack";
        }
        else if (tempValue > 21){
            handValue = tempValue + " - Bust";
        }
    }

    public int evaluateTempHandValue(){
        int tempHandValue = 0;

        for (Card card: cardsInHand) {
            if(card.isAce()){
                numberOfAces++;
            }
            tempHandValue = tempHandValue + card.getRank();
        }


        return tempHandValue;
    }

    public void checkIfSplittable(){
        if(cardsInHand.size()==2 && cardsInHand.get(0) != null && cardsInHand.get(1) != null){
            isSplitable = cardsInHand.get(0).getRank() == cardsInHand.get(1).getRank();
        }else if(cardsInHand.size()>2){
            isSplitable = false;
        }
    }

//    public void evaluateHand(){
//        int tempValue = evaluateTempHandValue();
//         checkIfSplittable();
//        boolean softAceUsed = false;
//        while (numberOfAces>0 && !softAceUsed){
//            if(tempValue>21){
//                tempValue -= 10;
//                numberOfAces -= 1;
//                softAceUsed = true;
//            }else {
//                break;
//            }
//        }
//        if(tempValue < 21){
//            handValue = Integer.toString(tempValue);
//        }
//        if(tempValue < 21 && numberOfAces == 1){
//            handValue = tempValue - 10 + "/" + tempValue;
//        }
//        if(tempValue == 21){
//            isBlackJack = true;
//            handValue = tempValue + " - BlackJack";
//        }
//        else if (tempValue > 21){
//            handValue = tempValue + " - Bust";
//        }
//    }
//
//    public int evaluateTempHandValue(){
//        int tempHandValue = 0;
//
//        for (Card card: cardsInHand) {
//            if(card.isAce()){
//                numberOfAces++;
//            }
//            tempHandValue = tempHandValue + card.getRank();
//        }
//
//
//        return tempHandValue;
//    }
//
//    public void checkIfSplittable(){
//        if(cardsInHand.size()==2 && cardsInHand.get(0) != null && cardsInHand.get(1) != null){
//            isSplitable = cardsInHand.get(0).getRank() == cardsInHand.get(1).getRank();
//        }else if(cardsInHand.size()>2){
//            isSplitable = false;
//        }
//    }
}
