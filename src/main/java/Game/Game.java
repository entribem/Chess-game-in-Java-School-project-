package Game;

import java.util.Optional;

public final class Game {
    /**
     * First player
     */
    public Player player1;

    /**
     * Second player
     */
    public Player player2;

    private static final Game INSTANCE = new Game();

    private Board board;

    /**
     * Turn
     */
    public int turn;

    private Game() {
    }

    public static Game getInstance() {
        return Game.INSTANCE;
    }

    /**
     * Creates board, loads players and pieces
     */
    public void loadGame() {
        board = Board.getInstance();
        loadPlayers();
        board.loadStandardPieces();
        turn = 0;
        player1.game = INSTANCE;
        player2.game = INSTANCE;
    }

    /**
     * Creates two players and randomly assigns colors
     */
    private void loadPlayers() {
        player1 = new Player(Color.WHITE);
        player2 = new Player(Color.BLACK);
    }
}
