import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private String Operator = "";
    private double result = 0;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        // Display field

        JTextField display = new JTextField();
        display.setBounds(20, 20, 340, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display);
        // Buttons

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"

        };

        int x = 20, y = 100;
        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons[i]);
            button.setBounds(x, y, 80, 60);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            add(button);

            x += 90;
            if ((i + 1) % 4 == 0) {
                x = 20;
                y += 70;

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            currentInput += command;
            display.setText(currentInput);
        } else if ("/-*+".contains(command)) {
            Operator = command;
            result = Double.parseDouble(currentInput);
            currentInput = "";
        } else if (command.equals("=")) {
            double secondOperand = Double.parseDouble(currentInput);
            switch (Operator) {
                case "+":
                    result += secondOperand;
                    break;
                case "-":
                    result -= secondOperand;
                    break;
                case "*":
                    result *= secondOperand;
                    break;
                case "/":
                    result /= secondOperand;

            }

            display.setText(String.valueOf(result));
            currentInput = "";
        } else if (command.equals("C")) {
            currentInput = "";
            Operator = "";
            result = 0;
            display.setText("");
        }
    }

    public static void main(String[] args) {

        new Calculator();
    }

}
