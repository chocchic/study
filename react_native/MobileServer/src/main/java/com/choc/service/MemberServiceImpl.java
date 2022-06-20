package com.choc.service;

import com.choc.dto.MemberDTO;
import com.choc.model.Member;
import com.choc.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

	//application.properties 파일에 작성한 속성 가져오기
	@Value("${com.choc.upload.path}")
	private String uploadPath;
	
	//업로드한 날짜 별로 이미지를 저장하기 위해서 날짜별로 디렉토리를 만들어서 경로를 리턴하는 메서드
	private String makeFolder() {
		//오늘 날짜를 문자열로 생성
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		//문자열을 치환 - /를 운영체제의 디렉토리 구분자로 치환
		String realUploadPath = str.replace("//", File.separator);
		//디렉토리 생성
		File uploadPathDir = new File(uploadPath, realUploadPath);
		if(uploadPathDir.exists() == false) {
			uploadPathDir.mkdirs();
		}
		return realUploadPath;
		
	}
	
	@Override
	public String registerMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		//이메일 중복 체크 - 별도로 구성할 수 있음
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		if(optional.isPresent()) {
			return "존재하는 이메일";
		}
		
		//이름 중복 체크 - 별도로 구성할 수 있음
		List<Member> list = memberRepository.findMemberByName(dto.getName());
		if(list.size() > 0) {
			return "존재하는 이름";
		}
		
		//파일 업로드 처리
		//전송 받은 파일을 가져오기
		MultipartFile uploadFile = dto.getImage();
		//전송된 파일이 있다면
		if(uploadFile.isEmpty() == false) {
			//이미지 파일 만 업로드하기 위해서 이미지 파일이 아니면 작업 중단
			if(uploadFile.getContentType().startsWith("image") == false) {
				return null;
			}
			//원본 파일의 파일 이름 찾아오기
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			//파일을 업로드할 디렉토리 경로를 생성
			String realUploadPath = makeFolder();
			
			//업로드 할 파일의 경로를 생성
			String uuid = UUID.randomUUID().toString(); //파일 이름의 중복을 피하기 위해서 생성
			String saveName = uploadPath + File.separator + 
					realUploadPath + File.separator + uuid + fileName;
			Path savePath = Paths.get(saveName);
			try {
				//파일 업로드
				uploadFile.transferTo(savePath);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			//이미지 경로를 DTO에 설정
			dto.setImageurl(realUploadPath + File.separator + uuid + fileName);
		}
		member = dtoToEntity(dto);
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public MemberDTO loginMember(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다.
		Optional <Member> optional = 
				memberRepository.findById(memberDTO.getEmail());
		//존재하는 이메일
		if(optional.isPresent()) {
			//비밀번호 확인
			Member member = optional.get();
			if(BCrypt.checkpw(memberDTO.getPassword(), member.getPassword())) {
				//로그인에 성공했을 때 로그인 한 시간을 업데이트
				ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
				LocalDateTime now = nowUTC.withZoneSameInstant(
						ZoneId.of("Asia/Seoul")).toLocalDateTime();
				Member updateMember = Member.builder()
						.email(member.getEmail())
						.password(member.getPassword())
						.imageurl(member.getImageurl())
						.name(member.getName())
						.lastlogindate(now)
						.build();
				memberRepository.save(updateMember);
				
				return entityToDto(member);
			}else {
				return null;
			}
		}
		//존재하지 않는 이메일
		else {
			return null;
		}
	}

	@Override
	public MemberDTO getMember(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다.
		Optional <Member> optional = 
				memberRepository.findById(memberDTO.getEmail());
		//존재하는 이메일
		if(optional.isPresent()) {
			//비밀번호 확인
			Member member = optional.get();
			return entityToDto(member);
		}else {
			return null;
		}
	}

	@Override
	public String updateMember(MemberDTO dto) {
		//전송된 이미지를 확인
		MultipartFile uploadFile = dto.getImage();
		//전송된 이미지가 있다면 파일 업로드
		if(dto.getImage().isEmpty() == false) {
			//이미지 파일 만 업로드하기 위해서 이미지 파일이 아니면 작업 중단
			if(uploadFile.getContentType().startsWith("image") == false) {
				return null;
			}
			//원본 파일의 파일 이름 찾아오기
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			//파일을 업로드할 디렉토리 경로를 생성
			String realUploadPath = makeFolder();
			
			//업로드 할 파일의 경로를 생성
			String uuid = UUID.randomUUID().toString(); //파일 이름의 중복을 피하기 위해서 생성
			String saveName = uploadPath + File.separator + 
					realUploadPath + File.separator + uuid + fileName;
			Path savePath = Paths.get(saveName);
			try {
				//파일 업로드
				uploadFile.transferTo(savePath);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			//이미지 경로를 DTO에 설정
			dto.setImageurl(realUploadPath + File.separator + uuid + fileName);
		}else {
			//전송된 이미지가 없다면 이전 이미지 사용
			dto.setImageurl(getMember(dto).getImageurl());
		}
		
		Member member = dtoToEntity(dto);
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public String deleteMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.delete(member);
		return member.getEmail();
	}

	@Override
	public ResponseEntity<Object> download(String path) {
		try {
			//다운로드 받을 파일 경로를 생성
			Path filePath = Paths.get(uploadPath + File.separator + path);
			//파일 resource 가져오기
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			//파일 정보를 헤더에 등록
			File file = new File(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());
			return new ResponseEntity<Object>(
					resource, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}

}