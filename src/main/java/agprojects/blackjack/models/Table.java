package agprojects.blackjack.models;

import java.util.ArrayList;
import java.util.List;

public final class Table {

    private final ArrayList<Seat> playerSeats;

    private static final int MAX_BLACKJACK_SEATS = 5;

    public Table() {
        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 0; i < MAX_BLACKJACK_SEATS; i++) {
            Seat seat = new Seat(i+1,false);
            seats.add(seat);
        }
        this.playerSeats = seats;
    }

    public String sitPlayer(int seatNumber, Player player){
        if(this.playerSeats.get(seatNumber).isTaken()){
            return "This seat is already taken";
        }else {
            this.playerSeats.get(seatNumber).setPlayer(player);
            return "Player" + player.getName() + "has been seated successfully";
        }
    }

    public List<Seat> getPlayerSeats() {
        return playerSeats;
    }
}
