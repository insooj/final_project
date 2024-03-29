package com.hk.project.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//일정추가 페이지에서 입력내용 : ID, 일정날짜, 제목, 내용
//         <select>2023 11 07 06 10 </select> 선탣해서 입력
               // DB: seq, id, title, content, mdate, regdate
//@Data
public class InsertMonthCommand {

   
   public int seq;
   
   private String id;
   
   private String name;
   
   private String role;
  
   private String content;
   
   private String sdate;
   
   private String edate;
   
   private int month;
   
   private int year;
   
   private int date;
   
   private int hour;
   
   private int min;
   
   private String work_hours;
   

   public InsertMonthCommand(int seq, String id, String name, String role, String content, String sdate, String edate,
		int month, int year, int date, int hour, int min, String work_hours) {
	super();
	this.seq = seq;
	this.id = id;
	this.name = name;
	this.role = role;
	this.content = content;
	this.sdate = sdate;
	this.edate = edate;
	this.month = month;
	this.year = year;
	this.date = date;
	this.hour = hour;
	this.min = min;
	this.work_hours = work_hours;
}



public String getWork_hours() {
	return work_hours;
}



public void setWork_hours(String work_hours) {
	this.work_hours = work_hours;
}



public InsertMonthCommand(int seq, String id, String name, String role, String content, String sdate, String edate,
			int month, int year, int date, int hour, int min) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.role = role;
		this.content = content;
		this.sdate = sdate;
		this.edate = edate;
		this.month = month;
		this.year = year;
		this.date = date;
		this.hour = hour;
		this.min = min;
	}


   
   @Override
public String toString() {
	return "InsertCalCommand [seq=" + seq + ", id=" + id + ", name=" + name + ", role=" + role + ", content=" + content
			+ ", sdate=" + sdate + ", edate=" + edate + ", month=" + month + ", year=" + year + ", date=" + date
			+ ", hour=" + hour + ", min=" + min + ", work_hours=" + work_hours + "]";
}
   
   
   public String getName() {
		return name;
	}
   public void setName(String name) {
		this.name = name;
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
   public int getMonth() {
      return month;
   }
   public void setMonth(int month) {
      this.month = month;
   }
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
   }
   
   public int getDate() {
      return date;
   }

   public void setDate(int date) {
      this.date = date;
   }

   public int getHour() {
      return hour;
   }

   public void setHour(int hour) {
      this.hour = hour;
   }

   public int getMin() {
      return min;
   }

   public void setMin(int min) {
      this.min = min;
   }
   
   public InsertMonthCommand() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   
   
   
   
}