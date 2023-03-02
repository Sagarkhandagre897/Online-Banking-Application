package com.DaoImpls;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Dao.PaymentDao;

@Repository
public class PaymentDaoImpls implements PaymentDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void makePayment(int account_id, String beneficiary, String beneficiary_acc_no, double amount,
			String reference_no, String status, String reason_code, LocalDateTime created_at) {
		
		String query= "CREATE TABLE IF NOT EXISTS payments(\r\n"
				+ "	payment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "    account_id INT,\r\n"
				+ "    beneficiary VARCHAR(50) NULL,\r\n"
				+ "    beneficiary_acc_no VARCHAR(255) NULL,\r\n"
				+ "    amount DECIMAL(18, 2) NULL,\r\n"
				+ "    reference_no VARCHAR(100) NULL,\r\n"
				+ "    status VARCHAR(50) NULL, -- success / failed\r\n"
				+ "    reason_code VARCHAR(100) NULL, -- INSUFFICIENT FUNDS\r\n"
				+ "    created_at TIMESTAMP,    \r\n"
				+ "    FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE\r\n"
				+ " )";
		
		this.jdbcTemplate.update(query);
		
		this.jdbcTemplate.update(" INSERT INTO payments(account_id, beneficiary, beneficiary_acc_no, amount, reference_no, status, reason_code, created_at) VALUES(?,?,?,?,?,?,?,?)", new Object[] {account_id, beneficiary, beneficiary_acc_no,amount,
				 reference_no, status, reason_code, created_at});
		
		
	}	
}
