package com.hk.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.BoardDto;

@Mapper
public interface AccountMapper {

   //글목록
   public List<AccountDto> getUserList();

   //글 삭제
//   public boolean mulDel(String[] seqs);
   
}
