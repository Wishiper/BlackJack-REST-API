package agprojects.blackjack.services;

import agprojects.blackjack.models.Player;
import agprojects.blackjack.repositories.PlayerRepository;
import agprojects.blackjack.services.base.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {


    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player createNewPlayer(String playerName) {
        Player newPlayer = new Player();
        newPlayer.setName(playerName);
        playerRepository.save(newPlayer);

        return newPlayer;
    }

    public Optional<Player> getPlayerById(int playerId){
        return playerRepository.findById(playerId);
    }
}
