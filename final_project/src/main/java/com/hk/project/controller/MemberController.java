package com.hk.project.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
@RequestMapping(value = "/user")
public class MemberController {

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

	@GetMapping(value = "/addUser")
	public String addUserForm(Model model) {
		System.out.println("회원가입폼 이동");

		// 회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서
		// null일경우 오류가 발생하기때문에 보내줘야 함
		model.addAttribute("addUserCommand", new AddUserCommand());

		return "member/addUserForm";
	}

	// 사용자 인증을 완료하면 code들을 반환해준다.
	// 반환받은 code를 이용해서 토큰 발급 요청을 진행한다.
	@GetMapping("/authresult")
	public String authResult(String code, Model model) throws IOException, ParseException {
		System.out.println("인증후 받은 code:" + code);

		HttpURLConnection conn = null;// api에서 제공하는 데이터를 받기 위한 연결 객체
		JSONObject result = null; // 받아온 데이터를 json으로 저장할 객체

		// 인증받고 얻은 code를 통해 토큰을 요청하여 발급받는다.
		URL url = new URL("https://testapi.openbanking.or.kr/oauth/2.0/token?" + "code=" + code
				+ "&client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd"
				+ "&client_secret=3ff7570f-fdfb-4f9e-8f5a-7b549bf2adec"
				+ "&redirect_uri=http://localhost:8087/user/authresult" + "&grant_type=authorization_code");

		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST"); // post로 요청 설정
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true); // 데이터를 가져온다면 true로 설정한다.

		// 데이터를 실제로 가져오는 작업
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		StringBuilder response = new StringBuilder();// 데이터를 저장할 객체
		String responseLine = null;
		while ((responseLine = br.readLine()) != null) {
//				System.out.println(responseLine);
			response.append(responseLine.trim());// 데이터를 문자열에 추가
		}

		// json 형태의 텍스트 데이터를 json객체로 변환하는 작업
		result = (JSONObject) new JSONParser().parse(response.toString());

		// json객체에서 전달받은 값을 가져오기
		String access_token = result.get("access_token").toString();
		String refresh_token = result.get("refresh_token").toString();
		String user_seq_no = result.get("user_seq_no").toString();

		System.out.println("access_token:" + access_token);
		System.out.println("refresh_token:" + refresh_token);
		System.out.println("user_seq_no:" + user_seq_no);

		// 클라이언트로 전달하기 위한 model저장
		model.addAttribute("access_token", access_token);
		model.addAttribute("refresh_token", refresh_token);
		model.addAttribute("user_seq_no", user_seq_no);

