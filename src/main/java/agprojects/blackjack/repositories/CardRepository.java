package agprojects.blackjack.repositories;

import agprojects.blackjack.models.card.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card,Integer> {
}
