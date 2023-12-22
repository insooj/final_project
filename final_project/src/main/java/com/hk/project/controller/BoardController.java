package com.hk.project.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hk.project.command.DelBoardCommand;
import com.hk.project.command.DeleteCalCommand;
import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.service.BoardService;
import com.hk.project.service.FileService;
import com.hk.project.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;

	//진료 안내
	@GetMapping(value = "/Department")
		public String Department() {
			System.out.println("진료과 보기");
			return "board/Department";
		}
	@GetMapping(value = "/Medical_procedure_info")
	public String Medical_procedure_info() {
		System.out.println("진료절차안내 보기");
		return "board/Medical_procedure_info";
	}
	@GetMapping(value = "/Instructions_usinginfo")
	public String Instructions_usinginfo() {
		System.out.println("검사실이용안내 보기");
		return "board/Instructions_usinginfo";
	}
	// 진료 예약 및 조회
	@GetMapping(value = "/Full_treatment_schedule")
	public String Full_treatment_schedule() {
		System.out.println("전체 진료 시간표 보기");
		return "board/Full_treatment_schedule";
	}
	
	@GetMapping(value = "/boardList")
	public String boardList(Model model) {
		System.out.println("글목록 보기");

		List<BoardDto> list = boardService.getAllList();
		model.addAttribute("list", list);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		return "board/boardList";// forward 기능, "redirect:board/boardList"
	}

	@GetMapping(value = "/boardInsert")
	public String boardInsertForm(Model model) {
		model.addAttribute("insertBoardCommand", new InsertBoardCommand());
		return "board/boardInsertForm";
	}

	@PostMapping(value = "/boardInsert")
	public String boardInsert(@Validated InsertBoardCommand insertBoardCommand, BindingResult result,
			MultipartRequest multipartRequest // multipart data를 처리할때 사용
			, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			System.out.println("글을 모두 입력하세요");
			return "board/boardInsertForm";
		}

		boardService.insertBoard(insertBoardCommand, multipartRequest, request);

		return "redirect:/board/boardList";

	}

	// 상세보기
	@GetMapping(value = "/boardDetail")
	public String boardDetail(int board_seq, Model model) {
		BoardDto dto = boardService.getBoard(board_seq);

		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		return "board/boardDetail";
	}
