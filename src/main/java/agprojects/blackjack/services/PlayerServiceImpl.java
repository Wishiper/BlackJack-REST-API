package agprojects.blackjack.services;

import agprojects.blackjack.exception.ApiRequestException;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.repositories.PlayerRepository;
import agprojects.blackjack.services.base.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {


    @Autowired
    PlayerRepository playerRepository;

    /**
     * Creates new player and save it into the database.
     * @param player Player object.
     * @return The new player.
     */
    @Override
    public Player createNewPlayer(Player player) {

        playerRepository.save(player);

        return player;
    }

    @Override
    public void deletePlayerByName(String playerName) {

    }

    /**
     * Gets all players from the database.
     * @return List of Players.
     */
    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Place a bet for a player by their playerId
     * @param playerId Id of the betting player
     * @param playerBet The amount that the player bets
     * @return Updated player object
     */
    @Override
    public Player placeBet(int playerId, double playerBet) {

        Optional<Player> playerToBet = playerRepository.findById(playerId);
        if(playerToBet.isPresent()){
            Player player = playerToBet.get();
            player.setBet(playerBet);
            player.setBalance(player.getBalance() - playerBet);
            playerRepository.save(player);
            return player;

        }else{
            throw new ApiRequestException("Player with id: " + playerId +" was not found");
        }
    }

    /**
     * Add balance to a player by their playerId
     * @param playerId  Id of the betting player
     * @param playerBalance The amount of balance the player inserted
     * @return Updated player object
     */
    @Override
    public Player addBalanceToPlayer(int playerId, double playerBalance) {
        Optional<Player> playerToBet = playerRepository.findById(playerId);
        if(playerToBet.isPresent()){
            Player player = playerToBet.get();
            player.setBalance(playerBalance);
            playerRepository.save(player);
            return player;

        }else{
            throw new ApiRequestException("Player with id: " + playerId +" was not found");
        }
    }

    @Override
    public void hit() {

    }

    @Override
    public void doubleDown() {

    }

    @Override
    public void stand() {

    }

    @Override
    public void split() {

    }

    public Optional<Player> getPlayerById(int playerId){
        return playerRepository.findById(playerId);
    }
}
