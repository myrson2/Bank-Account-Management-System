import cli.BankCLI;

public class App {
    public static void main(String[] args) throws Exception {
        BankCLI cli = new BankCLI();
        
        System.out.println("Bank Account Management System");
        cli.start();
    }
}
