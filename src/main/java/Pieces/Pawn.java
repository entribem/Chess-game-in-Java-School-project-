package Pieces;

import Game.Color;
import Game.Player;

public class Pawn extends Piece {
    public Pawn(int pieceX, int pieceY, final Color pieceColor) {
        super(pieceX, pieceY, pieceColor);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = destinationX - this.pieceX;
        int ySub = destinationY - this.pieceY;
        if (this.pieceX == 1 || this.pieceX == 6) {
            return (xSub == 1 || xSub == 2) && (ySub == 0);
        }
        //if (this.pieceX == 1 && pieceColor == Color.white) {
        //    return (xSub == 1 || xSub == 2) && (ySub == 0);
        //}
        return (xSub == 1) && (ySub == 0);
    }

    @Override
    public Type getPieceType() {
        return Type.Pawn;
    }

    public boolean pawnCanCapture(int destinationX, int destinationY) {
        int xSub = destinationX - this.pieceX;
        int ySub = destinationY - this.pieceY;
        return (xSub == ySub);
    }

}
