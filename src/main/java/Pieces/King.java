package Pieces;

import Game.Color;
import Game.Player;

public class King extends Piece {

    public King(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        return (xSub < 2) && (ySub < 2);
    }



    @Override
    public Type getPieceType() {
        return Type.King;
    }

}
