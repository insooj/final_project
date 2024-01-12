package com.hk.project.dtos;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//@Data
@Alias(value = "paymentDto") //mapper.xml 에서 사용하는 변수명 설정
public class PaymentDto {

	private int board_seq;
	private String id;
	private String name;
	private String role;
	private Date regdate;
	private int money;
	private String delflag;
	
	private int paymoney;

	
	
	
	
	public PaymentDto(int board_seq, String id, String name, String role, Date regdate, int money, String delflag,
			int paymoney) {
		super();
		this.board_seq = board_seq;
		this.id = id;
		this.name = name;
		this.role = role;
		this.regdate = regdate;
		this.money = money;
		this.delflag = delflag;
		this.paymoney = paymoney;
	}




	public int getPaymoney() {
		return paymoney;
	}




	public void setPaymoney(int paymoney) {
		this.paymoney = paymoney;
	}




	public void setMoney(int money) {
		this.money = money;
	}




	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "PaymentDto [board_seq=" + board_seq + ", id=" + id + ", name=" + name + ", role=" + role + ", regdate="
				+ regdate + ", money=" + money + ", delflag=" + delflag + ", paymoney=" + paymoney + "]";
	}
	
	
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getMoney() {
		return money;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
}
	