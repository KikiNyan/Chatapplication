/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount;

/**
 *
 * @author ryanf
 */
import java.util.Scanner;
public class BankingSystemApp {
    
     private static Bank bank = new Bank();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create a New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("7. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    transferFunds(scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                case 7:
                    viewTransactionHistory(scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // Helper methods for each action
    private static void createAccount(Scanner scanner) {
          scanner.nextLine();
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();
        bank.createAccount(name, deposit);
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            account.checkBalance();
        }
    }

    private static void transferFunds(Scanner scanner) {
        System.out.print("Enter your account number: ");
        int fromAccountNumber = scanner.nextInt();
        BankAccount fromAccount = bank.findAccount(fromAccountNumber);

        if (fromAccount != null) {
            System.out.print("Enter recipient account number: ");
            int toAccountNumber = scanner.nextInt();
            BankAccount toAccount = bank.findAccount(toAccountNumber);

            if (toAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                fromAccount.transfer(toAccount, amount);
            }
        }
    }
    private static void viewTransactionHistory(Scanner scanner) {
    System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        

    BankAccount account = bank.findAccount(accountNumber);
    if (account != null) {
        System.out.println("Transaction History for " + accountNumber + ":");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    } else {
        System.out.println("Account not found.");
    }
}
}
