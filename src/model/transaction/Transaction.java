package model.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(TransactionType type, BigDecimal amount, String description){
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }


    // getters 
    public BigDecimal getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
