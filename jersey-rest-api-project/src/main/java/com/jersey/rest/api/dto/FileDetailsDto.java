package com.jersey.rest.api.dto;

public class FileDetailsDto {
	private String id;
	private String fileName;
	private String fileType;
	private String fileUri;
	private String filePath;

	public FileDetailsDto() {
		super();
	}

	public FileDetailsDto(String fileName, String fileType, String fileUri, String filePath) {
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
		return "FileDetailsDto [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileUri=" + fileUri
				+ ", filePath=" + filePath + "]";
	}

}
