package com.servlet.jsp.crud.opeartion.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table
@Entity
public class Student {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	private String name;
	private String city;
	private String contactNo;
	private String email;
	private String fileName;
	@Column(length = 100000000)
	private String fileBase64Data;

	public Student() {
		super();
	}
	
	
	public Student(String name, String city, String contactNo, String email) {
		super();
		this.name = name;
		this.city = city;
		this.contactNo = contactNo;
		this.email = email;
	}



	public Student(String name, String city, String contactNo, String email, String fileName, String fileBase64Data) {
		this.name = name;
		this.city = city;
		this.contactNo = contactNo;
		this.email = email;
		this.fileName = fileName;
		this.fileBase64Data = fileBase64Data;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFileBase64Data() {
		return fileBase64Data;
	}

	public void setFileBase64Data(String fileBase64Data) {
		this.fileBase64Data = fileBase64Data;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + ", contactNo=" + contactNo + ", email="
				+ email + ", fileName=" + fileName + ", fileBase64Data=" + fileBase64Data + "]";
	}

	
}
