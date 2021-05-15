package GUI;

import Game.*;

import javax.swing.*;

public class Chess {

    public void gameLoop() {
        BoardGui gui = new BoardGui();
       // Game game = new Game();
        //gui.game = new Game();

        if (gui.game.player1.goesFirst) {
            gui.currentPlayer = gui.game.player1;
        }
        else if (gui.game.player2.goesFirst) {
            gui.currentPlayer = gui.game.player2;
        }
        gui.currentPlayer.isTurn = true;

        while (!gui.game.player1.hasLost || !gui.game.player2.hasLost) {
            if (gui.game.player1.hasLost || gui.game.player2.hasLost) {
                break;
            }
            System.out.println(gui.currentPlayer.color + " Turn");
            gui.waitForInput();
            System.out.println(gui.game.player1.hasLost + " " + gui.game.player2.hasLost);
            gui.currentPlayer.isTurn = false;
            if (gui.currentPlayer == gui.game.player1) {
                gui.currentPlayer = gui.game.player2;
            }
            else {
                gui.currentPlayer = gui.game.player1;
            }
            gui.endTurn = false;
        }
        String[] buttons = new String[]{"Yes", "No"};
        int returnValue = JOptionPane.showOptionDialog(null, "CHECKMATE. Play a new game?", "GAME OVER",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
        if(returnValue == 1)
            System.exit(0);

    }

}
