package agprojects.blackjack.services.base;

import agprojects.blackjack.models.Hand;
import java.util.Map;

public interface DealerService {

    Map<String, Hand> deal();
}
