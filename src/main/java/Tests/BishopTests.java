package Tests;
import Game.Game;
import Pieces.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BishopTests {

    @Test
    public void downLeftMove() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.player1.color);
        game.board.movePiece(bishop,2, 2);
        assertEquals(bishop, game.board.boardArr[2][2]);
    }

    @Test
    public void downRightMove() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.player1.color);
        game.board.movePiece(bishop,4, 2);
        assertEquals(bishop, game.board.boardArr[4][2]);
    }

    @Test
    public void upLeftMove() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.player1.color);
        game.board.movePiece(bishop,2, 4);
        assertEquals(bishop, game.board.boardArr[2][4]);
    }

    @Test
    public void upRightMove() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.player1.color);
        game.board.movePiece(bishop,4, 4);
        assertEquals(bishop, game.board.boardArr[4][4]);
    }

    @Test
    public void capture() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.player1.color);
        new Pawn(4, 4, game.player2.color, false);

        game.board.movePiece(bishop, 4, 4);
        assertEquals(bishop, game.board.boardArr[4][4]);
    }
}