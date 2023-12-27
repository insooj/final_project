package com.hk.project.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//일정추가 페이지에서 입력내용 : ID, 일정날짜, 제목, 내용
//         <select>2023 11 07 06 10 </select> 선탣해서 입력
               // DB: seq, id, title, content, mdate, regdate
//@Data
public class InsertCalCommand {

   
   public int seq;
   
   private String id;
   
   private String role;
   


   

   private String content;
   
   private String sdate;
   
   private String edate;
   
   private int month;
   
   private int year;
   
   private int date;
   
   private int hour;
   
   private int min;
   
   

   public InsertCalCommand(int seq,String id,String role, String content, String sdate, String edate, int month, int year, int date,
         int hour, int min) {
      super();
      this.seq = seq;
      this.id = id;
      this.content = content;
      this.sdate = sdate;
      this.edate = edate;
      this.month = month;
      this.year = year;
      this.date = date;
      this.hour = hour;
      this.min = min;
      this.role = role;
   }

   
   @Override
   public String toString() {
      return "InsertCalCommand [seq=" + seq + ", id=" + id + ", role=" + role + ", content=" + content + ", sdate="
            + sdate + ", edate=" + edate + ", month=" + month + ", year=" + year + ", date=" + date + ", hour="
            + hour + ", min=" + min + "]";
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
   
   public InsertCalCommand() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   
   
   
   
}