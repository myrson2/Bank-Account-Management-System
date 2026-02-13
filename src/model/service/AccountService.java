package model.service;

import java.util.ArrayList;

import model.account.Account;
import model.account.SavingsAccount;
import model.account.CurrentAccount;
import model.user.User;

public class AccountService {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    public User createUser(User user){
        users.add(user);
        return user;
    }

    public SavingsAccount openSavingsAccount(SavingsAccount savings){
        accounts.add(savings);
        return savings;
    }

    public CurrentAccount openCurrentAccount(CurrentAccount current){
        accounts.add(current);
        return current;
    }

    // getters 
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
