package agprojects.blackjack.services;

import agprojects.blackjack.exception.ApiRequestException;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.dto.CustomModelMapper;
import agprojects.blackjack.models.dto.PlayerDTO;
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

    @Autowired
    CustomModelMapper modelMapper;

    public static final String PLAYER_NOT_FOUND = "Player with id: %s was not found";
    /**
     * Creates new player and save it into the database.
     * @param playerDTO PlayerDTO object.
     * @return The new player.
     */
    @Override
    public Player createNewPlayer(PlayerDTO playerDTO) {
        Player player = modelMapper.convertFromPlayerDTO(playerDTO);
        playerRepository.save(player);

        return player;
    }

    /**
     * Get player by player Id.
     * @param playerId Id of the player.
     * @return Player object.
     */
    public Player getPlayerById(int playerId){
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if(playerOptional.isPresent()){
            return playerOptional.get();
        }else{

            throw new ApiRequestException(String.format(PLAYER_NOT_FOUND,playerId));
        }
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
            throw new ApiRequestException(String.format(PLAYER_NOT_FOUND,playerId));
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
            throw new ApiRequestException(String.format(PLAYER_NOT_FOUND,playerId));
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
}
