package com.jersey.rest.api.mappers;

import com.jersey.rest.api.dto.UserDto;
import com.jersey.rest.api.entity.User;

public class UserMapper {

	public static UserDto toDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getCity());
	}

	public static User toEntity(UserDto userDto) {
		return new User(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword(),
				userDto.getCity());
	}
}
