package com.hk.project.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.command.AddUserCommand;
import com.hk.project.command.DelBoardCommand;
import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.CalDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.dtos.PaymentDto;
import com.hk.project.service.BoardService;
import com.hk.project.service.FileService;
import com.hk.project.service.ICalService;
import com.hk.project.service.MemberService;
import com.hk.project.service.PayService;
import com.hk.project.service.UserListService;
import com.hk.project.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/manage")
public class MangementController {
	@Autowired
	private ICalService calService;
	@Autowired
	private UserListService userlistService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PayService payService;

	@GetMapping(value = "/pay")
	public String pay(@Validated AddUserCommand adduserCommand, BindingResult result, String name, String id,
			Model model) {
		// 이름을 사용하여 MemberDto를 가져옴
		MemberDto dto = memberService.getuserDetail(id);

		// AddUserCommand에서 pay 값을 가져와서 MemberDto에 설정w
		dto.setPay(adduserCommand.getPay());
		memberService.Pay(dto);
		// DTO에 대한 다른 로직 수행 가능

		// DTO를 모델에 추가
		model.addAttribute("dto", dto);

		// 콘솔에 출력
		System.out.println("시급 입력: " + adduserCommand.getPay());
		System.out.println("수정된 DTO: " + dto);

		// 다른 로직 수행 가능

		// UserDetailManagement 페이지로 리다이렉트
		return "redirect:/manage/UserDetailManagement?id=" + adduserCommand.getId();
	}

	@GetMapping(value = "/plus")
	public String plus(@Validated AddUserCommand adduserCommand, BindingResult result, String name, String id,
			Model model) {
		AccountDto accountDto = new AccountDto();
		MemberDto dto = memberService.getuserDetail(id);
		model.addAttribute("dto", dto);
		accountDto.setMemberid(dto.getMemberId());
		System.out.println("송금하기");
		accountDto.setMoney(adduserCommand.getMoney());
		memberService.Plus(accountDto);
		model.addAttribute("accountDto", accountDto);
		System.out.println(accountDto);
		return "redirect:/manage/UserDetailManagement?id=" + adduserCommand.getId();
	}
	

	@GetMapping(value = "/roleUpdate")
	public String roleUpdate(String id, Model model) {
		System.out.println(id);
		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용

		AccountDto dto = userlistService.UserDetail(id);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		System.out.println("직급변경 모달");

		return "board/roleUpdate";
	}
	
	@GetMapping(value = "/deleteAction")
	public String deleteAction(String id, Model model) {
		System.out.println(id);
		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용

		AccountDto dto = userlistService.UserDetail(id);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		System.out.println("직원 해고 모달");

		return "board/deletemember";
	}
	@GetMapping(value = "/delete")
	public String delete(String id, Model model) {
		AccountDto dto = new AccountDto();
		dto = userlistService.getUserDetail(id);
		userlistService.membermulDel(dto);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		// 유효값처리용
		System.out.println("해고");
		return "board/delete";
	}
	@GetMapping(value = "/roledown")
	public String roledown(String id, Model model) {
		AccountDto dto = new AccountDto();
		dto = userlistService.getUserDetail(id);
		userlistService.roledown(dto);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		// 유효값처리용
		System.out.println("직원등급으로 변경");
		return "board/roledown";
	}
	@GetMapping(value = "/roleup")
	public String roleup(String id, Model model) {
		AccountDto dto = new AccountDto();
		dto = userlistService.getUserDetail(id);
		userlistService.roleup(dto);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		// 유효값처리용
		System.out.println("사장등급으로 변경");
		return "board/roleup";
	}
	@GetMapping(value = "/rolem")
	public String rolem(String id, Model model) {
		AccountDto dto = new AccountDto();
		dto = userlistService.getUserDetail(id);
		userlistService.rolem(dto);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		// 유효값처리용
		System.out.println("매니저등급으로 변경");
		return "board/rolem";
	}

	@GetMapping(value = "/usermanagement")
	public String boardList(Model model) {
		System.out.println("직원 관리 폼");
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
//		System.out.println(list);

		List<AccountDto> mlist = userlistService.getUser();
		model.addAttribute("mlist", mlist);
		System.out.println(mlist);
		model.addAttribute("delBoardCommand", new DelBoardCommand());

		return "board/UserManagement";// forward 기능, "redirect:board/boardList"
	}

	@GetMapping(value = "/UserDetailManagement")
	public String userDetail(String id, AddUserCommand adduserCommand, Model model, HttpServletRequest request) {
		MemberDto dto = memberService.getuserDetail(id);
		System.out.println("정보");
		System.out.println(id);

		HttpSession session = (HttpSession) request.getSession();
		MemberDto fdto = (MemberDto) session.getAttribute("mdto");
		MemberDto filedto = memberService.getUser(fdto);
		List<FileUserDto> list = memberService.fileuser(filedto);
		model.addAttribute("list", list);
		System.out.println(list);

		MemberDto mdto = memberService.getUser(dto);
		List<FileUserDto> filelist = memberService.fileuser(mdto);
		model.addAttribute("filelist", filelist);
		System.out.println(filelist);

		String year = request.getParameter("year");
		String month = request.getParameter("month");

		if (year == null || month == null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR) + "";
			month = (cal.get(Calendar.MONTH) + 1) + "";
		}
		// 달력만들기위한 값 구하기
		Map<String, Integer> map = calService.makeCalendar(request);
		model.addAttribute("calMap", map);
		String yyyy = year + Util.isTwo(month);// 202311 6자리변환
		String yyyymm = year + "-" + Util.isTwo(month);// 2023-11 7자리변환
//		String id = dto.getId();

		System.out.println(yyyy);
//	      String MM =  Util.isTwo(month);
		Map<String, String> cMap = new HashMap<>();
		cMap.put("yyyyMM", yyyy);
		cMap.put("id", id);

		List<CalDto> clist = calService.totalworktime(cMap);
		// 월별 근무시간
		model.addAttribute("clist", clist);

		Map<String, String> pMap = new HashMap<>();

		pMap.put("yyyyMM", yyyy);
		pMap.put("id", id);
		List<CalDto> plist = calService.getmonth(pMap);

		// 월별 급여합계
		model.addAttribute("plist", plist);
		System.out.println(plist);

		Map<String, String> wMap = new HashMap<>();
		wMap.put("yyyyMM", yyyy);
		wMap.put("id", id);
		List<CalDto> wlist = calService.mworkList(wMap);
		model.addAttribute("wlist", wlist);
		System.out.println(wlist);

		// 승인된 선지급 급여합계
		Map<String, String> fMap = new HashMap<>();
		fMap.put("yyyy-MM", yyyymm);
		fMap.put("id", id);
		List<PaymentDto> flist = payService.firstpaymoney(fMap);
		model.addAttribute("flist", flist);
		System.out.println(flist);

		// 유효값처리용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		return "board/UserDetailManagement";
	}

}