package agprojects.blackjack.models.dto;

import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.utilities.CustomModelMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomModelMapperTest {

    CustomModelMapper modelMapper = new CustomModelMapper();

    Dealer dealer = new Dealer();



    @Test
    void convertFromPlayerDtoToPlayer_ShouldBeCorrect() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(1);
        playerDTO.setName("name");
        playerDTO.setBalance(100);
        playerDTO.setBet(50);
        playerDTO.setSeatNumber(1);
        Hand hand = new Hand();
        hand.addCard(dealer.draw());
        hand.addCard(dealer.draw());
        List<Hand> hands = new ArrayList<>();
        hands.add(hand);
        playerDTO.setHands(hands);


        Player player = modelMapper.convertFromPlayerDTO(playerDTO);
        assertEquals(playerDTO.getPlayerId(),player.getPlayerId());
        assertEquals(playerDTO.getName(),player.getName());
        assertEquals(playerDTO.getBalance(),player.getBalance());
        assertEquals(playerDTO.getBet(),player.getBet());
        assertEquals(playerDTO.getSeatNumber(),player.getSeatNumber());
        assertEquals(playerDTO.getHands().get(0).getCardsInHand().get(0).getName(), player.getHands().get(0).getCardsInHand().get(0).getName());
        assertEquals(playerDTO.getHands().get(0).getCardsInHand().get(1).getName(), player.getHands().get(0).getCardsInHand().get(1).getName());

    }

    @Test
    void convertFromPlayerToPlayerDTO_ShouldBeCorrect() {
        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");
        player.setBalance(100);
        player.setBet(50);
        player.setSeatNumber(1);
        Hand hand = new Hand();
        hand.addCard(dealer.draw());
        hand.addCard(dealer.draw());
        List<Hand> hands = new ArrayList<>();
        hands.add(hand);
        player.setHands(hands);

        PlayerDTO playerDTO = modelMapper.convertFromPlayer(player);
        assertEquals(player.getPlayerId(),playerDTO.getPlayerId());
        assertEquals(player.getName(),playerDTO.getName());
        assertEquals(player.getBalance(),playerDTO.getBalance());
        assertEquals(player.getBet(),playerDTO.getBet());
        assertEquals(player.getSeatNumber(),playerDTO.getSeatNumber());
        assertEquals(player.getHands().get(0).getCardsInHand().get(0).getName(), playerDTO.getHands().get(0).getCardsInHand().get(0).getName());
        assertEquals(player.getHands().get(0).getCardsInHand().get(1).getName(), playerDTO.getHands().get(0).getCardsInHand().get(1).getName());
    }
}