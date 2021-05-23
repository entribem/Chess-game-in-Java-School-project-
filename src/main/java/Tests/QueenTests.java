package Tests;
import Game.Game;
import Game.Board;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueenTests {
    Board board = Board.getInstance();
    Game game = Game.getInstance();

    @Test
    public void leftMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,3, 2);
        assertEquals(queen, board.boardArr[3][2]);
    }

    @Test
    public void rightMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,3, 4);
        assertEquals(queen, board.boardArr[3][4]);
    }

    @Test
    public void upMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,4, 3);
        assertEquals(queen, board.boardArr[4][3]);
    }

    @Test
    public void downMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,2, 3);
        assertEquals(queen, board.boardArr[2][3]);
    }

    @Test
    public void downLeftMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,2, 2);
        assertEquals(queen, board.boardArr[2][2]);
    }

    @Test
    public void downRightMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,4, 2);
        assertEquals(queen, board.boardArr[4][2]);
    }

    @Test
    public void upLeftMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,2, 4);
        assertEquals(queen, board.boardArr[2][4]);
    }

    @Test
    public void upRightMove() {
        Piece queen = new Queen(3, 3, game.player1);
        board.movePiece(queen,4, 4);
        assertEquals(queen, board.boardArr[4][4]);
    }

    @Test
    public void capture() {
        Piece queen = new Queen(3, 3, game.player1);
        new Pawn(5, 5, game.player2);
        board.movePiece(queen, 5, 5);
        assertEquals(queen, board.boardArr[5][5]);
    }
}
