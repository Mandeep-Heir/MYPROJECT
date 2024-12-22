import javax.swing.*; // provides GUI

import java.awt.*; // 

public class Calculator { // main container
    private JFrame jframe; // jframe variable for setting up the jframe
    private JTextField textfield;

    public Calculator() { // constructor of the main class that contains all the components to obtain the
                          // jframe and textfield
        // making the JFrame

        jframe = new JFrame("Calculator"); // setting up the title
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when we exit
        jframe.setSize(500, 500); // setting the size of window
        jframe.setVisible(true); // make the screen visible

        // making the textfiled

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50); // position and Size
        textfield.setFont(new Font("Arial", Font.BOLD, 18)); // Font styling
        textfield.setEditable(false); // make it read only
        jframe.add(textfield); // add the text field to the frame

    }

    public static void main(String[] args) { // main entry point
        new Calculator(); //

    }

}