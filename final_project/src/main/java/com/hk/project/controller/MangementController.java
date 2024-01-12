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
		MemberDto dto = memberService.getuserDetail(name);

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
		return "redirect:/manage/UserDetailManagement?name=" + adduserCommand.getName();
	}

//   @GetMapping(value = "/pay")
//   public String pay(@Validated AddUserCommand adduserCommand,  BindingResult result,String name, String id,Model model) {
//      MemberDto dto = memberService.getuserDetail(name);
//      model.addAttribute("dto", dto);
//      System.out.println("시급입력");
//      
//      
////      memberService.Pay(dto);
//      System.out.println(dto);
////      accountDto.setMoney(adduserCommand.getMoney());
//      model.addAttribute("dto", dto);
//      System.out.println(dto);
////      return "redirect:/board/UserManagement";
//      return "redirect:/manage/UserDetailManagement?name=" + adduserCommand.getName();
//   }   
//   

	@GetMapping(value = "/plus")
	public String plus(@Validated AddUserCommand adduserCommand, BindingResult result, String name, String id,
			Model model) {
		AccountDto accountDto = new AccountDto();
		MemberDto dto = memberService.getuserDetail(name);
		model.addAttribute("dto", dto);
		accountDto.setMemberid(dto.getMemberId());
		System.out.println("송금하기");
		accountDto.setMoney(adduserCommand.getMoney());
//      accountDto.setMoney(adduserCommand.getMoney());
		memberService.Plus(accountDto);
		model.addAttribute("accountDto", accountDto);
		System.out.println(accountDto);
//      return "redirect:/board/UserManagement";
		return "redirect:/manage/UserDetailManagement?name=" + adduserCommand.getName();
	}

	@GetMapping(value = "/usermanagement")
	public String boardList(Model model) {
		System.out.println("직원 관리 폼");
		MemberDto dto = new MemberDto();
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
//		System.out.println(list);

		List<AccountDto> mlist = userlistService.getUser();
		model.addAttribute("mlist", mlist);
		model.addAttribute("delBoardCommand", new DelBoardCommand());

		return "board/UserManagement";// forward 기능, "redirect:board/boardList"
	}

	@GetMapping(value = "/UserDetailManagement")
	public String userDetail(String name,  AddUserCommand adduserCommand, Model model, HttpServletRequest request) {
		MemberDto dto = memberService.getuserDetail(name);
		System.out.println("정보");
		System.out.println(name);
		MemberDto mdto = memberService.getUser(dto);
		List<FileUserDto> list = memberService.fileuser(mdto);
		model.addAttribute("list", list);
		System.out.println(list);
		
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
		String id = dto.getId();

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

	

//		Map<Object, Object> tMap = new HashMap<>();
//		tMap.put("plist", plist);
//		tMap.put("flist", flist);
//		
//		model.addAttribute("tMap",tMap);
//		System.out.println(tMap);
		// 유효값처리용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		return "board/UserDetailManagement";
	}

//   @RequestMapping(value="mulDel",method = {RequestMethod.POST,RequestMethod.GET})
//   public String mulDel(@Validated DelBoardCommand delBoardCommand
//                   ,BindingResult result
//                      , Model model) {
//      if(result.hasErrors()) {
//         System.out.println("최소하나 체크하기");
//         List<BoardDto> blist=boardService.getAllList();
//         model.addAttribute("blist", blist);
//         return "board/boardlist";
//      }
//      boardService.mulDel(delBoardCommand.getSeq());
//      System.out.println("글삭제함");
//      return "redirect:/board/boardList";
//   }
}