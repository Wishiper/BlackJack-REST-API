package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Player;

import java.util.List;

public interface PlayerService {

    Player createNewPlayer(String playerName);

    void deletePlayerByName(String playerName);

    List<Player> getAllActivePlayers();

    void hit();

    void doubleDown();

    void stand ();

    void split ();


}
