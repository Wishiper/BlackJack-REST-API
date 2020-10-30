package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.models.card.CardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {


    Card ACE = new Card(CardType.valueOf("ACE_OF_CLUBS"));
    Card TWO = new Card(CardType.valueOf("TWO_OF_CLUBS"));
    Card THREE = new Card(CardType.valueOf("THREE_OF_CLUBS"));
    Card FOUR = new Card(CardType.valueOf("FOUR_OF_CLUBS"));
    Card FIVE = new Card(CardType.valueOf("FIVE_OF_CLUBS"));
    Card SIX = new Card(CardType.valueOf("SIX_OF_CLUBS"));
    Card SEVEN = new Card(CardType.valueOf("SEVEN_OF_CLUBS"));
    Card EIGHT = new Card(CardType.valueOf("EIGHT_OF_CLUBS"));
    Card NINE = new Card(CardType.valueOf("NINE_OF_CLUBS"));
    Card TEN = new Card(CardType.valueOf("TEN_OF_CLUBS"));
    Card JACK = new Card(CardType.valueOf("JACK_OF_CLUBS"));

    @Test
    void mustDraw_ShouldReturnTrue_OnSoft14() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(THREE,ACE);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertTrue(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnTrue_On10() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(TEN);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertTrue(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnSoft17() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(SIX,ACE);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnSoft18() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(THREE,FOUR,ACE);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnSoft19() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(THREE,FIVE,ACE);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnSoft20() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(SEVEN,TWO,ACE);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnHard17() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(NINE,EIGHT);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnHard21() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(NINE,EIGHT,FOUR);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }

    @Test
    void mustDraw_ShouldReturnFalse_OnBlackJack() {
        Dealer dealer = new Dealer();
        Hand hand = new Hand(ACE,JACK);

        dealer.setDealersHand(hand);
        dealer.getDealersHand().evaluateHand();

        assertFalse(dealer.mustDraw());
    }
}