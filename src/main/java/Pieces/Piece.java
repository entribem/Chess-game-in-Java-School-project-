package Pieces;

import Game.Color;

public abstract class Piece {
    /**
     * X coordinate of the piece
     */
    public int pieceX;

    /**
     * Y coordinate of the piece
     */
    public int pieceY;

    /**
     * Color of the piece
     */
    public final Color pieceColor;

    /**
     Utility variable for en passant move
     * which indicates
     * if the pawn has advanced two squares
     */
    public boolean moved2Forward;

    /**
     * Utility variable for castling move
     * which indicates
     * if the rook or king has made any move
     */
    public boolean hasMoved;

    public Piece(int pieceX, int pieceY, final Color pieceColor) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColor = pieceColor;
    }

    /**
     * Checks if the path is valid
     *
     * @param destinationX  X coordinate of the square, where player wants to move
     * @param destinationY  Y coordinate of the square, where player wants to move
     * @return              true if the path is valid, false if is not
     */
    public abstract boolean isValidPath(int destinationX, int destinationY);

    /**
     * Returns the type of the piece
     *
     * @return  Type of the piece
     */
    public abstract Type getPieceType();
}
