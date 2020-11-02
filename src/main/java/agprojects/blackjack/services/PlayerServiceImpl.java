package agprojects.blackjack.services;

import agprojects.blackjack.exceptions.ApiRequestException;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.Table;
import agprojects.blackjack.services.base.HandService;
import agprojects.blackjack.utilities.CustomModelMapper;
import agprojects.blackjack.models.dto.PlayerDTO;
import agprojects.blackjack.repositories.PlayerRepository;
import agprojects.blackjack.services.base.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service responsible for all actions regarding a Player in the game of BlackJack.
 */
@Service
public class PlayerServiceImpl implements PlayerService {


    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    HandService handService;

    @Autowired
    CustomModelMapper modelMapper;


    @Autowired
    Table table;

    public static final String PLAYER_NOT_FOUND = "Player with id: %s was not found";

    public static final String ACTION_NOT_ALLOWED = "Action with name: %s is not allowed";

    public static final String NOT_ENOUGH_BALANCE = "Player balance is not enough for player with id: %s";

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
            return isPlayerPresent(playerId);
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
        Player player = isPlayerPresent(playerId);
        if(playerBet>player.getBalance()){
            throw new ApiRequestException(String.format(NOT_ENOUGH_BALANCE,playerId));
        }else{
            player.setBet(playerBet);
            player.setBalance(player.getBalance() - playerBet);
            playerRepository.save(player);
        }
        return player;
    }

    /**
     * Add balance to a player by their playerId
     * @param playerId  Id of the betting player
     * @param playerBalance The amount of balance the player inserted
     * @return Updated player object
     */
    @Override
    public Player addBalanceToPlayer(int playerId, double playerBalance) {
        Player player = isPlayerPresent(playerId);
        player.setBalance(playerBalance);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player seatPlayer(int playerId, int playerSeat) {
        Player player = isPlayerPresent(playerId);
        table.sitPlayer(playerSeat,player);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player hit(int playerId, int handId) {
        Player player = isPlayerPresent(playerId);

        handService.hit(player,handId);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player doubleDown(int playerId, int handId) {
        Player player = isPlayerPresent(playerId);

        handService.doubleDown(player,handId);
        playerRepository.save(player);

        return player;
        }

    @Override
    public Player stand(int playerId, int handId) {
        Player player = isPlayerPresent(playerId);

        handService.stand(player,handId);
        playerRepository.save(player);

        return player;
    }

    @Override
    public Player split(int playerId, int handId) {
        Player player = isPlayerPresent(playerId);
        handService.split(player,handId);
        playerRepository.save(player);

        return player;
    }

    @Override
    public Player surrender(int playerId, int handId) {
        Player player = isPlayerPresent(playerId);
        handService.surrender(player,handId);
        playerRepository.save(player);

        return player;
    }

    /**
     * Checks if the player is present in the database, if not it throws an exception.
     * @param playerId Id of the player.
     * @return The player object found by playerId.
     */
    private Player isPlayerPresent(int playerId) {
        Optional<Player> playerToBet = playerRepository.findById(playerId);
        if(playerToBet.isPresent()){
            return playerToBet.get();
        }else{
            throw new ApiRequestException(String.format(PLAYER_NOT_FOUND,playerId));
        }

    }

    public Player executeAction(String action, int playerId, int handId){
        switch (action){
            case "hit" : return hit(playerId,handId);
            case "double" : return doubleDown(playerId,handId);
            case "split" : return split(playerId,handId);
            case "stand" : return stand(playerId,handId);
            case "surrender" : return surrender(playerId,handId);
            default: throw new ApiRequestException(String.format(ACTION_NOT_ALLOWED,action));
        }
    }
}
