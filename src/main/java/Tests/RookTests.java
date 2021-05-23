package Tests;
import Game.Game;
import Game.Board;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class RookTests {
    Board board = Board.getInstance();
    Game game = Game.getInstance();

    @Test
    public void leftMove() {
        Piece rook = new Rook(3, 3, game.player1);
        board.movePiece(rook,3, 2);
        assertEquals(rook, board.boardArr[3][2]);
    }

    @Test
    public void rightMove() {
        Piece rook = new Rook(3, 3, game.player1);
        board.movePiece(rook,3, 4);
        assertEquals(rook, board.boardArr[3][4]);
    }

    @Test
    public void upMove() {
        Piece rook = new Rook(3, 3, game.player1);
        board.movePiece(rook,4, 3);
        assertEquals(rook, board.boardArr[4][3]);
    }

    @Test
    public void downMove() {
        Piece rook = new Rook(3, 3, game.player1);
        board.movePiece(rook,2, 3);
        assertEquals(rook, board.boardArr[2][3]);
    }

    @Test
    public void capture() {
        Piece rook = new Rook(3, 3, game.player1);
        new Pawn(4, 3, game.player2);
        board.movePiece(rook, 4, 3);
        assertEquals(rook, board.boardArr[4][3]);
    }
}
