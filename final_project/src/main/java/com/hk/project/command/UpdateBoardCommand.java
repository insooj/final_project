package com.hk.project.command;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateBoardCommand {
	
	 private MultipartFile imageFile;

	private int sold;
	private String sold_id;
	
	public UpdateBoardCommand(MultipartFile imageFile, int sold, String sold_id, String id, int board_seq,
			@NotBlank(message = "제목을 입력하세요") String title, @NotBlank(message = "내용을 입력하세요") String content,
			@NotNull(message = "가격을 입력하세요") int price) {
		super();
		this.imageFile = imageFile;
		this.sold = sold;
		this.sold_id = sold_id;
		this.id = id;
		this.board_seq = board_seq;
		this.title = title;
		this.content = content;
		this.price = price;
	}


	public String getSold_id() {
		return sold_id;
	}


	public void setSold_id(String sold_id) {
		this.sold_id = sold_id;
	}


	public UpdateBoardCommand(MultipartFile imageFile, int sold, String id, int board_seq,
			@NotBlank(message = "제목을 입력하세요") String title, @NotBlank(message = "내용을 입력하세요") String content,
			@NotNull(message = "가격을 입력하세요") int price) {
		super();
		this.imageFile = imageFile;
		this.sold = sold;
		this.id = id;
		this.board_seq = board_seq;
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
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	private String id;
	public UpdateBoardCommand(String id, int board_seq, @NotBlank(message = "제목을 입력하세요") String title,
			@NotBlank(message = "내용을 입력하세요") String content, @NotNull(message = "가격을 입력하세요") String stored_filename,
			int price) {
		super();
		this.id = id;
		this.board_seq = board_seq;
		this.title = title;
		this.content = content;
		this.price = price;
	}

	@Override
	public String toString() {
		return "UpdateBoardCommand [imageFile=" + imageFile + ", sold=" + sold + ", sold_id=" + sold_id + ", id=" + id
				+ ", board_seq=" + board_seq + ", title=" + title + ", content=" + content + ", price=" + price + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private int board_seq;
	@NotBlank(message="제목을 입력하세요")
	private String title;
	@NotBlank(message="내용을 입력하세요")
	private String content;
	@NotNull(message="가격을 입력하세요")
	public int price;
	
	public UpdateBoardCommand(String id, int board_seq, @NotBlank(message = "제목을 입력하세요") String title,
			@NotBlank(message = "내용을 입력하세요") String content, @NotNull(message = "가격을 입력하세요") int price) {
		super();
		this.id = id;
		this.board_seq = board_seq;
		this.title = title;
		this.content = content;
		this.price = price;
	}
	public UpdateBoardCommand(@NotNull(message = "가격을 입력하세요") int price) {
		super();
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
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

	public UpdateBoardCommand(int board_seq, @NotBlank(message = "제목을 입력하세요") String title,
			@NotBlank(message = "내용을 입력하세요") String content, @NotNull(message="가격을 입력하세요") int price) {
		super();
		this.board_seq = board_seq;
		this.title = title;
		this.content = content;
		this.price = price;
	}
	public UpdateBoardCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
