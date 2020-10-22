package agprojects.blackjack.models;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Player name cannot be empty")
    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @Column(name = "bet")
    private double bet;

    /**
     * List that holds the current hands of the player.
     */
    @OneToMany(cascade=CascadeType.ALL)
    @Column(name = "hands")
    private List<Hand> hands;

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }
}
