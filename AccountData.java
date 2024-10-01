// AccountData.java
import java.util.ArrayList;

public class AccountData {
    private int accountNumber;
    private int accountPin;
    private double balance;
    private String fullname;
    private String dob;

    // Public static list to hold account data
    public static ArrayList<AccountData> accounts = new ArrayList<>();

    // Constructor
    public AccountData(String fullname, String dob, int accountNumber, int accountPin, double balance) {
        this.fullname = fullname;
        this.dob = dob;
        this.accountNumber = accountNumber;
        this.accountPin = accountPin;
        this.balance = balance;
    }

    // Getters and setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(int accountPin) {
        this.accountPin = accountPin;
    }

    public String getFullName() {
        return fullname;
    }
    public String getdob(){
        return dob;
    }

    // Static block to initialize the account data
    static {
        accounts.add(new AccountData("Sukaina Shoaib", "20-07-2005", 123, 1234, 45000.0));
        accounts.add(new AccountData("Usman Ali", "11-01-2004", 104007, 3434, 850000.0));
        accounts.add(new AccountData("Suhaima Shoaib", "06-08-2001", 105008, 5656, 12000.0));
    }
}
