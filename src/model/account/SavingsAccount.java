package model.account;

import java.math.BigDecimal;
import model.user.User;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(User owner, BigDecimal balance){
        super(owner, balance);
        this.interestRate = getInterestRate();
        }

    @Override
    public BigDecimal deposit(double amount) {
        BigDecimal deposit = new BigDecimal(amount);
        balance = deposit.add(balance);

        return balance;
    }

    @Override
    public BigDecimal withdraw(double amount) {
        BigDecimal withdraw = new BigDecimal(amount);
        
        if(balance.compareTo(withdraw) < 0){
            System.out.println("Error...");
            return null;
        }

        balance = balance.subtract(withdraw);
        
        return balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return calculateInterest();
    }

    public double calculateInterest(){
        double balance = getBalance().doubleValue();

        double interest = balance * .02;
        return balance + interest;
    }
}
