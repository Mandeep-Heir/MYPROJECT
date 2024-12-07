import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class PawnMouseHandler extends MouseAdapter {
    private JLabel[][] board; // The chessboard

    private int selectedX = -1; // selected pawn's row
    private int selectedY = -1; // selected pawn's column
    private boolean isWhiteTurn = true; // Track whose turn it is(true = white, false = black)

    public PawnMouseHandler(JLabel[][] board, boolean isWhiteTurn) {
        this.board = board; // Reference to the main board
        this.isWhiteTurn = isWhiteTurn; // Initialize the current turn
    }

    @Override

    public void mouseClicked(MouseEvent e) {
        int squareSize = e.getComponent().getWidth() / 8; // Size of each square
        int x = e.getY() / squareSize; // row indexx of the click
        int y = e.getX() / squareSize; // column index of the click

        if (selectedX == -1 && selectedY == -1) {
            // First click: Select a piece

            if (isValidSelection(x, y)) {
                selectedX = x;
                selectedY = y;

                System.out.println("Selected pawn at:(" + x + ", " + y + ")");

            } else {
                // Second Click: Attempt to move

                System.out.println("Attempting to move to:(" + x + ", " + y + ")");
                if (isValidPawnMove(selectedX, selectedY, x, y)) {
                    movePawn(selectedX, selectedY, x, y); // Perform the move
                    System.out.println("Move successful!");
                    isWhiteTurn = !isWhiteTurn; // Switch turns
                } else {
                    System.out.println("Invalid move!");
                }

                // Reset selection
                selectedX = -1;
                selectedY = -1;

            }
        }

    }

    // Check if the selected piece is a valid pawn for the current player
    private boolean isValidSelection(int x, int y) {
        if (board[x][y].equals("\u2659") && isWhiteTurn)

            return true; // White Pawn
        if (board[x][y].equals("\u265F") && !isWhiteTurn)
            return true; // Black pawn
        return false; // not a valid selection
    }
    // validate the pawn's movement

    private boolean isValidPawnMove(int startX, int startY, int endX, int endY) {
        boolean isWhite = board[startX][startY].equals("\u2659"); // check if its a white pawn
        int direction = isWhite ? -1 : 1; // white moves up(-1), black moves down(+1)
        // Forward move: One square forward

        if (endY == startY && endX == startX + direction && board[endX][endY].equals("")) {
            return true;
        }

        // First move: two squares forward
        if (endY == startY && endX == startX + 2 * direction && (isWhite && startX == 6 || !isWhite && startX == 1) &&
                board[startX + direction][startY].equals("") && board[endX][endY].equals("")) {
            return true;
        }

        // Capture move: one square diagonally forward

        if (Math.abs(endY - startY) == 1 && endX == startX + direction && !board[endX][endY].equals("")) {
            return true;

        }

        // Invalid move

        return false;
    }

    // move the pawn to the new position

    private void movePawn(int startX, int startY, int endX, int endY) {
        board[endX][endY] = board[startX][startY]; // Move the piece
        board[startX][startY] = ""; // clear the original square

    }

}
