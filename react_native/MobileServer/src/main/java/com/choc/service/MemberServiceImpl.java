package com.choc.service;

import com.choc.dto.MemberDTO;
import com.choc.model.Member;
import com.choc.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberRepository memberRepository;
	
	// application.properties 파일에 작성한 속성 가져오기
	@Value("${com.choc.upload.path}")
	private String uploadPath;
	
	// 업로드 날짜별로 이미지를 저장하기 위해서 날짜별로 디렉터리를 만들어서 경로를 리턴하는 메서드
	private String makeFolder() {
		// 오늘 날짜를 문자열로 생성 
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// 문자열을 치환 - /를 운영체제의 디렉터리 구분자로 치환
		String realUploadPath = str.replace("//", File.separator);
		// 디렉터리 생성
		File uploadPathDir = new File(uploadPath, realUploadPath);
		if(uploadPathDir.exists() == false) {
			uploadPathDir.mkdir();
		}
		return realUploadPath;
	}
	
	
	@Override
	public String registerMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		// 이메일 중복체크 - 별도로 구성할 수 있음
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		if(optional.isPresent()) {
			return "존재하는 이메일";
		}
		// 이름 중복 체크 - 별도로 구성할 수 있음
		List<Member> list = memberRepository.findMemberByName(dto.getName());
		if(list.size() > 0) {
			return "존재하는 이름";
		}
		
		// 파일 업로드 처리
		// 전송 받은 파일을 가져오기
		MultipartFile uploadFile = dto.getImage();
		// 전송된 파일이 있다면
		if(uploadFile.isEmpty() == false) {
			// 이미지 파일만 업로드하기위해서 이미지 파일이 아니면 작업 중단
			if(uploadFile.getContentType().startsWith("image")== false) {
				return null;
			}
			// 원본파일의 이름을 찾아오기
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
			
			// 파일을 업로드할 디렉터리 경로를 생성
			String realUploadPath = makeFolder();
			
			// 업로드할 파일의 경로를 생성
			String uuid = UUID.randomUUID().toString(); // 파일 이름의 중복을 피하기 위함.
			String saveName= uploadPath + File.separator + realUploadPath + File.separator + uuid + fileName;
			Path savePath = Paths.get(saveName);
			try {
				// 파일 업로드
				uploadFile.transferTo(savePath);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			// 이미지 경로를 dTO에 설정
			dto.setImageurl(realUploadPath+File.separator+uuid+fileName);
		}
		member = dtoToEntity(dto);
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public MemberDTO loginMember(MemberDTO dto) {
		// 이메일을 가지고 데이터를 찾아옴
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		
		if(optional.isPresent()) { // 존재하는 이메일
			// 비밀번호 확인
			Member member = optional.get();
			if(BCrypt.checkpw(dto.getPassword(), member.getPassword())) {
				// 로그인에 성공했을 때 로그인한 시간을 업데이트
				ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
				LocalDateTime now = nowUTC.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
				System.out.println(now);
				Member updateM = Member.builder().email(member.getEmail()).password(member.getPassword())
						.imageurl(member.getImageurl()).name(member.getName()).lastlogindate(now).build();
				memberRepository.save(updateM);
				return entityToDto(member);
			}//else {
			//	return null;
			//}
		}//else { // 존재하지 않는 이메일
		//	return null;
		//}
		return null;
	}

	@Override
	public MemberDTO getMember(MemberDTO dto) {
		// 이메일을 가지고 데이터를 찾아옴
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		
		if(optional.isPresent()) { // 존재하는 이메일
			// 비밀번호 확인
			Member member = optional.get();
			return entityToDto(member);
		}
		return null;
	}

	@Override
	public String updateMember(MemberDTO dto) {
		Member member =dtoToEntity(dto);
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public String deleteMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.delete(member);
		return member.getEmail();
	}

}
