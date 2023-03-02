package com.DaoImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Dao.AccountDao;

import Entities.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Account> getUserAccountsById(int userId) {
		
		 String query = "CREATE TABLE IF NOT EXISTS  accounts(\r\n"
			  		+ "	 account_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n"
			  		+ "     user_id INT,\r\n"
			  		+ "     account_number VARCHAR(100) NOT NULL,\r\n"
			  		+ "     account_name VARCHAR(50) NOT NULL,\r\n"
			  		+ "     account_type VARCHAR(50) NOT NULL,\r\n"
			  		+ "     balance DECIMAL(18, 2) DEFAULT 0.00,\r\n"
			  		+ "     created_at TIMESTAMP,\r\n"
			  		+ "     updated_at TIMESTAMP DEFAULT NOW(),\r\n"
			  		+ "     FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE\r\n"
			  		+ " )\r\n"
			  		+ " ";
				
				this.jdbcTemplate.update(query);

        List<Account> accounts = this.jdbcTemplate.query("select * from accounts where user_id=?",new BeanPropertyRowMapper<Account>(Account.class),userId);
		
		return accounts; 
	}

	@Override
	public void createBankAccount(int User_id, String bankAccountNumber, String accountName, String accountType) {
		
	  String query = "CREATE TABLE IF NOT EXISTS  accounts(\r\n"
	  		+ "	 account_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n"
	  		+ "     user_id INT,\r\n"
	  		+ "     account_number VARCHAR(100) NOT NULL,\r\n"
	  		+ "     account_name VARCHAR(50) NOT NULL,\r\n"
	  		+ "     account_type VARCHAR(50) NOT NULL,\r\n"
	  		+ "     balance DECIMAL(18, 2) DEFAULT 0.00,\r\n"
	  		+ "     created_at TIMESTAMP,\r\n"
	  		+ "     updated_at TIMESTAMP DEFAULT NOW(),\r\n"
	  		+ "     FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE\r\n"
	  		+ " )\r\n"
	  		+ " ";
		
		this.jdbcTemplate.update(query);
		
		this.jdbcTemplate.update("INSERT INTO accounts(user_id, account_number, account_name, account_type) VALUES(?,?,?,?)",new Object[] {User_id , bankAccountNumber ,accountName , accountType});
	
	}

	@Override
	public Account getAccountBalance(int user_id, int acc_id) {
		// TODO Auto-generated method stub
		
		String query ="SELECT * FROM accounts WHERE user_id =?  AND account_id =?";
		
		Account account = this.jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Account>(Account.class),user_id,acc_id);
		
		return account;
	}

	@Override
	public void changeAccountBalanceById(double newBalance, int acc_id) {

		this.jdbcTemplate.update("UPDATE accounts SET balance =? WHERE account_id =?", new Object[] {newBalance,acc_id});
		
	}

}
