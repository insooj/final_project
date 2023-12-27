package com.hk.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.project.command.AddUserCommand;
import com.hk.project.command.InsertBoardCommand;
import com.hk.project.command.LoginCommand;
import com.hk.project.command.UpdatePasswordCommand;
import com.hk.project.command.UpdateUserCommand;
import com.hk.project.dtos.BoardDto;
import com.hk.project.dtos.FileBoardDto;
import com.hk.project.dtos.FileUserDto;
import com.hk.project.dtos.MemberDto;
import com.hk.project.mapper.FileMapper;
import com.hk.project.mapper.FileUserMapper;
import com.hk.project.mapper.MemberMapper;
import com.hk.project.status.RoleStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor ;lombok 기능: final 필드를 초기화 - Autowired 생략가능
@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileUserMapper fileUserMapper;
	
	@Autowired
	private FileUserService fileUserService;
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public boolean addUser(AddUserCommand dto) {

		MemberDto mdto = new MemberDto();
		mdto.setId(dto.getId());
		mdto.setName(dto.getName());

		// password암호화하여 저장하자
//		mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));

		mdto.setPassword(dto.getPassword());
		mdto.setEmail(dto.getEmail());
		mdto.setAddress(dto.getAddress());
		mdto.setUseraccesstoken(dto.getUseraccesstoken());
		mdto.setUserrefreshtoken(dto.getUserrefreshtoken());
		mdto.setUserseqno(dto.getUserseqno());
		mdto.setPhone(dto.getPhone());

		mdto.setRole(RoleStatus.직원 + "");// 등급추가
		return memberMapper.addUser(mdto);
	}

	public String idChk(String id) {
		return memberMapper.idChk(id);
	}

	public String login(LoginCommand loginCommand, HttpServletRequest request, Model model) {
		MemberDto dto = memberMapper.loginUser(loginCommand.getId());
		String path = "home";
		if (dto != null) {
			// 로그인 폼에서 입력받은 패스워드값과 DB에 암호화된 패스워드 비교
//			if (passwordEncoder.matches(loginCommand.getPassword(), dto.getPassword())) {
			if (loginCommand.getPassword().equals(dto.getPassword())) {
				System.out.println("패스워드 같음: 회원이 맞음");
				// session객체에 로그인 정보 저장
				request.getSession().setAttribute("mdto", dto);
				return path;
			} else {
				System.out.println("패스워드 틀림");
				model.addAttribute("msg", "패스워드를 확인하세요");
				path = "member/login";
			}
		} else {
			System.out.println("회원이 아닙니다. ");
			model.addAttribute("msg", "아이디를 확인하세요");
			path = "member/login";
		}

		return path;
	}

	public MemberDto getUser(MemberDto dto) {

		return memberMapper.getUser(dto);
	}

	public boolean userUpdate(UpdateUserCommand updateUserCommand) {

		MemberDto dto = new MemberDto();
		dto.setId(updateUserCommand.getId());
		dto.setPassword(updateUserCommand.getPassword());
		dto.setAddress(updateUserCommand.getAddress());
		dto.setEmail(updateUserCommand.getEmail());
//		dto.setPhone(updateUserCommand.getPhone());
		return memberMapper.userUpdate(dto);

	}

	public List<FileUserDto> fileuser(MemberDto dto) {
		
		return memberMapper.fileUser(dto);
	}

	
	
	@Transactional
	public void insertfile( MultipartRequest multipartRequest,MemberDto dto,
			HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("파일첨부여부:" + multipartRequest.getFiles("filename").get(0).isEmpty());
		// 첨부된 파일들이 있는 경우
		if (!multipartRequest.getFiles("filename").get(0).isEmpty()) {
			// 파일 저장경로 설정: 절대경로, 상대경로
			String filepath = request.getSession().getServletContext().getRealPath("upload");
			System.out.println("파일저장경로:" + filepath);
			// 파일업로드 작업은 FileService쪽에서 업로드하고 업로드된 파일정보 반환
			List<FileUserDto> uploadFileList = fileUserService.uploadFiles(filepath, multipartRequest, dto);
			// 파일정보를 DB에 추가
			// 글추가할때 board_seq 증가된 값---> file정보를 추가할때 사용ist<FileBoardDto> uploadFileList = fileService.uploadFiles(filepath, multipartRequest);
			// 파일정보를 DB에 추가
			// 글추가할때 board_seq 증가된 값---> file정보를 추가할때 사용
			// Testboard: board_seq PK board_seq FK
			for (FileUserDto fDto : uploadFileList) {
				fileUserMapper.insertFileuser(fDto);
				
//				fileMapper.insertFileBoard(new FileUserDto(0,dto.getId() // 증가된 board_seq값을 넣는다
//						fDto.getOrigin_filename(), fDto.getStored_filename()));
			}
		}
	}
}