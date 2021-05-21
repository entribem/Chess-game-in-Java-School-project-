package Pieces;

import Game.Player;

public class Rook extends Piece {
    public Rook(int pieceX, int pieceY, final Player player) {
        super(pieceX, pieceY, player);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        return (destinationX == this.pieceX) || (destinationY == this.pieceY);
    }

    @Override
    public Type getPieceType() {
        return Type.ROOK;
    }

}
