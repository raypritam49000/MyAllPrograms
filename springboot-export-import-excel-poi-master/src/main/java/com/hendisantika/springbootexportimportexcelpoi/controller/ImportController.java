package com.hendisantika.springbootexportimportexcelpoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hendisantika.springbootexportimportexcelpoi.utils.ExcelGenerator;

@Controller
public class ImportController {

	@Autowired
	private ExcelGenerator excel;

	@GetMapping("/import")
	public String createGetImport() {
		return "import";
	}

	@PostMapping("/import")
	public String createPostImport(@RequestParam(name = "file") MultipartFile file) throws Exception {
		 excel.importExcel(file);
		return "redirect:/import";
	}
}
