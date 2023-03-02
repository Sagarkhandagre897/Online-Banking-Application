package com.DaoImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Dao.TransactionHistoryDao;


import Entities.TransactionHistory;
@Repository
public class TransactionHistoryDaoImpls implements TransactionHistoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<TransactionHistory> getTransactionRecordsById(int user_id) {
		
		List<TransactionHistory> transactions = this.jdbcTemplate.query("SELECT \r\n"
				+ "	t.transaction_id,\r\n"
				+ "    a.account_id,\r\n"
				+ "    u.user_id,\r\n"
				+ "    t.transaction_type,\r\n"
				+ "    t.amount,\r\n"
				+ "    t.source,\r\n"
				+ "    t.status,\r\n"
				+ "    t.reason_code,\r\n"
				+ "    t.created_at\r\n"
				+ "FROM\r\n"
				+ "	transaction_history AS t\r\n"
				+ "INNER JOIN\r\n"
				+ "	accounts AS a\r\n"
				+ "ON\r\n"
				+ "	t.account_id = a.account_id\r\n"
				+ "INNER JOIN\r\n"
				+ "	userS as u\r\n"
				+ "ON\r\n"
				+ "	a.user_id = u.user_id\r\n"
				+ "    WHERE u.user_id=?", new BeanPropertyRowMapper<TransactionHistory>(TransactionHistory.class), user_id);
			
		return transactions;
	}
	
	
}
