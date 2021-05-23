package Tests;
import Game.Game;
import Game.Board;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTests {
    Board board = Board.getInstance();
    Game game = Game.getInstance();

    @Test
    public void up2Left1Move() {
        Piece knight = new Knight(3, 3, game.player1);
        board.movePiece(knight,5, 2);
        assertEquals(knight, board.boardArr[5][2]);
    }

    @Test
    public void down2Right1Move() {
        Piece knight = new Knight(4, 4, game.player1);
        board.movePiece(knight,2, 5);
        assertEquals(knight, board.boardArr[2][5]);
    }

    @Test
    public void up2Right1Move() {
        Piece knight = new Knight(3, 3, game.player1);
        board.movePiece(knight,5, 4);
        assertEquals(knight, board.boardArr[5][4]);
    }

    @Test
    public void down2Left1Move() {
        Piece knight = new Knight(4, 4, game.player1);
        board.movePiece(knight,2, 3);
        assertEquals(knight, board.boardArr[2][3]);
    }


    @Test
    public void knightMovementDown1Right2() {
        Piece knight = new Knight(3, 3, game.player1);
        board.movePiece(knight,2, 5);
        assertEquals(knight, board.boardArr[2][5]);
    }

    @Test
    public void knightMovementDown1Left2() {
        Piece knight = new Knight(3, 3, game.player1);
        board.movePiece(knight,2, 1);
        assertEquals(knight, board.boardArr[2][1]);
    }

    @Test
    public void knightMovementUp1Left2() {
        Piece knight = new Knight(3, 3, game.player1);
        board.movePiece(knight,4, 1);
        assertEquals(knight, board.boardArr[4][1]);
    }

    @Test
    public void knightMovementUp1Right2() {
        Piece knight = new Knight(3, 3, game.player1);
        board.movePiece(knight,4, 5);
        assertEquals(knight, board.boardArr[4][5]);
    }

    @Test
    public void capture() {
        Piece knight = new Knight(4, 4, game.player1);
        new Pawn(2, 3, game.player2);
        board.movePiece(knight, 2, 3);
        assertEquals(knight, board.boardArr[2][3]);
    }
}
