package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.models.card.CardType;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HandTest {

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



    //Naming convention MethodName_ExpectedBehavior_StateUnderTest

    @Test
    void evaluateHand_ShouldReturnBLACKJACK_WithAceAndTEN() {
        Hand hand = new Hand(ACE, TEN);

        hand.evaluateHand();

        assertEquals("21 - BlackJack",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturnBLACKJACK_WithJACKAndAce() {
        Hand hand = new Hand(JACK,ACE);

        hand.evaluateHand();

        assertEquals("21 - BlackJack",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturnSoft14_WithAceAndTHREE() {
        Hand hand = new Hand(ACE,THREE);

        hand.evaluateHand();

        assertEquals("4/14",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturnSoft19_WithSixTwoAndAce() {
        Hand hand = new Hand(SIX,TWO,ACE);

        hand.evaluateHand();

        assertEquals("9/19",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturnSoft20_WithNineAndAce() {
        Hand hand = new Hand(NINE,ACE);

        hand.evaluateHand();

        assertEquals("10/20",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturn21_WithNineAndTwoAces() {
        Hand hand = new Hand(NINE,ACE,ACE);

        hand.evaluateHand();

        assertEquals("21",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturn12_WithSevenFourAndAce() {
        Hand hand = new Hand(SEVEN,FOUR,ACE);

        hand.evaluateHand();

        assertEquals("12",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturnBUST_WithNineAndThreeAces_22() {
        Hand hand = new Hand(NINE,ACE,ACE,ACE);

        hand.evaluateHand();

        assertEquals("22 - Bust",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturnBUST_With22() {
        Hand hand = new Hand(THREE,EIGHT,ACE,TEN);

        hand.evaluateHand();

        assertEquals("22 - Bust",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturn15_WithTenAndFive() {
        Hand hand = new Hand(TEN,FIVE);

        hand.evaluateHand();

        assertEquals("15",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldReturn21_WithNineTwoAndTen() {
        Hand hand = new Hand(NINE,TWO,TEN);

        hand.evaluateHand();

        assertEquals("21",hand.getHandValue());
    }

    @Test
    void evaluateHand_ShouldSetIsBlackJackToTrue_WhenThereIsABlackJack() {
        Hand hand = new Hand(TEN,ACE);

        hand.evaluateHand();

        assertTrue(hand.isBlackJack());
    }

    @Test
    void evaluateHand_ShouldSetIsSplitableToTrue_WhenFirstAndSecondCardAreSameValue() {
        Hand hand = new Hand(TEN,TEN);

        hand.evaluateHand();

        assertTrue(hand.isSplittable());
    }
    @Test
    void evaluateHand_ShouldSetIsSplitableToTrue_WhenFirstTwoCardsAreAceAndAce() {
        Hand hand = new Hand(ACE,ACE);

        hand.evaluateHand();

        assertTrue(hand.isSplittable());
    }

    @Test
    void evaluateHand_ShouldSetIsBustToTrue_WhenOver22() {
        Hand hand = new Hand(TEN,FIVE,SEVEN);

        hand.evaluateHand();

        assertTrue(hand.isBust());
    }


    @Test
    void evaluateTempHandValue() {
    }
}