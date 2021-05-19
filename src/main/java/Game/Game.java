package Game;
import java.util.Random;

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
    }

    /**
     * Creates two players and randomly assigns colors
     */
    public void loadPlayers() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        if (randomNum == 0) {
            player1 = new Player(Color.white, true);
            player2 = new Player(Color.black, false);
            player1.goesFirst = true;
        }
        else {
            player1 = new Player(Color.black, false);
            player2 = new Player(Color.white, true);
            player2.goesFirst = true;
        }
    }
}
