package Tests;
import Game.Game;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueenTests {
    @Test
    public void leftMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,3, 2);
        assertEquals(queen, game.board.boardArr[3][2]);
    }

    @Test
    public void rightMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,3, 4);
        assertEquals(queen, game.board.boardArr[3][4]);
    }

    @Test
    public void upMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,4, 3);
        assertEquals(queen, game.board.boardArr[4][3]);
    }

    @Test
    public void downMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,2, 3);
        assertEquals(queen, game.board.boardArr[2][3]);
    }

    @Test
    public void downLeftMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,2, 2);
        assertEquals(queen, game.board.boardArr[2][2]);
    }

    @Test
    public void downRightMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,4, 2);
        assertEquals(queen, game.board.boardArr[4][2]);
    }

    @Test
    public void upLeftMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,2, 4);
        assertEquals(queen, game.board.boardArr[2][4]);
    }

    @Test
    public void upRightMove() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        game.board.movePiece(queen,4, 4);
        assertEquals(queen, game.board.boardArr[4][4]);
    }

    @Test
    public void capture() {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player1.color);
        new Pawn(5, 5, game.player2.color, false);

        game.board.movePiece(queen, 5, 5);
        assertEquals(queen, game.board.boardArr[5][5]);
    }
}
