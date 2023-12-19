package com.hk.project.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsertBoardCommand {

	private int sold;
	private String sold_id;
	public InsertBoardCommand(int sold, String sold_id, String id, @NotBlank(message = "제목을 입력하세요") String title,
			@NotBlank(message = "내용을 입력하세요") String content, @NotNull(message = "가격을 입려하세요") int price) {
		super();
		this.sold = sold;
		this.sold_id = sold_id;
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getSold_id() {
		return sold_id;
	}
	public void setSold_id(String sold_id) {
		this.sold_id = sold_id;
	}
	private String id;
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	@NotNull(message="가격을 입려하세요")
	private int price;
	public InsertBoardCommand(String id, @NotBlank(message = "제목을 입력하세요") String title,
			@NotBlank(message = "내용을 입력하세요") String content, @NotNull(message = "가격을 입력하세요") int price) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public InsertBoardCommand() {
		super();
		// TODO Auto-generated constructor stub
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
		return "InsertBoardCommand [sold=" + sold + ", sold_id=" + sold_id + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", price=" + price + "]";
	}
	
	
}