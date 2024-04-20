package com.mcnz.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class FileController {

	@Autowired
	private Environment env;

	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();

		try {

			String filePath = env.getProperty("filePath");

			File f = new File(filePath);

			if (!f.exists()) {
				f.mkdir();
			}

			file.transferTo(new File(filePath + fileName));

			System.out.println(generateImageUrl(fileName, request));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("File uploaded successfully.");

	}

	@GetMapping("/download/image/{fileName:.+}")
	public ResponseEntity<Resource> downloadImage(@PathVariable String fileName, HttpServletRequest request) {
		String filePath = env.getProperty("filePath");

		Path file = Paths.get(filePath).resolve(fileName);

		try {
			Resource resource = new UrlResource(file.toUri());

			String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

			if (resource.exists()) {
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
						.header(HttpHeaders.CONTENT_DISPOSITION,
								"attachment; filename=\"" + resource.getFilename() + "\"")
						.body(resource);

			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

	private String generateImageUrl(String fileName, HttpServletRequest request) {
		return UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString())
				.replacePath("/download/image/" + fileName).toUriString();
	}

}
