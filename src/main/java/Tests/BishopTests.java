package Tests;
import Game.Game;
import Game.Board;
import Pieces.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BishopTests {
    Board board = Board.getInstance();
    Game game = Game.getInstance();

    @Test
    public void downLeftMove() {
        Piece bishop = new Bishop(3, 3, game.player1);
        board.movePiece(bishop,2, 2);
        assertEquals(bishop, board.boardArr[2][2]);
    }

    @Test
    public void downRightMove() {
        Piece bishop = new Bishop(3, 3, game.player1);
        board.movePiece(bishop,4, 2);
        assertEquals(bishop, board.boardArr[4][2]);
    }

    @Test
    public void upLeftMove() {
        Piece bishop = new Bishop(3, 3, game.player1);
        board.movePiece(bishop,2, 4);
        assertEquals(bishop, board.boardArr[2][4]);
    }

    @Test
    public void upRightMove() {
        Piece bishop = new Bishop(3, 3, game.player1);
        board.movePiece(bishop,4, 4);
        assertEquals(bishop, board.boardArr[4][4]);
    }

    @Test
    public void capture() {
        Piece bishop = new Bishop(3, 3, game.player1);
        new Pawn(4, 4, game.player2);

        board.movePiece(bishop, 4, 4);
        assertEquals(bishop, board.boardArr[4][4]);
    }
}
