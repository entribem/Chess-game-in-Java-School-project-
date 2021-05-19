package Pieces;

import Game.Color;
import Game.Player;

public class King extends Piece {

    public King(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

   /* public King(int pieceX, int pieceY, final Color pieceColor, boolean hasMoved) {
        super(pieceX, pieceY, pieceColor);
        this.hasMoved = hasMoved;
    } */

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        if (xSub == 0 && ySub == 2) {
            return true;
        }
        return (xSub < 2) && (ySub < 2);
    }

    @Override
    public Type getPieceType() {
        return Type.King;
    }
}
