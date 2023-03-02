package com.Dao;

import java.time.LocalDateTime;

public interface TransactDao {

	public void logTransaction(int account_id,
            String transact_type,
            double amount,
           String source,
            String status,
            String reason_code,
             LocalDateTime created_at);
}
