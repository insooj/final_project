package com.hk.project.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CalDto {

	private int seq;
	private String id;
	private String content;
	private String mdate;
	private String sdate;
	private String edate;
	private String role;

	// Join용 맴버필드
	private List<FileUserDto> fileUserDto;

	public CalDto(List<FileUserDto> fileUserDto) {
		super();
		this.fileUserDto = fileUserDto;
	}

	public List<FileUserDto> getFileUserDto() {
		return fileUserDto;
	}

	public void setFileUserDto(List<FileUserDto> fileUserDto) {
		this.fileUserDto = fileUserDto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public CalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalDto(int seq, String id, String title, String content, String mdate, String sdate, String edate,
			String role) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.mdate = mdate;
		this.sdate = sdate;
		this.edate = edate;
		this.role = role;
	}

	@Override
	public String toString() {
		return "CalDto [seq=" + seq + ", id=" + id + ", content=" + content + ", mdate=" + mdate + ", sdate=" + sdate
				+ ", edate=" + edate + ", role=" + role + ", fileUserDto=" + fileUserDto + "]";
	}

}
