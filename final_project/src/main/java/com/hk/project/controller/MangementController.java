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

import com.hk.project.command.DelBoardCommand;
import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.service.BoardService;
import com.hk.project.service.FileService;
import com.hk.project.service.MemberService;
import com.hk.project.service.UserListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/manage")
public class MangementController {
   
   @Autowired
   private UserListService userlistService;
   
   @Autowired
   private MemberService memberService;
   
   @GetMapping(value = "/usermanagement")
   public String boardList(Model model) {
      System.out.println("직원 정보 폼");
      MemberDto dto = new MemberDto();
		List<FileUserDto> list = memberService.fileuser(dto);
		model.addAttribute("list", list);
      List<AccountDto> mlist=userlistService.getUserList();
      model.addAttribute("mlist", mlist);
      model.addAttribute("delBoardCommand", new DelBoardCommand());
      return "board/UserManagement";// forward 기능, "redirect:board/boardList"
   }
   
 
   
   //상세보기
//   @GetMapping(value = "/boardDetail")
//   public String boardDetail(int board_seq, Model model) {
//      BoardDto dto=boardService.getBoard(board_seq);
//      
//      //유효값처리용
//      model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
//      //출력용
//      model.addAttribute("dto", dto);
//      System.out.println(dto);
//      return "board/boardDetail";
//   }
   
   
 
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











