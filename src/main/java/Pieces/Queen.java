package Pieces;

import Game.Player;

public class Queen extends Piece {
    public Queen(int pieceX, int pieceY, final Player player) {
        super(pieceX, pieceY, player);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        return (xSub == ySub) || (destinationX == this.pieceX) || (destinationY == this.pieceY);
    }

    @Override
    public Type getPieceType() {
        return Type.QUEEN;
    }

}
