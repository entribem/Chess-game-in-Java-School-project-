package Game;

import Pieces.*;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
    public Game game;
    /**
     * Height of the board
     */
    public final int height;

    /**
     * Width of the board
     */
    public final int width;

    /**
     * Array which represents the board
     */
    public Piece[][] boardArr;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /*public Vector<Piece> whitePieces = new Vector<Piece>(16);
    public Vector<Piece> blackPieces = new Vector<Piece>(16);*/

    public Board(Game game, int height, int width) {
        this.game = game;
        this.height = height;
        this.width = width;
        boardArr = new Piece[height][width];

    }

    /**
     * Creates pieces and populates board with them for each player
     */
    public void loadStandardPieces() {
        loadPiecesPlayer1();
        loadPiecesPlayer2();
        //setPieceVectors();
    }

    /**
     * Creates pieces and populates board for the second player
     */
    public void loadPiecesPlayer2() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(1, i, game.player2.color);
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

    /**
     * Creates pieces and populates board for the first player
     */
    public void loadPiecesPlayer1() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(6, i, game.player1.color);
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

    /*public void setPieceVectors()
    {
        for (int i = 0; i < 8; i++)
        {
            if (game.player1.color == Color.white) {
                whitePieces.add(this.boardArr[i][6]);
                whitePieces.add(this.boardArr[i][7]);
                blackPieces.add(this.boardArr[i][0]);
                blackPieces.add(this.boardArr[i][1]);
            }
            else {
                whitePieces.add(this.boardArr[i][0]);
                whitePieces.add(this.boardArr[i][1]);
                blackPieces.add(this.boardArr[i][6]);
                blackPieces.add(this.boardArr[i][7]);
            }
        }
    }*/

    /**
     * Moves the piece if the move is correct
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     */
    public void movePiece(Piece piece, int destinationX, int destinationY) {
        if (piece.isValidPath(destinationX, destinationY) && isMoveValid(piece, destinationX, destinationY)) {
            if (piece.getPieceType() == Type.Pawn) {
                if (pawnCanEnPassant(piece) && piece.pieceColor == game.player1.color) { //En passant for player 1
                    boardArr[destinationX + 1][destinationY] = null;
                } else if (pawnCanEnPassant(piece) && piece.pieceColor == game.player2.color) { //En passant for player 2
                    boardArr[destinationX - 1][destinationY] = null;
                }
            }
            if (piece.getPieceType() == Type.King) {
                if (kingCanCastle(piece, destinationX, destinationY)) { //checks if the king can castle
                    castleMove(destinationX, destinationY);
                }
            }

            if (piece.getPieceType() == Type.King || piece.getPieceType() == Type.Rook) {
                piece.hasMoved = true;
            }

            setPieceLocation(piece, destinationX, destinationY);
        }
        else {
            LOGGER.log(Level.WARNING, "Invalid move");
        }
    }

    /**
     * Moves piece to the new location
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     */
    public void setPieceLocation(Piece piece, int destinationX, int destinationY) {
        int sourceX = piece.pieceX;
        int sourceY = piece.pieceY;

        canCaptureKing(destinationX, destinationY);

        piece.pieceX = destinationX;
        piece.pieceY = destinationY;

        boardArr[sourceX][sourceY] = null;
        boardArr[destinationX][destinationY] = piece;

    }

    /**
     * Checks if the move is valid
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if the move is valid, false if is not
     */
    public boolean isMoveValid(Piece piece, int destinationX, int destinationY) {
        if (piece.getPieceType() == Type.Pawn) {
            if (!Pawn.pawnCanCapture(piece, destinationX, destinationY) && boardArr[destinationX][destinationY] != null) {
                return false;
            }
        }

        //pawn can not move sideways if it can not capture or en passant
        if (piece.getPieceType() == Type.Pawn && Pawn.pawnCanCapture(piece, destinationX, destinationY)
            && boardArr[destinationX][destinationY] == null && !pawnCanEnPassant(piece)) {
            return false;
        }

        return isDestinationValid(piece, destinationX, destinationY) &&
                isLeapingValid(piece, destinationX, destinationY);
    }

    /**
     * Checks if the king is going to be captured and sets loser
     *
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     */
    public void canCaptureKing(int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] != null) {
            if (boardArr[destinationX][destinationY].getPieceType() == Type.King) {
                if (boardArr[destinationX][destinationY].pieceColor == game.player1.color) {
                    game.player1.hasLost = true;
                    LOGGER.log(Level.INFO, "PLAYER 1 HAS LOST");
                } else if (boardArr[destinationX][destinationY].pieceColor == game.player2.color) {
                    game.player2.hasLost = true;
                    LOGGER.log(Level.INFO, "PLAYER 2 HAS LOST");
                }
            }
        }
    }

    /**
     * Checks if the square is not occupied by the piece of the same color
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              False if occupied by the piece of the same color, true if not
     */
    public boolean isDestinationValid(Piece piece, int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] == null) {
            return true;
        }
        else {
            //piece can not capture pieces of the same color
            if (boardArr[destinationX][destinationY].pieceColor == piece.pieceColor) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the destination square is the same as source square
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if the destination square is the same as source square, false if is not
     */
    public boolean isOriginSquare(Piece piece, int destinationX, int destinationY)
    {
        return (piece.pieceX == destinationX) && (piece.pieceY == destinationY);
    }

    /**
     * Checks if the piece can leap over other pieces
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if can leap, false if can not
     */
    public boolean isLeapingValid(Piece piece, int destinationX, int destinationY) {
        //Knights can always leap, kings will not ever leap
        if (piece.getPieceType() == Type.Knight || piece.getPieceType() == Type.King) {
            return true;
        }

        int xSub = destinationX - piece.pieceX;
        int ySub = destinationY - piece.pieceY;

        int x = piece.pieceX;
        int y = piece.pieceY;

        //pawns will not leap unless it is advancing two squares
        if (piece.getPieceType() == Type.Pawn && Math.abs(xSub) != 2) {
            return true;
        }

        //tests if there are any pieces in the way for all directions
        if (ySub == 0) {
            if (xSub > 0) {
                for (int i = x + 1; i < destinationX; ++i) {
                    if (boardArr[i][y] != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = x - 1; i > destinationX; --i) {
                    if (boardArr[i][y] != null) {
                        return false;
                    }
                }
            }
        }
        else if (xSub == 0) {
            if (ySub > 0) {
                for (int i = y + 1; i < destinationY; ++i) {
                    if (boardArr[x][i] != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = y - 1; i > destinationY; --i) {
                    if (boardArr[x][i] != null) {
                        return false;
                    }
                }
            }
        }
        else if (xSub > 0 && ySub > 0) {
            for (int k = 1; k < xSub; ++k) {
                if (boardArr[x + k][y + k] != null) {
                    return false;
                }
            }
        }
        else if (xSub < 0 && ySub < 0) {
            for (int k = 1; k < Math.abs(xSub); ++k) {
                if (boardArr[x - k][y - k] != null) {
                    return false;
                }
            }
        }
        else if (xSub > 0 && ySub < 0) {
            for (int k = 1; k < Math.abs(xSub); ++k) {
                if (boardArr[x + k][y - k] != null) {
                    return false;
                }
            }
        }
        else if (xSub < 0 && ySub > 0) {
            for (int k = 1; k < Math.abs(xSub); ++k) {
                if (boardArr[x - k][y + k] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the square and the piece on it with the given coordinate
     *
     * @param coordinate    Coordinate of the square
     * @return              Square and the piece on it
     */
    public Piece getSquare(int coordinate) {
        int row = coordinate / 8;
        int col = coordinate - (row * 8);
        return boardArr[row][col];
    }

    /**
     * Promotes pawn into queen
     *
     * @param piece Piece to be promoted
     */
    public void promotePawn(Piece piece) {
        if (pawnCanPromote(piece)) {
            Piece queen = new Queen(piece.pieceX, piece.pieceY, piece.pieceColor);
            boardArr[piece.pieceX][piece.pieceY] = queen;
        }
    }

    /**
     * Checks if the pawn can be promoted
     *
     * @param piece Piece to be promoted
     * @return      True if can be promoted, false if can not
     */
    public boolean pawnCanPromote(Piece piece) {
        if (piece.getPieceType() == Type.Pawn) {
            //pawn can be promoted when it reaches the end of the board
            if (piece.pieceX == 7 || piece.pieceX == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the pawn can perform en passant move
     *
     * @param piece     Piece to make en passant
     * @return          true if pawn can make en passant move, false if can not
     */
    public boolean pawnCanEnPassant(Piece piece) {
        //pawn can make en passant on another pawn if it has advanced by 2 squares
        if (piece.pieceY != 7) {
            if (boardArr[piece.pieceX][piece.pieceY + 1] != null) {
                if (boardArr[piece.pieceX][piece.pieceY + 1].getPieceType() == Type.Pawn &&
                        boardArr[piece.pieceX][piece.pieceY + 1].pieceColor != piece.pieceColor) {
                    return boardArr[piece.pieceX][piece.pieceY + 1].moved2Forward;
                }
            }
            else if (boardArr[piece.pieceX][piece.pieceY - 1] != null) {
                if (boardArr[piece.pieceX][piece.pieceY - 1].getPieceType() == Type.Pawn &&
                        boardArr[piece.pieceX][piece.pieceY - 1].pieceColor != piece.pieceColor) {
                    return boardArr[piece.pieceX][piece.pieceY - 1].moved2Forward;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the king can make castling move
     *
     * @param piece         Piece to perform castling move
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if king can mak castling move, false if can not
     */
    public boolean kingCanCastle(Piece piece, int destinationX, int destinationY) {
        //king can castle if king and rook have not made any moves
        if (boardArr[destinationX][destinationY + 1] != null || boardArr[destinationX][destinationY - 2] != null) {
            if (destinationY > piece.pieceY) {
                if (boardArr[destinationX][destinationY + 1].getPieceType() == Type.Rook) {
                    return (!piece.hasMoved) && (!boardArr[destinationX][destinationY + 1].hasMoved);
                }
            } else {
                if (boardArr[destinationX][destinationY - 2].getPieceType() == Type.Rook) {
                    return (!piece.hasMoved) && (!boardArr[destinationX][destinationY - 2].hasMoved);
                }
            }
        }
        return false;
    }

    /**
     * Makes castling move
     *
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     */

    public void castleMove(int destinationX, int destinationY) {
        //kingside castling
        if (boardArr[destinationX][destinationY + 1] != null) {
            if (boardArr[destinationX][destinationY + 1].getPieceType() == Type.Rook) {
                boardArr[destinationX][destinationY - 1] = boardArr[destinationX][destinationY + 1];
                boardArr[destinationX][destinationY + 1] = null;
            }
        }
        //queenside castling
        else if (boardArr[destinationX][destinationY - 2] != null) {
            if (boardArr[destinationX][destinationY - 2].getPieceType() == Type.Rook) {
                boardArr[destinationX][destinationY + 1] = boardArr[destinationX][destinationY - 2];
                boardArr[destinationX][destinationY - 2] = null;
            }
        }
    }

    /**
     * Checks if the move has been made
     *
     * @param piece         Piece, which made a move
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if the move has been made, false if has not
     */
    public boolean moveSuccessful(Piece piece, int destinationX, int destinationY) {
        return (piece.pieceX == destinationX) && (piece.pieceY == destinationY);
    }
}
