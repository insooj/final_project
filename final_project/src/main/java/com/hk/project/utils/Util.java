package com.hk.project.utils;

import java.awt.Font;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;

import com.hk.project.command.InsertBoardCommand;
import com.hk.project.dtos.CalDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.mapper.MemberMapper;

@Component
public class Util {
   
   //한자리를 두자리로 변환
   public  static String isTwo(String str) {
      return str.length()<2?"0"+str:str; // 5 --> "05", 10 --> "10"
   }
   
   //mdate: 날짜 형식 변환 --> yy-MM-dd HH:mm
   public String toDates(String mdate) {
      //Date 형식: "yyyy-MM-dd HH:mm:ss"
      String m=mdate.substring(0, 4)+"-"
            +mdate.substring(4, 6)+"-"
            +mdate.substring(6, 8)+" "
            +mdate.substring(8, 10)+":"
            +mdate.substring(10)+":00";
      
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
      Timestamp tm=Timestamp.valueOf(m);//문자열값을 Date타입으로 변환하는 코드
      return sdf.format(tm);// 문자열 타입일 경우 date타입으로 변환해서 사용해야 한다.
   }
   
   // 요일별 날짜 색깔 적용하기 : 파라미터 - i , dayOfWeek 필요
   // (공백수+현재일)%7==0 토요일
   // (공백수+현재일)%7==1 일요일
   public static String fontColor(int i, int dayOfWeek) {
      String str="black";//평일
      if((dayOfWeek-1+i)%7==0) {//토요일
         str="blue";
      }else if((dayOfWeek-1+i)%7==1) {//일요일
         str="red";
      }
      return str;
   }
   
   
//   //일일별 일정 목록 구하는 기능
   public static String getCalViewList(int i,List<CalDto> clist) {
      String d=isTwo(i+"");// 1 --> "01" 2자리로 변환
      String calList="";//"<p>title</p><p>title</p><p>title</p>"
      for (int j = 0; j < clist.size(); j++) {
         //한달 일정 목록중에 해당일(i)값과 일치하는지 여부 판단
         if(clist.get(j).getMdate().substring(6, 8).equals(d)){
            calList+="<p style='font-size:14px;'>"
                  +(clist.get(j).getSdate().length()>7?
                    clist.get(j).getSdate().substring(0, 7)+"..":
                    clist.get(j).getSdate())
                  +"~"
                  +(clist.get(j).getEdate().length()>7?
                    clist.get(j).getEdate().substring(0, 7)+"..":
                    clist.get(j).getEdate())    
                  +"</p>";
         
         }
      }
      return calList;
   }
   
   
// 월별스케쥴 일정 목록 구하는 기능
 public static String getMonth_scheduleList(int i,List<CalDto> slist) {
    String d=isTwo(i+"");// 1 --> "01" 2자리로 변환
    String monthList="";//"<p>title</p><p>title</p><p>title</p>"
    for (int j = 0; j < slist.size(); j++) {
       //한달 일정 목록중에 해당일(i)값과 일치하는지 여부 판단
       if(slist.get(j).getMdate().substring(6, 8).equals(d)){
          monthList+="<p style='font-size:14px;'>"
                +(slist.get(j).getName().length()>8?
                  slist.get(j).getName().substring(0, 8)+"..":
                  slist.get(j).getName())
                +"|"
                +(slist.get(j).getSdate().length()>7?
                  slist.get(j).getSdate().substring(0, 7)+"..":
                  slist.get(j).getSdate()) 
                +"~"
                +(slist.get(j).getEdate().length()>7?
                        slist.get(j).getEdate().substring(0, 7)+"..":
                        slist.get(j).getEdate()) 
                +"</p>";
       
       }
    }
    return monthList;
 }
   public static String roleColor(String role) {
      String str="black";   
      
      if(role.equals("사장")) {
          str="red";
          Font font = new Font("돋음",Font.BOLD,30);
      }
//      }else if(role.equals("직원")){
//         str="blue";
//      }   
      return str;
   }
   public static String scheduletime() {
	      String str="black";   
	      str="#6200EA";
	     

	      return str;
	   }
}














