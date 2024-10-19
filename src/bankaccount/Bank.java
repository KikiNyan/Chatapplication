/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount;

/**
 *
 * @author ryanf
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Bank {
      private ArrayList<BankAccount> accounts = new ArrayList<>();
       private Map<String, BankAccount> accounts1 = new HashMap<>();
    private int accountNumberCounter = 1001;

    // Create a new account
    public void createAccount(String accountHolderName, double initialDeposit) {
        BankAccount newAccount = new BankAccount(accountHolderName, accountNumberCounter++, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully: " + newAccount);
    }

          public BankAccount getAccount(String accountHolderName) {
              
          return accounts1.get(accountHolderName);
    }
    // Find account by account number
    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

}
