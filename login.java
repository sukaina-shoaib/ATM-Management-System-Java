// login.java
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class login extends JFrame implements ActionListener {

    private JTextField accountNoField;
    private JPasswordField pinField;
    private JLabel errorLabel, numberLabel, pinLabel, headingLabel;
    private JButton loginButton;
    private Image imageIcon;
    private JPanel imagePanel;

    public login() {

        // JFrame settings
        setSize(500, 500);
        setTitle("ATM Login");
        setLayout(null);
        setLocationRelativeTo(null);

        // Heading label
        headingLabel = new JLabel("WELCOME TO ATM MANAGEMENT SYSTEM", SwingConstants.CENTER);
        headingLabel.setBounds(0, 0, 500, 60);
        headingLabel.setBackground(new Color(58, 16, 120));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headingLabel.setOpaque(true);
        add(headingLabel);

        // Image panel (optional)
    
        imageIcon = new ImageIcon("image//Atm.png").getImage(); // Ensure "atm.jpg" exists in the project directory
        imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageIcon, 5, 35, 240, 300, this);
            }
        };
        imagePanel.setBounds(5, 35, 240, 300);
        add(imagePanel);

        // Account Number Label and Field
        numberLabel = new JLabel("Enter your Account Number:");
        numberLabel.setBounds(250, 110, 200, 30);
        numberLabel.setForeground(Color.BLACK);
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(numberLabel);

        accountNoField = new JTextField();
        accountNoField.setBounds(250, 140, 200, 30);
        accountNoField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(accountNoField);

        // PIN Label and Field
        pinLabel = new JLabel("Enter PIN:");
        pinLabel.setBounds(250, 190, 200, 30);
        pinLabel.setForeground(Color.BLACK);
        pinLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(250, 220, 200, 30);
        pinField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(pinField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(300, 270, 90, 30);
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(this);
        add(loginButton);

        // Error Label
        errorLabel = new JLabel("Invalid account number or PIN.", SwingConstants.CENTER);
        errorLabel.setBounds(250, 310, 200, 25);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);

        // Optional Signup Button (Not Implemented)
        /*
        signup = new JButton("Sign Up");
        signup.setBounds(200, 350, 100, 30);
        signup.setFocusable(false);
        signup.setFont(new Font("Arial", Font.BOLD, 14));
        signup.addActionListener(this);
        add(signup);
        */

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String accountNumberInput = accountNoField.getText().trim();
            String pinInput = new String(pinField.getPassword()).trim(); 

            int accNo;
            int accPin;
            try {
                accNo = Integer.parseInt(accountNumberInput);
                accPin = Integer.parseInt(pinInput);
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter valid account number and pin.");
                errorLabel.setVisible(true);
                return;
            }

            ArrayList<AccountData> accountList = AccountData.accounts;
            boolean found = false;
            AccountData authenticatedAccount = null;

            for (AccountData account : accountList) {
                if (account.getAccountNumber() == accNo && account.getAccountPin() == accPin) {
                    authenticatedAccount = account;
                    found = true;
                    break;
                }
            }

            if (found && authenticatedAccount != null) {
                errorLabel.setVisible(false);
                new ATM(authenticatedAccount);
                dispose(); 
            } else {
                errorLabel.setText("Invalid account number or PIN.");
                errorLabel.setVisible(true);
            }
        }
    }
}
