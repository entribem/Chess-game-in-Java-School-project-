package Game;

import Pieces.Piece;

import java.util.Vector;

public class Player {
    protected Game game;

    /**
     * Color of the players pieces
     */
    public Color color;

    /**
     * If the player has lost
     */
    public boolean hasLost = false;

    /**
     * If it is players turn
     */
    public boolean isTurn = false;

    private final Board board = Board.getInstance();

    public Player(Color color) {
        this.color = color;
    }

    /**
     * Gets all enemy pieces
     *
     * @param color Player Color
     * @return      Vector containing enemy pieces
     */
    public Vector<Piece> getEnemyPieces(Color color)
    {
        Vector<Piece> enemyPieces;

        if(color == Color.WHITE) {
            enemyPieces = board.blackPieces;
        }
        else {
            enemyPieces = board.whitePieces;
        }
        return enemyPieces;
    }
}
