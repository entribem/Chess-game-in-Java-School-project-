package Tests;
import Game.Game;
import Game.Board;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTests {
    Board board = Board.getInstance();
    Game game = Game.getInstance();

    @Test
    public void forwardMove() {
        Piece pawn = new Pawn(3, 3, game.player1);
        board.movePiece(pawn,4, 3);
        assertEquals(pawn, board.boardArr[4][3]);
    }

    @Test
    public void forward2Move() {
        Piece pawn = new Pawn(1, 1, game.player1);
        board.movePiece(pawn,3, 1);
        assertEquals(pawn, board.boardArr[3][1]);
    }

    @Test
    public void capture() {
        Piece pawn = new Pawn(3, 3, game.player1);
        new Pawn(4, 4, game.player2);
        board.movePiece(pawn, 4, 4);
        assertEquals(pawn, board.boardArr[4][4]);
    }
}
