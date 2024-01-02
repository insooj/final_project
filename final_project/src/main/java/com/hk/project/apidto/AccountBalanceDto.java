package com.hk.project.apidto;

import lombok.Data;

@Data
public class AccountBalanceDto {
	private String api_tran_id;
	private String api_tran_dtm;
	private String rsp_code;
	private String rsp_message;
	
	private String bank_tran_id;
	private String bank_tran_date;
	private String bank_code_tran;
	private String bank_rsp_code;
	private String bank_rsp_message;
	private String bank_name;
	private String fintech_use_num;
	private String tran_dtime;
	private String balance_amt;
	private String available_amt;
	private String account_type;
	private String product_name;
	private String account_issue_date;
	private String maturity_date;
	private String last_tran_date;
	
	public AccountBalanceDto(String api_tran_id, String api_tran_dtm, String rsp_code, String rsp_message,
			String bank_tran_id, String bank_tran_date, String bank_code_tran, String bank_rsp_code,
			String bank_rsp_message, String bank_name, String fintech_use_num, String tran_dtime, String balance_amt,
			String available_amt, String account_type, String product_name, String account_issue_date,
			String maturity_date, String last_tran_date) {
		super();
		this.api_tran_id = api_tran_id;
		this.api_tran_dtm = api_tran_dtm;
		this.rsp_code = rsp_code;
		this.rsp_message = rsp_message;
		this.bank_tran_id = bank_tran_id;
		this.bank_tran_date = bank_tran_date;
		this.bank_code_tran = bank_code_tran;
		this.bank_rsp_code = bank_rsp_code;
		this.bank_rsp_message = bank_rsp_message;
		this.bank_name = bank_name;
		this.fintech_use_num = fintech_use_num;
		this.tran_dtime = tran_dtime;
		this.balance_amt = balance_amt;
		this.available_amt = available_amt;
		this.account_type = account_type;
		this.product_name = product_name;
		this.account_issue_date = account_issue_date;
		this.maturity_date = maturity_date;
		this.last_tran_date = last_tran_date;
	}
	public AccountBalanceDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountBalanceDto(String api_tran_id, String api_tran_dtm, String rsp_code, String rsp_message,
			String bank_tran_id, String bank_tran_date, String bank_code_tran, String bank_rsp_code,
			String bank_rsp_message, String bank_name, String fintech_use_num, String balance_amt, String available_amt,
			String account_type, String product_name, String account_issue_date, String maturity_date,
			String last_tran_date) {
		super();
		this.api_tran_id = api_tran_id;
		this.api_tran_dtm = api_tran_dtm;
		this.rsp_code = rsp_code;
		this.rsp_message = rsp_message;
		this.bank_tran_id = bank_tran_id;
		this.bank_tran_date = bank_tran_date;
		this.bank_code_tran = bank_code_tran;
		this.bank_rsp_code = bank_rsp_code;
		this.bank_rsp_message = bank_rsp_message;
		this.bank_name = bank_name;
		this.fintech_use_num = fintech_use_num;
		this.balance_amt = balance_amt;
		this.available_amt = available_amt;
		this.account_type = account_type;
		this.product_name = product_name;
		this.account_issue_date = account_issue_date;
		this.maturity_date = maturity_date;
		this.last_tran_date = last_tran_date;
	}
	@Override
	public String toString() {
		return "AccountBalanceDto [api_tran_id=" + api_tran_id + ", api_tran_dtm=" + api_tran_dtm + ", rsp_code="
				+ rsp_code + ", rsp_message=" + rsp_message + ", bank_tran_id=" + bank_tran_id + ", bank_tran_date="
				+ bank_tran_date + ", bank_code_tran=" + bank_code_tran + ", bank_rsp_code=" + bank_rsp_code
				+ ", bank_rsp_message=" + bank_rsp_message + ", bank_name=" + bank_name + ", fintech_use_num="
				+ fintech_use_num + ", tran_dtime=" + tran_dtime + ", balance_amt=" + balance_amt + ", available_amt="
				+ available_amt + ", account_type=" + account_type + ", product_name=" + product_name
				+ ", account_issue_date=" + account_issue_date + ", maturity_date=" + maturity_date
				+ ", last_tran_date=" + last_tran_date + "]";
	}
	public String getApi_tran_id() {
		return api_tran_id;
	}
	public void setApi_tran_id(String api_tran_id) {
		this.api_tran_id = api_tran_id;
	}
	public String getApi_tran_dtm() {
		return api_tran_dtm;
	}
	public void setApi_tran_dtm(String api_tran_dtm) {
		this.api_tran_dtm = api_tran_dtm;
	}
	public String getRsp_code() {
		return rsp_code;
	}
	public void setRsp_code(String rsp_code) {
		this.rsp_code = rsp_code;
	}
	public String getRsp_message() {
		return rsp_message;
	}
	public void setRsp_message(String rsp_message) {
		this.rsp_message = rsp_message;
	}
	public String getBank_tran_id() {
		return bank_tran_id;
	}
	public void setBank_tran_id(String bank_tran_id) {
		this.bank_tran_id = bank_tran_id;
	}
	public String getBank_tran_date() {
		return bank_tran_date;
	}
	public void setBank_tran_date(String bank_tran_date) {
		this.bank_tran_date = bank_tran_date;
	}
	public String getBank_code_tran() {
		return bank_code_tran;
	}
	public void setBank_code_tran(String bank_code_tran) {
		this.bank_code_tran = bank_code_tran;
	}
	public String getBank_rsp_code() {
		return bank_rsp_code;
	}
	public void setBank_rsp_code(String bank_rsp_code) {
		this.bank_rsp_code = bank_rsp_code;
	}
	public String getBank_rsp_message() {
		return bank_rsp_message;
	}
	public void setBank_rsp_message(String bank_rsp_message) {
		this.bank_rsp_message = bank_rsp_message;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getFintech_use_num() {
		return fintech_use_num;
	}
	public void setFintech_use_num(String fintech_use_num) {
		this.fintech_use_num = fintech_use_num;
	}
	public String getBalance_amt() {
		return balance_amt;
	}
	public void setBalance_amt(String balance_amt) {
		this.balance_amt = balance_amt;
	}
	public String getAvailable_amt() {
		return available_amt;
	}
	public void setAvailable_amt(String available_amt) {
		this.available_amt = available_amt;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getAccount_issue_date() {
		return account_issue_date;
	}
	public void setAccount_issue_date(String account_issue_date) {
		this.account_issue_date = account_issue_date;
	}
	public String getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
	public String getLast_tran_date() {
		return last_tran_date;
	}
	public void setLast_tran_date(String last_tran_date) {
		this.last_tran_date = last_tran_date;
	}
	public String getTran_dtime() {
		return tran_dtime;
	}
	public void setTran_dtime(String tran_dtime) {
		this.tran_dtime = tran_dtime;
	}
}