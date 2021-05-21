package Pieces;

import Game.Player;

public class Knight extends Piece {
    public Knight(int pieceX, int pieceY, final Player player) {
        super(pieceX, pieceY, player);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        return (xSub == 1 && ySub == 2) || (xSub == 2 && ySub == 1);
    }

    @Override
    public Type getPieceType() {
        return Type.KNIGHT;
    }

}
