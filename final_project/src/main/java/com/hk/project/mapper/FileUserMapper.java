package com.hk.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.FileUserDto;

@Mapper
public interface FileUserMapper {
	
	//파일 정보 추가
	public boolean insertFileuser(FileUserDto dto);
	//파일 정보 조회
	public FileUserDto getFileUserInfo(int file_seq);
	
}




