package com.hk.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.MemberDto;

@Mapper
public interface MemberMapper {
	
	public boolean addUser(MemberDto dto);
	
	public String idChk(String id);
	
	public MemberDto loginUser(String id);
	
	public MemberDto getUser(MemberDto dto);
}






