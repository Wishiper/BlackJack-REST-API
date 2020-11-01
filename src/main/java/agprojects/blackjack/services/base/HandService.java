package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Player;

/**
 * Contains base methods for the hand service.
 */
public interface HandService {

    void hit(Player player, int handId);
    void doubleDown(Player player, int handId);
    void split(Player player, int handId);
    void surrender(Player player, int handId);
    void stand(Player player, int handId);

}