//	

	// 수정하기
	@PostMapping(value = "/boardUpdate")
	public String boardUpdate(int board_seq, @Validated UpdateBoardCommand updateBoardCommand, BindingResult result,
			Model model) {

		BoardDto dto = boardService.getBoard(board_seq);
		boardService.updateBoard(updateBoardCommand);
		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println("ㅎㅇ");

		return "redirect:/board/boardDetail?board_seq=" + updateBoardCommand.getBoard_seq();
	}

	// 수정폼으로 이동
	@GetMapping(value = "/boardUpdate")
	public String boardUpdateForm(int board_seq, Model model) {
		// 유효값처리용
		BoardDto dto = boardService.getBoard(board_seq);
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println("수정폼이동");
		return "board/boardUpdate";

	}

	@GetMapping(value = "/download")
	public void download(int file_seq, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		FileBoardDto fdto = fileService.getFileInfo(file_seq);// 파일정보가져오기

		fileService.fileDownload(fdto.getOrigin_filename(), fdto.getStored_filename(), request, response);
	}

	@RequestMapping(value = "mulDel", method = { RequestMethod.POST, RequestMethod.GET })
	public String mulDel(@Validated DelBoardCommand delBoardCommand, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("최소하나 체크하기");
			List<BoardDto> list = boardService.getAllList();
			model.addAttribute("list", list);
			return "board/boardlist";
		}
		boardService.mulDel(delBoardCommand.getSeq());
		System.out.println("글삭제함");
		return "redirect:/board/boardList";
	}

	@GetMapping(value = "/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		System.out.println("달력보기");

		// 달력에서 일일별 일정목록 구하기
//		String id="aaaa";//나중에 세션에서 가져온 아이디 사용

		String year = request.getParameter("year");
		String month = request.getParameter("month");

		if (year == null || month == null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR) + "";
			month = (cal.get(Calendar.MONTH) + 1) + "";
		}
		System.out.println("year:" + year);
		System.out.println("month:" + month);
		// 달력만들기위한 값 구하기
		Map<String, Integer> map = boardService.makeCalendar(request);
		model.addAttribute("calMap", map);

		String yyyyMM = year + Util.isTwo(month);// 202311 6자리변환
		List<BoardDto> clist = boardService.calViewList(yyyyMM);
		model.addAttribute("clist", clist);
		System.out.println("clist" + clist.size());
		return "board/calendar";
	}

	@ResponseBody
	@GetMapping(value = "/calCountAjax")
	public Map<String, Integer> calCountAjax(String yyyyMMdd) {
		Map<String, Integer> map = new HashMap<>();
//		String  id="aaaa";
		int count = boardService.calBoardCount(yyyyMMdd);
		map.put("count", count);
		return map;
	}

	@GetMapping(value = "/calBoardList")
	public String calBoardList(@RequestParam Map<String, String> map, HttpServletRequest request, Model model) {
//		HttpSession session=request.getSession();
//		String id=session.getAttribute("id");

		// command 유효값 처리를 위해 기본 생성해서 보내줌
		model.addAttribute("deleteCalCommand", new DeleteCalCommand());

		// 일정목록을 조회할때마다 year, month, date를 세션에 저장
		HttpSession session = request.getSession();

		if (map.get("year") == null) {
			// 조회한 상태이기때문에 ymd가 저장되어 있어서 값을 가져옴
			map = (Map<String, String>) session.getAttribute("ymdMap");
		} else {
			// 일정을 처음 조회했을때 ymd를 저장함
			session.setAttribute("ymdMap", map);
		}

		// 달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
		String yyyyMMdd = map.get("year") + Util.isTwo(map.get("month")) + Util.isTwo(map.get("date"));
		List<BoardDto> list = boardService.calBoardList(yyyyMMdd);
		model.addAttribute("list", list);

		return "board/calBoardList";
	}

	// 상세보기
	@GetMapping(value = "/calBoardDetail")
	public String calboardDetail(int board_seq, Model model) {
		BoardDto dto = boardService.getBoard(board_seq);

		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		return "board/calboardDetail";
	}

	// 수정폼으로 이동
	@GetMapping(value = "/calboardUpdate")
	public String calboardUpdateForm(int board_seq, Model model) {
		// 유효값처리용
		BoardDto dto = boardService.getBoard(board_seq);
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);
		System.out.println("수정폼이동");
		return "board/calboardUpdate";

	}

	// 수정하기
	@PostMapping(value = "/calboardUpdate")
	public String calboardUpdate(int board_seq, @Validated UpdateBoardCommand updateBoardCommand, BindingResult result,
			Model model) {

		BoardDto dto = boardService.getBoard(board_seq);
		boardService.updateBoard(updateBoardCommand);
		// 유효값처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);

		return "redirect:/board/calBoardDetail?board_seq=" + updateBoardCommand.getBoard_seq();
	}

	@GetMapping(value = "/calboardInsertForm")
	public String calboardInsertForm(Model model) {
		model.addAttribute("insertBoardCommand", new InsertBoardCommand());
		return "board/calboardInsertForm";
	}

	@PostMapping(value = "/calboardInsert")
	public String calboardInsert(@Validated InsertBoardCommand insertBoardCommand, BindingResult result,
			MultipartRequest multipartRequest // multipart data를 처리할때 사용
			, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			System.out.println("글을 모두 입력하세요");
			return "board/calboardInsertForm";
		}

		boardService.insertBoard(insertBoardCommand, multipartRequest, request);

		return "redirect:/board/calendar";

	}


	//상세보기
			@PostMapping(value = "/boardDetail1")
			public String boardDetail1( int board_seq,
					@Validated UpdateBoardCommand updateBoardCommand
					,BindingResult result,  HttpServletRequest request	
					,Model model) {
				BoardDto dto = boardService.getBoard(board_seq);
				dto.setSold(1);
				dto.setSold_id(request.getSession().getId());
				boardService.updateBoard1(updateBoardCommand);
				//유효값처리용
				model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
				//출력용
				model.addAttribute("dto", dto);
				System.out.println(dto);
				return "redirect:/board/boardList";
				}
//			
//			@GetMapping(value = "/solditemlist")
//			public String solditemlist(Model model, HttpServletRequest request	) {
//				System.out.println("판매목록 보기");
//				
//				MemberDto mdto = (MemberDto)request.getSession().getAttribute("mdto");
//				List<BoardDto> list = boardService.getsoldAllList(mdto.getId());
//				model.addAttribute("list", list);
//				model.addAttribute("delBoardCommand", new DelBoardCommand());
//				return "board/solditemlist";// forward 기능, "redirect:board/boardList"
//			}
//			@GetMapping(value = "/myitemlist")
//			public String myitemlist(Model model, HttpServletRequest request	) {
//				System.out.println("구매목록 보기");
//				
//				MemberDto mdto = (MemberDto)request.getSession().getAttribute("mdto");
//				List<BoardDto> list = boardService.getmyAllList(mdto.getId());
//				model.addAttribute("list", list);
//				model.addAttribute("delBoardCommand", new DelBoardCommand());
//				return "board/myitemlist";// forward 기능, "redirect:board/boardList"
//			}
			
			
}
