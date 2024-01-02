package com.hk.project.dtos;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class AccountDto {

	private int account_seq;
	private int memberid;
	private String fintech_use_num;
	private String bank_name;
	private String account_num_masked;
	private int money;
	
	


	

	public AccountDto(int account_seq, int memberid, String fintech_use_num, String bank_name,
			String account_num_masked, int money) {
		super();
		this.account_seq = account_seq;
		this.memberid = memberid;
		this.fintech_use_num = fintech_use_num;
		this.bank_name = bank_name;
		this.account_num_masked = account_num_masked;
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getAccount_seq() {
		return account_seq;
	}

	public void setAccount_seq(int account_seq) {
		this.account_seq = account_seq;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getFintech_use_num() {
		return fintech_use_num;
	}

	public void setFintech_use_num(String fintech_use_num) {
		this.fintech_use_num = fintech_use_num;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_num_masked() {
		return account_num_masked;
	}

	public void setAccount_num_masked(String account_num_masked) {
		this.account_num_masked = account_num_masked;
	}

	@Override
	public String toString() {
		return "AccountDto [account_seq=" + account_seq + ", memberid=" + memberid + ", fintech_use_num="
				+ fintech_use_num + ", bank_name=" + bank_name + ", account_num_masked=" + account_num_masked + ", money=" + money + "]";
	}

	public AccountDto(int account_seq, int memberid, String fintech_use_num, String bank_name,
			String account_num_masked) {
		super();
		this.account_seq = account_seq;
		this.memberid = memberid;
		this.fintech_use_num = fintech_use_num;
		this.bank_name = bank_name;
		this.account_num_masked = account_num_masked;
	}

	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}