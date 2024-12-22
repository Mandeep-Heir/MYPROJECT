import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chess {

    private static JPanel[][] squares = new JPanel[8][8]; // Store the squares for interaction
    private static JLabel[][] pieces = new JLabel[8][8]; // Track pieces on the board
    private static int selectedRow = -1; // Selected row index
    private static int selectedCol = -1; // Selected column index

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

        // Load the chess piece images
        final ImageIcon WhitePawn = new ImageIcon("Resources/WP.png");
        final ImageIcon BlackPawn = new ImageIcon("Resources/BP.png");
        final ImageIcon WhiteRook = new ImageIcon("Resources/WR.png");
        final ImageIcon BlackRook = new ImageIcon("Resources/BR.png");
        final ImageIcon WhiteQueen = new ImageIcon("Resources/WQ.png");
        final ImageIcon BlackQueen = new ImageIcon("Resources/BQ.png");
        final ImageIcon WhiteKnight = new ImageIcon("Resources/WKN.png");
        final ImageIcon BlackKnight = new ImageIcon("Resources/BKN.png");
        final ImageIcon WhiteBishop = new ImageIcon("Resources/WB.png");
        final ImageIcon BlackBishop = new ImageIcon("Resources/BB.png");
        final ImageIcon WhiteKing = new ImageIcon("Resources/WK.png");
        final ImageIcon BlackKing = new ImageIcon("Resources/BK.png");

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
                if (row == 1) { // Black Pawns
                    pieceLabel.setIcon(BlackPawn);
                } else if (row == 6) { // White Pawns
                    pieceLabel.setIcon(WhitePawn);
                } else if (row == 0 && (col == 0 || col == 7)) { // Black Rooks
                    pieceLabel.setIcon(BlackRook);
                } else if (row == 7 && (col == 0 || col == 7)) { // White Rooks
                    pieceLabel.setIcon(WhiteRook);
                } else if (row == 0 && (col == 1 || col == 6)) { // Black Knights
                    pieceLabel.setIcon(BlackKnight);
                } else if (row == 7 && (col == 1 || col == 6)) { // White Knights
                    pieceLabel.setIcon(WhiteKnight);
                } else if (row == 0 && (col == 2 || col == 5)) { // Black Bishops
                    pieceLabel.setIcon(BlackBishop);
                } else if (row == 7 && (col == 2 || col == 5)) { // White Bishops
                    pieceLabel.setIcon(WhiteBishop);
                } else if (row == 0 && col == 3) { // Black Queen
                    pieceLabel.setIcon(BlackQueen);
                } else if (row == 7 && col == 3) { // White Queen
                    pieceLabel.setIcon(WhiteQueen);
                } else if (row == 0 && col == 4) { // Black King
                    pieceLabel.setIcon(BlackKing);
                } else if (row == 7 && col == 4) { // White King
                    pieceLabel.setIcon(WhiteKing);
                }

                // Store the square and piece for interaction
                squares[row][col] = square;
                pieces[row][col] = pieceLabel;
                square.add(pieceLabel);

                // Add a mouse listener for interaction
                final int currentRow = row;
                final int currentCol = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(currentRow, currentCol);
                    }
                });

                // Add the square to the chessboard
                panel.add(square);
            }
        }

        frame.add(panel, BorderLayout.CENTER);
    }

    // Handle clicks for moving pawns
    private static void handleSquareClick(int row, int col) {
        if (selectedRow == -1 && selectedCol == -1) {
            // Select the piece
            if (pieces[row][col].getIcon() != null) {
                selectedRow = row;
                selectedCol = col;
                squares[row][col].setBackground(Color.YELLOW); // Highlight selected square
            }
        } else {
            // Attempt to move the selected piece
            if (isValidPawnMove(selectedRow, selectedCol, row, col)) {
                // Move the piece
                pieces[row][col].setIcon(pieces[selectedRow][selectedCol].getIcon());
                pieces[selectedRow][selectedCol].setIcon(null);

                // Reset square colors
                resetSquareColors();

                // Deselect
                selectedRow = -1;
                selectedCol = -1;
            } else {
                // Invalid move, deselect
                resetSquareColors();
                selectedRow = -1;
                selectedCol = -1;
            }
        }
    }

    // Validate pawn movement
    private static boolean isValidPawnMove(int oldRow, int oldCol, int newRow, int newCol) {
        if (pieces[oldRow][oldCol].getIcon() == null)
            return false; // No piece selected

        ImageIcon pieceIcon = (ImageIcon) pieces[oldRow][oldCol].getIcon();
        if (pieceIcon.getDescription().contains("WP")) {
            // White pawn movement logic
            return newRow == oldRow - 1 && oldCol == newCol && pieces[newRow][newCol].getIcon() == null;
        } else if (pieceIcon.getDescription().contains("BP")) {
            // Black pawn movement logic
            return newRow == oldRow + 1 && oldCol == newCol && pieces[newRow][newCol].getIcon() == null;
        }
        return false;
    }

    // Reset square colors
    private static void resetSquareColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE);
                } else {
                    squares[row][col].setBackground(Color.GRAY);
                }
            }
        }
    }
}
