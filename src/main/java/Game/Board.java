package Game;

import Pieces.*;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
    /**
     * If the king is in check
     */
    public boolean kingCheck;
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

    /**
     * Vector containing all white pieces
     */
    public Vector<Piece> whitePieces = new Vector<Piece>(16);
    /**
     * Vector containing all black pieces
     */
    public Vector<Piece> blackPieces = new Vector<Piece>(16);

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
        setPieceVectors();
    }

    /**
     * Creates pieces and populates board for the second player
     */
    public void loadPiecesPlayer2() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(1, i, game.player2);
            boardArr[1][i] = pawn;
        }

        Piece knight = new Knight(0, 1, game.player2);
        Piece knight1 = new Knight(0, 6, game.player2);
        boardArr[0][1] = knight;
        boardArr[0][6] = knight1;

        Piece rook = new Rook(0, 0, game.player2);
        Piece rook1 = new Rook(0, 7, game.player2);
        boardArr[0][0] = rook;
        boardArr[0][7] = rook1;

        Piece bishop = new Bishop(0, 2, game.player2);
        Piece bishop1 = new Bishop(0, 5, game.player2);
        boardArr[0][2] = bishop;
        boardArr[0][5] = bishop1;

        Piece queen = new Queen(0, 3, game.player2);
        boardArr[0][3] = queen;

        Piece king = new King(0, 4, game.player2);
        boardArr[0][4] = king;
    }

    /**
     * Creates pieces and populates board for the first player
     */
    public void loadPiecesPlayer1() {
        for (int i = 0; i < width; ++i) {
            Piece pawn = new Pawn(6, i, game.player1);
            boardArr[6][i] = pawn;
        }

        Piece knight = new Knight(7, 1, game.player1);
        Piece knight1 = new Knight(7, 6, game.player1);
        boardArr[7][1] = knight;
        boardArr[7][6] = knight1;

        Piece rook = new Rook(7, 0, game.player1);
        Piece rook1 = new Rook(7, 7, game.player1);
        boardArr[7][0] = rook;
        boardArr[7][7] = rook1;

        Piece bishop = new Bishop(7, 2, game.player1);
        Piece bishop1 = new Bishop(7, 5, game.player1);
        boardArr[7][2] = bishop;
        boardArr[7][5] = bishop1;

        Piece queen = new Queen(7, 3, game.player1);
        boardArr[7][3] = queen;

        Piece king = new King(7, 4, game.player1);
        boardArr[7][4] = king;
    }

    /**
     * Add pieces to the vectors
     */
    public void setPieceVectors() {
        for (int i = 0; i < 8; i++) {
            if (game.player1.color == Color.WHITE) {
                whitePieces.add(this.boardArr[6][i]);
                whitePieces.add(this.boardArr[7][i]);
                blackPieces.add(this.boardArr[0][i]);
                blackPieces.add(this.boardArr[1][i]);
            } else {
                whitePieces.add(this.boardArr[0][i]);
                whitePieces.add(this.boardArr[1][i]);
                blackPieces.add(this.boardArr[6][i]);
                blackPieces.add(this.boardArr[7][i]);
            }
        }
    }

    /**
     * Moves the piece if the move is correct
     *
     * @param piece        Piece to be moved
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     */
    public void movePiece(Piece piece, int destinationX, int destinationY) {
        if (piece.isValidPath(destinationX, destinationY) && isMoveValid(piece, destinationX, destinationY)) {
            if (piece.getPieceType() == Type.PAWN) {
                if (Pawn.pawnCanEnPassant(piece) && piece.player.color == game.player1.color) { //En passant for player 1
                    boardArr[destinationX + 1][destinationY] = null;
                } else if (Pawn.pawnCanEnPassant(piece) && piece.player.color == game.player2.color) { //En passant for player 2
                    boardArr[destinationX - 1][destinationY] = null;
                }
            }
            if (piece.getPieceType() == Type.KING) {
                if (King.kingCanCastle(piece, destinationX, destinationY)) { //checks if the king can castle
                    King.castleMove(piece, destinationX, destinationY);
                }
            }

            if (piece.getPieceType() == Type.KING || piece.getPieceType() == Type.ROOK) {
                piece.hasMoved = true;
            }

            setPieceLocation(piece, destinationX, destinationY);
        } else {
            LOGGER.log(Level.WARNING, "Invalid move");
        }
    }

    /**
     * Moves piece to the new location
     *
     * @param piece        Piece to be moved
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     */
    public void setPieceLocation(Piece piece, int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] != null) {
            if (boardArr[destinationX][destinationY].player.color == Color.WHITE) {
                whitePieces.remove(boardArr[destinationX][destinationY]);
            } else {
                blackPieces.remove(boardArr[destinationX][destinationY]);
            }
        }

        int sourceX = piece.pieceX;
        int sourceY = piece.pieceY;

        isGoingToCaptureKing(destinationX, destinationY);

        piece.pieceX = destinationX;
        piece.pieceY = destinationY;

        boardArr[sourceX][sourceY] = null;
        boardArr[destinationX][destinationY] = piece;

    }

    /**
     * Checks if the move is valid
     *
     * @param piece        Piece to be moved
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     * @return True if the move is valid, false if is not
     */
    public boolean isMoveValid(Piece piece, int destinationX, int destinationY) {

        if (piece.getPieceType() != Type.KING) {
            Piece king = King.findKing(piece.player);
            if (King.kingInCheck(piece, king.pieceX, king.pieceY) &&
                    !King.allyCanDefendKing(piece, king, destinationX, destinationY)) {
                LOGGER.log(Level.WARNING, "King is in check");
                kingCheck = true;
                if (King.isCheckmate(king)) {
                    if (king.player.color == Color.WHITE) {
                        game.player1.hasLost = true;
                        LOGGER.log(Level.INFO, "PLAYER 1 HAS LOST");
                    }
                    else {
                        game.player2.hasLost = true;
                        LOGGER.log(Level.INFO, "PLAYER 2 HAS LOST");
                    }
                }
                return false;
            }
        }

        if (piece.getPieceType() == Type.PAWN) {
            if (!Pawn.pawnCanCapture(piece, destinationX, destinationY) &&
                    boardArr[destinationX][destinationY] != null) {
                return false;
            }
        }

        //pawn can not move sideways if it can not capture or en passant
        if (piece.getPieceType() == Type.PAWN && Pawn.pawnCanCapture(piece, destinationX, destinationY)
                && boardArr[destinationX][destinationY] == null && !Pawn.pawnCanEnPassant(piece)) {
            return false;
        }

        return isDestinationValid(piece, destinationX, destinationY) &&
                isLeapingValid(piece, destinationX, destinationY);
    }

    /**
     * Checks if the king is going to be captured and sets loser
     *
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     */
    public void isGoingToCaptureKing(int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] != null) {
            if (boardArr[destinationX][destinationY].getPieceType() == Type.KING) {
                if (boardArr[destinationX][destinationY].player.color == game.player1.color) {
                    game.player1.hasLost = true;
                    LOGGER.log(Level.INFO, "PLAYER 1 HAS LOST");
                } else if (boardArr[destinationX][destinationY].player.color == game.player2.color) {
                    game.player2.hasLost = true;
                    LOGGER.log(Level.INFO, "PLAYER 2 HAS LOST");
                }
            }
        }
    }

    /**
     * Checks if the square is not occupied by the piece of the same color
     *
     * @param piece        Piece to be moved
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     * @return False if occupied by the piece of the same color, true if not
     */
    public boolean isDestinationValid(Piece piece, int destinationX, int destinationY) {
        if (boardArr[destinationX][destinationY] == null) {
            return true;
        } else {
            //piece can not capture pieces of the same color
            if (boardArr[destinationX][destinationY].player.color == piece.player.color) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the destination square is the same as source square
     *
     * @param piece        Piece to be moved
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     * @return True if the destination square is the same as source square, false if is not
     */
    public boolean isOriginSquare(Piece piece, int destinationX, int destinationY) {
        return (piece.pieceX == destinationX) && (piece.pieceY == destinationY);
    }

    /**
     * Checks if the piece can leap over other pieces
     *
     * @param piece        Piece to be moved
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     * @return True if can leap, false if can not
     */
    public boolean isLeapingValid(Piece piece, int destinationX, int destinationY) {
        //Knights can always leap, kings will not ever leap
        if (piece.getPieceType() == Type.KNIGHT|| piece.getPieceType() == Type.KING) {
            return true;
        }

        int xSub = destinationX - piece.pieceX;
        int ySub = destinationY - piece.pieceY;

        int x = piece.pieceX;
        int y = piece.pieceY;

        //pawns will not leap unless it is advancing two squares
        if (piece.getPieceType() == Type.PAWN && Math.abs(xSub) != 2) {
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
            } else {
                for (int i = x - 1; i > destinationX; --i) {
                    if (boardArr[i][y] != null) {
                        return false;
                    }
                }
            }
        } else if (xSub == 0) {
            if (ySub > 0) {
                for (int i = y + 1; i < destinationY; ++i) {
                    if (boardArr[x][i] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = y - 1; i > destinationY; --i) {
                    if (boardArr[x][i] != null) {
                        return false;
                    }
                }
            }
        } else if (xSub > 0 && ySub > 0) {
            for (int k = 1; k < xSub; ++k) {
                if (boardArr[x + k][y + k] != null) {
                    return false;
                }
            }
        } else if (xSub < 0 && ySub < 0) {
            for (int k = 1; k < Math.abs(xSub); ++k) {
                if (boardArr[x - k][y - k] != null) {
                    return false;
                }
            }
        } else if (xSub > 0 && ySub < 0) {
            for (int k = 1; k < Math.abs(xSub); ++k) {
                if (boardArr[x + k][y - k] != null) {
                    return false;
                }
            }
        } else if (xSub < 0 && ySub > 0) {
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
     * @param coordinate Coordinate of the square
     * @return Square and the piece on it
     */
    public Piece getSquare(int coordinate) {
        int row = coordinate / 8;
        int col = coordinate - (row * 8);
        return boardArr[row][col];
    }

    /**
     * Checks if the move has been made successful
     *
     * @param piece         Piece to be moved
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if the move has been successful, false if not
     */
    public boolean moveSuccessful(Piece piece, int destinationX, int destinationY) {
        return (piece.pieceX == destinationX) && (piece.pieceY == destinationY);
    }
}
