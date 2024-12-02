import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3]; // 3 x 3 board
    private static char currentPlayer = 'X'; // current player

    public static void main(String[] args) {
        initializeBoard();// initialize the board with empty spaces
        while (true) {
            printBoard(); // print the board
            playerMove(); // get the current player's move

            if (checkWin()) { // check if the current player has won
                printBoard(); // print the board
                System.out.println("Player " + currentPlayer + " wins!");
                break; // end the game
            } else if (checkDraw()) { // Check if the game ends in a draw

                printBoard();
                System.out.println("It's a draw");
                break;// end the game
            }
            switchPlayer(); // Switch the player for the next turn
        }

    }

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) { // loop through rows
            for (int j = 0; j < 3; j++) { // Loop through columnns
                board[i][j] = '-'; // set each cell to empty
            }
        }
    }
    // print the current state of the board

    private static void printBoard() {
        System.out.println("Current Board: ");
        for (int i = 0; i < 3; i++) { // Loop through rows
            for (int j = 0; j < 3; j++) { // Loop through columns
                System.out.print(board[i][j] + " "); // print each cell
            }
            System.out.println(); // move to the next row
        }
    }

    // Get the player's move and place it on the board

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println("Player " + currentPlayer + " , enter your move (row and column: 1-3): ");
            row = scanner.nextInt() - 1; // Convert to 0-based index
            col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer; // place the player's mark
                break; // exit the loop after valid move
            } else {
                System.out.println("Invalid move. Try again."); // ask for input again
            }
        }

    }

    // check if the current player has won
    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) { // check rows and columns
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true; // Row win
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true; // column win

        }

        // check diagonals

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true; // Main diagonal win
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            return true; // Anti-diagonal win

        return false; // no win condition met

    }

    // Check if the board is full, idicating a draw

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) { // Loop through rows
            for (int j = 0; j < 3; j++) { // Loop through columns
                if (board[i][j] == '-') {

                    return false; // an empty cell exists, no draw
                }

            }

        }
        return true; // No empty cells, it's a draw

    }

    // Switch the current player between 'X' and 'O'
    private static void switchPlayer() {

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

}
