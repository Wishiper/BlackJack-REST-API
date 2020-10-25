package agprojects.blackjack.services;

import agprojects.blackjack.exceptions.ApiRequestException;
import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.Table;
import agprojects.blackjack.utilities.CustomModelMapper;
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

    @Autowired
    Dealer dealer;

    @Autowired
    Table table;

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
            return isPlayerPresent(playerId);
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
        Player player = isPlayerPresent(playerId);
        player.setBet(playerBet);
        player.setBalance(player.getBalance() - playerBet);
        playerRepository.save(player);
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
        for (Hand hand :player.getHands()) {
                if(handId == hand.getHandId()){
                    hand.addCard(dealer.draw());
                }
            }
        playerRepository.save(player);
        return player;
    }

    //TODO validate player has enough balance to double
    @Override
    public Player doubleDown(int playerId, int handId) {
        Player player = isPlayerPresent(playerId);
        player.setBalance(player.getBalance() - player.getBet());
        player.setBet(player.getBet() * 2);
        for (Hand hand : player.getHands()) {
                if (handId == hand.getHandId()) {
                    hand.addCard(dealer.draw());
                }
            }
        playerRepository.save(player);
        return player;

        }

    @Override
    public void stand() {

    }

    @Override
    public void split() {

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
}
