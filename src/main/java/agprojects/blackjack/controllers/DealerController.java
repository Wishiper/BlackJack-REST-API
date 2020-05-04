package agprojects.blackjack.controllers;

import agprojects.blackjack.models.card.Card;
import agprojects.blackjack.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dealer")
public class DealerController {

    @Autowired
    DealerService dealerService;


    @GetMapping("draw")
    public Card hit(){
        return dealerService.draw();
    }

    @GetMapping("decks")
    public int getNumberOfCards(){
       return dealerService.getNumberOfCards();
    }
}

