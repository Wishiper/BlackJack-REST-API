package agprojects.blackjack.services;

import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DealerService {


    @Autowired
    Dealer dealer;

    @Autowired
    CardRepository cardRepository;

    public Card draw() {
        Card card = dealer.draw();
        cardRepository.save(card);
        return card;
    }

    public int getNumberOfCards() {
        return dealer.getDeck().getNumberOfCards();
    }
}
