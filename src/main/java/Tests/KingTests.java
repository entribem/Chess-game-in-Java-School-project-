package Tests;
import Game.Game;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KingTests {
    @Test
    public void leftMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,3, 2);
        assertEquals(king, game.board.boardArr[3][2]);
    }

    @Test
    public void rightMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,3, 4);
        assertEquals(king, game.board.boardArr[3][4]);
    }

    @Test
    public void upMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,4, 3);
        assertEquals(king, game.board.boardArr[4][3]);
    }

    @Test
    public void downMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,2, 3);
        assertEquals(king, game.board.boardArr[2][3]);
    }

    @Test
    public void downLeftMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,2, 2);
        assertEquals(king, game.board.boardArr[2][2]);
    }

    @Test
    public void downRightMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,4, 2);
        assertEquals(king, game.board.boardArr[4][2]);
    }

    @Test
    public void upLeftMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,2, 4);
        assertEquals(king, game.board.boardArr[2][4]);
    }

    @Test
    public void upRightMove() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        game.board.movePiece(king,4, 4);
        assertEquals(king, game.board.boardArr[4][4]);
    }

    @Test
    public void capture() {
        Game game = new Game();
        Piece king = new King(3, 3, game.player1.color);
        new Pawn(4, 4, game.player2.color, false);

        game.board.movePiece(king, 4, 4);
        assertEquals(king, game.board.boardArr[4][4]);
    }
}