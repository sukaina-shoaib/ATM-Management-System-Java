// ATM.java
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ATM extends JFrame implements ActionListener {
    private JButton withdrawButton, depositButton, changePinButton, balanceButton, logoutButton;
    private JLabel accNoLabel, accNameLabel, welcomeLabel, optionsLabel;
    private JPanel buttonPanel;
    private AccountData account; 

    public ATM(AccountData account) {
        this.account = account; 

        // JFrame settings
        setSize(600, 500);
        setTitle("ATM - Welcome " + account.getFullName());
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0x023e8a));
        setResizable(false);

        // Options Label
        optionsLabel = new JLabel("Select a Transaction Option", SwingConstants.CENTER);
        optionsLabel.setBounds(0, 0, 600, 60);
        optionsLabel.setBackground(new Color(0x023e8a));
        optionsLabel.setForeground(Color.WHITE);
        optionsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        optionsLabel.setOpaque(true);
        add(optionsLabel);

        // Welcome Label
        welcomeLabel = new JLabel("Welcome,", SwingConstants.LEFT);
        welcomeLabel.setBounds(30, 100, 300, 30);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        welcomeLabel.setForeground(new Color(0x48cae4));
        add(welcomeLabel);

        // Account Name Label
        accNameLabel = new JLabel(account.getFullName(), SwingConstants.LEFT);
        accNameLabel.setBounds(30, 130, 300, 30);
        accNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        accNameLabel.setForeground(Color.WHITE);
        add(accNameLabel);

        // Account Number Label
        accNoLabel = new JLabel("Account Number: " + account.getAccountNumber(), SwingConstants.LEFT);
        accNoLabel.setBounds(30, 170, 300, 30);
        accNoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        accNoLabel.setForeground(new Color(0x48cae4));
        add(accNoLabel);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 220, 400, 150);
        buttonPanel.setBackground(new Color(0x023e8a));
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2x2 grid with spacing

        // Initialize Buttons
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        changePinButton = new JButton("Change PIN");
        balanceButton = new JButton("Balance");

        // Add Action Listeners
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        changePinButton.addActionListener(this);
        balanceButton.addActionListener(this);

        // Style Buttons
        JButton[] buttons = {withdrawButton, depositButton, changePinButton, balanceButton};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setBackground(new Color(0x48cae4));
            btn.setForeground(Color.WHITE);
            btn.setFocusable(false);
        }

        // Add Buttons to Panel
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(changePinButton);
        buttonPanel.add(balanceButton);

        add(buttonPanel);

        // Logout Button
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(250, 400, 100, 40);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(0xE4007C));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
        add(logoutButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            handleTransaction("Withdraw");
        } 
        else if (e.getSource() == depositButton) {
            handleTransaction("Deposit");
        } 
        else if (e.getSource() == changePinButton) {
            handleTransaction("Change PIN");
        } 
        else if (e.getSource() == balanceButton) {
            handleTransaction("Balance");
        } 
        else if (e.getSource() == logoutButton) {
            new login();
            dispose(); 
        }
    }

    private void handleTransaction(String type) {
        JFrame transactionFrame = new JFrame(type + " Transaction");
        transactionFrame.setSize(400, 300);
        transactionFrame.setTitle(type + " Transaction");
        transactionFrame.setLayout(null);
        transactionFrame.setLocationRelativeTo(this);
        transactionFrame.getContentPane().setBackground(new Color(0x023e8a));
        transactionFrame.setResizable(false);
        transactionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(50, 50, 100, 30);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        transactionFrame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(160, 50, 180, 30);
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        transactionFrame.add(amountField);

        JButton submitButton = new JButton(type);
        submitButton.setBounds(150, 120, 100, 40);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(0x48cae4));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusable(false);
        transactionFrame.add(submitButton);

        if (type.equals("Balance")) {
            amountLabel.setText("Current Balance:");
            amountField.setEditable(false);
            amountField.setText(String.valueOf(account.getBalance()));
            submitButton.setText("OK");
        } else if (type.equals("Change PIN")) {
            amountLabel.setText("Enter New PIN:");
        }

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String input = amountField.getText().trim();

                if (type.equals("Balance")) {
                    JOptionPane.showMessageDialog(transactionFrame, "Your Current Balance: RS" + account.getBalance());
                    transactionFrame.dispose();
                    return;
                }

                if (type.equals("Change PIN")) {
                    try {
                        int newPin = Integer.parseInt(input);
                        if (String.valueOf(newPin).length() < 4) {
                            JOptionPane.showMessageDialog(transactionFrame, "PIN should be at least 4 digits.");
                            return;
                        }
                        account.setAccountPin(newPin);
                        JOptionPane.showMessageDialog(transactionFrame, "PIN changed successfully!");
                        transactionFrame.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(transactionFrame, "Please enter a valid numeric PIN.");
                    }
                    return;
                }

                // For Withdraw and Deposit
                try {
                    double amount = Double.parseDouble(input);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(transactionFrame, "Please enter amount.");
                        return;
                    }

                    if (type.equals("Withdraw")) {
                        if (account.getBalance() >= amount) {
                            account.setBalance(account.getBalance() - amount);
                            JOptionPane.showMessageDialog(transactionFrame, "Withdrawal successful!\nNew Balance: RS" + account.getBalance());
                        } else {
                            JOptionPane.showMessageDialog(transactionFrame, "Insufficient balance.");
                        }
                    } else if (type.equals("Deposit")) {
                        account.setBalance(account.getBalance() + amount);
                        JOptionPane.showMessageDialog(transactionFrame, "Deposit successful!\nNew Balance: RS" + account.getBalance());
                    }

                    transactionFrame.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(transactionFrame, "Please enter a valid numeric amount.");
                }
            }
        });

        transactionFrame.setVisible(true);
    }
}
