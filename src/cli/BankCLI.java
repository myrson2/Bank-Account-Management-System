package cli;

import java.util.Scanner;
import model.User;

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
        System.out.println("Full Name: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        user = new User(name, email);

        System.out.println("Your id: " + user.idGenerator());
        return user;   
    }
}

