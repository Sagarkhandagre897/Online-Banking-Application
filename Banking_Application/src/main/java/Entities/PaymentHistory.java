package Entities;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@EntityScan
@Data
public class PaymentHistory {
  
    private int payment_id;
    private int account_id;
    private int user_id;
    private String beneficiary;
    private String beneficiary_acc_no;
    private double amount;
    private String status;
    private String reference_no;
    private String reason_code;
    private LocalDateTime created_at;

}
