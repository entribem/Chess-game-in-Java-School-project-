package Pieces;

import Game.Color;

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
}
