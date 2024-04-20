package com.hendisantika.springbootexportimportexcelpoi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hendisantika.springbootexportimportexcelpoi.service.StudentService;

@SpringBootApplication
public class SpringbootExportImportExcelPoiApplication implements ApplicationRunner {

	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootExportImportExcelPoiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		//studentService.insertRandomData();

	}

}
