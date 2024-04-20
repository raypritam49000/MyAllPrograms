package com.jersey.rest.api.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUploadUtility {
	
	
	// Helper method to save the uploaded file to the server
	public static void saveToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
		try (OutputStream out = new FileOutputStream(new java.io.File(uploadedFileLocation))) {
			int read;
			byte[] bytes = new byte[1024];
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		}
	}
	
}
