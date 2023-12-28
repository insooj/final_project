package com.hk.project.apidto;

public class UserAccountinfoDto {
	private String bank_tran_id;
	private String user_seq_no;
	private String bank_code_std;
	private String account_num;
	private String account_seq;
	private String scope;
	public String getBank_tran_id() {
		return bank_tran_id;
	}
	public void setBank_tran_id(String bank_tran_id) {
		this.bank_tran_id = bank_tran_id;
	}
	public String getUser_seq_no() {
		return user_seq_no;
	}
	public void setUser_seq_no(String user_seq_no) {
		this.user_seq_no = user_seq_no;
	}
	public String getBank_code_std() {
		return bank_code_std;
	}
	public void setBank_code_std(String bank_code_std) {
		this.bank_code_std = bank_code_std;
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
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "UserAccountinfoDto [bank_tran_id=" + bank_tran_id + ", user_seq_no=" + user_seq_no + ", bank_code_std="
				+ bank_code_std + ", account_num=" + account_num + ", account_seq=" + account_seq + ", scope=" + scope
				+ "]";
	}
	public UserAccountinfoDto(String bank_tran_id, String user_seq_no, String bank_code_std, String account_num,
			String account_seq, String scope) {
		super();
		this.bank_tran_id = bank_tran_id;
		this.user_seq_no = user_seq_no;
		this.bank_code_std = bank_code_std;
		this.account_num = account_num;
		this.account_seq = account_seq;
		this.scope = scope;
	}
	public UserAccountinfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}