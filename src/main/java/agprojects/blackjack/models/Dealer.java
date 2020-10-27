package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;
import org.springframework.stereotype.Component;

/**
 * This class represents the dealer in BlackJack.
 */
@Component
public final class Dealer {

    /**
     * The dealers deck which consists of 312 cards (6 decks)
     */
    private final Deck deck;

    /**
     * The dealers current hand in the BlackJack round.
     */
    private Hand dealersHand;

    private boolean mustDraw = true;

    public Dealer() {
        this.deck = new Deck();
        this.dealersHand = new Hand();
    }

    /**
     * Draws one card from the deck.
     * @return Card Object - the top card in the deck.
     */
    public Card draw(){
        return deck.getCard();

    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getDealersHand() {
        return dealersHand;
    }

    public void setDealersHand(Hand dealersHand) {
        this.dealersHand = dealersHand;
    }

    public boolean mustDraw() {
        String dealerHand = dealersHand.getHandValue();
        if(dealerHand.equals("7/17") || dealerHand.equals("8/18") || dealerHand.equals("9/19")  || dealerHand.equals("10/20")){
            mustDraw = false;
        }else if(dealerHand.length() == 2){
            int handValue = Integer.parseInt(dealerHand);
            if(handValue>= 17 && handValue<= 21){
                mustDraw = false;
            }
        }else if(dealerHand.contains("Bust") || dealerHand.contains("BlackJack")){
            mustDraw = false;
        }

        return mustDraw;
    }
}
