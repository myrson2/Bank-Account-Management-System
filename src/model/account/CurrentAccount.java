package model.account;

import java.math.BigDecimal;

import model.user.User;

public class CurrentAccount extends Account{
    private BigDecimal overdraftLimit;

    public CurrentAccount(User owner,  BigDecimal balance){
        super(owner, balance);
    }

    @Override
    public BigDecimal deposit(double amount) {
        BigDecimal deposit = new BigDecimal(amount);

        return deposit.add(getBalance());
    }

    @Override
    public BigDecimal withdraw(double amount) {
        BigDecimal withdraw = new BigDecimal(amount);
        
        if(withdraw.compareTo(getBalance()) < 0){
            System.out.println("Error...");
            return null;
        }
        
        return withdraw.subtract(getBalance());
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

}
