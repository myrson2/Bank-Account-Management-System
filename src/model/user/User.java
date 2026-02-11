package model.user;

import java.util.ArrayList;
import java.util.Random;
import model.account.Account;

public class User {
    private static Random random = new Random();

    private String id;
    private String fullName;
    private String email;

    private ArrayList<Account> accounts = new ArrayList<>();

    public User(String fullName, String email){
        this.fullName = fullName;
        this.email = email;
        this.id = idGenerator();
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public String idGenerator(){
        char letter = (char) ('A' + random.nextInt(26));
        int numbers = random.nextInt(10000);

        String s = String.format("%04d", numbers);

        String idString = letter + s;

        return idString;
    }

    public void addAccount(Account acc){
        accounts.add(acc);
        System.out.println("Added Account.");
    }

}
