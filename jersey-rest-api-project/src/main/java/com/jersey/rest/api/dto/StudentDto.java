package com.jersey.rest.api.dto;

public class StudentDto {
	private String id;
	private String name;
	private String city;
	private String contactNo;
	private String email;

	public StudentDto() {
		super();
	}

	public StudentDto(String name, String city, String contactNo, String email) {
		super();
		this.name = name;
		this.city = city;
		this.contactNo = contactNo;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + ", contactNo=" + contactNo + ", email="
				+ email + "]";
	}

}