package com.DaoImpls;

import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Dao.userDao;

import Entities.User;
@Repository
public class DaoImpls  implements userDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void createUser(User user , String token , int code , String hashed_password) {
		
		String query = "CREATE TABLE IF NOT EXISTS users(user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY , first_name VARCHAR(50) NOT NULL,last_name VARCHAR(50) NOT NULL,email VARCHAR(255) NOT NULL UNIQUE,password VARCHAR(255) NOT NULL,token VARCHAR(255) NULL,code INT NULL,verified INT DEFAULT 0,verified_at DATETIME,created_at TIMESTAMP,updated_at TIMESTAMP DEFAULT NOW())";
		this.jdbcTemplate.update(query);
		
		this.jdbcTemplate.update("INSERT INTO users(user_id,first_name,last_name,email,password,token,code) values(?,?,?,?,?,?,?)", new Object[] {user.getUser_id(),user.getFirst_name(),user.getLast_name(),user.getEmail(),hashed_password,token,code} );
		
	}
	@Override
	public List<User> getUser() {
		

		String query = "CREATE TABLE IF NOT EXISTS users(user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY , first_name VARCHAR(50) NOT NULL,last_name VARCHAR(50) NOT NULL,email VARCHAR(255) NOT NULL UNIQUE,password VARCHAR(255) NOT NULL,token VARCHAR(255) NULL,code INT NULL,verified INT DEFAULT 0,verified_at DATETIME,created_at TIMESTAMP,updated_at TIMESTAMP DEFAULT NOW())";
		this.jdbcTemplate.update(query);
		
		List<User> users = this.jdbcTemplate.query("select * from users" , new BeanPropertyRowMapper<User>(User.class));
		
		return users;
	}
	@Override
	public User checkToken(String token) {
		
		String query = "select * from users where token=?";
		
		User user = this.jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<User>(User.class),token);
		return user;
	}
	@Override
	public void verifyAccount(String token, int code) {
		
		 this.jdbcTemplate.update("UPDATE users SET token=null, code=null, verified=1, verified_at=NOW(), updated_at=NOW() WHERE token =? AND code=?",new Object[] {token, code});
		
	}
	@Override
	public User getUserByEmail(String email) {
		
		String query = "select * from users where email=?";
		
		User user = this.jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<User>(User.class),email);
		
		return user;
	}
}

