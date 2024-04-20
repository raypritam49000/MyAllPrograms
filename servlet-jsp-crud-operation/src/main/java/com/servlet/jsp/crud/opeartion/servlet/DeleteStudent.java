package com.servlet.jsp.crud.opeartion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.jsp.crud.opeartion.service.StudentService;
import com.servlet.jsp.crud.opeartion.service.impl.StudentServiceImpl;

@WebServlet("/deleteStudent/*")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		String id = pathInfo[1];
		StudentService studentService = new StudentServiceImpl();
		boolean isDeleted = studentService.deleteStudent(id);

		if (isDeleted) {
			response.sendRedirect(request.getContextPath() + "/getStudents");
		}

	}
}
