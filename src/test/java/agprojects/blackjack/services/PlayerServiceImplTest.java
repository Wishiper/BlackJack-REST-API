package agprojects.blackjack.services;

import agprojects.blackjack.exceptions.ApiRequestException;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.models.Table;
import agprojects.blackjack.models.dto.PlayerDTO;
import agprojects.blackjack.repositories.PlayerRepository;
import agprojects.blackjack.services.base.HandService;
import agprojects.blackjack.utilities.CustomModelMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    CustomModelMapper modelMapper;

    @Mock
    HandService handService;

    @InjectMocks
    Table table;

    @InjectMocks
    private static PlayerServiceImpl playerService = new PlayerServiceImpl();

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createNewPlayer_ShouldCallPlayerRepositorySaveOnce_WithTheCreatedPlayerFromModelMapper() {

        PlayerDTO playerDTO = new PlayerDTO();

        Player player = new Player();
        player.setPlayerId(1);
        player.setName("name");

        when(modelMapper.convertFromPlayerDTO(playerDTO)).thenReturn(player);

        playerService.createNewPlayer(playerDTO);

        verify(playerRepository,times(1)).save(player);
    }

    @Test
    void getPlayerById_ShouldRetrieveTheRightPlayerFromPlayerRepository() {
        int playerId = 1;

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setName("name");

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        Player playerResult = playerService.getPlayerById(playerId);

        Assert.assertEquals(player,playerResult);
    }

    @Test
    void getPlayerById_ShouldThrowApiRequestException_WhenPlayerIsNotFound() throws ApiRequestException {
        int playerId = 1;

        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ApiRequestException.class, () -> {
            playerService.getPlayerById(playerId);
        });

        String expectedMessage = "Player with id: 1 was not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllPlayers() {
    }

    @Test
    void placeBet() {
    }

    @Test
    void addBalanceToPlayer() {
    }

    @Test
    void seatPlayer() {
    }

    @Test
    void hit() {
    }

    @Test
    void doubleDown() {
    }

    @Test
    void stand() {
    }

    @Test
    void split() {
    }

    @Test
    void surrender() {
    }

    @Test
    void executeAction() {
    }
}