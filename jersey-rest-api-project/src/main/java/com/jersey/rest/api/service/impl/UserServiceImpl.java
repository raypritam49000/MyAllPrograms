package com.jersey.rest.api.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import com.jersey.rest.api.dao.UserDao;
import com.jersey.rest.api.dao.impl.UserDaoImpl;
import com.jersey.rest.api.dto.UserDto;
import com.jersey.rest.api.mappers.UserMapper;
import com.jersey.rest.api.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public UserDto findUserByEmail(String email) {
		UserDao userDao = new UserDaoImpl();
		return UserMapper.toDto(userDao.findUserByEmail(email));
	}

	@Override
	public void createUser(UserDto userDto) {
	    userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		UserDao userDao = new UserDaoImpl();
		userDao.createUser(UserMapper.toEntity(userDto));
	}

}
