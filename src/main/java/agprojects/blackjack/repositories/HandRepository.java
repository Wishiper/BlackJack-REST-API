package agprojects.blackjack.repositories;

import agprojects.blackjack.models.Hand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for all Hands.
 */
@Repository
public interface HandRepository extends JpaRepository<Hand, Integer> {
}
