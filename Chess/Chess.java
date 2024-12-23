import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chess {

    private static JPanel[][] squares = new JPanel[8][8]; // Chessboard squares
    private static JLabel[][] pieces = new JLabel[8][8]; // Track pieces on the board
    private static int selectedRow = -1; // Selected piece row index
    private static int selectedCol = -1; // Selected piece column index

    public static void main(final String[] args) {
        final JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));

        // Colors for the chessboard squares
        final Color lightColor = Color.WHITE;
        final Color darkColor = Color.GRAY;

        // Load chess piece images
        final ImageIcon WhitePawn = new ImageIcon("Resources/WP.png");
        final ImageIcon BlackPawn = new ImageIcon("Resources/BP.png");
        final ImageIcon WhiteRook = new ImageIcon("Resources/WR.png");
        final ImageIcon BlackRook = new ImageIcon("Resources/BR.png");
        final ImageIcon WhiteQueen = new ImageIcon("Resources/WQ.png");
        final ImageIcon BlackQueen = new ImageIcon("Resources/BQ.png");
        final ImageIcon WhiteBishop = new ImageIcon("Resources/WB.png");
        final ImageIcon BlackBishop = new ImageIcon("Resources/BB.png");

        // Initialize chessboard
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                final JPanel square = new JPanel(new BorderLayout());

                // Alternate square colors
                square.setBackground((row + col) % 2 == 0 ? lightColor : darkColor);

                // Add pieces based on positions
                final JLabel pieceLabel = new JLabel();
                if (row == 1) { // Black Pawns
                    pieceLabel.setIcon(BlackPawn);
                } else if (row == 6) { // White Pawns
                    pieceLabel.setIcon(WhitePawn);
                } else if (row == 0 && (col == 0 || col == 7)) { // Black Rooks
                    pieceLabel.setIcon(BlackRook);
                } else if (row == 7 && (col == 0 || col == 7)) { // White Rooks
                    pieceLabel.setIcon(WhiteRook);
                } else if (row == 0 && col == 3) { // Black Queen
                    pieceLabel.setIcon(BlackQueen);
                } else if (row == 7 && col == 3) { // White Queen
                    pieceLabel.setIcon(WhiteQueen);
                } else if (row == 0 && (col == 2 || col == 5)) { // Black Bishops
                    pieceLabel.setIcon(BlackBishop);
                } else if (row == 7 && (col == 2 || col == 5)) { // White Bishops
                    pieceLabel.setIcon(WhiteBishop);
                }

                // Store the square and piece
                squares[row][col] = square;
                pieces[row][col] = pieceLabel;
                square.add(pieceLabel);

                // Add mouse listener for interaction
                final int currentRow = row;
                final int currentCol = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(currentRow, currentCol);
                    }
                });

                panel.add(square);
            }
        }

        frame.add(panel, BorderLayout.CENTER);
    }

    // Handle square clicks
    private static void handleSquareClick(int row, int col) {
        if (selectedRow == -1 && selectedCol == -1) {
            // Select piece
            if (pieces[row][col].getIcon() != null) {
                selectedRow = row;
                selectedCol = col;
                squares[row][col].setBackground(Color.YELLOW); // Highlight selected square
                highlightLegalMoves(row, col);
            }
        } else {
            // Move piece if valid
            if (isValidMove(selectedRow, selectedCol, row, col)) {
                pieces[row][col].setIcon(pieces[selectedRow][selectedCol].getIcon());
                pieces[selectedRow][selectedCol].setIcon(null);
            }

            resetSquareColors(); // Reset colors
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    // Validate moves
    private static boolean isValidMove(int oldRow, int oldCol, int newRow, int newCol) {
        if (pieces[oldRow][oldCol].getIcon() == null)
            return false;

        ImageIcon pieceIcon = (ImageIcon) pieces[oldRow][oldCol].getIcon();
        String pieceDescription = pieceIcon.getDescription();

        if (pieceDescription.contains("WR") || pieceDescription.contains("BR")) {
            return isValidRookMove(oldRow, oldCol, newRow, newCol);
        } else if (pieceDescription.contains("WB") || pieceDescription.contains("BB")) {
            return isValidBishopMove(oldRow, oldCol, newRow, newCol);
        } else if (pieceDescription.contains("WQ") || pieceDescription.contains("BQ")) {
            return isValidQueenMove(oldRow, oldCol, newRow, newCol);
        }

        return false; // Default to invalid move
    }

    // Rook movement logic
    private static boolean isValidRookMove(int oldRow, int oldCol, int newRow, int newCol) {
        if (oldRow == newRow) {
            int start = Math.min(oldCol, newCol);
            int end = Math.max(oldCol, newCol);
            for (int i = start + 1; i < end; i++) {
                if (pieces[oldRow][i].getIcon() != null)
                    return false; // Blocked
            }
            return true;
        } else if (oldCol == newCol) {
            int start = Math.min(oldRow, newRow);
            int end = Math.max(oldRow, newRow);
            for (int i = start + 1; i < end; i++) {
                if (pieces[i][oldCol].getIcon() != null)
                    return false; // Blocked
            }
            return true;
        }
        return false;
    }

    // Bishop movement logic
    private static boolean isValidBishopMove(int oldRow, int oldCol, int newRow, int newCol) {
        if (Math.abs(newRow - oldRow) == Math.abs(newCol - oldCol)) {
            int rowDirection = (newRow - oldRow) / Math.abs(newRow - oldRow);
            int colDirection = (newCol - oldCol) / Math.abs(newCol - oldCol);
            for (int i = 1; i < Math.abs(newRow - oldRow); i++) {
                if (pieces[oldRow + i * rowDirection][oldCol + i * colDirection].getIcon() != null)
                    return false; // Blocked
            }
            return true;
        }
        return false;
    }

    // Queen movement logic
    private static boolean isValidQueenMove(int oldRow, int oldCol, int newRow, int newCol) {
        return isValidRookMove(oldRow, oldCol, newRow, newCol) || isValidBishopMove(oldRow, oldCol, newRow, newCol);
    }

    // Highlight legal moves
    private static void highlightLegalMoves(int row, int col) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (isValidMove(row, col, r, c)) {
                    squares[r][c].setBackground(Color.GREEN);
                }
            }
        }
    }

    // Reset square colors
    private static void resetSquareColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
            }
        }
    }
}
