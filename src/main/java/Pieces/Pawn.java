package Pieces;

import Game.Color;


public class Pawn extends Piece {
    public Pawn(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    /*public Pawn(int pieceX, int pieceY, final Color pieceColor, boolean moved2Forward) {
        super(pieceX, pieceY, pieceColor);
        this.moved2Forward = moved2Forward;
    }*/

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        if ((xSub == 1) && (ySub == 0)) {
            return true;
        }

        if (pawnCanCapture(this, destinationX, destinationY)) {
            return true;
        }
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

    public static boolean pawnCanCapture(Piece piece, int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - piece.pieceX);
        int ySub = Math.abs(destinationY - piece.pieceY);
        return (xSub == 1) && (ySub == 1);
    }

    /*public static boolean pawnCanEnPassant(Piece piece, int destinationX, int destinationY) {
        if (pawnCanCapture(piece, destinationX, destinationY)) {

        }
    }*/

}
