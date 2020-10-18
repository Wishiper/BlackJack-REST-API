package agprojects.blackjack.services;

import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.repositories.HandRepository;
import agprojects.blackjack.repositories.PlayerRepository;
import agprojects.blackjack.services.base.DealerService;
import agprojects.blackjack.services.base.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DealerServiceImpl implements DealerService {


    @Autowired
    Dealer dealer;

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    HandRepository handRepository;

    @Override
    public Map<String, Hand> deal() {
        List<Player> activePlayers = playerService.getAllActivePlayers();
        Map<String,Hand> playerHands = new HashMap<>();
        dealPlayers(activePlayers, playerHands);


        return playerHands;
    }

    private void dealDealer(Map<String, Hand> playerHands) {
        if(dealer.getDealersHand().getCardsInHand().isEmpty()) {
            Card dealtCard = dealer.draw();
            dealer.getDealersHand().addCard(dealtCard);
            playerHands.put("Dealer", dealer.getDealersHand());
        }
    }

    private void dealPlayers(List<Player> activePlayers, Map<String, Hand> playerHands) {
        for (int i = 0; i < 2; i++) {
            for (Player player : activePlayers) {
                Card dealtCard = dealer.draw();
                if (player.getHand().isEmpty()) {
                    Hand playerHand = new Hand();
                    playerHand.addCard(dealtCard);
                    player.getHand().add(playerHand);
                } else {
                    player.getHand().get(0).addCard(dealtCard);
                }
                playerHands.put(player.getName(), player.getHand().get(0));
                playerRepository.save(player);
            }
            dealDealer(playerHands);
        }
    }


    public int getNumberOfCards() {
        return dealer.getDeck().getNumberOfCards();
    }
}
