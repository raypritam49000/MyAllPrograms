package com.hendisantika.springbootexportimportexcelpoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hendisantika.springbootexportimportexcelpoi.model.User;
import com.hendisantika.springbootexportimportexcelpoi.repository.UserRepository;

@Service
@Transactional
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	public List<User> listAll() {
		return userRepository.findAll(Sort.by("email").ascending());
	}
}
