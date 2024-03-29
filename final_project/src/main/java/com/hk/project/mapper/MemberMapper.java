package com.hk.project.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.AccountDto;
import com.hk.project.dtos.CalDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;

@Mapper
public interface MemberMapper {
   
   public boolean addUser(MemberDto dto);
   
   public String idChk(String id);
   
   public MemberDto loginUser(String id);
   
   public MemberDto getUser(MemberDto dto);
   
   public boolean userUpdate(MemberDto dto);
   
   public List<FileUserDto> fileUser(MemberDto dto);
   
   public List<FileUserDto> fileUserList(MemberDto dto);
   
   public boolean addAccount(AccountDto dto);
   
   public int getmemberid(String id);
  
   public MemberDto getuserAccount(MemberDto dto);
   
   public MemberDto getuserDetail(String id);
   
   public boolean Plus(AccountDto dto);
   public boolean Pay(MemberDto dto);
   

    //글목록
      public List<MemberDto> getuserAccount();
   public String totalworktime(Map<String, String>map);
   
   
}