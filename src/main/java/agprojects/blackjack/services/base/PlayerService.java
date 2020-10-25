package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.dto.PlayerDTO;

import java.util.List;

/**
 * Base methods for the Player Service.
 */
public interface PlayerService {

    Player createNewPlayer(PlayerDTO player);

    void deletePlayerByName(String playerName);

    List<Player> getAllPlayers();

    Player placeBet(int playerId, double playerBet);

    Player addBalanceToPlayer(int playerId, double playerBalance);

    Player seatPlayer(int playerId, int playerSeat);

    Player hit(int playerId, int handId);

    Player doubleDown(int playerId, int handId);

    void stand ();

    void split ();

}
