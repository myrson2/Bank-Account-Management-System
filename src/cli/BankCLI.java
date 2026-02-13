package cli;

import java.math.BigDecimal;
import java.util.Scanner;

import model.user.User;
import model.account.*;
import model.service.AccountService;
import model.service.TransactionService;
import model.transaction.Transaction;
import model.transaction.TransactionType;

public class BankCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static User user;
    private static AccountService accountService = new AccountService();
    private static TransactionService transactionService = new TransactionService();
    
    public void start(){
        boolean running = true;
    
        while(running){
            menu();
            System.out.print("Select: ");
            int select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1 -> createAccount(); 
                case 2 -> openAccount();
                case 3 -> deposit();
                case 4 -> withdraw();
                // case 5 -> transfer();
                case 6 -> viewAccountDetails();
                case 7 -> viewTransactionHistory();
                case 0 -> {
                    System.out.println("System closing...");
                    System.out.print("(Enter to close)");
                    scanner.nextLine();

                    running = false;
                }
            }
        }
    }

    static void menu(){
        System.out.println("""
            Menu: 
            1. Create Account
            2. Open Account
            3. Deposit
            4. Withdraw
            5. Transfer
            6. View Account Details
            7. View Transaction History
            0. Exit
        """);
    }

    public static void createAccount(){
        System.out.print("Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        user = new User(name, email);
        System.out.println("Your id: " + user.getId());
        
        user = new User(name, email);

        User created = accountService.createUser(user);
        System.out.printf("Hi %s! Welcome to our bank!\n", created.getFullName());
    }

    public static void openAccount(){

        if(user == null) {
            System.out.println("Create Account First");
            return;
        }

        System.out.printf("""
               -- Open Account --
               1. Savings Account
               2. Current Account
                """
            );
        
        System.out.print("Select > ");
        int select = scanner.nextInt();
        scanner.nextLine();

        for (Account acc  : accountService.getAccounts()) {
            if (select == 1 && acc instanceof SavingsAccount) {
                System.out.println("You already have an account for this account");
                return;
            }
            if (select == 2 && acc instanceof CurrentAccount) {
                System.out.println("You already have an account for this account");
                return;
            }
        }

        System.out.print("Initial Deposit: ");
        int initDeposit = scanner.nextInt();
        scanner.nextLine();

        String typeAccount = "";
        
        switch (select) {
            case 1 -> {
                SavingsAccount savings = new SavingsAccount(user, new BigDecimal(initDeposit));
                System.out.printf("""
                    !! Savings Account Added Successfully.
                        Account Number: %s
                        Owner: %s
                        Balance: %.2f
                        Interest (Yearly): %.2f 
                    """, 
                    savings.getAccountNumber(), 
                    savings.getOwner().getFullName(), 
                    savings.getBalance(),
                    savings.getInterestRate()
                        );
                    
                    accountService.openSavingsAccount(savings);
                    typeAccount = "Savings Account";
            }
            case 2 -> {
                CurrentAccount current = new CurrentAccount(user, new BigDecimal(initDeposit));
                    System.out.printf("""
                        !! Savings Account Added Successfully.
                            Account Number: %s
                            Owner: %s
                            Balance: P%.2f
                        """, 
                        current.getAccountNumber(), 
                        current.getOwner().getFullName(), 
                        current.getBalance());

                    accountService.openCurrentAccount(current);
                    typeAccount = "Current Account";
            }
        }

        String description = "Placed Initial Balance in " + typeAccount + ".";
        TransactionType type = TransactionType.DEPOSIT;

        transactionService.addTransaction(new Transaction(type, new BigDecimal(initDeposit), description));
    }

    public static void deposit(){
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

        for (Account acc : accountService.getAccounts()) {
            if(choice == 1 && acc instanceof SavingsAccount) acc.deposit(amount);
            if(choice == 2 && acc instanceof CurrentAccount) acc.deposit(amount);
        }

        TransactionType type = TransactionType.DEPOSIT;
        String description = "Cash Deposit";

        transactionService.addTransaction(new Transaction(type, new BigDecimal(amount), description));
    }

    public void withdraw(){
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

        for (Account acc : accountService.getAccounts()) {
            if(choice == 1 && acc instanceof SavingsAccount) acc.withdraw(amount);
            if(choice == 2 && acc instanceof CurrentAccount) acc.withdraw(amount);
        }

        TransactionType type = TransactionType.WITHDRAWAL;
        String description = "Cash Withdrawal";

        transactionService.addTransaction(new Transaction(type, new BigDecimal(amount), description));
    }

    public void viewAccountDetails(){
        System.out.printf("""
                -- Account Details -- 
                ID: %s
                Name: %s
                Email: %s
                ---------------------
                """, user.getId(), user.getFullName(), user.getEmail()
        );

           if (accountService.getAccounts().isEmpty()) {
        System.out.println("No accounts available.");
        return;
    }

        for (Account acc : accountService.getAccounts()) {

            if (acc instanceof SavingsAccount savings) {
                System.out.printf("""
                        [Savings Account]
                        Account Number: %s
                        Balance: %.2f
                        Interest Rate: %.2f
                        ---------------------
                        """,
                        savings.getAccountNumber(),
                        savings.getBalance(),
                        savings.getInterestRate()
                );
            }

            else if (acc instanceof CurrentAccount current) {
                System.out.printf("""
                        [Current Account]
                        Account Number: %s
                        Balance: %s
                        Overdraft Limit: %.2f
                        ---------------------
                        """,
                        current.getAccountNumber(),
                        current.getBalance(),
                        current.getOverdraftLimit()
                );
            }
        }
    }

    public void viewTransactionHistory(){
        System.out.println("Transactions: \n");
        transactionService.viewTransactionHistory();
    }

    // public void transfer(){
        
    // }
}

