package model.account;

import java.math.BigDecimal;

import model.user.User;

public class CurrentAccount extends Account{
    private BigDecimal overdraftLimit = BigDecimal.valueOf(1000);

    public CurrentAccount(User owner,  BigDecimal balance){
        super(owner, balance);
    }

    @Override
    public BigDecimal deposit(double amount) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            overdraftLimit = overdraftLimit.add(balance).abs();   
        }

        BigDecimal deposit = BigDecimal.valueOf(amount);
        balance = deposit.add(balance);

        return balance;
    }

    @Override
    public BigDecimal withdraw(double amount) {
        BigDecimal totalAvailable = balance.add(overdraftLimit);
        BigDecimal withdrawAmount = BigDecimal.valueOf(amount);  // Convert double to BigDecimal for precision
        
        if (withdrawAmount.compareTo(totalAvailable) <= 0) {  // Allow withdrawals up to totalAvailable
            balance = balance.subtract(withdrawAmount);
            
            BigDecimal usedOverdraft = BigDecimal.ZERO;
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                usedOverdraft = balance.abs();  // How much overdraft is used
                overdraftLimit = overdraftLimit.subtract(usedOverdraft);
            }
        } else {
        System.out.println("Not Allowed. Insufficient Amount.");
        }
        
        return balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

}
