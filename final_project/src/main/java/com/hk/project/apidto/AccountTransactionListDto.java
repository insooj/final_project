package com.hk.project.apidto;

import java.util.List;

import lombok.Data;

@Data
public class AccountTransactionListDto {
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
	private String balance_amt;
	private String page_record_cnt;
	private String next_page_yn;
	private String befor_inquiry_trace_info;
	private List<AccountTransactionDto> res_list;
	public AccountTransactionListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountTransactionListDto(String api_tran_id, String api_tran_dtm, String rsp_code, String rsp_message,
			String bank_tran_id, String bank_tran_date, String bank_code_tran, String bank_rsp_code,
			String bank_rsp_message, String bank_name, String fintech_use_num, String balance_amt,
			String page_record_cnt, String next_page_yn, String befor_inquiry_trace_info,
			List<AccountTransactionDto> res_list) {
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
		this.page_record_cnt = page_record_cnt;
		this.next_page_yn = next_page_yn;
		this.befor_inquiry_trace_info = befor_inquiry_trace_info;
		this.res_list = res_list;
	}
	@Override
	public String toString() {
		return "AccountTransactionListDto [api_tran_id=" + api_tran_id + ", api_tran_dtm=" + api_tran_dtm
				+ ", rsp_code=" + rsp_code + ", rsp_message=" + rsp_message + ", bank_tran_id=" + bank_tran_id
				+ ", bank_tran_date=" + bank_tran_date + ", bank_code_tran=" + bank_code_tran + ", bank_rsp_code="
				+ bank_rsp_code + ", bank_rsp_message=" + bank_rsp_message + ", bank_name=" + bank_name
				+ ", fintech_use_num=" + fintech_use_num + ", balance_amt=" + balance_amt + ", page_record_cnt="
				+ page_record_cnt + ", next_page_yn=" + next_page_yn + ", befor_inquiry_trace_info="
				+ befor_inquiry_trace_info + ", res_list=" + res_list + "]";
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
	public String getPage_record_cnt() {
		return page_record_cnt;
	}
	public void setPage_record_cnt(String page_record_cnt) {
		this.page_record_cnt = page_record_cnt;
	}
	public String getNext_page_yn() {
		return next_page_yn;
	}
	public void setNext_page_yn(String next_page_yn) {
		this.next_page_yn = next_page_yn;
	}
	public String getBefor_inquiry_trace_info() {
		return befor_inquiry_trace_info;
	}
	public void setBefor_inquiry_trace_info(String befor_inquiry_trace_info) {
		this.befor_inquiry_trace_info = befor_inquiry_trace_info;
	}
	public List<AccountTransactionDto> getRes_list() {
		return res_list;
	}
	public void setRes_list(List<AccountTransactionDto> res_list) {
		this.res_list = res_list;
	}
	
}