package GUI;
import Game.Color;
import PGN.PGNUtilities;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chess {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private BoardGui gui;
    public static void main(String[] args) {
        Chess chess = new Chess();
        chess.gameLoop();
    }

    private void gameLoop() {
        gui = new BoardGui();
        if (gui.game.player1.color == Color.WHITE) {
            gui.currentPlayer = gui.game.player1;
        }
        else {
            gui.currentPlayer = gui.game.player2;
        }
        gui.currentPlayer.isTurn = true;

        while (true) {
            if (gui.game.player1.hasLost || gui.game.player2.hasLost) {
                break;
            }
            LOGGER.log(Level.INFO, gui.currentPlayer.color + " turn");
            //waits for the other player to finish his move
            gui.waitForInput();
            gui.currentPlayer.isTurn = false;
            if (gui.currentPlayer == gui.game.player1) {
                gui.currentPlayer = gui.game.player2;
            } else {
                gui.currentPlayer = gui.game.player1;
            }
            ++gui.game.turn;
            gui.endTurn = false;
        }
        String[] buttons = new String[]{"Yes", "No", "Save game"};
        int returnValue = JOptionPane.showOptionDialog(null, "Game over. Play a new game?",
                "GAME OVER",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
        if (returnValue == 1) {
            System.exit(0);
        }
        //restarts the game
        else if (returnValue == 0){
            gameLoop();
        }
        //saves match to pgn file
        else {
            try {
                PGNUtilities.writeGameToPGNFile();
                LOGGER.log(Level.INFO, "Successfully saved the  game to PGN file");
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Could not save the game to PGN file");
                e.printStackTrace();
            }
        }
    }
}
