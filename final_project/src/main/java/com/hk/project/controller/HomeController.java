package com.hk.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	Logger logger=LoggerFactory.getLogger(getClass());
	
	@GetMapping(value = "/")
	public String home() {
		logger.info("HOME페이지이동");
		return "home";
	}
	
	@GetMapping(value = "/loginhome")
	public String main() {
		logger.info("MAIN페이지이동");
		return "loginhome";
	}
}