		return "member/authresult";
	}

	@PostMapping(value = "/addUser")
	public String addUser(@Validated AddUserCommand adduserCommand, BindingResult result, Model model) {
		System.out.println("회원가입하기");

		if (result.hasErrors()) {
			System.out.println("회원가입 유효값 오류");
			return "member/addUserForm";
		}

		try {
			memberService.addUser(adduserCommand);
			System.out.println(adduserCommand);
			UserMeDto userMeDto = openBankingFeign.requestUserMe("Bearer " + adduserCommand.getUseraccesstoken(),
					adduserCommand.getUserseqno());
			List<UserAccountDto> list = userMeDto.getRes_list();
			System.out.println(list);
			String fintech_use_num = list.get(0).getFintech_use_num();
			String account_num_masked = list.get(0).getAccount_num_masked();
			String bank_name = list.get(0).getBank_name();
			System.out.println(fintech_use_num);
			System.out.println(account_num_masked);
			System.out.println(bank_name);
			AccountDto adto = new AccountDto();
			memberService.addAccount(fintech_use_num, account_num_masked, bank_name, adduserCommand.getId(), adto.getMoney());
			System.out.println("addAccount성공");
			System.out.println("회원가입 성공");
			return "redirect:/";
		} catch (Exception e) {
			System.out.println("회원가입실패");
			e.printStackTrace();
			return "redirect:addUser";
		}

	}

	@ResponseBody
	@GetMapping(value = "/idChk")
	public Map<String, String> idChk(String id) {
		System.out.println("ID중복체크");

		String resultId = memberService.idChk(id);
		// json객체로 보내기 위해 Map에 담아서 응답
		// text라면 그냥 String으로 보내도 됨
		Map<String, String> map = new HashMap<>();
		map.put("id", resultId);
		return map;
	}

	// 로그인 폼 이동
	@GetMapping(value = "/login")
	public String loginForm(Model model) {
		model.addAttribute("loginCommand", new LoginCommand());
		System.out.println("로그인폼 이동");
		return "member/login";
	}

	// 로그인 실행
	@PostMapping(value = "/login")
	public String login(@Validated LoginCommand loginCommand, BindingResult result, Model model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println("로그인 유효값 오류");
			return "member/login";
		}
		MemberDto dto = new MemberDto();
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);

		String path = memberService.login(loginCommand, request, model);
		System.out.println(path);
		return path;
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		request.getSession().invalidate();
		return "redirect:/";
	}

	@GetMapping(value = "/mypage")
	public String mypage(Model model) {
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		
		System.out.println("마이페이지 이동");
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
			
		model.addAttribute("addUserCommand", new AddUserCommand());
		System.out.println(list);

		return "member/mypage";
	}

	@GetMapping(value = "/mypageform")
	public String mypageform(Model model, HttpServletRequest request) {
		System.out.println("내정보");
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
		model.addAttribute("dto", dto);
		return "member/mypageform";
	}

	@PostMapping(value = "/myupdateform")
	public String myupdateform(Model model, HttpServletRequest request) {
		System.out.println("정보수정 폼 이동");
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
		model.addAttribute("dto", dto);
		return "member/myupdateform";
	}

	@PostMapping(value = "/userupdate")
	public String userUpdate(@Validated HttpServletRequest request, UpdateUserCommand updateuserCommand,
			BindingResult result, Model model) {
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
		memberService.userUpdate(updateuserCommand);
		// 유효값처리용
		model.addAttribute("updateuserCommand", new UpdateUserCommand());
		// 출력용
		model.addAttribute("dto", dto);

		try {
			memberService.userUpdate(updateuserCommand);
			System.out.println("update 성공");
			request.getSession().invalidate();
			return "redirect:/";
		} catch (Exception e) {
			System.out.println("update 실패");
			e.printStackTrace();
			return "redirect:myupdateform";
		}

	}

	@GetMapping(value = "/mypopup")
	public String mypopup(Model model) {
		System.out.println("팝업");

		model.addAttribute("addUserCommand", new AddUserCommand());

		return "member/mypopup";
	}

	@PostMapping(value = "/fileInsert")
	public String myfileInsert(@Validated InsertBoardCommand insertBoardCommand, BindingResult result, MemberDto dto,
			MultipartRequest multipartRequest // multipart data를 처리할때 사용
			, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		memberService.insertfile(multipartRequest, dto, request);
			
		return "member/mypopupclose";

	}

	@GetMapping(value = "/myaccount")
	public String myaccount(Model model,  HttpServletRequest request)
			throws MalformedURLException {
		System.out.println("계좌등록 폼 이동");
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		model.addAttribute("dto", dto);
//		프로필 사진 가져오기
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
//		fintech 정보 조회
		MemberDto adto = memberService.getuserAccount(dto);
		model.addAttribute("adto", adto);
		System.out.println(adto);
		AccountDto accountDto = new AccountDto();
//		 필요한 정보 설정
		String useraccesstoken = dto.getUseraccesstoken();
		System.out.println(useraccesstoken);
		String bank_tran_id = "M202201886U" + createNum();
		System.out.println(bank_tran_id);
		String fintech_use_num =adto.getAccountDto().getFintech_use_num();
		System.out.println(fintech_use_num);
		String tran_dtime = getDateTime();
		
		
//		// OpenFeign을 사용하여 잔액 조회
		AccountBalanceDto accountBalDto =openBankingFeign.requestAccountBalance("Bearer " + useraccesstoken,
				bank_tran_id, fintech_use_num, tran_dtime + "");
		System.out.println(accountBalDto.getBalance_amt());
		
        int money = accountDto.getMoney();
        // DecimalFormat을 사용하여 천 단위로 콤마를 넣습니다.
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
//        
        // 천 단위로 콤마를 추가한 문자열로 변환
        
        
        
        model.addAttribute("accountBalDto", accountBalDto);
        System.out.println(accountBalDto);
        
        
		return "member/myaccount";
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
