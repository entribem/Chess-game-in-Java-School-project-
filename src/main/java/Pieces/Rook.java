package Pieces;

import Game.Color;

public class Rook extends Piece {
    public Rook(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        return (destinationX == this.pieceX) || (destinationY == this.pieceY);
    }

    @Override
    public Type getPieceType() {
        return Type.Rook;
    }

}
