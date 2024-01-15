package com.hk.project.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.service.BoardService;
import com.hk.project.service.FileService;
import com.hk.project.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

   @Autowired
   private MemberService memberService;
   @Autowired
   private BoardService boardService;
   @Autowired
   private FileService fileService;

   @GetMapping(value = "/boardList")
   public String boardList(Model model,HttpServletRequest request) {
      System.out.println("글목록 보기");
      HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
      List<BoardDto> blist = boardService.getAllList();
      model.addAttribute("blist", blist);
      model.addAttribute("delBoardCommand", new DelBoardCommand());
      return "board/boardList";// forward 기능, "redirect:board/boardList"
   }

   @GetMapping(value = "/boardInsert")
   public String boardInsertForm(Model model,HttpServletRequest request) {
      model.addAttribute("insertBoardCommand", new InsertBoardCommand());
      HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto dto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
      
      return "board/boardInsertForm";
   }

   @PostMapping(value = "/boardInsert")
   public String boardInsert(@Validated InsertBoardCommand insertBoardCommand, BindingResult result,
         MultipartRequest multipartRequest // multipart data를 처리할때 사용
         , HttpServletRequest request, Model model) throws IllegalStateException, IOException {
	   HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto fdto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(fdto);
		model.addAttribute("list", list);
	   
	   if (result.hasErrors()) {
         System.out.println("글을 모두 입력하세요");
         return "board/boardInsertForm";
      }

      boardService.insertBoard(insertBoardCommand, multipartRequest, request);

      return "redirect:/board/boardList";
   }

   // 상세보기
   @GetMapping(value = "/boardDetail")
   public String boardDetail(int board_seq, Model model,HttpServletRequest request) {
      BoardDto dto = boardService.getBoard(board_seq);
      HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto fdto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(fdto);
		model.addAttribute("list", list);
      // 유효값처리용
      model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
      // 출력용
      model.addAttribute("dto", dto);
      System.out.println(dto);
      return "board/boardDetail";
   }

   

   // 수정하기
   @PostMapping(value = "/boardUpdate")
   public String boardUpdate(@Validated UpdateBoardCommand updateBoardCommand,Model model,HttpServletRequest request, BindingResult result) {
	   HttpSession session = (HttpSession) request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("mdto");
		MemberDto fdto = memberService.getUser(mdto);
		List<FileUserDto> list = memberService.fileuser(fdto);
		model.addAttribute("list", list);
      if (result.hasErrors()) {
         System.out.println("수정내용을 모두 입력하세요");
         return "board/boardDetail";
      }

      boardService.updateBoard(updateBoardCommand);

      return "redirect:/board/boardDetail?board_seq=" + updateBoardCommand.getBoard_seq();
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
         return "board/boardlist";
      }
      boardService.mulDel(delBoardCommand.getSeq());
      System.out.println("글삭제함");
      return "redirect:/board/boardList";
   }
}