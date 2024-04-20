package com.jersey.rest.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jersey.rest.api.dbConfig.HibernateUtils;
import com.jersey.rest.api.dto.FileDetailsDto;
import com.jersey.rest.api.entity.FileDetails;
import com.jersey.rest.api.utils.FileUploadUtility;

@Path("/files")
@RequestScoped
public class FileUploadController {

	@Inject
	private UriInfo useUriInfo;

	@POST
	@Path("/imageupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImage(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) throws Exception {

		String filenName = UUID.randomUUID().toString() + "_" + fileDetails.getFileName();

		FileTypeMap fileTypeMap = new MimetypesFileTypeMap();

		String fileType = fileTypeMap.getContentType(fileDetails.getFileName());

		File file = new File("D://uploads");

		if (!file.exists()) {
			file.mkdir();
		}

		String uploadedFileLocation = "D://uploads" + File.separator + filenName;

		// Save the file to the server
		FileUploadUtility.saveToFile(uploadedInputStream, uploadedFileLocation);

		// Build the image URI
		String fileUri = useUriInfo.getBaseUri().toString() + "files/image/" + filenName;

		FileDetailsDto fileDetailsDto = new FileDetailsDto(filenName, fileType, fileUri, uploadedFileLocation);

		FileDetails fDetails = new FileDetails(fileDetailsDto.getFileName(), fileDetailsDto.getFileType(),
				fileDetailsDto.getFileUri(), fileDetailsDto.getFilePath());

		Session session = HibernateUtils.getsessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(fDetails);

		transaction.commit();

		return Response.status(200).status(Status.OK)
				.entity(Map.of("status", 200, "success", true, "message", "file upload successfully")).build();
	}

	@GET
	@Path("/image/{filename}")
	@Produces("image/*")
	public Response getImage(@PathParam("filename") String filename) {
		try {
			// Adjust this path to the directory where your uploaded images are stored
			String imagePath = "D://uploads" + File.separator + filename;

			// Read the image file
			File file = new File(imagePath);
			FileInputStream fileInputStream = new FileInputStream(file);

			// Detect file type
			FileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String contentType = fileTypeMap.getContentType(file);

			// Return the image as response
			return Response.ok(fileInputStream, contentType)
					.header("Content-Disposition", "inline; filename=\"" + filename + "\"").build();

		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error serving image").build();
		}
	}

}