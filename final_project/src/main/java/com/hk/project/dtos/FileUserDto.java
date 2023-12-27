package com.hk.project.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "fileUserDto")
public class FileUserDto {

	private int file_seq;
	private String id;
	private String origin_filename;
	private String stored_filename;
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrigin_filename() {
		return origin_filename;
	}
	public void setOrigin_filename(String origin_filename) {
		this.origin_filename = origin_filename;
	}
	public String getStored_filename() {
		return stored_filename;
	}
	public void setStored_filename(String stored_filename) {
		this.stored_filename = stored_filename;
	}
	@Override
	public String toString() {
		return "FileUserDto [file_seq=" + file_seq + ", id=" + id + ", origin_filename=" + origin_filename
				+ ", stored_filename=" + stored_filename + "]";
	}
	public FileUserDto(int file_seq, String id, String origin_filename, String stored_filename) {
		super();
		this.file_seq = file_seq;
		this.id = id;
		this.origin_filename = origin_filename;
		this.stored_filename = stored_filename;
	}
	public FileUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
	