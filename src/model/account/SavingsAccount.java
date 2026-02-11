package model.account;

import java.math.BigDecimal;
import model.user.User;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(User owner, BigDecimal balance){
        super(owner, balance);
        this.interestRate = calculateInterest();
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
        
        if(withdraw.compareTo(getBalance()) < 0){
            System.out.println("Error...");
            return null;
        }
        return withdraw.subtract(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double calculateInterest(){
         double balance = getBalance().doubleValue();

        double interest = balance * .02;
        
        return balance + interest;
    }
}
