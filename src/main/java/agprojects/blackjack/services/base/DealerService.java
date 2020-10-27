package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Hand;
import java.util.Map;

/**
 * Base methods for the Dealer Service.
 */
public interface DealerService {

    Map<String, Hand> deal();

    Hand hitDealer();
}
