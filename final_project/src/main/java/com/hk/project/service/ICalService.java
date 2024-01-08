package com.hk.project.service;

import java.util.List;
import java.util.Map;


import com.hk.project.command.InsertCalCommand;
import com.hk.project.command.UpdateCalCommand;
import com.hk.project.dtos.CalDto;
import com.hk.project.dtos.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

public interface ICalService {
   
   //달력생성시 필요한 값 구하는 메서드
   public Map<String, Integer> makeCalendar(HttpServletRequest request);
   
   //일정 추가
   public boolean insertCalBoard(InsertCalCommand insertCalCommand) throws Exception  ;
   //일정 목록
   public List<CalDto> calBoardList(String yyyyMMdd);
   //근무시간 목록
   public List<CalDto> worktime(String yyyyMMdd);
   //일정 상세조회
   public CalDto calBoardDetail(int seq);
   //일정 수정하기
   public boolean calBoardUpdate(UpdateCalCommand updateCalCommand);
   //일정 삭제하기
   public boolean calMulDel(Map<String, String[]>map);
   //한달의 일정보여주기
   public List<CalDto> calViewList(String yyyyMM);
 //한달의 근무시간보여주기
   public List<CalDto> getmonth(Map<String, String>map);
   //한달의 급여
   public List<CalDto> totalworktime(Map<String, String>map);
   //일일의 일정개수 보여주기
   public int calBoardCount(String yyyyMMdd);
}