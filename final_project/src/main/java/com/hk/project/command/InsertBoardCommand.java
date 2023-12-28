package com.hk.project.command;

import jakarta.validation.constraints.NotBlank;

public class InsertBoardCommand {

	private String id;
	private String role;
	
	
	
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	
	
	public InsertBoardCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public InsertBoardCommand(String id, String role, @NotBlank(message = "제목을 입력하세요") String title,
			@NotBlank(message = "내용을 입력하세요") String content) {
		super();
		this.id = id;
		this.role = role;
		this.title = title;
		this.content = content;
	}
	
	
	
	
	
	
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "InsertBoardCommand [id=" + id + ", role=" + role + ", title=" + title + ", content=" + content + "]";
	}
	
	
}