package agprojects.blackjack.controllers;

import agprojects.blackjack.models.dto.PlayerDTO;
import agprojects.blackjack.services.PlayerServiceImpl;
import agprojects.blackjack.utilities.CustomModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlayerServiceImpl playerService;

    @MockBean
    CustomModelMapper modelMapper;


    @Test
    void getAllPlayers() throws Exception {
        this.mockMvc.perform(get("/api/players"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(playerService,times(1)).getAllPlayers();
    }

    @Test
    void createNewPlayer() throws Exception {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(playerDTO);

        mockMvc.perform(post("/api/players").contentType(MediaType.APPLICATION_JSON).content(json).characterEncoding("utf-8"))
                .andExpect(status().isOk());

        verify(modelMapper,times(1)).convertFromPlayer(playerService.createNewPlayer(playerDTO));
    }

    @Test
    void getPlayerById() {
    }

    @Test
    void placeBetByPlayerId() {
    }

    @Test
    void addBalanceToPlayer() {
    }

    @Test
    void sitPlayer() {
    }

    @Test
    void executePlayerAction() {
    }
}