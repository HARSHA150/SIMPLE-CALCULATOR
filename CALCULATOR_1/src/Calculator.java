import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Roman", Font.BOLD, 30); // Roman font style

    Color buttonColor = new Color(255, 69, 0); // Reddish-orange color
    Color backgroundColor = Color.BLACK;

    double num1 = 0, num2 = 0, result = 0;
    String operator = "";
    String operand1 = "", operand2 = "";

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.WHITE);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(buttonColor);
            functionButtons[i].setForeground(Color.WHITE);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(buttonColor);
            numberButtons[i].setForeground(Color.WHITE);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(backgroundColor);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.getContentPane().setBackground(backgroundColor); // Set the background color of the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Handle number button clicks
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText() + i);
            }
        }

        // Handle decimal point button click
        if (e.getSource() == decButton) {
            String text = textfield.getText();
            if (!text.contains(".")) {
                textfield.setText(text + ".");
            }
        }

        // Handle operator button clicks
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            operator = ((JButton) e.getSource()).getText();
            operand1 = textfield.getText();
            textfield.setText(operand1 + " " + operator + " ");
        }

        // Handle equal button click
        if (e.getSource() == equButton) {
            operand2 = textfield.getText().split(" ")[2];
            num1 = Double.parseDouble(operand1);
            num2 = Double.parseDouble(operand2);

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            textfield.setText(operand1 + " " + operator + " " + operand2 + " = " + result);
        }

        // Handle clear button click
        if (e.getSource() == clrButton) {
            textfield.setText("");
            operand1 = "";
            operand2 = "";
            operator = "";
        }

        // Handle delete button click
        if (e.getSource() == delButton) {
            String text = textfield.getText();
            if (text.length() > 0) {
                textfield.setText(text.substring(0, text.length() - 1));
            }
        }

        // Handle negation button click
        if (e.getSource() == negButton) {
            String text = textfield.getText();
            if (!text.isEmpty()) {
                double temp = Double.parseDouble(text);
                temp *= -1;
                textfield.setText(String.valueOf(temp));
            }
        }
    }
}
