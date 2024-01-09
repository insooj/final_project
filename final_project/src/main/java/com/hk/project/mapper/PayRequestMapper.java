package com.hk.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.PaymentDto;

@Mapper
public interface PayRequestMapper {

	//요청목록
	public List<PaymentDto> getAllList();
	//요청상세조회
	public PaymentDto getPayment(int board_seq);
	//요청추가
	public boolean insertPayment(PaymentDto dto);
//	글 수정
//	public boolean updatepay(PaymentDto dto);
	//요청승인
	public boolean complete(String seqs);
	
	//요청거절
	public boolean refuse(String seqs);
	
}






