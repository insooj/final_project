package com.hk.project.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.apidto.AccountBalanceDto;
import com.hk.project.apidto.AccountTransactionDto;
import com.hk.project.apidto.AccountTransactionListDto;
import com.hk.project.apidto.UserAccountDto;
import com.hk.project.apidto.UserMeDto;
import com.hk.project.command.AddUserCommand;
import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.LoginCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.command.UpdatePasswordCommand;
import com.hk.project.command.UpdateUserCommand;
import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.feignMapper.OpenBankingFeign;
import com.hk.project.service.FileService;
import com.hk.project.service.FileUserService;
import com.hk.project.service.MemberService;

import groovyjarjarantlr4.v4.runtime.ParserInterpreter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/salary")
public class SalaryController {

	@Autowired
	private OpenBankingFeign openBankingFeign;
	@Autowired
	private MemberService memberService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private FileService fileService;
	@Autowired
	private FileUserService fileUserService;

	@GetMapping(value = "/salaryinfo")
	public String mypage(Model model) {
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		System.out.println("급여정보 이동");
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
		
//		fintech 정보 조회
		String useraccesstoken = dto.getUseraccesstoken();
			System.out.println(useraccesstoken);
		MemberDto adto = memberService.getuserAccount(dto);
		String user_seq_no = dto.getUserseqno();
			System.out.println(user_seq_no);
		String sort_order ="D";
			String bank_tran_id = "M202201886U" + createNum();
			System.out.println(bank_tran_id);
		String fintech_use_num =adto.getAccountDto().getFintech_use_num();
			System.out.println(fintech_use_num);
		String inquiry_type ="A";
		String inquiry_base = "D";
		String from_date = "20190101";
		String to_date = "20190101";
		String tran_dtime = getDateTime();
		

		
		AccountTransactionListDto accountTransListDto =openBankingFeign.requestAccountTransactionList("Bearer " +useraccesstoken, bank_tran_id,
				fintech_use_num, inquiry_type, inquiry_base,from_date,to_date,sort_order,tran_dtime+"");
		
		System.out.println(accountTransListDto);
		
		return "salary/salaryinfo";
	}


	// 이용기관 부여번호 9자리를 생성하는 메서드
	public String createNum() {
		String createNum = "";
		for (int i = 0; i < 9; i++) {
			createNum += ((int) (Math.random() * 10)) + "";
		}
		System.out.println("이용기관부여번호9자리생성:" + createNum);
		return createNum;
	}

	// 현재시간 구하는 메서드
	public String getDateTime() {
		LocalDateTime now = LocalDateTime.now(); // 현재시간 구하기
		String formatNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		return formatNow;
	}
}
