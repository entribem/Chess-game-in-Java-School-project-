package Tests;
import Game.Game;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class RookTests {
    @Test
    public void leftMove() {
        Game game = Game.getInstance();
        Piece rook = new Rook(3, 3, game.player1);
        game.board.movePiece(rook,3, 2);
        assertEquals(rook, game.board.boardArr[3][2]);
    }

    @Test
    public void rightMove() {
        Game game = Game.getInstance();
        Piece rook = new Rook(3, 3, game.player1);
        game.board.movePiece(rook,3, 4);
        assertEquals(rook, game.board.boardArr[3][4]);
    }

    @Test
    public void upMove() {
        Game game = Game.getInstance();
        Piece rook = new Rook(3, 3, game.player1);
        game.board.movePiece(rook,4, 3);
        assertEquals(rook, game.board.boardArr[4][3]);
    }

    @Test
    public void downMove() {
        Game game = Game.getInstance();
        Piece rook = new Rook(3, 3, game.player1);
        game.board.movePiece(rook,2, 3);
        assertEquals(rook, game.board.boardArr[2][3]);
    }

    @Test
    public void capture() {
        Game game = Game.getInstance();
        Piece rook = new Rook(3, 3, game.player1);
        new Pawn(4, 3, game.player2);

        game.board.movePiece(rook, 4, 3);
        assertEquals(rook, game.board.boardArr[4][3]);
    }
}
