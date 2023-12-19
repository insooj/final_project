package com.hk.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.BoardDto;

@Mapper
public interface BoardMapper {

	//글목록
	public List<BoardDto> getAllList();
	//판매목록
	public List<BoardDto> getsoldAllList(String id);
	//구매목록
	public List<BoardDto> getmyAllList(String id);
	//글상세조회
	public BoardDto getBoard(int board_seq);
	//글추가
	public boolean insertBoard(BoardDto dto);
	//글 수정
	public boolean updateBoard(BoardDto dto);
	//글 삭제
	public boolean mulDel(String[] seqs);
	
	//상품 구매 
	public boolean updateBoard1(BoardDto dto);
	
	//한달의 일정보여주기
	public List<BoardDto> calViewList(String yyyyMM);
	//일일의 일정개수 보여주기
	public int calBoardCount(String yyyyMMdd);
	//일정 목록
	public List<BoardDto> calBoardList(String yyyyMMdd);
	
	
}






