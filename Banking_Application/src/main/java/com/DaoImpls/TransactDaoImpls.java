package com.DaoImpls;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Dao.TransactDao;

@Repository
public class TransactDaoImpls implements TransactDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void logTransaction(int account_id, String transact_type, double amount, String source, String status,
			String reason_code, LocalDateTime created_at) {

        String query = "CREATE TABLE IF NOT EXISTS transaction_history(\r\n"
        		+ "	transaction_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n"
        		+ "    account_id INT,\r\n"
        		+ "    transaction_type VARCHAR(50) NOT NULL,\r\n"
        		+ "    amount DECIMAL(18, 2),\r\n"
        		+ "    source VARCHAR(50) NULL,\r\n"
        		+ "    status VARCHAR(50) NULL, -- success / failed\r\n"
        		+ "    reason_code VARCHAR(100) NULL, -- INSUFFICIENT FUNDS\r\n"
        		+ "    created_at TIMESTAMP,\r\n"
        		+ "    FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE\r\n"
        		+ " )";
		
        this.jdbcTemplate.update(query);
        
        this.jdbcTemplate.update("INSERT INTO transaction_history(account_id, transaction_type, amount, source, status, reason_code, created_at)" +
                "VALUES(?,?,?,?,?,?,?)",new Object[] {account_id,  transact_type, amount, source, status,
            			reason_code, created_at} );
		
	}
	
	
}
