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

    /*public boolean kingInCheck() {

        for (directions) { // up, down, left and right
            for (square in direction) { // square by square from the king and out in the current direction
                if (square contains opponent rook or queen)
                return true;
            else if (square contains friendly piece)
                continue;


                for (directions) { // left-up, left-down, right-up and right-down
                    for (square in direction) { // square by square from the king and out in the current direction
                        if (square contains opponent bishop or queen)
                        return true;
            else if (square contains friendly piece)
                        continue;


                        if (squares where pawns would threaten the king contains pawns)
                        return true;


                        if (squares where a king would threaten the king contains king)
                        return true;


                        if (squares where knights would threaten the king contains knights)
                        return true;
    }*/
}
