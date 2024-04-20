package com.jersey.rest.api.service;

import com.jersey.rest.api.dto.UserDto;


public interface UserService {
	public UserDto findUserByEmail(String email);
	public void createUser(UserDto userDto);
}
