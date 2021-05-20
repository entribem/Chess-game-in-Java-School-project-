package Pieces;

import Game.Color;

public class King extends Piece {
    public King(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }


    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        if (xSub == 0 && ySub == 2) { //if the player wants to castle
            return true;
        }
        return (xSub < 2) && (ySub < 2); //king can move only one square in any direction
    }

    @Override
    public Type getPieceType() {
        return Type.King;
    }
}
