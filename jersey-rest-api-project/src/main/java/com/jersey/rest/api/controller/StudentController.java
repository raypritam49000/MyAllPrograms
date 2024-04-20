package com.jersey.rest.api.controller;

import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;

import com.jersey.rest.api.dto.StudentDto;
import com.jersey.rest.api.filters.JWTTokenNeeded;
import com.jersey.rest.api.service.StudentService;
import com.jersey.rest.api.service.impl.StudentServiceImpl;

@Path("/students")
@JWTTokenNeeded
public class StudentController {
	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@GET
	@Path("/hello")
	public String hello(@Context SecurityContext securityContext) {
		LOGGER.info("====>>> Hello World "+securityContext.getUserPrincipal().getName());
		return "Hello World";
	}

	@POST
	@Path("/addStudent")
	public Response createStudent(StudentDto studentDto) {
		StudentService studentService = new StudentServiceImpl();
		studentService.createStudent(studentDto);
		return Response
				.ok(Map.of("success", true, "status", "Created", "statusCode", 201, "message",
						"Student has been created"), MediaType.APPLICATION_JSON)
				.status(201).status(Status.CREATED).build();
	}

	@GET
	@Path("/getStudents")
	public Response getStudents() {
		StudentService studentService = new StudentServiceImpl();
		return Response
				.ok(Map.of("success", true, "status", "Success", "statusCode", 200, "message", "Student Details",
						"data", studentService.getStudents()), MediaType.APPLICATION_JSON)
				.status(200).status(Status.OK).build();
	}

	@GET
	@Path("/getStudent/{id}")
	public Response getStudent(@PathParam("id") String id) {
		StudentService studentService = new StudentServiceImpl();
		return Response
				.ok(Map.of("success", true, "status", "Success", "statusCode", 200, "message", "Student Details",
						"data", studentService.getStudent(id)), MediaType.APPLICATION_JSON)
				.status(200).status(Status.OK).build();
	}

	@DELETE
	@Path("/deleteStudent/{id}")
	public Response deleteStudent(@PathParam("id") String id) {
		StudentService studentService = new StudentServiceImpl();
		return Response.ok(Map.of("success", true, "status", "Success", "statusCode", 200, "message",
				"Student has been Deleted", "isDeleted", studentService.deleteStudent(id)), MediaType.APPLICATION_JSON)
				.status(200).status(Status.OK).build();
	}

	@PUT
	@Path("/updateStudent/{id}")
	public Response updateStudent(@PathParam("id") String id, StudentDto studentDto) {
		StudentService studentService = new StudentServiceImpl();
		studentService.updateStudent(id, studentDto);
		return Response.ok(Map.of("success", true, "status", "Success", "statusCode", 200, "message", "Student has been updated"),
				MediaType.APPLICATION_JSON).status(200).status(Status.OK).build();
	}

}
