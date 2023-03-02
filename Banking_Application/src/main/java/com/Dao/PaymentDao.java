package com.Dao;

import java.time.LocalDateTime;

public interface PaymentDao {

	public void makePayment(  int account_id,
     String beneficiary,
     String beneficiary_acc_no,
     double amount,
     String reference_no,
     String status,
     String reason_code,
    LocalDateTime created_at);
	
	
	
}
