package agprojects.blackjack.services;

import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DealerService {


    @Autowired
    Dealer dealer;

    public Card draw() {
        return dealer.draw();
    }

    public int getNumberOfCards() {
        return dealer.getDeck().getNumberOfCards();
    }
}
