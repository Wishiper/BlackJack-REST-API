package agprojects.blackjack.controllers;

import agprojects.blackjack.models.Player;
import agprojects.blackjack.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Player> createNewPlayer(@Valid @RequestBody Player player){
        return new ResponseEntity<>(playerService.createNewPlayer(player), HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public Optional<Player> getPlayerById(@PathVariable int playerId){
        return playerService.getPlayerById(playerId);
    }


    @PutMapping("/bet/{playerId}/{playerBet}")
    public Player bet(@PathVariable int playerId, @PathVariable double playerBet) {
        return playerService.placeBet(playerId,playerBet);
    }

    @PutMapping("/{playerId}/balance/{playerBalance}")
    public Player addBalanceToPlayer(@PathVariable int playerId, @PathVariable double playerBalance) {
        return playerService.addBalanceToPlayer(playerId,playerBalance);
    }
}
