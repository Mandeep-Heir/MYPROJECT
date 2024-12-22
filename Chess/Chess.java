import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chess {

    private static JLabel selectedPiece = null; // to store the selected piece
    private static JPanel[][] squares = new JPanel[8][8]; // to keep track of all squares on the board
    private static int selectedRow = -1;
    private static int selectedCol = -1;

    public static void main(final String[] args) {
        final JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));

        // Colors
        final Color lightColor = Color.WHITE;
        final Color darkColor = Color.GRAY;

        // load the chess piece images

        final ImageIcon WhitePawn = new ImageIcon("Resources/WP.png");
        final ImageIcon WhiteKing = new ImageIcon("Resources/WK.png");
        final ImageIcon BlackPawn = new ImageIcon("Resources/BP.png");
        final ImageIcon BlackKing = new ImageIcon("Resources/BK.png");
        final ImageIcon WhiteRook = new ImageIcon("Resources/WR.png");
        final ImageIcon BlackRook = new ImageIcon("Resources/BR.png");
        final ImageIcon WhiteQueen = new ImageIcon("Resources/WQ.png");
        final ImageIcon BlackQueen = new ImageIcon("Resources/BQ.png");
        final ImageIcon WhiteKnight = new ImageIcon("Resources/WKN.png");
        final ImageIcon BlackKnight = new ImageIcon("Resources/BKN.png");
        final ImageIcon WhiteBishop = new ImageIcon("Resources/WB.png");
        final ImageIcon BlackBishop = new ImageIcon("Resources/BB.png");

        // Add squares and pieces based on index

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                final JPanel square = new JPanel(new BorderLayout());

                // Alternate square colors

                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }

                // Add pieces based on their positions

                final JLabel pieceLabel = new JLabel();
                if (row == 1) { // BlackPawns
                    pieceLabel.setIcon(BlackPawn);
                } else if (row == 6) { // White Pawns
                    pieceLabel.setIcon(WhitePawn);
                } else if (row == 0 && (col == 0 || col == 7)) { // Black rooks
                    pieceLabel.setIcon(BlackRook);
                } else if (row == 7 && (col == 0 || col == 7)) { // White rooks
                    pieceLabel.setIcon(WhiteRook);
                } else if (row == 0 && (col == 1 || col == 6)) { // Black knights
                    pieceLabel.setIcon(BlackKnight);
                } else if (row == 7 && (col == 1 || col == 6)) { // White knights
                    pieceLabel.setIcon(WhiteKnight);
                } else if (row == 0 && (col == 2 || col == 5)) { // Black bishops
                    pieceLabel.setIcon(BlackBishop);
                } else if (row == 7 && (col == 2 || col == 5)) { // White bishops
                    pieceLabel.setIcon(WhiteBishop);
                } else if (row == 0 && col == 3) { // Black queen
                    pieceLabel.setIcon(BlackQueen);
                } else if (row == 7 && col == 3) { // White queen
                    pieceLabel.setIcon(WhiteQueen);
                } else if (row == 0 && col == 4) { // Black king
                    pieceLabel.setIcon(BlackKing);
                } else if (row == 7 && col == 4) { // White king
                    pieceLabel.setIcon(WhiteKing);
                }

                // Add the piece label to the square

                square.add(pieceLabel);
                // Add mouse listener for the movemnet
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleSquareClick(square,  row, col);
                    }

                   
                });

                // Add the square to the chessboard

                panel.add(square);
            }

        }

        frame.add(panel, BorderLayout.CENTER);
    }

    private static void handleSquareClick(final JPanel square, final int row, final int col) {
        if (selectedPiece == null) {
            // First click: Select the piece
            if (square.getComponentCount() > 0) {
                selectedPiece = (JLabel) square.getComponent(0); // Get the piece
                selectedRow = row;
                selectedCol = col;
                square.remove(selectedPiece); // Temporarily remove it
                square.revalidate();
                square.repaint();
                System.out.println("Piece selected at: " + row + ", " + col);
            }
        } else {
            // Second click: Move the piece
            square.add(selectedPiece); // Add the piece to the target square
            selectedPiece = null; // Clear the selection
            selectedRow = -1;
            selectedCol = -1;
            square.revalidate();
            square.repaint();
            System.out.println("Piece moved to: " + row + ", " + col);

        }

    }
}
