/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankaccount;

/**
 *
 * @author ryanf
 */
import java.util.ArrayList;
import java.util.List;
public class BankAccount {

  private String accountHolderName;
    private int accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolderName, int accountNumber, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
         this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account created with initial deposit of " + initialDeposit);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: $" + balance);
            transactionHistory.add("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: $" + balance);
            transactionHistory.add("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
             transactionHistory.add("Failed withdrawal attempt: " + amount + " (Insufficient funds)");
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            targetAccount.balance += amount;
            System.out.println("Transfer successful. Your new balance: $" + balance);
            transactionHistory.add("Transfer successful to "  + targetAccount.getAccountHolderName() + " (Account Number: " 
            + targetAccount.getAccountNumber()+" (Amount send: "+ amount  + ", New Balance: " + balance);
        } else {
            System.out.println("Transfer failed. Insufficient balance or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }
    public String getAccountHolderName() {
    return accountHolderName;
}

 

    @Override
    public String toString() {
        return "Account Holder: " + accountHolderName + ", Account Number: " + accountNumber + ", Balance: $" + balance;
    }
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
    
}
