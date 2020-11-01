package agprojects.blackjack.services;

import agprojects.blackjack.exceptions.ApiRequestException;
import agprojects.blackjack.models.Dealer;
import agprojects.blackjack.models.Hand;
import agprojects.blackjack.models.Player;
import agprojects.blackjack.services.base.HandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandServiceImpl implements HandService {

    public static final String HAND_NOT_FOUND = "Hand with id: %s was not found";

    public static final String HAND_NOT_SPLITTABLE = "Hand with id: %s is not Splittable";


    @Autowired
    Dealer dealer;

    @Override
    public void hit(Player player, int handId) {
        Hand hand = getHandByHandId(player,handId);
        hand.addCard(dealer.draw());
        hand.evaluateHand();
    }

    @Override
    public void doubleDown(Player player, int handId) {
        Hand hand = getHandByHandId(player,handId);
        hand.addCard(dealer.draw());
        hand.evaluateHand();
        hand.setFinished(true);
    }

    @Override
    public void split(Player player, int handId) {
        Hand hand = getHandByHandId(player,handId);
        if(hand.isSplittable()){
            player.getHands().add(hand.split());
        }else {
            throw new ApiRequestException(String.format(HAND_NOT_SPLITTABLE,handId));

        }
    }

    @Override
    public void surrender(Player player, int handId) {
        Hand hand = getHandByHandId(player,handId);
        player.setBalance(player.getBet()/2);
        hand.setFinished(true);

    }

    @Override
    public void stand(Player player, int handId) {
        Hand hand = getHandByHandId(player,handId);
        hand.setFinished(true);
    }

    private Hand getHandByHandId(Player player, int handId){
        for (Hand hand : player.getHands()) {
            if (handId == hand.getHandId()) {
                return hand;
            }
        }
        throw new ApiRequestException(String.format(HAND_NOT_FOUND,handId));
    }

}
