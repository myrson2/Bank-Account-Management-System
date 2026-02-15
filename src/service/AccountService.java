package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountException;
import model.account.Account;
import model.account.SavingsAccount;
import model.account.CurrentAccount;
import model.transaction.Transaction;
import model.transaction.TransactionType;
import model.user.User;

public class AccountService {
    private static Scanner scanner = new Scanner(System.in);
    private static TransactionService transactionService = new TransactionService();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    public User createUser(User user){
        users.add(user);
        return user;
    }

    public SavingsAccount openSavingsAccount(SavingsAccount savings){
        accounts.add(savings);
        return savings;
    }

    public CurrentAccount openCurrentAccount(CurrentAccount current){
        accounts.add(current);
        return current;
    }

    public Transaction deposit(){
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.printf("""
               -- Account --
               1. Savings Account
               2. Current Account   
            """);

        System.out.print("Select: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        for (Account acc : this.getAccounts()) {
            if(choice == 1 && acc instanceof SavingsAccount) acc.deposit(amount);
            if(choice == 2 && acc instanceof CurrentAccount) acc.deposit(amount);
        }

        TransactionType type = TransactionType.DEPOSIT;
        String description = "Cash Deposit";

        Transaction created = transactionService.addTransaction(new Transaction(type, new BigDecimal(amount), description));

        return created;
    }

    public Transaction withdraw(){
        try {
            System.out.print("Enter Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.printf("""
                -- Account --
                1. Savings Account
                2. Current Account   
                """);

            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice != 1 && choice != 2) {
                throw new InvalidAccountException("Invalid selection. Please choose 1 for Savings or 2 for Current.");
            }

            for (Account acc : this.getAccounts()) {
                if(choice == 1 && acc instanceof SavingsAccount) acc.withdraw(amount);
                if(choice == 2 && acc instanceof CurrentAccount) acc.withdraw(amount);
            }

            TransactionType type = TransactionType.WITHDRAWAL;
            String description = "Cash Withdrawal";

            Transaction created = transactionService.addTransaction(new Transaction(type, new BigDecimal(amount), description));

            return created;
            
        } 
        
        catch (InsufficientFundsException ife) {
            System.out.println("Error: " + ife.getMessage());
            return null; 
        }

        catch (InvalidAccountException iae){
            System.out.println("Error: " + iae.getMessage());
            return null; 
        }

    }

    // getters 
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
