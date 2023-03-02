package Entities;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@EntityScan
@Data
public class TransactionHistory {


    private int transaction_id;
    private int account_id;
    private int user_id;
    private String transaction_type;
    private double amount;
    private String  source;
    private String  status;
    private String  reason_code;
    private LocalDateTime created_at;
    
}