package com.Dao;

import java.util.List;

import Entities.TransactionHistory;

public interface TransactionHistoryDao {

	public List<TransactionHistory> getTransactionRecordsById(int user_id);
	
}
