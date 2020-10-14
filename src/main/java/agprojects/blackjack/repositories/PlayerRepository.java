package agprojects.blackjack.repositories;

import agprojects.blackjack.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for all Players.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
}
