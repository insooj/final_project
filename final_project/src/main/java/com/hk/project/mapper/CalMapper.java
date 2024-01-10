package com.hk.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.CalDto;
import com.hk.project.dtos.MemberDto;

@Mapper
public interface CalMapper {

   //일정 추가
   public int insertCalBoard(CalDto dto);
   //일정 목록
   public List<CalDto> calBoardList(String yyyyMMdd);
   //일정 상세조회
   public CalDto calBoardDetail(int seq);
   //근무 시간 상세조회
   public List<CalDto> worktime(String yyyyMMdd);
   //일정 수정하기
   public boolean calBoardUpdate(CalDto dto);
   //일정 삭제하기
   public boolean calMulDel(Map<String, String[]>map);
   //한달의 일정보여주기
   public List<CalDto> calViewList(String yyyyMM);
   //한달의 총 급여
   public List<CalDto> getmonth(Map<String, String>map);
   //한달의 총 근무리스트
   public List<CalDto> mworkList(Map<String, String>map);
   //한달의 총 근무시간
   public List<CalDto> totalworktime(Map<String, String>map);
   //일일의 일정개수 보여주기
   public int calBoardCount(String yyyyMMdd);
}



