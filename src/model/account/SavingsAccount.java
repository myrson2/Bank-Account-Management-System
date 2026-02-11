package model.account;

import java.math.BigDecimal;
import model.user.User;

public class SavingsAccount extends Account{
    // private double interestRate;

    public SavingsAccount(User owner,  BigDecimal balance){
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

    // @Override
    // public BigDecimal getBalance() {
    //     // default 0
        
    //     return balance;
    // }
}
