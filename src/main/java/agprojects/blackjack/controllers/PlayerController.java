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
    public ResponseEntity<List<Player>> getAllPlayers(){
        return new ResponseEntity<>(playerService.getAllPlayers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createNewPlayer(@Valid @RequestBody Player player){
        return new ResponseEntity<>(playerService.createNewPlayer(player), HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Optional<Player>> getPlayerById(@PathVariable int playerId){
        return new ResponseEntity<>(playerService.getPlayerById(playerId),HttpStatus.OK);
    }


    @PutMapping("/bet/{playerId}/{playerBet}")
    public ResponseEntity<Player> placeBetByPlayerId(@PathVariable int playerId, @PathVariable double playerBet) {
        return new ResponseEntity<>(playerService.placeBet(playerId,playerBet), HttpStatus.OK);
    }

    @PutMapping("/{playerId}/balance/{playerBalance}")
    public ResponseEntity<Player> addBalanceToPlayer(@PathVariable int playerId, @PathVariable double playerBalance) {
        return new ResponseEntity<>(playerService.addBalanceToPlayer(playerId,playerBalance), HttpStatus.OK);
    }
}
