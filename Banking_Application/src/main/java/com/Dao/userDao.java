package com.Dao;

import java.util.List;

import Entities.User;

public interface userDao {

	public void createUser(User user, String toekn, int code, String hashed_password); 
	
	public List<User> getUser();
	
	public User checkToken(String token);
	
	public void verifyAccount(String token, int  code);

	public User getUserByEmail(String email);
	
	
}
