package Game;

public class Player {
    public Color color;
    public boolean goesFirst;
    public Game game;
    public boolean hasLost = false;
    public boolean isTurn = false;
    public int score = 0;

    public Player(Color color, boolean goesFirst) {
        this.color = color;
        this.goesFirst = goesFirst;
    }
}
