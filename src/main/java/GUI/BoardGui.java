package GUI;

import PGN.PGNUtilities;
import Pieces.*;
import Game.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static PGN.PGNUtilities.saveMove;
import static javax.swing.SwingUtilities.*;

public class BoardGui {
    Game game;
    Player currentPlayer;
    /**
     * If the turn has ended
     */
    public boolean endTurn = false;
    private final JFrame gameFrame;
    private final Board chessBoard;
    //path to the piece icons
    private static String pieceIconPath = "src/main/java/GUI/Icons/";
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    //the size of the board panel
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    //the size of the square panel
    private final static Dimension SQUARE_PANEL_DIMENSION = new Dimension(10, 10);
    /**
     * Piece, which player wants to move
     */
    public Piece sourceSquare;
    /**
     * Square, where players wants to move his piece
     */
    public Piece destinationSquare;

    /**
     * Waits for the end of other players turn
     */
    public synchronized void waitForInput() {
        while (!endTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Notifies that the player has ended their turn
     */
    public synchronized void notifyInput() {
        endTurn = true;
        notifyAll();
    }

    public BoardGui() {
        this.game = new Game();
        this.chessBoard = game.board;
        gameFrame = new JFrame("Chess");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 600);
        gameFrame.setLayout(new BorderLayout());
        final JMenuBar menuBar = new JMenuBar();
        fillMenu(menuBar);
        this.gameFrame.setJMenuBar(menuBar);
        BoardPanel panel = new BoardPanel();
        gameFrame.add(panel, BorderLayout.CENTER);
        gameFrame.setVisible(true);
    }

    private void fillMenu(final JMenuBar menuBar) {
        menuBar.add(createMenu());
    }

    private JMenu createMenu() {
        final JMenu fileMenu = new JMenu("Game");
        final JMenuItem resign = new JMenuItem("Resign");
        resign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer.hasLost = true;
                System.out.println(currentPlayer + " has lost!");
                notifyInput();
            }
        });
        fileMenu.add(resign);
        return fileMenu;
    }


    public class BoardPanel extends JPanel {
        final List<SquarePanel> boardSquares;

        /**
         * Represents board
         */
        public BoardPanel() {
            //draws board
            setLayout(new GridLayout(chessBoard.height, chessBoard.width));
            boardSquares = new ArrayList<>();
            //fill the board with squares
            for (int i = 0; i < chessBoard.height * chessBoard.width; ++i) {
                final SquarePanel squarePanel = new SquarePanel(i, this);
                boardSquares.add(squarePanel);
                add(squarePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        /**
         * Redraws the updated board
         *
         * @param board Board to be redrawn
         */
        public void redrawBoard(Board board) {
            removeAll();
            for (SquarePanel squarePanel : boardSquares) {
                squarePanel.drawSquare(board);
                add(squarePanel);
            }
            repaint();
            validate();
        }
    }

    /**
     * Represents squares on the board
     */
    public class SquarePanel extends JPanel {
        /**
         * Coordinate which represents the square (0-63)
         */
        public final int squareNum;

        public SquarePanel(final int squareNum, final BoardPanel boardPanel) {
            setLayout(new GridBagLayout());

            this.squareNum = squareNum;
            setPreferredSize(SQUARE_PANEL_DIMENSION);
            paintSquare();
            placeFigure(chessBoard);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!currentPlayer.isComputer) {
                        if (isLeftMouseButton(e)) {
                            if (sourceSquare == null) {
                                //chooses the piece which player wants to move
                                sourceSquare = chessBoard.getSquare(squareNum);
                                //checks if the player tries to move from empty square
                                if (sourceSquare == null) {
                                    LOGGER.log(Level.WARNING, "Empty square, choose another");
                                } else {
                                    //checks if the player tries to move other players pieces
                                    if (sourceSquare.player.color != currentPlayer.color) {
                                        sourceSquare = null;
                                        LOGGER.log(Level.WARNING, "Invalid move, can not move other players pieces");
                                    }
                                    LOGGER.log(Level.INFO, "Source square = " + sourceSquare);
                                }
                            } else {
                                //chooses where player wants to move their piece
                                destinationSquare = chessBoard.getSquare(squareNum);
                                LOGGER.log(Level.INFO, "Destination square = " + destinationSquare);
                                int row = squareNum / 8;
                                int col = squareNum - (row * 8);
                                int pieceY = sourceSquare.pieceY;
                                //if the destination square is not the same as a source square
                                if (!chessBoard.isOriginSquare(sourceSquare, row, col)) {
                                    //tries to move the piece
                                    chessBoard.movePiece(sourceSquare, row, col);
                                    //if the move has been made, notifies the other player and saves the move
                                    if (chessBoard.moveSuccessful(sourceSquare, row, col) ||
                                        chessBoard.game.player1.hasLost ||
                                        chessBoard.game.player2.hasLost) {
                                        endTurn = true;
                                        notifyInput();
                                        saveMove(destinationSquare, sourceSquare, pieceY, chessBoard);
                                    }
                                }

                                //tries to promote the pawn if possible
                                if (sourceSquare.getPieceType() == Type.PAWN) {
                                    Pawn.promotePawn(sourceSquare);
                                }
                                sourceSquare = null;
                                destinationSquare = null;
                            }
                        }
                        //cancel the choice of the piece by pressing right mouse button
                        else if (isRightMouseButton(e)) {
                            sourceSquare = null;
                            destinationSquare = null;
                        }
                        //updates the board with the new move
                        boardPanel.redrawBoard(chessBoard);
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            validate();
        }

        /**
         * Loads an image of the piece and puts it on a square
         *
         * @param board Board
         */
        public void placeFigure(Board board) {
            try {
                Piece piece;
                if ((piece = board.getSquare(this.squareNum)) != null) {
                    //read and set icons of the pieces
                    String imgPath = pieceIconPath + piece.player.color + "_"
                            + piece.getPieceType() + ".png";
                    BufferedImage icon = ImageIO.read(new File(imgPath));
                    add(new JLabel(new ImageIcon(icon)));
                }
            } catch(IOException e){
                    e.printStackTrace();
            }
        }

        /**
         * Paints the square light or dark
         */
        public void paintSquare() {
            this.removeAll();
            if ((this.squareNum / 8) % 2 == 0) {
                if ((this.squareNum % 2) == 0) {
                    setBackground(Color.lightGray);
                } else {
                    setBackground(Color.darkGray);
                }
            } else {
                if ((this.squareNum % 2) == 0) {
                    setBackground(Color.darkGray);
                } else {
                    setBackground(Color.lightGray);
                }
            }
        }

        /**
         * Paints the square and puts an image of the piece
         *
         * @param board Board
         */
        public void drawSquare(final Board board) {
            paintSquare();
            placeFigure(board);
            repaint();
            validate();
        }
    }
}
