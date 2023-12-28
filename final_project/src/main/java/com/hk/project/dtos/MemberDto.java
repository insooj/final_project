package com.hk.project.dtos;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

@Alias(value = "memberDto")
public class MemberDto {	
	private String name;
	private String password;
	private String email;
	private String address;
	private int memberId;
	private String id;
	private String role;
	private String useraccesstoken;
	private String userrefreshtoken;
	private int userseqno;
	private String phone;
	
	
	public MemberDto(String name, String password, String email, String address, int memberId, String id, String role,
			String useraccesstoken, String userrefreshtoken, int userseqno, String phone) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.memberId = memberId;
		this.id = id;
		this.role = role;
		this.useraccesstoken = useraccesstoken;
		this.userrefreshtoken = userrefreshtoken;
		this.userseqno = userseqno;
		this.phone = phone;
	}
	
	//Join용 맴버필드
		private List<FileUserDto> fileUserDto;
		
	
	public MemberDto(String name, String password, String email, String address, int memberId, String id, String role,
			String useraccesstoken, String userrefreshtoken, int userseqno, String phone,
			List<FileUserDto> fileUserDto) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.memberId = memberId;
		this.id = id;
		this.role = role;
		this.useraccesstoken = useraccesstoken;
		this.userrefreshtoken = userrefreshtoken;
		this.userseqno = userseqno;
		this.phone = phone;
		this.fileUserDto = fileUserDto;
	}

	public List<FileUserDto> getFileUserDto() {
			return fileUserDto;
		}

		public void setFileUserDto(List<FileUserDto> fileUserDto) {
			this.fileUserDto = fileUserDto;
		}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, memberId, name, password, phone, role, useraccesstoken,
				userrefreshtoken, userseqno);
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUseraccesstoken() {
		return useraccesstoken;
	}
	public void setUseraccesstoken(String useraccesstoken) {
		this.useraccesstoken = useraccesstoken;
	}
	public String getUserrefreshtoken() {
		return userrefreshtoken;
	}
	public void setUserrefreshtoken(String userrefreshtoken) {
		this.userrefreshtoken = userrefreshtoken;
	}
	public int getUserseqno() {
		return userseqno;
	}
	public void setUserseqno(int userseqno) {
		this.userseqno = userseqno;
	}
	@Override
	public String toString() {
		return "MemberDto [name=" + name + ", password=" + password + ", email=" + email + ", address=" + address
				+ ", memberId=" + memberId + ", id=" + id + ", role=" + role + ", useraccesstoken=" + useraccesstoken
				+ ", userrefreshtoken=" + userrefreshtoken + ", userseqno=" + userseqno + ", phone=" + phone
				+ ", fileUserDto=" + fileUserDto + "]";
	}
	public MemberDto(int memberId, String id, String name, String password, String email, String address, String role,
			String useraccesstoken, String userrefreshtoken, int userseqno) {
		super();
		this.memberId = memberId;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
		this.useraccesstoken = useraccesstoken;
		this.userrefreshtoken = userrefreshtoken;
		this.userseqno = userseqno;
	}
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}