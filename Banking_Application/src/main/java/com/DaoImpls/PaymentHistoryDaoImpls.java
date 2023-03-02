package com.DaoImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Dao.PaymentHistoryDao;



import Entities.PaymentHistory;

@Repository
public class PaymentHistoryDaoImpls implements PaymentHistoryDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PaymentHistory> getPaymentRecordsById(int user_id) {
		

		List<PaymentHistory> payments =this.jdbcTemplate.query("SELECT\r\n"
				+ "	p.payment_id,\r\n"
				+ "    a.account_id,\r\n"
				+ "    u.user_id,\r\n"
				+ "    p.beneficiary,\r\n"
				+ "    p.beneficiary_acc_no,\r\n"
				+ "    p.amount,\r\n"
				+ "    p.status,\r\n"
				+ "    p.reference_no,\r\n"
				+ "    p.reason_code,\r\n"
				+ "    p.created_at\r\n"
				+ "FROM\r\n"
				+ " payments AS p \r\n"
				+ "INNER JOIN\r\n"
				+ "	accounts AS a\r\n"
				+ " ON\r\n"
				+ "	p.account_id = a.account_id\r\n"
				+ "INNER JOIN\r\n"
				+ "	users AS u\r\n"
				+ "ON\r\n"
				+ "	a.user_id = u.user_id\r\n"
				+ " WHERE u.user_id=?", new BeanPropertyRowMapper<PaymentHistory>(PaymentHistory.class), user_id);
			
		return payments;
}
}
