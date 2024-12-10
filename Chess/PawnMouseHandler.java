import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class PawnMouseHandler extends MouseAdapter {
  private JLabel[][] tiles;
  private boolean isWhiteTurn;
  private Point selectedTile = null;

  public PawnMouseHandler(JLabel[][] tiles, boolean isWhiteTurn) {
    this.tiles = tiles;
    this.isWhiteTurn = isWhiteTurn;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    JLabel clickedLabel = (JLabel) e.getSource();
    int row = -1, col = -1;

    // Find which tile was clicked
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (tiles[i][j] == clickedLabel) {
          row = i;
          col = j;
          break;
        }
      }
    }

    if (row == -1 || col == -1) {
      System.out.println("Error: Clicked outside the valid board!");
      return;
    }

    System.out.println("Clicked on tile: (" + row + ", " + col + ")");

    if (selectedTile == null) {
      // First click: Select a piece
      if (isPawn(row, col)) {
        selectedTile = new Point(row, col);
        System.out.println("Selected pawn at: (" + row + ", " + col + ")");
      } else {
        System.out.println("No pawn selected. Try again.");
      }
    } else {
      // Second click: Attempt to move the piece
      int startRow = selectedTile.x;
      int startCol = selectedTile.y;

      if (isValidPawnMove(startRow, startCol, row, col)) {
        movePawn(startRow, startCol, row, col);
        selectedTile = null; // Reset selection
        isWhiteTurn = !isWhiteTurn; // Switch turns
        System.out.println("Move successful!");
      } else {
        System.out.println("Invalid move! Try again.");
        selectedTile = null; // Reset selection
      }
    }
  }

  private boolean isPawn(int row, int col) {
    String piece = tiles[row][col].getText();
    System.out.println("Checking if tile (" + row + ", " + col + ") contains a pawn. Found: " + piece);
    return isWhiteTurn ? "\u2659".equals(piece) : "\u265F".equals(piece);
  }

  private boolean isValidPawnMove(int startRow, int startCol, int endRow, int endCol) {
    int direction = isWhiteTurn ? -1 : 1;

    // Forward move (1 square)
    if (endRow == startRow + direction && endCol == startCol && tiles[endRow][endCol].getText().isEmpty()) {
      return true;
    }

    // Forward move (2 squares on first move)
    if ((isWhiteTurn && startRow == 6 || !isWhiteTurn && startRow == 1) &&
        endRow == startRow + 2 * direction && endCol == startCol &&
        tiles[startRow + direction][startCol].getText().isEmpty() && tiles[endRow][endCol].getText().isEmpty()) {
      return true;
    }

    // Capture move (diagonal)
    if (endRow == startRow + direction && Math.abs(endCol - startCol) == 1 &&
        !tiles[endRow][endCol].getText().isEmpty() &&
        isOpponentPiece(tiles[endRow][endCol].getText())) {
      return true;
    }

    return false; // Invalid move
  }

  private boolean isOpponentPiece(String piece) {
    return isWhiteTurn ? "\u265F".equals(piece) : "\u2659".equals(piece);
  }

  private void movePawn(int startRow, int startCol, int endRow, int endCol) {
    System.out.println("Moving pawn from (" + startRow + ", " + startCol + ") to (" + endRow + ", " + endCol + ")");
    tiles[endRow][endCol].setText(tiles[startRow][startCol].getText());
    tiles[startRow][startCol].setText("");
    System.out.println("Pawn moved successfully.");
  }
}
