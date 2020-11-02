package agprojects.blackjack.services;

import agprojects.blackjack.exceptions.ApiRequestException;
import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.models.card.CardType;
import org.junit.Before;
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

    @Mock
    Dealer dealer;

    @InjectMocks
    private static final HandServiceImpl handService = new HandServiceImpl();

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
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

    @Test
    void doubleDown() {
    }

    @Test
    void split() {
    }

    @Test
    void surrender() {
    }

    @Test
    void stand() {
    }
}