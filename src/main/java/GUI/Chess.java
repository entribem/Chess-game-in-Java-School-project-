package GUI;
import PGN.PGNUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
//import static PGN.PGNUtilities.writeGameToPGNFile;

public class Chess {
    boolean againstHuman;
    boolean standardBoard;
    boolean beginGame;

    public static void main(String args[]) {
        Chess chess = new Chess();
        //chess.startGame();
        chess.gameLoop();
    }

    public void startGame() {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        JButton button = new JButton("Play against another player");
        JButton button1 = new JButton("Play against computer");
        frame.add(button, BorderLayout.LINE_START);
        frame.add(button1, BorderLayout.LINE_END);
        //frame.getContentPane().add(button, BorderLayout.LINE_START);
        //frame.getContentPane().add(button1, BorderLayout.LINE_END);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                againstHuman = true;
                frame.dispose();
                createFrame();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                againstHuman = false;
                frame.dispose();
                createFrame();
            }
        });
        frame.pack();
        frame.setVisible(true);

    }

    public void gameLoop() {
        /*JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        //frame.setLayout(new BorderLayout());
        JButton button = new JButton("Play against another player");
        JButton button1 = new JButton("Play against computer");
        //JButton button2 = new JButton("Play standard game");
        //JButton button3 = new JButton("Place figures on the board");

        frame.getContentPane().add(button, BorderLayout.LINE_START);
        frame.getContentPane().add(button1, BorderLayout.LINE_END);
        frame.pack();
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                againstHuman = true;
                frame.dispose();
                createFrame();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                againstHuman = false;
                frame.dispose();
                createFrame();
            }
        });

        /*JFrame frame1 = new JFrame("Chess");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1000, 1000);
        frame1.setLayout(new BorderLayout());
        frame1.getContentPane().add(button, BorderLayout.LINE_START);
        frame1.getContentPane().add(button1, BorderLayout.LINE_END);
        frame1.pack();

        frame1.setVisible(true);*/


        //if (beginGame) {

            BoardGui gui = new BoardGui();
            // Game game = new Game();
            //gui.game = new Game();

            if (gui.game.player1.goesFirst) {
                gui.currentPlayer = gui.game.player1;
            } else if (gui.game.player2.goesFirst) {
                gui.currentPlayer = gui.game.player2;
            }
            gui.currentPlayer.isTurn = true;

            while (true) {
                if (gui.game.player1.hasLost || gui.game.player2.hasLost) {
                    break;
                }
                System.out.println(gui.currentPlayer.color + " Turn");
               // System.out.println("TURN = " + gui.game.turn);
                gui.waitForInput();
                ++gui.game.turn;

                //System.out.println(gui.game.player1.hasLost + " " + gui.game.player2.hasLost);
                gui.currentPlayer.isTurn = false;
                if (gui.currentPlayer == gui.game.player1) {
                    gui.currentPlayer = gui.game.player2;
                } else {
                    gui.currentPlayer = gui.game.player1;
                }
                gui.endTurn = false;
            }
            String[] buttons = new String[]{"Yes", "No", "Save game"};
            int returnValue = JOptionPane.showOptionDialog(null, "CHECKMATE. Play a new game?", "GAME OVER",
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
            if (returnValue == 1) {
                System.exit(0);
            }
            else if (returnValue == 0){
                gameLoop();
            }
            else {
                System.out.println("SAVING GAME");
                try {
                    PGNUtilities.writeGameToPGNFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

   // }

    /*public static void savePGNFile(File pgnFile) {
        try {
            writeGameToPGNFile(pgnFile, Table.get().getMoveLog());
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }*/

    public void createFrame() {
        JFrame frame1 = new JFrame("Chess");
        JButton button2 = new JButton("Play standard game");
        JButton button3 = new JButton("Place figures on the board");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1000, 1000);
        frame1.setLayout(new BorderLayout());
        frame1.getContentPane().add(button2, BorderLayout.LINE_START);
        frame1.getContentPane().add(button3, BorderLayout.LINE_END);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                standardBoard = true;
                frame1.dispose();
                gameLoop();
                //BoardGui gui = new BoardGui();

                //gameLoop(gui);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                standardBoard = false;
                frame1.dispose();
                //gameLoop();
            }
        });
        frame1.pack();
        frame1.setVisible(true);
    }

}
