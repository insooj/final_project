package com.hk.project.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CalDto {

	private int seq;
	private String id;
	private String name;
	private String content;
	private String mdate;
	private String sdate;
	private String edate;
	private String role;
	private String work_hours;
	private String total_hours;
	private String month_pay;
	private String total_minutes;
	public CalDto(int seq, String id, String name, String content, String mdate, String sdate, String edate,
			String role, String work_hours, String total_hours, String month_pay, MemberDto memberDto,
			String total_minutes, List<FileUserDto> fileUserDto) {
		super();	
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.content = content;
		this.mdate = mdate;
		this.sdate = sdate;
		this.edate = edate;
		this.role = role;
		this.work_hours = work_hours;
		this.total_hours = total_hours;
		this.month_pay = month_pay;
		this.memberDto = memberDto;
		this.total_minutes = total_minutes;
		this.fileUserDto = fileUserDto;
	}

	public String getMonth_pay() {
		return month_pay;
	}

	public void setMonth_pay(String month_pay) {
		this.month_pay = month_pay;
	}


	private MemberDto memberDto;
	public CalDto(int seq, String id, String name, String content, String mdate, String sdate, String edate,
			String role, String work_hours, String total_hours, MemberDto memberDto, String total_minutes,
			List<FileUserDto> fileUserDto) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.content = content;
		this.mdate = mdate;
		this.sdate = sdate;
		this.edate = edate;
		this.role = role;
		this.work_hours = work_hours;
		this.total_hours = total_hours;
		this.memberDto = memberDto;
		this.total_minutes = total_minutes;
		this.fileUserDto = fileUserDto;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	public CalDto(int seq, String id, String name, String content, String mdate, String sdate, String edate,
			String role, String work_hours, String total_hours, String total_minutes, List<FileUserDto> fileUserDto) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.content = content;
		this.mdate = mdate;
		this.sdate = sdate;
		this.edate = edate;
		this.role = role;
		this.work_hours = work_hours;
		this.total_hours = total_hours;
		this.total_minutes = total_minutes;
		this.fileUserDto = fileUserDto;
	}

	public String getTotal_hours() {
		return total_hours;
	}

	public void setTotal_hours(String total_hours) {
		this.total_hours = total_hours;
	}

	public String getTotal_minutes() {
		return total_minutes;
	}

	public void setTotal_minutes(String total_minutes) {
		this.total_minutes = total_minutes;
	}


	
	
	public CalDto(int seq, String id, String name, String content, String mdate, String sdate, String edate,
			String role, String work_hours, List<FileUserDto> fileUserDto) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.content = content;
		this.mdate = mdate;
		this.sdate = sdate;
		this.edate = edate;
		this.role = role;
		this.work_hours = work_hours;
		this.fileUserDto = fileUserDto;
	}

	public String getWork_hours() {
		return work_hours;
	}

	public void setWork_hours(String work_hours) {
		this.work_hours = work_hours;
	}


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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public CalDto(int seq, String id, String name, String content, String mdate, String sdate, String edate,
			String role, List<FileUserDto> fileUserDto) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.content = content;
		this.mdate = mdate;
		this.sdate = sdate;
		this.edate = edate;
		this.role = role;
		this.fileUserDto = fileUserDto;
	}


	@Override
	public String toString() {
		return "CalDto [seq=" + seq + ", id=" + id + ", name=" + name + ", content=" + content + ", mdate=" + mdate
				+ ", sdate=" + sdate + ", edate=" + edate + ", role=" + role + ", work_hours=" + work_hours
				+ ", total_hours=" + total_hours + ", month_pay=" + month_pay + ", memberDto=" + memberDto
				+ ", total_minutes=" + total_minutes + ", fileUserDto=" + fileUserDto + "]";
	}

}
