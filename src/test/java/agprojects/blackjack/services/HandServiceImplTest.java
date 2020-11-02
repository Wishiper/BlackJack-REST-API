package agprojects.blackjack.services;

import agprojects.blackjack.exceptions.ApiRequestException;
import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.models.card.CardType;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HandServiceImplTest {

    Card ACE = new Card(CardType.valueOf("ACE_OF_CLUBS"));
    Card FIVE = new Card(CardType.valueOf("FIVE_OF_CLUBS"));
    Card SIX = new Card(CardType.valueOf("SIX_OF_CLUBS"));
    Card TEN = new Card(CardType.valueOf("TEN_OF_CLUBS"));

    @Mock
    Dealer dealer;

    @InjectMocks
    private static final HandServiceImpl handService = new HandServiceImpl();

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Hit should work properly when hand is not finished")
    void hit_ShouldDrawTheCorrectCardWhenCalled_WhenHandIsFinishedFalse() {
        int handId = 1;
        Hand hand = new Hand(TEN,FIVE);
        hand.setHandId(handId);
        List<Hand> handList = new ArrayList<>();
        handList.add(hand);
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setHands(handList);

        when(dealer.draw()).thenReturn(SIX);

        handService.hit(player,handId);

        assertEquals(SIX,player.getHands().get(0).getCardsInHand().get(2));
        assertEquals("21",player.getHands().get(0).getHandValue());
    }

    @Test
    @DisplayName("Hit should throw exception if hand is finished")
    void hit_ShouldThrowHAND_IS_FINISHED_WhenHandIsFinishedTrue() {
        int handId = 1;
        Hand hand = new Hand(TEN,FIVE);
        hand.setHandId(handId);
        hand.setFinished(true);
        List<Hand> handList = new ArrayList<>();
        handList.add(hand);
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setHands(handList);

        Exception exception = assertThrows(ApiRequestException.class, () -> handService.hit(player,handId));

        String expectedMessage = String.format(HandServiceImpl.HAND_IS_FINISHED,handId);
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));

    }

    @Test
    @DisplayName("Hit should throw exception if hand is not found")
    void hit_ShouldThrowHAND_NOT_FOUND_WhenHandIsNotFound() {
        int handId = 1;
        Hand hand = new Hand(TEN,FIVE);
        hand.setHandId(handId);
        hand.setFinished(true);
        List<Hand> handList = new ArrayList<>();
        handList.add(hand);
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setHands(handList);

        Exception exception = assertThrows(ApiRequestException.class, () -> handService.hit(player,2));

        String expectedMessage = String.format(HandServiceImpl.HAND_NOT_FOUND,2);
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));

    }

//    @Test
//    void doubleDown() {
//    }
//
//    @Test
//    void split() {
//    }

    @Test
    @DisplayName("Surrender should mark the hand as finished and adjust player's balance")
    void surrender_ShouldSetHandIsFinishedToTrueAndReturnHalfTheBetToPlayersBalance() {
        int handId = 1;
        Hand hand = new Hand(TEN,FIVE);
        Hand dealersHand = new Hand(TEN,FIVE);
        hand.setHandId(handId);
        List<Hand> handList = new ArrayList<>();
        handList.add(hand);
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setBalance(100);
        player.setBet(50);
        player.setHands(handList);

        when(dealer.getDealersHand()).thenReturn(dealersHand);
        handService.surrender(player,handId);

        assertTrue(player.getHands().get(0).isFinished());
        assertEquals(125,player.getBalance());
    }

    @Test
    @DisplayName("Surrender is not allowed against an Ace")
    void surrender_ShouldThrowCANNOT_SURRENDER_AGAINST_ACE_WhenDealersFirstCardIsAnAce() {
        int handId = 1;
        Hand hand = new Hand(TEN,FIVE);
        Hand dealersHand = new Hand(ACE);
        hand.setHandId(handId);
        List<Hand> handList = new ArrayList<>();
        handList.add(hand);
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setBalance(100);
        player.setBet(50);
        player.setHands(handList);

        when(dealer.getDealersHand()).thenReturn(dealersHand);

        Exception exception = assertThrows(ApiRequestException.class, () -> handService.surrender(player,handId));

        String expectedMessage = HandServiceImpl.CANNOT_SURRENDER_AGAINST_ACE;
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    @DisplayName("Stand should mark the hand as finished")
    void stand_ShouldSetHandIsFinishedToTrue_WhenCalled() {
        int handId = 1;
        Hand hand = new Hand(TEN,FIVE);
        hand.setHandId(handId);
        List<Hand> handList = new ArrayList<>();
        handList.add(hand);
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setHands(handList);

        handService.stand(player,handId);

        assertTrue(player.getHands().get(0).isFinished());
    }
}