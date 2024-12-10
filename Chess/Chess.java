import javax.swing.*; // for the graphics
import java.awt.*;// for layout and color

// define the Chesss Classs(blueprint)
public class Chess extends JFrame {
    private JLabel[][] tiles = new JLabel[8][8]; // 2D array to store tiles
    private boolean isWhiteTurn;

    // Constructor: sets up the screen
    public Chess() {
        // set the title of the window
        setTitle("Chess Game");

        // Set the size of the screen(width x height)

        setSize(800, 800);
        // set the behaviour for the claose operation

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the ChessBoardA

        JPanel chessBoard = new JPanel();
        chessBoard.setLayout(new GridLayout(8, 8));

        // Add tiles to the board

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel tile = new JPanel(); // create a tile

                // Alternate colors for black and white squares
                if ((row + col) % 2 == 0) {
                    tile.setBackground(Color.WHITE);
                } else {
                    tile.setBackground(Color.GRAY);
                }
                JLabel label = new JLabel("", SwingConstants.CENTER); // Label for Chess Pieces
                tiles[row][col] = label; // store the label in the array
                tile.add(label); // Add label to the tile
                chessBoard.add(tile); // Add tile to the board

            }

        }

        // Initialize chess pieces
        initializePieces();

        // Attach the mouse handler
        PawnMouseHandler mouseHandler = new PawnMouseHandler(tiles, isWhiteTurn);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col].addMouseListener(mouseHandler);
            }
        }
        // add the chessBoard to the window
        add(chessBoard);
        // Make the window visisble
        setVisible(true);

    }

    private void initializePieces() {
        // Place the pawns
        for (int col = 0; col < 8; col++) {
            tiles[6][col].setText("\u2659"); // Unicode for White pawn

        }

        // Place black pawns

        for (int col = 0; col < 8; col++) {
            tiles[1][col].setText("\u265F");

        }

        // Place other white pieces

        tiles[7][0].setText("\u2656");// rook
        tiles[7][7].setText("\u2656");
        tiles[7][1].setText("\u2658");// knight
        tiles[7][6].setText("\u2658");
        tiles[7][2].setText("\u2657");// bishop
        tiles[7][5].setText("\u2657");
        tiles[7][3].setText("\u2655");// queen
        tiles[7][4].setText("\u2654");// king

        // Place other Black pieces
        tiles[0][0].setText("\u265C");// rook
        tiles[0][7].setText("\u265C");
        tiles[0][1].setText("\u265E");// knight
        tiles[0][6].setText("\u265E");
        tiles[0][2].setText("\u265D");// bishop
        tiles[0][5].setText("\u265D");
        tiles[0][3].setText("\u265B");// queen
        tiles[0][4].setText("\u265A");// king

    }

    // main method : Where the program will starts

    public static void main(String[] args) {
        // create an object of the chess class

        new Chess();

    }

}
