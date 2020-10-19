package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Player;

import java.util.List;

/**
 * Base methods for the Player Service.
 */
public interface PlayerService {

    Player createNewPlayer(Player player);

    void deletePlayerByName(String playerName);

    List<Player> getAllPlayers();

    void hit();

    void doubleDown();

    void stand ();

    void split ();


}
