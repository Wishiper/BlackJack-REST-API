package agprojects.blackjack.controllers;

import agprojects.blackjack.models.Player;
import agprojects.blackjack.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerService;

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PostMapping
    public Player createNewPlayer(@Valid @RequestBody Player player){
        return playerService.createNewPlayer(player);
    }

    @GetMapping("/{playerId}")
    public Optional<Player> getPlayerById(@PathVariable int playerId){
        return playerService.getPlayerById(playerId);
    }

}
