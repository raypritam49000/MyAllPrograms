package com.jersey.rest.api.dto;

public class UserDto {

	private String id;
	
//	@NotNull(message = "Username cannot be null")
	private String username;
	
//	@NotNull(message = "Email cannot be null")
	private String email;
	
//	@NotNull(message = "Password cannot be null")
	private String password;
	
//	@NotNull(message = "City cannot be null")
	private String city;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String id, String username, String email, String password, String city) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.city = city;
	}

	public UserDto(String username, String email, String password, String city) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", city=" + city + "]";
	}

}
