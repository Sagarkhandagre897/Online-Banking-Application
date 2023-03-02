package com.Dao;

import java.util.List;

import Entities.PaymentHistory;

public interface PaymentHistoryDao {

	public List<PaymentHistory> getPaymentRecordsById(int user_id);
	
}
