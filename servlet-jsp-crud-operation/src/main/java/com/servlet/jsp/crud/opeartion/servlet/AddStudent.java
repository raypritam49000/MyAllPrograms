package com.servlet.jsp.crud.opeartion.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.servlet.jsp.crud.opeartion.enity.Student;
import com.servlet.jsp.crud.opeartion.service.StudentService;
import com.servlet.jsp.crud.opeartion.service.impl.StudentServiceImpl;

@WebServlet("/addStudent")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String contactNo = request.getParameter("contactNo");

		Part filePart = request.getPart("profile");
		String fileName = filePart.getSubmittedFileName();
		InputStream is = filePart.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);

		File file = new File("D://uploads");

		if (!file.exists()) {
			file.mkdir();
		}

		String filePath = "D://uploads" + File.separator + fileName;

		FileOutputStream fos = new FileOutputStream(filePath);

		fos.write(data);
		
		fos.flush();
		fos.close();

		System.out.println(name + " " + email + " " + city + " " + contactNo + " " + filePart + " " + fileName);

		Student student = new Student(name, city, contactNo, email, fileName, Base64.getEncoder().encodeToString(data));
		StudentService studentService = new StudentServiceImpl();
		studentService.createStudent(student);
		
		response.sendRedirect(request.getContextPath()+"/getStudents");
		
	}

}
