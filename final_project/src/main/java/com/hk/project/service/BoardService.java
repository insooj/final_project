package com.hk.project.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.mapper.BoardMapper;
import com.hk.project.mapper.FileMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private FileMapper fileMapper;
	@Autowired
	private FileService fileService;

	public List<BoardDto> fileBoardDto(int fileBoardDto) {
		return fileBoardDto(fileBoardDto);
	}

	// 글목록 조회
	public List<BoardDto> getAllList() {
		return boardMapper.getAllList();
	}

	// 글 추가, 파일업로드및 파일정보 추가
	@Transactional
	public void insertBoard(InsertBoardCommand insertBoardCommand, MultipartRequest multipartRequest,
			HttpServletRequest request) throws IllegalStateException, IOException {
		// command:UI -> dto:DB 데이터 옮겨담기
		BoardDto boardDto = new BoardDto();
		boardDto.setId(insertBoardCommand.getId());
		boardDto.setTitle(insertBoardCommand.getTitle());
		boardDto.setContent(insertBoardCommand.getContent());
		boardDto.setPrice(insertBoardCommand.getPrice());
		boardDto.setSold(insertBoardCommand.getSold());
		boardDto.setSold_id(insertBoardCommand.getSold_id());
		// 새글을 추가할때 파라미터로 전달된 boardDto객체에 자동으로,
		// 증가된 board_seq값이 저장
		boardMapper.insertBoard(boardDto);// 새글 추가
		System.out.println("파일첨부여부:" + multipartRequest.getFiles("filename").get(0).isEmpty());
		// 첨부된 파일들이 있는 경우
		if (!multipartRequest.getFiles("filename").get(0).isEmpty()) {
			// 파일 저장경로 설정: 절대경로, 상대경로
			String filepath = request.getSession().getServletContext().getRealPath("upload");
			System.out.println("파일저장경로:" + filepath);
			// 파일업로드 작업은 FileService쪽에서 업로드하고 업로드된 파일정보 반환
			List<FileBoardDto> uploadFileList = fileService.uploadFiles(filepath, multipartRequest);
			// 파일정보를 DB에 추가
			// 글추가할때 board_seq 증가된 값---> file정보를 추가할때 사용
			// Testboard: board_seq PK board_seq FK
			for (FileBoardDto fDto : uploadFileList) {
				fileMapper.insertFileBoard(new FileBoardDto(0, boardDto.getBoard_seq(), // 증가된 board_seq값을 넣는다
						fDto.getOrigin_filename(), fDto.getStored_filename()));
			}
		}

	}

	// 상세내용조회
	public BoardDto getBoard(int board_seq) {
		return boardMapper.getBoard(board_seq);
	}

	// 수정하기
	public boolean updateBoard(UpdateBoardCommand updateBoardCommand) {
		// command:UI ---> DTO:DB
		BoardDto dto = new BoardDto();
		dto.setId(updateBoardCommand.getId());
		dto.setBoard_seq(updateBoardCommand.getBoard_seq());
		dto.setTitle(updateBoardCommand.getTitle());
		dto.setContent(updateBoardCommand.getContent());
		dto.setPrice(updateBoardCommand.getPrice());
		return boardMapper.updateBoard(dto);
	}

	public boolean mulDel(String[] seqs) {
		return boardMapper.mulDel(seqs);
	}

	// 달력
	public Map<String, Integer> makeCalendar(HttpServletRequest request) {
		Map<String, Integer> map = new HashMap<>();

		// 달력의 날짜를 바꾸기 위해 전달된 year와 month 파라미터를 받는 코드
		String paramYear = request.getParameter("year");
		String paramMonth = request.getParameter("month");

		Calendar cal = Calendar.getInstance(); // 추상클래스이고, static 메서드 new(X)

		int year = (paramYear == null) ? cal.get(Calendar.YEAR) : Integer.parseInt(paramYear);
		int month = (paramMonth == null) ? cal.get(Calendar.MONTH) + 1 : Integer.parseInt(paramMonth);

		// 기본 오늘날짜로 저장할지 : 요청된 날짜로 저장할지
		// calendar객체에서 month는 0~11월임

		// 11월,12월,13월..... 오류 처리
		// -2월, -1월 , 0월 , 1월 오류 처리
		if (month > 12) {
			month = 1;
			year++;
		}
		if (month < 1) {
			month = 12;
			year--;
		}

		// 1.월의 1일에 대한 요일 구하기
		cal.set(year, month - 1, 1);// 원하는 날짜로 셋팅
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// 1~7중에 반환(1:일요일~7:토요일)

		// 2.월의 마지막 날 구하기
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);

		return map;
	}

	public List<BoardDto> calViewList(String yyyyMM) {
		return boardMapper.calViewList(yyyyMM);
	}

	public int calBoardCount(String yyyyMMdd) {
		return boardMapper.calBoardCount(yyyyMMdd);
	}

	public List<BoardDto> calBoardList(String yyyyMMdd) {
		return boardMapper.calBoardList(yyyyMMdd);
	}

	// 구매하기
	public boolean updateBoard1(UpdateBoardCommand updateBoardCommand) {
		// command:UI ---> DTO:DB
		BoardDto dto = new BoardDto();
		dto.setId(updateBoardCommand.getId());
		dto.setBoard_seq(updateBoardCommand.getBoard_seq());
		dto.setTitle(updateBoardCommand.getTitle());
		dto.setContent(updateBoardCommand.getContent());
		dto.setPrice(updateBoardCommand.getPrice());
		dto.setSold(1);
		dto.setSold_id(updateBoardCommand.getSold_id());
		return boardMapper.updateBoard1(dto);
	}

	// 판매 상품목록 조회
	public List<BoardDto> getsoldAllList(String id) {
		return boardMapper.getsoldAllList(id);
	}

	// 구매목록 조회
	public List<BoardDto> getmyAllList(String id) {
		return boardMapper.getmyAllList(id);
	}
}