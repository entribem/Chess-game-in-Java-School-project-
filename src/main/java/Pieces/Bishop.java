package Pieces;

import Game.Player;

public class Bishop extends Piece {
    public Bishop(int pieceX, int pieceY, final Player player) {
        super(pieceX, pieceY, player);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        return xSub == ySub;
    }

    @Override
    public Type getPieceType() {
        return Type.BISHOP;
    }
}
