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

    public void loadStandardPieces() {
        loadPiecesPlayer1();
        loadPiecesPlayer2();
    }

    public void loadPiecesPlayer2() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(1, i, game.player2.color, false);
            boardArr[1][i] = pawn;
        }

        Piece knight = new Knight(0, 1, game.player2.color);
        Piece knight1 = new Knight(0, 6, game.player2.color);
        boardArr[0][1] = knight;
        boardArr[0][6] = knight1;

        Piece rook = new Rook(0, 0, game.player2.color);
        Piece rook1 = new Rook(0, 7, game.player2.color);
        boardArr[0][0] = rook;
        boardArr[0][7] = rook1;

        Piece bishop = new Bishop(0, 2, game.player2.color);
        Piece bishop1 = new Bishop(0, 5, game.player2.color);
        boardArr[0][2] = bishop;
        boardArr[0][5] = bishop1;

        Piece queen = new Queen(0, 3, game.player2.color);
        boardArr[0][3] = queen;

        Piece king = new King(0, 4, game.player2.color);
        boardArr[0][4] = king;
    }

    public void loadPiecesPlayer1() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(6, i, game.player1.color, false);
            boardArr[6][i] = pawn;
        }

        Piece knight = new Knight(7, 1, game.player1.color);
        Piece knight1 = new Knight(7, 6, game.player1.color);
        boardArr[7][1] = knight;
        boardArr[7][6] = knight1;

        Piece rook = new Rook(7, 0, game.player1.color);
        Piece rook1 = new Rook(7, 7, game.player1.color);
        boardArr[7][0] = rook;
        boardArr[7][7] = rook1;

        Piece bishop = new Bishop(7, 2, game.player1.color);
        Piece bishop1 = new Bishop(7, 5, game.player1.color);
        boardArr[7][2] = bishop;
        boardArr[7][5] = bishop1;

        Piece queen = new Queen(7, 3, game.player1.color);
        boardArr[7][3] = queen;

        Piece king = new King(7, 4, game.player1.color);
        boardArr[7][4] = king;
    }

    public void movePiece(Piece piece, int destinationX, int destinationY) {
        if (piece.isValidPath(destinationX, destinationY) && isMoveValid(piece, destinationX, destinationY)) {
            if (pawnCanEnPassant(piece)) {
                boardArr[destinationX + 1][destinationY] = null;
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

        canCaptureKing(destinationX, destinationY);

        piece.pieceX = destinationX;
        piece.pieceY = destinationY;

        boardArr[sourceX][sourceY] = null;
        boardArr[destinationX][destinationY] = piece;

    }

    public boolean isMoveValid(Piece piece, int destinationX, int destinationY) {
        if (piece.getPieceType() == Type.Pawn && Pawn.pawnCanCapture(piece, destinationX, destinationY)
            && boardArr[destinationX][destinationY] == null && !pawnCanEnPassant(piece)) {
            return false;
        }
        return isDestinationValid(piece, destinationX, destinationY) && isLeapingValid(piece, destinationX, destinationY);
    }

    /*Checks if the king is going to be captured*/
    public void canCaptureKing(int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] != null) {
            if (boardArr[destinationX][destinationY].getPieceType() == Type.King) {
                System.out.println("Player1 color = " + game.player1.color + " Player2 color = " + game.player2.color);
                System.out.println("PIECE COLOR = " + boardArr[destinationX][destinationY].pieceColor);
                if (boardArr[destinationX][destinationY].getPieceColor() == game.player1.color) {
                    game.player1.hasLost = true;
                    System.out.println("PLAYER 1 HAS LOST");
                } else if (boardArr[destinationX][destinationY].getPieceColor() == game.player2.color) {
                    game.player2.hasLost = true;
                    System.out.println("PLAYER 2 HAS LOST");
                }
            }
        }
    }

    /*public boolean canCapture(Piece piece, int destinationX, int destinationY) {
        System.out.println("DEBUG FOR " + piece.getPieceType());
        if ((boardArr[destinationX][destinationY]!= null &&
                boardArr[destinationX][destinationY].pieceColor != piece.pieceColor)) {
            System.out.println("DEBUG");
            if (boardArr[destinationX][destinationY].getPieceType() == Type.King) {
                System.out.println("Player1 color = " + game.player1.color + " Player2 color = " + game.player2.color);
                System.out.println("PIECE COLOR = " + boardArr[destinationX][destinationY].pieceColor);
                if (boardArr[destinationX][destinationY].getPieceColor() == game.player1.color) {
                    game.player1.hasLost = true;
                    System.out.println("PLAYER 1 HAS LOST");
                }
                else if (boardArr[destinationX][destinationY].getPieceColor() == game.player2.color){
                    game.player2.hasLost = true;
                    System.out.println("PLAYER 2 HAS LOST");
                }
            }
            if (piece.getPieceType() == Type.Pawn) {
                return Pawn.pawnCanCapture(piece, destinationX, destinationY);
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }*/

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
        if (piece.getPieceType() == Type.Knight || piece.getPieceType() == Type.King) {
            return true;
        }

        int xSub = destinationX - piece.pieceX;
        int ySub = destinationY - piece.pieceY;

        if (piece.getPieceType() == Type.Pawn && Math.abs(xSub) != 2) {
            return true;
        }

        if (ySub == 0) {
            if (xSub > 0) {
                for (int i = piece.pieceX + 1; i < destinationX; ++i) {
                    if (boardArr[i][piece.pieceY] != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = piece.pieceX - 1; i > destinationX; --i) {
                    if (boardArr[i][piece.pieceY] != null) {
                        return false;
                    }
                }
            }
        }
        else if (xSub == 0) {
            if (ySub > 0) {
                for (int i = piece.pieceY + 1; i < destinationY; ++i) {
                    if (boardArr[piece.pieceX][i] != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = piece.pieceY - 1; i > destinationY; --i) {
                    if (boardArr[piece.pieceX][i] != null) {
                        return false;
                    }
                }
            }
        }
        else if (xSub > 0 && ySub > 0) {
            int i = piece.pieceX + 1;
            int j = piece.pieceY + 1;
            for (int k = 0; k < xSub; ++k) {
                if (boardArr[i + k][j + k] != null) {
                    return false;
                }
            }
        }
        else if (xSub < 0 && ySub < 0) {
            int i = piece.pieceX - 1;
            int j = piece.pieceY - 1;
            for (int k = 0; k < Math.abs(xSub); ++k) {
                if (boardArr[i + k][j + k] != null) {
                    return false;
                }
            }
        }
        else if (xSub > 0 && ySub < 0) {
            int i = piece.pieceX + 1;
            int j = piece.pieceY - 1;
            for (int k = 0; k < Math.abs(xSub); ++k) {
                if (boardArr[i + k][j + k] != null) {
                    return false;
                }
            }
        }
        else if (xSub < 0 && ySub > 0) {
            int i = piece.pieceX - 1;
            int j = piece.pieceY + 1;
            for (int k = 0; k < Math.abs(xSub); ++k) {
                if (boardArr[i + k][j + k] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Piece getSquare(int coordinate) {
        int row = coordinate / 8;
        int col = coordinate - (row * 8);
        return boardArr[row][col];
    }

    /*Promotes pawn into queen*/
    public void promotePawn(Piece piece) {
        if (pawnCanPromote(piece)) {
            Piece queen = new Queen(piece.pieceX, piece.pieceY, piece.pieceColor);
            boardArr[piece.pieceX][piece.pieceY] = queen;
        }
    }

    public boolean pawnCanPromote(Piece piece) {
        if (piece.getPieceType() == Type.Pawn) {
            if (piece.pieceColor == game.player1.color) {
                if (piece.pieceX == 7) {
                    return true;
                }
            }
            else {
                if (piece.pieceX == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /*Checks if the pawn can perform En Passant move*/
    public boolean pawnCanEnPassant(Piece piece) {
        if (piece.pieceY != 7) {
            if (boardArr[piece.pieceX][piece.pieceY + 1] != null) {
                if (boardArr[piece.pieceX][piece.pieceY + 1].getPieceType() == Type.Pawn &&
                        boardArr[piece.pieceX][piece.pieceY + 1].pieceColor != piece.pieceColor) {
                    System.out.println(piece.pieceX);
                    return boardArr[piece.pieceX][piece.pieceY + 1].moved2Forward;

                }
            } else if (boardArr[piece.pieceX][piece.pieceY - 1] != null) {
                if (boardArr[piece.pieceX][piece.pieceY - 1].getPieceType() == Type.Pawn &&
                        boardArr[piece.pieceX][piece.pieceY - 1].pieceColor != piece.pieceColor) {
                    return boardArr[piece.pieceX][piece.pieceY - 1].moved2Forward;
                }
            }
        }
        return false;
    }

    /*Checks if the move has been made*/
    public boolean moveSuccessful(Piece piece, int destinationX, int destinationY) {
        return (piece.pieceX == destinationX) && (piece.pieceY == destinationY);
    }
}
