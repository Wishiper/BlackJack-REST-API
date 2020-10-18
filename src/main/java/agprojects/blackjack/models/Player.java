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

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hand> getHand() {
        return hands;
    }


}
