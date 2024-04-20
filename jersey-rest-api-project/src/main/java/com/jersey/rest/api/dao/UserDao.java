package com.jersey.rest.api.dao;


import com.jersey.rest.api.entity.User;

public interface UserDao {
	public User findUserByEmail(String email);
	public void createUser(User user);	
}
