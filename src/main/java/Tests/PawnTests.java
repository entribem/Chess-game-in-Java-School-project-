package Tests;
import Game.Game;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTests {
    @Test
    public void forwardMove() {
        Game game = Game.getInstance();
        Piece pawn = new Pawn(3, 3, game.player1);
        game.board.movePiece(pawn,4, 3);
        assertEquals(pawn, game.board.boardArr[4][3]);
    }

    @Test
    public void forward2Move() {
        Game game = Game.getInstance();
        Piece pawn = new Pawn(1, 1, game.player1);
        game.board.movePiece(pawn,3, 1);
        assertEquals(pawn, game.board.boardArr[3][1]);
    }

    @Test
    public void capture() {
        Game game = Game.getInstance();
        Piece pawn = new Pawn(3, 3, game.player1);
        new Pawn(4, 4, game.player2);

        game.board.movePiece(pawn, 4, 4);
        assertEquals(pawn, game.board.boardArr[4][4]);
    }
}
