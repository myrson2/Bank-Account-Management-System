package model.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.transaction.Transaction;
import model.user.User;

public abstract class Account {
    private Random random = new Random();
    private String accountNumber;
    private User owner;
    protected BigDecimal balance;
    private ArrayList<Transaction> transactions;

    public Account(User owner, BigDecimal initialBalance){
        this.accountNumber = accNumberGenerator();
        this.owner = owner;
        this.transactions = new ArrayList<>();
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String accNumberGenerator(){
        String acc = "BAMS";

        LocalDate today = LocalDate.now();       // Current date
        int year = today.getYear();
        String yr = Integer.toString(year);

        int num = random.nextInt(100000);

        String s = String.format("%04d", num);

        return acc + "-" + yr + "-" + s;
    }

    public abstract BigDecimal deposit(double amount);
    public abstract BigDecimal withdraw(double amount);
    public abstract BigDecimal getBalance();
}
