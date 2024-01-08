package com.hk.project.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.SetOfIntegerSyntax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.project.command.DeleteCalCommand;
import com.hk.project.command.InsertCalCommand;
import com.hk.project.command.UpdateCalCommand;
import com.hk.project.dtos.CalDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.service.ICalService;
import com.hk.project.service.MemberService;
import com.hk.project.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/schedule", method = RequestMethod.GET)
public class CalController {
   // log를 원하는 위치에 설정하여 디버깅 하기
   private static final Logger logger = LoggerFactory.getLogger(CalController.class);

   @Autowired
   private MemberService memberService;

   @Autowired
   private ICalService calService;

   @PostMapping(value = "/calendar")
   public String calendar(Model model, HttpServletRequest request) {

      logger.info("달력보기");

      // 달력에서 일일별 일정목록 구하기

      String year = request.getParameter("year");
      String month = request.getParameter("month");
      
      MemberDto dto = new MemberDto();
      List<FileUserDto> list = memberService.fileuser(dto);
      model.addAttribute("list", list);
      
      if (year == null || month == null) {
         Calendar cal = Calendar.getInstance();
         year = cal.get(Calendar.YEAR) + "";
         month = (cal.get(Calendar.MONTH) + 1) + "";
      }
      // 달력만들기위한 값 구하기
      Map<String, Integer> map = calService.makeCalendar(request);
      model.addAttribute("calMap", map);





      return "board/calendar";
   }

   @GetMapping(value = "/addCalBoardForm")
   public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
      logger.info("일정추가폼이동");
      System.out.println(insertCalCommand);
      MemberDto dto = new MemberDto();
      List<FileUserDto> list = memberService.fileuser(dto);
      model.addAttribute("list", list);
      // addCalBoardfForm 페이지에서 유효값 처리를 위해 insertCalCommand 받고 있기때문에
      model.addAttribute("insertCalCommand", insertCalCommand);
      return "board/addCalBoardForm";
   }

   @PostMapping(value = "/addCalBoard")
   public String addCalBoard(@Validated InsertCalCommand insertCalCommand,Model model, BindingResult result) throws Exception {
      logger.info("일정추가하기");
      System.out.println(insertCalCommand);
      MemberDto dto = new MemberDto();
      List<FileUserDto> list = memberService.fileuser(dto);
      model.addAttribute("list", list);
      if (result.hasErrors()) {
         System.out.println("글을 모두 입력해야 함");
         return "board/addCalBoardForm";
      }

      calService.insertCalBoard(insertCalCommand);

      return "redirect:/schedule/calendar?year=" + insertCalCommand.getYear() + "&month="
            + insertCalCommand.getMonth();
   }

   @GetMapping(value = "/calBoardList")
   public String calBoardList(@RequestParam Map<String, String> map, HttpServletRequest request, Model model) {
      logger.info("일정목록보기");
      HttpSession session = request.getSession();
//      String id=(String) session.getAttribute("id");
//      String id="jth";//임시로 id 저장
      MemberDto dto = new MemberDto();
      List<FileUserDto> list = memberService.fileuser(dto);
      model.addAttribute("list", list);
      // command 유효값 처리를 위해 기본 생성해서 보내줌
      model.addAttribute("deleteCalCommand", new DeleteCalCommand()); 

      // 일정목록을 조회할때마다 year, month, date를 세션에 저장
//      HttpSession session=request.getSession();

      if (map.get("year") == null) {
         // 조회한 상태이기때문에 ymd가 저장되어 있어서 값을 가져옴
         map = (Map<String, String>) session.getAttribute("ymdMap");
      } else {
         // 일정을 처음 조회했을때 ymd를 저장함
         session.setAttribute("ymdMap", map);
      }

      // 달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
      String yyyyMMdd = map.get("year") + Util.isTwo(map.get("month")) + Util.isTwo(map.get("date"));
      List<CalDto> flist = calService.calBoardList(yyyyMMdd);
      
      model.addAttribute("flist", flist);
      
      
      return "board/calBoardList";
   }

   @PostMapping(value = "/calMulDel")
   public String calMulDel(@Validated DeleteCalCommand deleteCalCommand, BindingResult result,
         HttpServletRequest request, Model model) {

      if (result.hasErrors()) {
         System.out.println("최소 하나 이상 체크하기");

         HttpSession session = request.getSession();
//         String id=(String) session.getAttribute("id");
//         String id="jth";//임시로 id 저장

         // session에 저장된 ymd 값은 목록 조회할때 추가되는 코드임
         Map<String, String> map = (Map<String, String>) session.getAttribute("ymdMap");

         // 달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
         String yyyyMMdd = map.get("year") + Util.isTwo(map.get("month")) + Util.isTwo(map.get("date"));
         List<CalDto> list = calService.calBoardList(yyyyMMdd);
         model.addAttribute("list", list);
         return "board/calBoardList";
      }
      Map<String, String[]> map = new HashMap<>();
      map.put("seqs", deleteCalCommand.getSeq());
      calService.calMulDel(map);

      return "redirect:/schedule/calBoardList";
   }

   @GetMapping("/calMulDel")
   public String calDel(String[] seq) {
      logger.info("일정삭제하기");
      System.out.println(seq[0]);
      Map<String, String[]> map = new HashMap<>();
      map.put("seqs", seq);
      calService.calMulDel(map);
      return "redirect:/schedule/calBoardList";
   }

   @GetMapping(value = "/calBoardDetail")
   public String calBoardDetail(UpdateCalCommand updateCalCommand, int seq, Model model) {
      logger.info("일정상세보기");

      CalDto dto = calService.calBoardDetail(seq);
      MemberDto mdto = new MemberDto();
      List<FileUserDto> list = memberService.fileuser(mdto);
      model.addAttribute("list", list);
      // dto ---> command
      updateCalCommand.setSeq(dto.getSeq());
//      updateCalCommand.setTitle(dto.getTitle());
      updateCalCommand.setContent(dto.getContent());
      updateCalCommand.setYear(Integer.parseInt(dto.getMdate().substring(0, 4)));
      updateCalCommand.setMonth(Integer.parseInt(dto.getMdate().substring(4, 6)));
      updateCalCommand.setDate(Integer.parseInt(dto.getMdate().substring(6, 8)));
      updateCalCommand.setHour(Integer.parseInt(dto.getMdate().substring(8, 10)));
      updateCalCommand.setMin(Integer.parseInt(dto.getMdate().substring(10)));
      model.addAttribute("updateCalCommand", updateCalCommand);

      return "board/calBoardDetail";
   }

   @PostMapping(value = "/calBoardUpdate")
   public String calBoardUpdate(@Validated UpdateCalCommand updateCalCommand, BindingResult result, Model model) {
      logger.info("일정 수정하기");

      if (result.hasErrors()) {
         System.out.println("수정할 목록을 확인하세요");
         return "board/calBoardDetail";
      }
      MemberDto dto = new MemberDto();
      List<FileUserDto> list = memberService.fileuser(dto);
      model.addAttribute("list", list);
      calService.calBoardUpdate(updateCalCommand);

      return "redirect:/schedule/calBoardDetail?seq=" + updateCalCommand.getSeq();
   }

   @ResponseBody
   @GetMapping(value = "/calCountAjax")
   public Map<String, Integer> calCountAjax(String yyyyMMdd) {
      logger.info("일정개수");
      Map<String, Integer> map = new HashMap<>();
//      String id = "Taeho";
      int count = calService.calBoardCount(yyyyMMdd);
      map.put("count", count);
      return map;
   }
}