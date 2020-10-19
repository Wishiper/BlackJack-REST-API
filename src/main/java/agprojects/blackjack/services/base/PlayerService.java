package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Player;

import java.util.List;

public interface PlayerService {

    Player createNewPlayer(Player player);

    void deletePlayerByName(String playerName);

    List<Player> getAllPlayers();

    void hit();

    void doubleDown();

    void stand ();

    void split ();


}
