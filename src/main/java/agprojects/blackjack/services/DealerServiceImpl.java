package agprojects.blackjack.services;

import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.repositories.PlayerRepository;
import agprojects.blackjack.services.base.DealerService;
import agprojects.blackjack.services.base.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for all the dealing done in the game by the dealer.
 */
@Service
public class DealerServiceImpl implements DealerService {


    @Autowired
    Dealer dealer;

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepository;


    @Override
    public Map<String, Hand> deal() {
        List<Player> activePlayers = playerService.getAllPlayers();
        Map<String,Hand> playerHands = new HashMap<>();
        dealPlayers(activePlayers, playerHands);


        return playerHands;
    }

    /**
     * Deals a card to the dealer if his hand is empty in dealing phase.
     * @param playerHands List of all the player's hands in the current game round.
     */
    private void dealDealer(Map<String, Hand> playerHands) {
        if(dealer.getDealersHand().getCardsInHand().isEmpty()) {
            Card dealtCard = dealer.draw();
            dealer.getDealersHand().addCard(dealtCard);
            playerHands.put("Dealer", dealer.getDealersHand());
        }
    }

    /**
     * Deals 2 cards to each player and one to the dealer in order.
     * @param activePlayers List of all active players in the current round.
     * @param playerHands List of all the player's hands in the current game round.
     */
    private void dealPlayers(List<Player> activePlayers, Map<String, Hand> playerHands) {
        for (int i = 0; i < 2; i++) {
            for (Player player : activePlayers) {
                Card dealtCard = dealer.draw();
                if (player.getHands().isEmpty()) {
                    Hand playerHand = new Hand();
                    playerHand.addCard(dealtCard);
                    player.getHands().add(playerHand);
                } else {
                    player.getHands().get(0).addCard(dealtCard);
                }
                playerHands.put(player.getName(), player.getHands().get(0));
                playerRepository.save(player);
            }
            dealDealer(playerHands);
        }
    }


    /**
     * Gets the number of cards left in the deck of cards.
     * @return number of cards left in the deck.
     */
    public int getNumberOfCards() {
        return dealer.getDeck().getNumberOfCards();
    }
}
