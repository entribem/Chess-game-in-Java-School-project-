package Game;

import Pieces.Piece;

public abstract class Square {
    int squareNum;

    Square(int squareNum) {
        this.squareNum = squareNum;
    }

    public abstract Piece getPiece();

    public abstract boolean isEmpty();


}
