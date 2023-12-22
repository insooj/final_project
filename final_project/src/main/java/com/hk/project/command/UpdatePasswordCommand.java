package com.hk.project.command;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class UpdatePasswordCommand {

	@NotBlank(message = "비밀번호를 입력하세요")
	@Length(min = 8 , max = 16, message = "8자리이상, 16자이하로 입력하세요")
	private String password;
	
	private String id;
	public UpdatePasswordCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UpdatePasswordCommand(
			@NotBlank(message = "비밀번호를 입력하세요") @Length(min = 8, max = 16, message = "8자리이상, 16자이하로 입력하세요") String password,
			String id) {
		super();
		this.password = password;
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UpdatePasswordCommand [password=" + password + ", id=" + id + "]";
	}
	
}