package com.hk.project.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.UpdateBoardCommand;
import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.mapper.AccountMapper;
import com.hk.project.mapper.MemberMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserListService {
   
   @Autowired
   private AccountMapper accountMapper;
//   @Autowired
//   private MemberMapper memberMapper;
   
   //유저목록 조회
   public List<AccountDto> getUser(){
	  
      return accountMapper.getUser();
   }

   
   public AccountDto getUserDetail(String id) {
	   return accountMapper.getUserDetail(id);
   }
   //글 추가, 파일업로드및 파일정보 추가
   @Transactional
   public void getUser( MultipartRequest multipartRequest
                       , HttpServletRequest request) 
                       throws IllegalStateException, IOException {
      //command:UI -> dto:DB 데이터 옮겨담기
      AccountDto accountDto=new AccountDto();
      accountDto.getAccount_seq();
      accountDto.getMemberid();
      accountDto.getFintech_use_num();
      accountDto.getBank_name();
      accountDto.getAccount_num_masked();
      accountDto.getName();
      accountDto.getRole();
      accountDto.getId();
      
      
      
      
   }

   


}




