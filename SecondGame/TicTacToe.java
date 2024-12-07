import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TicTacToe extends JFrame implements KeyListener {
    private JButton[][] buttons = new JButton[3][3]; // 3x3 grid of buttons
    private int currentRow = 0, currentCol = 0; // Track the focused cell
    private char currentPlayer = 'X'; // Current player

    public TicTacToe() {

        setTitle("Tic Tac Toe"); // Window title
        setSize(400, 400); // Window size
        setDefaultCloseOperation(EXIT_ON_CLOSE); // exit the prgram on close
        setLayout(new GridLayout(3, 3)); // use grid Layout for a 3x3 frid

        // Create buttons

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(""); // craete button
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60)); // Large font
                buttons[row][col].setFocusPainted(false); // Remove focus border
                add(buttons[row][col]); // Add button to the grid
            }

        }

        addKeyListener(this); // ADD key Listener
        setFocusable(true); // Make JFrame Focusable

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Remove current highlight
        buttons[currentRow][currentCol].setBorder(null);
        // handle arrow keys
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentRow = (currentRow > 0) ? currentRow - 1 : currentRow;
                break;
            case KeyEvent.VK_DOWN:
                currentRow = (currentRow < 2) ? currentRow + 1 : currentRow;
                break;
            case KeyEvent.VK_LEFT:
                currentCol = (currentCol > 0) ? currentCol - 1 : currentCol;
                break;
            case KeyEvent.VK_RIGHT:
                currentCol = (currentCol < 2) ? currentCol + 1 : currentCol;
                break;
            case KeyEvent.VK_ENTER:
                // Place move
                if (buttons[currentRow][currentCol].getText().equals("")) {

                    buttons[currentRow][currentCol].setText(String.valueOf(currentPlayer));
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
                }
                break;
        }

        // Add highlight to the new cell
        buttons[currentRow][currentCol].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.setVisible(true);

    }
}
