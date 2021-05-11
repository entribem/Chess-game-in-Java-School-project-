package Pieces;

import Game.Color;
import Game.Player;

public abstract class Piece {
    public int pieceX, pieceY;
    public final Color pieceColor;

    public Piece(int pieceX, int pieceY, final Color pieceColor) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColor = pieceColor;
    }

    public abstract boolean isValidPath(int destinationX, int destinationY);

    public int getPiecePositionX() {
        return this.pieceX;
    }

    public int getPiecePositionY() {
        return this.pieceY;
    }

    public abstract Type getPieceType();

    public Color getPieceColor() {
        return this.pieceColor;
    }


}
