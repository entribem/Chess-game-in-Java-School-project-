package Game;

import Pieces.Piece;

import java.util.Vector;

public class Player {
    public Game game;
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

    /**
     * If the second player is a computer
     */
    public boolean isComputer = false;

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
            enemyPieces = game.board.blackPieces;
        }
        else {
            enemyPieces = game.board.whitePieces;
        }
        return enemyPieces;
    }
}
