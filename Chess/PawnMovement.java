public class PawnMovement {
    private boolean[][] pawns; // Board state
    private boolean isWhite; // Flag for white or black pawns

    public PawnMovement(boolean[][] pawns, boolean isWhite) {
        this.pawns = pawns;
        this.isWhite = isWhite;
    }

    // Validate the pawn's move
    public boolean isValidMove(int oldRow, int oldCol, int newRow, int newCol) {
        int direction = isWhite ? 1 : -1; // White moves down, Black moves up

        // Standard forward move
        if (oldCol == newCol) {
            // Single square forward
            if (newRow == oldRow + direction && !pawns[newRow][newCol]) {
                return true;
            }
            // Two squares forward (first move)
            if ((isWhite && oldRow == 1 || !isWhite && oldRow == 6) &&
                    newRow == oldRow + 2 * direction &&
                    !pawns[newRow][newCol] &&
                    !pawns[oldRow + direction][newCol]) {
                return true;
            }
        }

        // Diagonal capture
        if (Math.abs(newCol - oldCol) == 1 && newRow == oldRow + direction) {
            if (pawns[newRow][newCol]) {
                return true;
            }
        }

        // Invalid move
        return false;
    }

    // Move the pawn on the board
    public void movePawn(int oldRow, int oldCol, int newRow, int newCol) {
        pawns[oldRow][oldCol] = false; // Remove pawn from old position
        pawns[newRow][newCol] = true; // Place pawn in new position
    }
}
