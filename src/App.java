import cli.BankCLI;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bank Account Management System");

        BankCLI cli = new BankCLI();
        
        cli.start();
    }
}
