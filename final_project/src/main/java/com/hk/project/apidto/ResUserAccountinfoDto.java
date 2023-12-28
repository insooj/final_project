package com.hk.project.apidto;

public class ResUserAccountinfoDto {
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
	private String savings_bank_name;
	private String user_seq_no;
	private String account_num;
	private String account_seq;
	private String account_type;
	private String scope;
	private String fintech_use_num;
	private String account_num_masked;
	private String payer_num;
	private String inquiry_agree_yn;
	private String transfer_agree_yn;
	private String user_email;
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
	public String getSavings_bank_name() {
		return savings_bank_name;
	}
	public void setSavings_bank_name(String savings_bank_name) {
		this.savings_bank_name = savings_bank_name;
	}
	public String getUser_seq_no() {
		return user_seq_no;
	}
	public void setUser_seq_no(String user_seq_no) {
		this.user_seq_no = user_seq_no;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getAccount_seq() {
		return account_seq;
	}
	public void setAccount_seq(String account_seq) {
		this.account_seq = account_seq;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getFintech_use_num() {
		return fintech_use_num;
	}
	public void setFintech_use_num(String fintech_use_num) {
		this.fintech_use_num = fintech_use_num;
	}
	public String getAccount_num_masked() {
		return account_num_masked;
	}
	public void setAccount_num_masked(String account_num_masked) {
		this.account_num_masked = account_num_masked;
	}
	public String getPayer_num() {
		return payer_num;
	}
	public void setPayer_num(String payer_num) {
		this.payer_num = payer_num;
	}
	public String getInquiry_agree_yn() {
		return inquiry_agree_yn;
	}
	public void setInquiry_agree_yn(String inquiry_agree_yn) {
		this.inquiry_agree_yn = inquiry_agree_yn;
	}
	public String getTransfer_agree_yn() {
		return transfer_agree_yn;
	}
	public void setTransfer_agree_yn(String transfer_agree_yn) {
		this.transfer_agree_yn = transfer_agree_yn;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	@Override
	public String toString() {
		return "ResUserAccountinfoDto [api_tran_id=" + api_tran_id + ", api_tran_dtm=" + api_tran_dtm + ", rsp_code="
				+ rsp_code + ", rsp_message=" + rsp_message + ", bank_tran_id=" + bank_tran_id + ", bank_tran_date="
				+ bank_tran_date + ", bank_code_tran=" + bank_code_tran + ", bank_rsp_code=" + bank_rsp_code
				+ ", bank_rsp_message=" + bank_rsp_message + ", bank_name=" + bank_name + ", savings_bank_name="
				+ savings_bank_name + ", user_seq_no=" + user_seq_no + ", account_num=" + account_num + ", account_seq="
				+ account_seq + ", account_type=" + account_type + ", scope=" + scope + ", fintech_use_num="
				+ fintech_use_num + ", account_num_masked=" + account_num_masked + ", payer_num=" + payer_num
				+ ", inquiry_agree_yn=" + inquiry_agree_yn + ", transfer_agree_yn=" + transfer_agree_yn
				+ ", user_email=" + user_email + "]";
	}
	public ResUserAccountinfoDto(String api_tran_id, String api_tran_dtm, String rsp_code, String rsp_message,
			String bank_tran_id, String bank_tran_date, String bank_code_tran, String bank_rsp_code,
			String bank_rsp_message, String bank_name, String savings_bank_name, String user_seq_no, String account_num,
			String account_seq, String account_type, String scope, String fintech_use_num, String account_num_masked,
			String payer_num, String inquiry_agree_yn, String transfer_agree_yn, String user_email) {
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
		this.savings_bank_name = savings_bank_name;
		this.user_seq_no = user_seq_no;
		this.account_num = account_num;
		this.account_seq = account_seq;
		this.account_type = account_type;
		this.scope = scope;
		this.fintech_use_num = fintech_use_num;
		this.account_num_masked = account_num_masked;
		this.payer_num = payer_num;
		this.inquiry_agree_yn = inquiry_agree_yn;
		this.transfer_agree_yn = transfer_agree_yn;
		this.user_email = user_email;
	}
	public ResUserAccountinfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}