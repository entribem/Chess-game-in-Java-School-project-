package Game;

public class Game {
    /**
     * First player
     */
    public Player player1;

    /**
     * Second player
     */
    public Player player2;
    public Board board;

    /**
     * Turn
     */
    public int turn;

    /**
     * Standard height of the board, which is 8
     */
    final static int standardHeight = 8;

    /**
     * Standard width of the board, which is 8
     */
    final static int standardWidth = 8;

    public Game() {
        loadGame();
    }

    /**
     * Creates board, loads players and pieces
     */
    public void loadGame() {
        board = new Board(this, standardHeight, standardWidth);
        loadPlayers();
        board.loadStandardPieces();
        turn = 0;
        player1.game = this;
        player2.game = this;
    }

    /**
     * Creates two players and randomly assigns colors
     */
    public void loadPlayers() {
        player1 = new Player(Color.WHITE);
        player2 = new Player(Color.BLACK);
    }
}
