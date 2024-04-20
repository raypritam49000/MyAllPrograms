package com.jersey.rest.api.controller;

import java.util.Map;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mindrot.jbcrypt.BCrypt;

import com.jersey.rest.api.dto.UserDto;
import com.jersey.rest.api.service.UserService;
import com.jersey.rest.api.service.impl.UserServiceImpl;
import com.jersey.rest.api.utils.JwtUtility;

@Path("/auth")
public class AuthenticationController {

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(UserDto userDto) {
		
		System.out.println("@@@ UserDto ::: "+userDto);
		
		UserService userService = new UserServiceImpl();
		UserDto existingUser = userService.findUserByEmail(userDto.getEmail());
		if (Objects.nonNull(existingUser)) {
			return Response
					.ok(Map.of("status", 409, "success", false, "message",
							"User already exists with given email " + userDto.getEmail()))
					.status(409).status(Status.CONFLICT).build();
		}

		userService.createUser(userDto);
		return Response.ok(Map.of("status", 200, "success", true, "message", "User has been register")).status(201)
				.status(Status.CREATED).build();

	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserDto userDto) {

		try {
			UserService userService = new UserServiceImpl();
			UserDto existingUser = userService.findUserByEmail(userDto.getEmail());

			if (Objects.isNull(existingUser)) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity(Map.of("statusCode", 404, "message", "User Not Found", "success", false)).build();
			} else {
				if (BCrypt.checkpw(userDto.getPassword(), existingUser.getPassword())) {

					String token = JwtUtility.generateToken(existingUser);

					return Response.status(Response.Status.OK).entity(Map.of("statusCode", 200, "token", token, "type",
							"Bearer", "success", true, "body", existingUser)).build();
				} else {
					return Response.status(Response.Status.BAD_REQUEST)
							.entity(Map.of("statusCode", 400, "success", false, "message", "Bad Request")).build();
				}
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(Map.of("statusCode", 501, "success", true, "message", "Internal Server Error")).build();
		}
	}

}
