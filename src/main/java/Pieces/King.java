package Pieces;

import Game.Board;
import Game.Color;
import Game.Player;

import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class King extends Piece {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final static Board board = Board.getInstance();
    public King(int pieceX, int pieceY, final Player player) {
        super(pieceX, pieceY, player);
    }

    @Override
    public boolean isValidPath(int destinationX, int destinationY) {
        int xSub = Math.abs(destinationX - this.pieceX);
        int ySub = Math.abs(destinationY - this.pieceY);
        if (xSub == 0 && ySub == 2 && !kingInCheck(this, destinationX, destinationY)) { //if the player wants to castle
            return true;
        }
        return (xSub < 2) && (ySub < 2) && !kingInCheck(this, destinationX, destinationY); //king can move only one square in any direction
    }

    @Override
    public Type getPieceType() {
        return Type.KING;
    }

    /**
     * Checks if the ally piece can defend king by shielding him
     *
     * @param piece         Piece to be moved
     * @param king          King piece
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if can defend, false if can not
     */
    public static boolean allyCanDefendKing(Piece piece, Piece king, int destinationX, int destinationY) {
        if (Optional.ofNullable(board.boardArr[destinationX][destinationY]).isPresent()) {
            return true;
        }
        board.boardArr[destinationX][destinationY] = piece;
        if (kingInCheck(king, king.pieceX, king.pieceY)) {
            board.boardArr[destinationX][destinationY] = null;
            return false;
        }
        else {
            board.boardArr[destinationX][destinationY] = null;
            return true;
        }
    }

    /**
     * Checks if the king is in check
     *
     * @param piece         Piece
     * @param destinationX  X coordinate of the king
     * @param destinationY  Y coordinate of the king
     * @return              True if the king is in check, false if is not
     */
     public static boolean kingInCheck(Piece piece, int destinationX, int destinationY) {
        Vector<Piece> enemyPieces = piece.player.getEnemyPieces(piece.player.color);

        for(int i = 0; i < enemyPieces.size(); i++)
        {
            if (canCaptureKing(enemyPieces.elementAt(i), destinationX, destinationY)) {
                LOGGER.log(Level.WARNING, "Cant move there, king will be under check!");
                board.kingCheck = true;
                return true;
            }
        }
        board.kingCheck = false;
        return false;
    }

    /**
     * Checks if the given piece can capture king
     *
     * @param enemyPiece    Enemy piece
     * @param x             X coordinate of the king
     * @param y             Y coordinate of the king
     * @return              True if can capture, false if can not
     */
    private static boolean canCaptureKing(Piece enemyPiece, int x, int y)
    {
        return enemyPiece.isValidPath(x, y) && board.isMoveValid(enemyPiece, x, y);
    }

    /**
     * Checks if the king can not get out of check
     *
     * @param piece King piece
     * @return      True if checkmate, false if not
     */
    public static boolean isCheckmate(Piece piece) {
        if (!kingCanMove(piece, piece.pieceX, piece.pieceY)
                && !kingCanMove(piece, piece.pieceX + 1, piece.pieceY)
                && !kingCanMove(piece, piece.pieceX + 1, piece.pieceY + 1)
                && !kingCanMove(piece, piece.pieceX + 1, piece.pieceY - 1)
                && !kingCanMove(piece, piece.pieceX - 1, piece.pieceY)
                && !kingCanMove(piece, piece.pieceX - 1, piece.pieceY+ 1)
                && !kingCanMove(piece, piece.pieceX - 1, piece.pieceY - 1)
                && !kingCanMove(piece, piece.pieceX + 1, piece.pieceY + 1)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if the king can move in the given direction without getting in check
     *
     * @param piece         King piece
     * @param destinationX  X coordinate of the destination square
     * @param destinationY  Y coordinate of the destination square
     * @return              True if the king can move there, false if can not
     */
    private static boolean kingCanMove(Piece piece, int destinationX, int destinationY) {
        if (destinationX != 8 && destinationY != 8 && destinationX != -1 && destinationY != -1) {
            if (piece.isValidPath(destinationX, destinationY)
                    && !kingInCheck(piece, destinationX, destinationY)
                    && board.isMoveValid(piece, destinationX, destinationY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the king and his position on board
     *
     * @param player    Player
     * @return          King piece
     */
    public static Piece findKing(Player player) {
        if (player.color == Color.WHITE) {
            for (int i = 0; i < board.whitePieces.size(); i++) {
                if (board.whitePieces.elementAt(i).getPieceType() == Type.KING) {
                    return board.whitePieces.elementAt(i);
                }
            }
        }
        else {
            for (int i = 0; i < board.blackPieces.size(); i++) {
                if (board.blackPieces.elementAt(i).getPieceType() == Type.KING) {
                    return board.blackPieces.elementAt(i);
                }
            }
        }
        return board.whitePieces.elementAt(0);
    }

    /**
     * Checks if the king can make castling move
     *
     * @param piece        Piece to perform castling move
     * @param destinationX X coordinate of the destination square
     * @param destinationY Y coordinate of the destination square
     * @return True if king can mak castling move, false if can not
     */
    public static boolean kingCanCastle(Piece piece, int destinationX, int destinationY) {
        //king can castle if king and rook have not made any moves
        if (!piece.hasMoved) {
            if (board.boardArr[destinationX][destinationY + 1] != null) {
                if (destinationY > piece.pieceY) {
                    if (board.boardArr[destinationX][destinationY + 1].getPieceType() == Type.ROOK) {
                        return (!board.boardArr[destinationX][destinationY + 1].hasMoved);
                    }
                } else if (board.boardArr[destinationX][destinationY - 2] != null) {
                    if (board.boardArr[destinationX][destinationY - 2].getPieceType() == Type.ROOK) {
                        return (!board.boardArr[destinationX][destinationY - 2].hasMoved);
                    }
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
    public static void castleMove(Piece piece, int destinationX, int destinationY) {
        //kingside castling
        if (board.boardArr[destinationX][destinationY + 1] != null) {
            if (board.boardArr[destinationX][destinationY + 1].getPieceType() == Type.ROOK) {
                board.boardArr[destinationX][destinationY - 1] = board.boardArr[destinationX][destinationY + 1];
                board.boardArr[destinationX][destinationY + 1] = null;
            }
        }
        //queenside castling
        else if (board.boardArr[destinationX][destinationY - 2] != null) {
            if (board.boardArr[destinationX][destinationY - 2].getPieceType() == Type.ROOK) {
                board.boardArr[destinationX][destinationY + 1] = board.boardArr[destinationX][destinationY - 2];
                board.boardArr[destinationX][destinationY - 2] = null;
            }
        }
    }
}
