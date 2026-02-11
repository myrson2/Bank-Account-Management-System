package model.account;

import java.math.BigDecimal;

import model.user.User;

public class CurrentAccount extends Account{
    // private BigDecimal overdraftLimit;

    public CurrentAccount(User owner,  BigDecimal balance){
        super(owner, balance);
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

}
