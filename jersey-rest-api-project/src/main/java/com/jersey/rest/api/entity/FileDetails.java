package com.jersey.rest.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table
@Entity
public class FileDetails {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	private String fileName;
	private String fileType;
	private String fileUri;
	private String filePath;

	public FileDetails() {
		super();
	}

	public FileDetails(String fileName, String fileType, String fileUri, String filePath) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileUri = fileUri;
		this.filePath = filePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "FileDetails [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileUri=" + fileUri
				+ ", filePath=" + filePath + "]";
	}

}
