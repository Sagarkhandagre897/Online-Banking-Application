package com.Dao;

import java.util.List;

import Entities.Account;

public interface AccountDao {

	public List<Account> getUserAccountsById(int userId);
	
	public void createBankAccount(int User_id ,String bankAccountNumber, String accountName, String accountType );

	public Account getAccountBalance(int user_id, int acc_id);
	
	public void changeAccountBalanceById(double newBalance,int  acc_id);
}
