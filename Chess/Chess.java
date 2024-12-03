
public class Chess {
    public static void main(String[] args) {
        char[][] board = new char[8][8]; // create the 2d array
        initializeBoard(board); // set up the initial condition of the board
        printBoard(board); // print the board to display it
    }

    // Initialize the board
    public static void initializeBoard(char[][] board) {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = 'P'; // position for white pawns
            board[6][i] = 'P'; // position for black pawns
        }

        // Rooks
        board[0][0] = 'R'; // white rooks
        board[0][7] = 'R';
        board[7][0] = 'r'; // black rooks
        board[7][7] = 'r';

        // Knights
        board[0][1] = 'N'; // White knights
        board[0][6] = 'N';

        board[7][1] = 'n'; // Black knights
        board[7][7] = 'n';
        // Bishops
        board[0][2] = 'B'; // white bishops
        board[0][5] = 'B';

        board[7][2] = 'b'; // Black bishops
        board[7][5] = 'b';
        // Queens
        board[0][3] = 'Q'; // white queen
        board[7][3] = 'q'; // Black quenn
        // Kings
        board[0][4] = 'K'; // White king
        board[7][4] = 'K'; // Black King
    }

    // print the board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < 8; i++) { // Loop through each row
            for (int j = 0; j < 8; j++) { // Loop through each column
                char piece = board[i][j];
                if (piece == '\u0000') { // if no piece is present
                    System.out.print(". ");

                } else {
                    System.out.print(piece + " "); // Print the piece
                }
            }
            System.out.println();// move to next line

        }

    }
}
