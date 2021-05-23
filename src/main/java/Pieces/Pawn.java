package Pieces;

import Game.Board;
import Game.Player;


public class Pawn extends Piece {
    private final static Board board = Board.getInstance();
    public Pawn(int pieceX, int pieceY, final Player player) {
        super(pieceX, pieceY, player);
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
        return Type.PAWN;
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

    /**
     * Promotes pawn into queen
     *
     * @param piece Piece to be promoted
     */
    public static void promotePawn(Piece piece) {
        if (pawnCanPromote(piece)) {
            Piece queen = new Queen(piece.pieceX, piece.pieceY, piece.player);
            board.boardArr[piece.pieceX][piece.pieceY] = queen;
        }
    }

    /**
     * Checks if the pawn can be promoted
     *
     * @param piece Piece to be promoted
     * @return True if can be promoted, false if can not
     */
    private static boolean pawnCanPromote(Piece piece) {
        if (piece.getPieceType() == Type.PAWN) {
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
     * @param piece Piece to make en passant
     * @return true if pawn can make en passant move, false if can not
     */
    public static boolean pawnCanEnPassant(Piece piece) {
        //pawn can make en passant on another pawn if it has advanced by 2 squares
        if (piece.pieceY != 7 && piece.pieceY != 0) {
            if (board.boardArr[piece.pieceX][piece.pieceY + 1] != null) {
                if (board.boardArr[piece.pieceX][piece.pieceY + 1].getPieceType() == Type.PAWN &&
                        board.boardArr[piece.pieceX][piece.pieceY + 1].player.color != piece.player.color) {
                    return board.boardArr[piece.pieceX][piece.pieceY + 1].moved2Forward;
                }
            }
            else if (board.boardArr[piece.pieceX][piece.pieceY - 1] != null) {
                if (board.boardArr[piece.pieceX][piece.pieceY - 1].getPieceType() == Type.PAWN &&
                        board.boardArr[piece.pieceX][piece.pieceY - 1].player.color != piece.player.color) {
                    return board.boardArr[piece.pieceX][piece.pieceY - 1].moved2Forward;
                }
            }
        }
        return false;
    }
}
