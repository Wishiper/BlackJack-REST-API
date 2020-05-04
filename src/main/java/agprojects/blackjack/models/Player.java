package agprojects.blackjack.models;

public class Player {
    /**
     * Id of the user.
     */
    private int usedId;
    /**
     * Name of the player.
     */
    private String name;

    /**
     * The current hand of the player.
     */
    private Hand hand;

    public int getUsedId() {
        return usedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }


}
