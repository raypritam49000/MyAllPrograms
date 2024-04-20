package com.hendisantika.springbootexportimportexcelpoi.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hendisantika.springbootexportimportexcelpoi.model.Student;
import com.hendisantika.springbootexportimportexcelpoi.repository.StudentRepository;
import com.hendisantika.springbootexportimportexcelpoi.utils.ExcelGenerator;
import com.hendisantika.springbootexportimportexcelpoi.utils.ExcelUtility;

@Controller
public class ImportApiController {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ExcelGenerator excel;

	@Autowired
	private ExcelUtility<Student> excelUtility;

	@GetMapping("/export")
	public ResponseEntity<Resource> excelStudentReport() throws Exception {
		List<Student> studentList = studentRepository.findAll();

		//ByteArrayInputStream in = excel.exportExcel(studentList);

		String[] columns = { "Id", "Name", "Kelas", "Jurusan" };

		ByteArrayInputStream in = excelUtility.exportExcel(studentList, columns);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=students.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

}
