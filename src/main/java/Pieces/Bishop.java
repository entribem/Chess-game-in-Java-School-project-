package Pieces;

import Game.Color;
import Game.Player;

public class Bishop extends Piece {
    public Bishop(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        return xSub == ySub;
    }

    @Override
    public Type getPieceType() {
        return Type.Bishop;
    }

    @Override
    public Color getPieceColor() {
        return this.pieceColor;
    }
}
