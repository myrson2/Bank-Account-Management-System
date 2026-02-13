package model.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;

    
}
