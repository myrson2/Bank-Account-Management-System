package model.account;

import java.math.BigDecimal;

import exceptions.InsufficientFundsException;
import model.user.User;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(User owner, BigDecimal balance){
        super(owner, balance);
        this.interestRate = getInterestRate();
        }

    @Override
    public BigDecimal deposit(double amount) {
        BigDecimal deposit = BigDecimal.valueOf(amount);
        balance = deposit.add(balance);

        return balance;
    }

    @Override
    public BigDecimal withdraw(double amount) throws InsufficientFundsException {
        BigDecimal withdraw = BigDecimal.valueOf(amount);
        
        if(balance.compareTo(withdraw) < 0){
            throw new InsufficientFundsException();
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

        interestRate = balance + interest;
        
        return this.interestRate;
    }
}
