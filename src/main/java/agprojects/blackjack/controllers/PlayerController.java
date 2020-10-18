package agprojects.blackjack.controllers;

import agprojects.blackjack.models.Player;
import agprojects.blackjack.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerService;

    @PostMapping("new")
    public Player createNewPlayer(@Valid @RequestBody String playerName){
        return playerService.createNewPlayer(playerName);
    }

    @GetMapping("/{playerId}")
    public Optional<Player> getPlayerById(@PathVariable int playerId){
        return playerService.getPlayerById(playerId);
    }

}
