package com.hk.project.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.InsertPayCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.PaymentDto;
import com.hk.project.mapper.BoardMapper;
import com.hk.project.mapper.FileMapper;
import com.hk.project.mapper.PayRequestMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PayService {
	
	@Autowired
	private PayRequestMapper payrequestMapper;
	
	//요청목록 조회
		public List<PaymentDto> getAllList(){
			return payrequestMapper.getAllList();
		}
		
		
		
		//글 추가, 파일업로드및 파일정보 추가
		@Transactional
		public void insertPayment(InsertPayCommand insertPayCommand
				              , MultipartRequest multipartRequest
				              , HttpServletRequest request) 
				              throws IllegalStateException, IOException {
			//command:UI -> dto:DB 데이터 옮겨담기
			PaymentDto paymentDto=new PaymentDto();
			paymentDto.setId(insertPayCommand.getId());
			paymentDto.setName(insertPayCommand.getName());
			paymentDto.setRole(insertPayCommand.getRole());
			paymentDto.setMoney(insertPayCommand.getMoney());
			paymentDto.setDelflag(insertPayCommand.getDelflag());
//			paymentDto.setBoard_seq(insertPayCommand.getBoard_seq());
			
			
			//새글을 추가할때 파라미터로 전달된 boardDto객체에 자동으로,
			//증가된 board_seq값이 저장
			payrequestMapper.insertPayment(paymentDto);//새글 추가
			
			
		}
	
		
		//상세내용조회
		public PaymentDto getBoard(int board_seq) {
			return payrequestMapper.getPayment(board_seq);
		}
		
		//수정하기
//		public boolean updateBoard(UpdateBoardCommand updateBoardCommand) {
//			//command:UI ---> DTO:DB 
//			BoardDto dto=new BoardDto();
//			dto.setBoard_seq(updateBoardCommand.getBoard_seq());
//			dto.setTitle(updateBoardCommand.getTitle());
//			dto.setContent(updateBoardCommand.getContent());
//			return boardMapper.updateBoard(dto);
//		}

//		public boolean mulDel(String[] seqs) {
//			return boardMapper.mulDel(seqs);
//		}
		
		//요청 승인
		public boolean complete(String board_seq) {
			return payrequestMapper.complete(board_seq);
		}
		//요청 거절
		public boolean refuse(String board_seq) {
			return payrequestMapper.refuse(board_seq);
		}



		public void approveRequest(Long id) {
			
			
		}
}





