package agprojects.blackjack.models;

import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.models.card.CardType;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("evaluateHand should return BlackJack with AceTen")
    void evaluateHand_ShouldReturnBLACKJACK_WithAceAndTEN() {
        Hand hand = new Hand(ACE, TEN);

        hand.evaluateHand();

        assertEquals("21 - BlackJack",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return BlackJack with JackAce")
    void evaluateHand_ShouldReturnBLACKJACK_WithJACKAndAce() {
        Hand hand = new Hand(JACK,ACE);

        hand.evaluateHand();

        assertEquals("21 - BlackJack",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 4/14 with AceThree")
    void evaluateHand_ShouldReturnSoft14_WithAceAndTHREE() {
        Hand hand = new Hand(ACE,THREE);

        hand.evaluateHand();

        assertEquals("4/14",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 9/19 with AceSevenThree")
    void evaluateHand_ShouldReturnSoft19_WithAceAndTHREE() {
        Hand hand = new Hand(ACE,SEVEN,ACE);

        hand.evaluateHand();

        assertEquals("9/19",hand.getHandValue());
    }

    @Test
a    @DisplayName("evaluateHand should return 9/19 with SixTwoAce")
    void evaluateHand_ShouldReturnSoft19_WithSixTwoAndAce() {
        Hand hand = new Hand(SIX,TWO,ACE);

        hand.evaluateHand();

        assertEquals("9/19",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 10/20 with NineAce")
    void evaluateHand_ShouldReturnSoft20_WithNineAndAce() {
        Hand hand = new Hand(NINE,ACE);

        hand.evaluateHand();

        assertEquals("10/20",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 10/20 with SixTwoAceAce")
    void evaluateHand_ShouldReturnSoft20_WithSixTwoAndAceAce() {
        Hand hand = new Hand(SIX,TWO,ACE,ACE);

        hand.evaluateHand();

        assertEquals("10/20",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 11/21 with NineAceAce")
    void evaluateHand_ShouldReturnSoft21_WithNineAndTwoAces() {
        Hand hand = new Hand(NINE,ACE,ACE);

        hand.evaluateHand();

        assertEquals("11/21",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 12 with SevenFourAce")
    void evaluateHand_ShouldReturn12_WithSevenFourAndAce() {
        Hand hand = new Hand(SEVEN,FOUR,ACE);

        hand.evaluateHand();

        assertEquals("12",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 22 - Bust with NineAceAceAce")
    void evaluateHand_ShouldReturnBUST_WithNineAndThreeAces_22() {
        Hand hand = new Hand(NINE,ACE,ACE,ACE);

        hand.evaluateHand();

        assertEquals("22 - Bust",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 22 - Bust with ThreeEightAceTen")
    void evaluateHand_ShouldReturnBUST_With22() {
        Hand hand = new Hand(THREE,EIGHT,ACE,TEN);

        hand.evaluateHand();

        assertEquals("22 - Bust",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 15 with TenFive")
    void evaluateHand_ShouldReturn15_WithTenAndFive() {
        Hand hand = new Hand(TEN,FIVE);

        hand.evaluateHand();

        assertEquals("15",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should return 21 with NineTwoTen")
    void evaluateHand_ShouldReturn21_WithNineTwoAndTen() {
        Hand hand = new Hand(NINE,TWO,TEN);

        hand.evaluateHand();

        assertEquals("21",hand.getHandValue());
    }

    @Test
    @DisplayName("evaluateHand should set isBlackJack to true with TenAce")
    void evaluateHand_ShouldSetIsBlackJackToTrue_WhenThereIsABlackJack() {
        Hand hand = new Hand(TEN,ACE);

        hand.evaluateHand();

        assertTrue(hand.isBlackJack());
    }

    @Test
    @DisplayName("evaluateHand should leave isBlackJack to be false if there is no BlackJack")
    void evaluateHand_IsBlackJackShouldBeFalse_WhenThereIsNoBlackJack() {
        Hand hand = new Hand(TEN,SIX);

        hand.evaluateHand();

        assertFalse(hand.isBlackJack());
    }

    @Test
    @DisplayName("evaluateHand should set isSplittable to true with TenTen")
    void evaluateHand_ShouldSetIsSplitableToTrue_WhenFirstAndSecondCardAreSameValue() {
        Hand hand = new Hand(TEN,TEN);

        hand.evaluateHand();

        assertTrue(hand.isSplittable());
    }
    @Test
    @DisplayName("evaluateHand should set isSplittable to true with AceAce")
    void evaluateHand_ShouldSetIsSplitableToTrue_WhenFirstTwoCardsAreAceAndAce() {
        Hand hand = new Hand(ACE,ACE);

        hand.evaluateHand();

        assertTrue(hand.isSplittable());
    }

    @Test
    @DisplayName("evaluateHand should set isBust to true with TenFiveSeven")
    void evaluateHand_ShouldSetIsBustToTrue_WhenOver22() {
        Hand hand = new Hand(TEN,FIVE,SEVEN);

        hand.evaluateHand();

        assertTrue(hand.isBust());
    }

}