package Game;

import Pieces.*;

public class Board {
    public Game game;
    public int height, width;
    public Piece[][] boardArr;

    public Board(Game game, int height, int width) {
        this.game = game;
        this.height = height;
        this.width = width;
        boardArr = new Piece[height][width];
    }

    public void loadPieces() {
        loadPiecesPlayer1();
        loadPiecesPlayer2();
    }

    public void loadPiecesPlayer1() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(1, i, game.player1.color);
            boardArr[1][i] = pawn;
        }

        Piece knight = new Knight(0, 1, game.player1.color);
        Piece knight1 = new Knight(0, 6, game.player1.color);
        boardArr[0][1] = knight;
        boardArr[0][6] = knight1;

        Piece rook = new Rook(0, 0, game.player1.color);
        Piece rook1 = new Rook(0, 7, game.player1.color);
        boardArr[0][0] = rook;
        boardArr[0][7] = rook1;

        Piece bishop = new Bishop(0, 2, game.player1.color);
        Piece bishop1 = new Bishop(0, 5, game.player1.color);
        boardArr[0][2] = bishop;
        boardArr[0][5] = bishop1;

        Piece queen = new Queen(0, 3, game.player1.color);
        boardArr[0][3] = queen;

        Piece king = new King(0, 4, game.player1.color);
        boardArr[0][4] = king;
    }

    public void loadPiecesPlayer2() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(6, i, game.player2.color);
            boardArr[6][i] = pawn;
        }

        Piece knight = new Knight(7, 1, game.player2.color);
        Piece knight1 = new Knight(7, 6, game.player2.color);
        boardArr[7][1] = knight;
        boardArr[7][6] = knight1;

        Piece rook = new Rook(7, 0, game.player2.color);
        Piece rook1 = new Rook(7, 7, game.player2.color);
        boardArr[7][0] = rook;
        boardArr[7][7] = rook1;

        Piece bishop = new Bishop(7, 2, game.player2.color);
        Piece bishop1 = new Bishop(7, 5, game.player2.color);
        boardArr[7][2] = bishop;
        boardArr[7][5] = bishop1;

        Piece queen = new Queen(7, 3, game.player2.color);
        boardArr[7][3] = queen;

        Piece king = new King(7, 4, game.player2.color);
        boardArr[7][4] = king;
    }

    public void movePiece(Piece piece, int destinationX, int destinationY) {
        if (piece.isValidPath(destinationX, destinationY) && isMoveValid(piece, destinationX, destinationY)) {
            if (canCapture(piece, destinationX, destinationY)) {
                boardArr[destinationX][destinationY] = null;
            }
            setPieceLocation(piece, destinationX, destinationY);
        }
        else {
            System.out.println("invalid move");
        }
    }

    public void setPieceLocation(Piece piece, int destinationX, int destinationY) {
        int sourceX = piece.pieceX;
        int sourceY = piece.pieceY;

        piece.pieceX = destinationX;
        piece.pieceY = destinationY;

        boardArr[destinationX][destinationY] = piece;
        boardArr[sourceX][sourceY] = null;
    }

    public boolean isMoveValid(Piece piece, int destinationX, int destinationY) {
        return isDestinationValid(piece, destinationX, destinationY) && isLeapingValid(piece, destinationX, destinationY);
    }

    public boolean canCapture(Piece piece, int destinationX, int destinationY) {
        //if (piece.getPieceType() == Type.Pawn) {
        //   return Pawn.pawnCanCapture(destinationX, destinationY);
       // }
        if (boardArr[destinationX][destinationY]!= null &&
                boardArr[destinationX][destinationY].pieceColor != piece.pieceColor) {
            if (piece.getPieceType() == Type.Pawn) {
                int xSub = destinationX - piece.pieceX;
                int ySub = destinationY - piece.pieceY;
                return (xSub == 1 && ySub == 1);
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean isDestinationValid(Piece piece, int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] == null) {
            return true;
        }
        else {
            if (boardArr[destinationX][destinationY].pieceColor == piece.pieceColor) {
                return false;
            }
        }
        return true;

    }

    public boolean isLeapingValid(Piece piece, int destinationX, int destinationY) {
        if (piece.getPieceType() == Type.Knight) {
            return true;
        }
        //int pairs = boardArr[0].length;




        int xSub = Math.abs(destinationX - piece.pieceX);
        int ySub = Math.abs(destinationY - piece.pieceY);
        if (xSub > ySub) {
            for (int i = piece.pieceX + 1; i < destinationX; ++i) {
                if (boardArr[i][piece.pieceY] != null) {
                    return false;
                }
            }
        }
        else if (xSub < ySub) {
            for (int i = piece.pieceY + 1; i < destinationY; ++i) {
                if (boardArr[piece.pieceX][i] != null) {
                    return false;
                }
            }
        }
        else {
            for (int i = piece.pieceX + 1; i < destinationX; ++i) {
                if (boardArr[i][i] != null) {
                    return false;
                }
            }
        }
        return true;

    }

    //public Type getPiece() {
    //    //int row = coordinate / 8;
    //    //int col = coordinate - (row * 8);
    //    return getPieceType();
   // }

   // public Color getColorOfPiece() {
   //     //return boardArr[row][col].player.color;
   //     return color;
   //     }

    public Piece getSquare(int coordinate) {
        int row = coordinate / 8;
        int col = coordinate - (row * 8);
        return boardArr[row][col];
    }
}
