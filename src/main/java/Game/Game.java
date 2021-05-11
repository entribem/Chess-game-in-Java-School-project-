package Game;
import java.util.Random;

public class Game {
    public Player player1, player2;
    public Board board;
    final static int standardHeight = 8, standardWidth = 8;

    public Game() {
        loadGame();
    }

    public void loadGame() {
        board = new Board(this, standardHeight, standardWidth);
        loadPlayers();
        board.loadPieces();
    }

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
