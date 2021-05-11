package GUI;

import Pieces.*;
import Game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.*;

public class BoardGui {
    Game game;
    private final JFrame gameFrame;
    private final Board chessBoard;
    private static String pieceIconPath = "src/main/java/GUI/Icons/";
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension SQUARE_PANEL_DIMENSION = new Dimension(10, 10);
    private Piece sourceSquare;
    private Piece destinationSquare;
    private Piece movedPiece;




    public BoardGui() {
        this.game = new Game();
        this.chessBoard = game.board;
        gameFrame = new JFrame("Chess");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 600);
        gameFrame.setLayout(new BorderLayout());
        BoardPanel panel = new BoardPanel();
        gameFrame.add(panel, BorderLayout.CENTER);
        gameFrame.setVisible(true);
    }

    public class BoardPanel extends JPanel {
        final List<SquarePanel> boardSquares;

        public BoardPanel() {
            setLayout(new GridLayout(chessBoard.height, chessBoard.width));
            boardSquares = new ArrayList<>();
            for (int i = 0; i < chessBoard.height * chessBoard.width; ++i) {
                final SquarePanel squarePanel = new SquarePanel(i, this);
                boardSquares.add(squarePanel);
                add(squarePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();

        }

        public void drawBoard(Board board) {
            removeAll();
            for (SquarePanel squarePanel : boardSquares) {
                squarePanel.drawSquare(board);
                add(squarePanel);
            }
            repaint();
            validate();


    }

    private class SquarePanel extends JPanel {
        private final int squareNum;

        public SquarePanel(final int squareNum, final BoardPanel boardPanel) {
            setLayout(new GridBagLayout());

            this.squareNum = squareNum;
            setPreferredSize(SQUARE_PANEL_DIMENSION);
            paintSquare();
            placeFigure(chessBoard);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isLeftMouseButton(e)) {
                        if (sourceSquare == null) {
                            sourceSquare = chessBoard.getSquare(squareNum);
                            movedPiece = sourceSquare;
                            System.out.println("square num = " + squareNum);
                            System.out.println("source square = " + sourceSquare);
                            //System.out.println("square num = " + squareNum);
                            if (movedPiece == null) {
                                sourceSquare = null;
                            }
                        }
                        else {
                            destinationSquare = chessBoard.getSquare(squareNum);
                            System.out.println("square num = " + squareNum);
                            //System.out.println("source square = " + sourceSquare);
                            //System.out.println("Color = " + sourceSquare.pieceColor);
                            System.out.println("destination square = " + destinationSquare);
                            int row = squareNum / 8;
                            int col = squareNum - (row * 8);
                            chessBoard.movePiece(sourceSquare, row, col);
                            System.out.println(chessBoard.boardArr[row][col]);

                            sourceSquare = null;
                            destinationSquare = null;
                            movedPiece = null;
                        }
                    }
                    else if (isRightMouseButton(e)) {
                        sourceSquare = null;
                        destinationSquare = null;
                        movedPiece = null;
                    }
                    invokeLater(() -> {
                        boardPanel.drawBoard(chessBoard);

                    });

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

        private void placeFigure(Board board) {
            try {
                Piece piece;
                if ((piece = board.getSquare(this.squareNum)) != null) {
                    Image icon = ImageIO.read(new File(pieceIconPath + piece.getPieceColor()
                            + piece.getPieceType() + ".png"));
                    add(new JLabel(new ImageIcon(icon)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void paintSquare() {
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

        public void drawSquare(final Board board) {
            paintSquare();
            placeFigure(board);
            repaint();
            validate();
        }
    }
    }


    public static void main(String args[]) {
        Chess chess = new Chess();
        chess.gameLoop();
    }
}
