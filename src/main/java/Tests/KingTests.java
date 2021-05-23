package Tests;
import Game.Game;
import Game.Board;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KingTests {
    Board board = Board.getInstance();
    Game game = Game.getInstance();

    @Test
    public void leftMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,3, 2);
        assertEquals(king, board.boardArr[3][2]);
    }

    @Test
    public void rightMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,3, 4);
        assertEquals(king, board.boardArr[3][4]);
    }

    @Test
    public void upMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,4, 3);
        assertEquals(king, board.boardArr[4][3]);
    }

    @Test
    public void downMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,2, 3);
        assertEquals(king, board.boardArr[2][3]);
    }

    @Test
    public void downLeftMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,2, 2);
        assertEquals(king, board.boardArr[2][2]);
    }

    @Test
    public void downRightMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,4, 2);
        assertEquals(king, board.boardArr[4][2]);
    }

    @Test
    public void upLeftMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,2, 4);
        assertEquals(king, board.boardArr[2][4]);
    }

    @Test
    public void upRightMove() {
        Piece king = new King(3, 3, game.player1);
        board.movePiece(king,4, 4);
        assertEquals(king, board.boardArr[4][4]);
    }

    @Test
    public void capture() {
        Piece king = new King(3, 3, game.player1);
        new Pawn(4, 4, game.player2);
        board.movePiece(king, 4, 4);
        assertEquals(king, board.boardArr[4][4]);
    }
}
