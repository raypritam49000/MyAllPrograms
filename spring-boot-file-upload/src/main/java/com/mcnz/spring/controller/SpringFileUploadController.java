package com.mcnz.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringFileUploadController {

	@GetMapping("/index")
	public String hello() {
		return "uploader";
	}

}
