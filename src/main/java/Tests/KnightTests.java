package Tests;
import Game.Game;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTests {
    @Test
    public void up2Left1Move() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.player1.color);
        game.board.movePiece(knight,5, 2);
        assertEquals(knight, game.board.boardArr[5][2]);
    }

    @Test
    public void down2Right1Move() {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1.color);
        game.board.movePiece(knight,2, 5);
        assertEquals(knight, game.board.boardArr[2][5]);
    }

    @Test
    public void up2Right1Move() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.player1.color);
        game.board.movePiece(knight,5, 4);
        assertEquals(knight, game.board.boardArr[5][4]);
    }

    @Test
    public void down2Left1Move() {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1.color);
        game.board.movePiece(knight,2, 3);
        assertEquals(knight, game.board.boardArr[2][3]);
    }


    @Test
    public void knightMovementDown1Right2() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.player1.color);
        game.board.movePiece(knight,2, 5);
        assertEquals(knight, game.board.boardArr[2][5]);
    }

    @Test
    public void knightMovementDown1Left2() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.player1.color);
        game.board.movePiece(knight,2, 1);
        assertEquals(knight, game.board.boardArr[2][1]);
    }

    @Test
    public void knightMovementUp1Left2() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.player1.color);
        game.board.movePiece(knight,4, 1);
        assertEquals(knight, game.board.boardArr[4][1]);
    }

    @Test
    public void knightMovementUp1Right2() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.player1.color);
        game.board.movePiece(knight,4, 5);
        assertEquals(knight, game.board.boardArr[4][5]);
    }

    @Test
    public void capture() {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1.color);
        new Pawn(2, 3, game.player2.color, false);

        game.board.movePiece(knight, 2, 3);
        assertEquals(knight, game.board.boardArr[2][3]);
    }
}
