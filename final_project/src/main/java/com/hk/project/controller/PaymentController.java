package com.hk.project.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.command.AddUserCommand;
import com.hk.project.command.DelBoardCommand;
import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.InsertPayCommand;
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
import com.hk.project.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/pay")
public class PaymentController {
	@Autowired
	private ICalService calService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private PayService payService;
	@Autowired
	private MemberService memberService;

	@GetMapping(value = "/RequestList")
	public String payList(Model model, String name) {
		System.out.println("요청목록 보기");
		HttpSession session = (HttpSession) request.getSession();
		MemberDto fdto = (MemberDto) session.getAttribute("mdto");
		MemberDto filedto = memberService.getUser(fdto);
		List<FileUserDto> list = memberService.fileuser(filedto);
		model.addAttribute("list", list);
		List<PaymentDto> plist = payService.getAllList();
		model.addAttribute("plist", plist);
		System.out.println(plist);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		return "firstpay/PaymentRequest";// forward 기능, "redirect:board/boardList"

	}

	@GetMapping(value = "/RequestDetail")
	public String RequestDetail(int board_seq, Model model) {
		PaymentDto dto = payService.getBoard(board_seq);
		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		System.out.println("승인모달");
		return "firstpay/PayDetail";
	}

	@GetMapping(value = "/RequestRefuse")
	public String RequestRefuse(int board_seq, Model model) {
		PaymentDto dto = payService.getBoard(board_seq);
		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		System.out.println("거절모달");
		return "firstpay/PayRefuse";
	}

	@GetMapping(value = "/RequestInsert")
	public String payInsertForm(Model model) {
		HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		System.out.println("요청등록폼");

		MemberDto fdto = (MemberDto) session.getAttribute("mdto");
		MemberDto filedto = memberService.getUser(fdto);
		List<FileUserDto> list = memberService.fileuser(filedto);
		model.addAttribute("list", list);
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null || month == null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR) + "";
			month = (cal.get(Calendar.MONTH) + 1) + "";
		}
		String yyyy = year + Util.isTwo(month);// 202311 6자리변환
		String yyyymm = year + "-" + Util.isTwo(month);// 2023-11 7자리변환
		String id = dto.getId();
		System.out.println(yyyy);
		System.out.println(id);
		Map<String, String> pMap = new HashMap<>();

		pMap.put("yyyyMM", yyyy);
		pMap.put("id", id);
		List<CalDto> plist = calService.getmonth(pMap);

		// 월별 급여합계
		model.addAttribute("plist", plist);
		System.out.println(plist);

		model.addAttribute("insertPayCommand", new InsertPayCommand());
		// 승인된 선지급 급여합계
		Map<String, String> fMap = new HashMap<>();
		fMap.put("yyyy-MM", yyyymm);
		fMap.put("id", id);
		List<PaymentDto> flist = payService.firstpaymoney(fMap);
		model.addAttribute("flist", flist);
		System.out.println(flist);
		return "firstpay/PayInsertForm";
	}

	@PostMapping(value = "/RequestInsert")
	public String RequestInsert(@Validated InsertPayCommand insertPayCommand, BindingResult result,
			MultipartRequest multipartRequest // multipart data를 처리할때 사용
			, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		HttpSession session = (HttpSession) request.getSession();
		MemberDto fdto = (MemberDto) session.getAttribute("mdto");
		MemberDto filedto = memberService.getUser(fdto);
		List<FileUserDto> list = memberService.fileuser(filedto);
		model.addAttribute("list", list);
		payService.insertPayment(insertPayCommand, multipartRequest, request);

		return "redirect:/pay/RequestList";
	}

	@GetMapping(value = "/success")
	public String plus(@Validated AddUserCommand adduserCommand, BindingResult result, int board_seq, String name,
			String id, Model model) {
		AccountDto accountDto = new AccountDto();
		MemberDto dto = memberService.getuserDetail(id);
		model.addAttribute("dto", dto);
		accountDto.setMemberid(dto.getMemberId());
		System.out.println("송금하기");
		accountDto.setMoney(adduserCommand.getMoney());
		memberService.Plus(accountDto);
		model.addAttribute("accountDto", accountDto);
		PaymentDto pdto = new PaymentDto();
		pdto.setBoard_seq(board_seq);
		payService.complete(pdto);
		model.addAttribute("pdto", pdto);
		System.out.println("승인완료");
		return "firstpay/PaymentMini";
	}

	@GetMapping(value = "/refuse")
	public String refuse(int board_seq, String name, String id, Model model) {
		PaymentDto pdto = new PaymentDto();
		pdto.setBoard_seq(board_seq);
		payService.refuse(pdto);
		model.addAttribute("pdto", pdto);
		System.out.println("거절완료");
		return "firstpay/PaymentMiniRefuse";
	}
}