package GUI;
import PGN.PGNUtilities;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chess {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public static void main(String[] args) {
        Chess chess = new Chess();
        //chess.startGame();
        chess.gameLoop();
    }

    /*public void startGame() {
        String[] buttons = new String[]{"Play against human", "Play against computer"};
        int returnValue = JOptionPane.showOptionDialog(null, "Choose a gamemode", "Chess",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);

        if (returnValue == 0) {
            againstHuman = true;
        }
        else if (returnValue == 1){
            againstHuman = false;
        }

        buttons = new String[]{"Yes", "No"};

        returnValue = JOptionPane.showOptionDialog(null,
                "Do you want to place figures by yourself?", "Chess",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);

        if (returnValue == 0) {
            standardBoard = true;
            gameLoop();
        }
        else if (returnValue == 1){
            standardBoard = false;
            gameLoop();
        }
    }*/

    public void gameLoop() {
        BoardGui gui = new BoardGui();
        if (gui.game.player1.goesFirst) {
            gui.currentPlayer = gui.game.player1;
        }
        else if (gui.game.player2.goesFirst) {
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
        int returnValue = JOptionPane.showOptionDialog(null, "CHECKMATE. Play a new game?",
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
            LOGGER.log(Level.INFO, "Saving game to PGN file");
            try {
                PGNUtilities.writeGameToPGNFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
