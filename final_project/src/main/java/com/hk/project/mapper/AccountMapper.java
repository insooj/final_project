package com.hk.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.MemberDto;

@Mapper
public interface AccountMapper {

   //글목록
//   public List<AccountDto> getUserList();
   //글목록
   public List<AccountDto> getUser();
   public boolean roleup(AccountDto dto);
   public boolean roledown(AccountDto dto);
   public boolean rolem(AccountDto dto);
   
   public AccountDto getUserDetail(String id);
   public boolean membermulDel(AccountDto dto);
   public AccountDto UserDetail(String id);
   //글 삭제
//   public boolean mulDel(String[] seqs);
   
}
