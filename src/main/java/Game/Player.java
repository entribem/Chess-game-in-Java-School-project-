package Game;

public class Player {
    /**
     * Color of the players pieces
     */
    public Color color;

    /**
     * If the player goes first
     */
    public boolean goesFirst;
    public Game game;

    /**
     * If the player has lost
     */
    public boolean hasLost = false;

    /**
     * If it is players turn
     */
    public boolean isTurn = false;

    /**
     * If the second player is a computer
     */
    public boolean isComputer = false;

    public Player(Color color, boolean goesFirst) {
        this.color = color;
        this.goesFirst = goesFirst;
    }
}
