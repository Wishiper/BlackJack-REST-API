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

    public static final String HAND_IS_FINISHED = "Hand with id: %s is already finished";

    public static final String HAND_NOT_SPLITTABLE = "Hand with id: %s is not Splittable";

    public static final String PLAYER_BALANCE_NOT_ENOUGH = "Player balance is not enough to double";

    public static final String CANNOT_SURRENDER_AGAINST_ACE = "You cannot surrender hands when dealer's first card is an Ace";


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
        if(player.getBalance() >= hand.getHandBet()) {
            player.setBalance(player.getBalance() - hand.getHandBet());
            player.setBet(player.getBet() + hand.getHandBet());
            hand.addCard(dealer.draw());
            hand.evaluateHand();
            hand.setFinished(true);
        }else{
            throw new ApiRequestException(PLAYER_BALANCE_NOT_ENOUGH);
        }
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
        if(dealer.getDealersHand().getCardsInHand().get(0).getRank()!=11){
            player.setBalance(player.getBalance() + (player.getBet()/2));
            hand.setFinished(true);
        }else{
            throw new ApiRequestException(CANNOT_SURRENDER_AGAINST_ACE);
        }

    }

    @Override
    public void stand(Player player, int handId) {
        Hand hand = getHandByHandId(player,handId);
        hand.setFinished(true);
    }

    private Hand getHandByHandId(Player player, int handId){
        for (Hand hand : player.getHands()) {
            if (handId == hand.getHandId()) {
                if(hand.isFinished()){
                    throw new ApiRequestException(String.format(HAND_IS_FINISHED, handId));
                }
                return hand;
            }
        }
        throw new ApiRequestException(String.format(HAND_NOT_FOUND,handId));
    }

}
