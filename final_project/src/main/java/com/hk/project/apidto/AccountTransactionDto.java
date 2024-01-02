package com.hk.project.apidto;

import lombok.Data;

@Data
public class AccountTransactionDto {
	private String tran_date;
	private String tran_time;
	private String inout_type;
	private String tran_type;
	private String printed_content;
	private String tran_amt;
	private String after_balance_amt;
	public AccountTransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountTransactionDto(String tran_date, String tran_time, String inout_type, String tran_type,
			String printed_content, String tran_amt, String after_balance_amt, String branch_name) {
		super();
		this.tran_date = tran_date;
		this.tran_time = tran_time;
		this.inout_type = inout_type;
		this.tran_type = tran_type;
		this.printed_content = printed_content;
		this.tran_amt = tran_amt;
		this.after_balance_amt = after_balance_amt;
		this.branch_name = branch_name;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public String getTran_time() {
		return tran_time;
	}
	public void setTran_time(String tran_time) {
		this.tran_time = tran_time;
	}
	public String getInout_type() {
		return inout_type;
	}
	public void setInout_type(String inout_type) {
		this.inout_type = inout_type;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getPrinted_content() {
		return printed_content;
	}
	public void setPrinted_content(String printed_content) {
		this.printed_content = printed_content;
	}
	public String getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(String tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getAfter_balance_amt() {
		return after_balance_amt;
	}
	public void setAfter_balance_amt(String after_balance_amt) {
		this.after_balance_amt = after_balance_amt;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	private String branch_name;
	@Override
	public String toString() {
		return "AccountTransactionDto [tran_date=" + tran_date + ", tran_time=" + tran_time + ", inout_type="
				+ inout_type + ", tran_type=" + tran_type + ", printed_content=" + printed_content + ", tran_amt="
				+ tran_amt + ", after_balance_amt=" + after_balance_amt + ", branch_name=" + branch_name + "]";
	}
}