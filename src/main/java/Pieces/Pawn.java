package Pieces;

import Game.Color;


public class Pawn extends Piece {
    public Pawn(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        if ((xSub == 1) && (ySub == 0)) {
            return true;
        }

        //if the players wants to capture with pawn
        if (pawnCanCapture(this, destinationX, destinationY)) {
            return true;
        }
        //advance pawn by two squares
        if (this.pieceX == 1 || this.pieceX == 6) {
            this.moved2Forward = true;
            return (xSub == 1 || xSub == 2) && (ySub == 0);
        }
        return false;
    }

    @Override
    public Type getPieceType() {
        return Type.Pawn;
    }

    /**
     * Checks if the pawn can capture
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if the pawn can capture, false if not
     */
    public static boolean pawnCanCapture(Piece piece, int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - piece.pieceX);
        int ySub = Math.abs(destinationY - piece.pieceY);
        return (xSub == 1) && (ySub == 1);
    }
}
