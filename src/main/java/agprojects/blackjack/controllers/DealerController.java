package agprojects.blackjack.controllers;

import agprojects.blackjack.models.Hand;
import agprojects.blackjack.services.DealerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("/api/dealer")
public class DealerController {

    @Autowired
    DealerServiceImpl dealerService;


    @GetMapping("deal")
    public Map<String, Hand> deal(){
        return dealerService.deal();
    }

    @GetMapping("decks")
    public int getNumberOfCards(){
       return dealerService.getNumberOfCards();
    }
}

