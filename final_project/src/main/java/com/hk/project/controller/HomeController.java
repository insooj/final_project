package com.hk.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	private MemberService memberService;
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@GetMapping(value = "/")
	public String home(Model model) {
		MemberDto dto= new MemberDto();
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list",list);
		logger.info("HOME페이지이동");
		return "home";
	}
	
}


