package com.hk.project.command;

import jakarta.validation.constraints.NotBlank;

public class InsertPayCommand {

	private String id;
	private String role;
	private String name;
//	@NotBlank(message = "금액을 입력하세요")
	private String money;
	private String delflag;
	private int board_seq;
	
	





	public InsertPayCommand(String id, String role, String name, String money, String delflag, int board_seq) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.money = money;
		this.delflag = delflag;
		this.board_seq = board_seq;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}






	
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	
	
	
	
	public InsertPayCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	

	@Override
	public String toString() {
		return "InsertPayCommand [id=" + id + ", role=" + role + ", name=" + name + ", money=" + money + ", delflag="
				+ delflag + ", board_seq=" + board_seq + "]";
	}
}