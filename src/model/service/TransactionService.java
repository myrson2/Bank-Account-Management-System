package model.service;

import java.util.ArrayList;

import model.transaction.Transaction;

public class TransactionService {
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public Transaction addTransaction(Transaction transaction){
        transactions.add(transaction);
        return transaction;
    }

    public void viewTransactionHistory(){
        for (Transaction t : transactions) {
            System.out.printf("Transac Type: %s || Amount: %.2f || Time: %s || Description: %s\n", t.getType(), t.getAmount(), t.getTimestamp(), t.getDescription());
        }
    }
}
