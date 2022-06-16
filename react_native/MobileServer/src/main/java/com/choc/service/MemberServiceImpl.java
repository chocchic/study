package com.choc.service;

import com.choc.dto.MemberDTO;
import com.choc.model.Member;
import com.choc.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberRepository memberRepository;
	
	@Override
	public String registerMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		// 이메일 중복체크
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		if(optional.isPresent()) {
			return "존재하는 이메일";
		}
		// 이름 중복 체크
		List<Member> list = memberRepository.findMemberByName(dto.getName());
		if(list.size() > 0) {
			return "존재하는 이름";
		}
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
