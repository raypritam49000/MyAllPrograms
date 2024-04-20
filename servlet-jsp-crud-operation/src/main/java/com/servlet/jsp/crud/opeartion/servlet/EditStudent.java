package com.servlet.jsp.crud.opeartion.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.jsp.crud.opeartion.enity.Student;
import com.servlet.jsp.crud.opeartion.service.StudentService;
import com.servlet.jsp.crud.opeartion.service.impl.StudentServiceImpl;

@WebServlet("/editStudent")
public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");	
		
		StudentService studentService = new StudentServiceImpl();
		Student student = studentService.getStudent(id);
		request.setAttribute("student", student);

		RequestDispatcher dispatcher = request.getRequestDispatcher("updateStudent.jsp");
		dispatcher.forward(request, response);
	
	}
	
	

}
