package agprojects.blackjack.models;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    /**
     * Id of the user.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int playerId;
    /**
     * Name of the player.
     */
    @Column(name = "name")
    private String name;

    /**
     * The current hand of the player.
     */
    @OneToMany(cascade=CascadeType.ALL)
    @Column(name = "hands")
    private List<Hand> hands;

    @Column(name = "balance")
    private double balance;

    @Column(name = "bet")
    private double bet;

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }
}
