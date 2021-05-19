package Pieces;

import Game.Color;

public class Queen extends Piece {
    public Queen(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        return (xSub == ySub) || (destinationX == this.pieceX) || (destinationY == this.pieceY);
    }

    @Override
    public Type getPieceType() {
        return Type.Queen;
    }

}
