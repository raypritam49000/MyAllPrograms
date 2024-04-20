package com.hendisantika.springbootexportimportexcelpoi.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hendisantika.springbootexportimportexcelpoi.model.User;
import com.hendisantika.springbootexportimportexcelpoi.service.UserServices;
import com.hendisantika.springbootexportimportexcelpoi.utils.CsvExporter;
import com.hendisantika.springbootexportimportexcelpoi.utils.CsvGeneratorUtil;
import com.hendisantika.springbootexportimportexcelpoi.utils.ExcelUtility;
import com.hendisantika.springbootexportimportexcelpoi.utils.UserExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private ExcelUtility<User> excelUtility;

	@GetMapping("/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<User> listUsers = userServices.listAll();

		UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/export")
	public ResponseEntity<Resource> excelStudentReport() throws Exception {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		List<User> listUsers = userServices.listAll();

		String[] columns = { "User ID", "E-mail", "Password", "Full Name", "Enabled", "Roles" };

		ByteArrayInputStream in = excelUtility.exportExcel(listUsers, columns);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=users_" + currentDateTime + ".xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@ResponseBody
	@GetMapping("/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<User> listUsers = userServices.listAll();

		String[] csvHeader = { "User ID", "E-mail", "Full Name", "Roles", "Enabled" };
		String[] nameMapping = { "id", "email", "fullName", "roles", "enabled" };

		CsvExporter<User> csvExporter = new CsvExporter<>();
		csvExporter.exportToCsv(response, "users", listUsers, csvHeader, nameMapping);
	}

	@GetMapping("/csv")
	public ResponseEntity<byte[]> generateCsvFile() {

		String fileName = "users" + new Date().getTime() + ".csv";

		List<User> listUsers = userServices.listAll();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);

		String csvHeader = "User ID, E-mail, Password, Full Name, Enabled, Roles";
		List<Function<User, Object>> fieldExtractors = List.of(User::getId, User::getEmail, User::getPassword,
				User::getFullName, User::isEnabled, User::getRoles);
		
		CsvGeneratorUtil<User> csvGeneratorUtil = new CsvGeneratorUtil<>(fieldExtractors);
		byte[] csvBytes = csvGeneratorUtil.generateCsv(csvHeader, listUsers);

		return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
	}

}
