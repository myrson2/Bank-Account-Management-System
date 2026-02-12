package cli;

import java.math.BigDecimal;
import java.util.Scanner;

import model.user.User;
import model.account.*;

public class BankCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static User user;
    
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
                case 5 -> System.out.println("Transfer");
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

    public static User createAccount(){
        System.out.print("Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        user = new User(name, email);
        System.out.println("Your id: " + user.getId());
        
        return user;   
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

        for (Account acc  : user.getAccounts()) {
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

                    user.addAccount(savings);
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

                    user.addAccount(current);
            }
        }
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

        for (Account acc : user.getAccounts()) {
            if(choice == 1 && acc instanceof SavingsAccount) acc.deposit(amount);
            if(choice == 2 && acc instanceof CurrentAccount) acc.deposit(amount);
        }
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

        for (Account acc : user.getAccounts()) {
            if(choice == 1 && acc instanceof SavingsAccount) acc.withdraw(amount);
            if(choice == 2 && acc instanceof CurrentAccount) acc.withdraw(amount);
        }
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

           if (user.getAccounts().isEmpty()) {
        System.out.println("No accounts available.");
        return;
    }

        for (Account acc : user.getAccounts()) {

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
        return;
    }
}

