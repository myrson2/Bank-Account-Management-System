package model.user;

import java.util.Random;

public class User {
    private static Random random = new Random();

    private String id;
    private String fullName;
    private String email;

    public User(String fullName, String email){
        this.fullName = fullName;
        this.email = email;
        this.id = idGenerator();
    }

    public User(String fullName, String email, String id){
        this.fullName = fullName;
        this.email = email;
        this.id = id;
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

    public String idGenerator(){
        char letter = (char) ('A' + random.nextInt(26));
        int numbers = random.nextInt(10000);

        String s = String.format("%04d", numbers);

        String idString = letter + s;

        return idString;
    }

}
