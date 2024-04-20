package com.servlet.jsp.crud.opeartion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.servlet.jsp.crud.opeartion.enity.Student;
import com.servlet.jsp.crud.opeartion.service.StudentService;
import com.servlet.jsp.crud.opeartion.service.impl.StudentServiceImpl;

@WebServlet("/updateStudent")
public class UpdateStudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String contactNo = request.getParameter("contactNo");

		StudentService studentService = new StudentServiceImpl();
		Student student = new Student(name, city, contactNo, email);
		studentService.updateStudent(id, student);

		response.sendRedirect(request.getContextPath() + "/" + "getStudents");
	}

}
