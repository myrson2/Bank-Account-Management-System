package model.service;

import java.util.ArrayList;

import model.account.Account;
import model.account.SavingsAccount;
import model.account.CurrentAccount;
import model.user.User;

public class AccountService {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    public void createUser(User user){
        users.add(user);

        System.out.println("Done Adding.");
    }

    public void openSavingsAccount(SavingsAccount savings){
        accounts.add(savings);
    }

    public void openCurrentAccount(CurrentAccount current){
        accounts.add(current);
    }

    // getters 
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
