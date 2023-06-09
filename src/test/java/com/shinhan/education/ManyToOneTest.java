package com.shinhan.education;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.repository.ProfileRepository;
import com.shinhan.education.vo.JobVO;
import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.MemberRole;
import com.shinhan.education.vo.ProfileDTO;

import lombok.extern.java.Log;


@SpringBootTest
@Log
public class ManyToOneTest {
	
	@Autowired
	MemberRepository mRepo;
	@Autowired
	ProfileRepository pReop;
	
	//멤버의 profile갯수얻기
	//@Test
	public void getProfileCount() {
		List<Object[]> result = pReop.getMemberWithProfileCount("user");
		result.forEach(arr->log.info(Arrays.toString(arr)));
	}
	//@Test
	void addMember() {
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("manager-" + i)
					.mname("매니저" + i)
					.mpassword("8888")
					.mrole(MemberRole.MANAGER)
					.build();
			mRepo.save(member);
		});
	}
	
	//해당profile의 member정보알아내기
	//@Test
	void getMemberByProfile() {
		Long pno = 106L;
		pReop.findById(pno).ifPresent(p->{
		MemberDTO member = p.getMember();
		log.info(p.isCurrentYn() + ":" + member.getMname() + "----" + member);
		});
	}
	
	//특정멤버의 profile조회하기
	//@Test
	void getProfileByMember() {
		MemberDTO member = mRepo.findById("user1").orElse(null);
		pReop.findByMember(member).forEach(p->log.info(p.toString()));
	}
	
	
	//@Test
	void profileInsertTest() {
		// user1의 profile이 5건 입력
		MemberDTO member = mRepo.findById("user1").orElse(null);
		MemberDTO member2 = mRepo.findById("user2").orElse(null);
//		if(member != null) {
//			log.info(member.toString());
//			IntStream.range(1, 6).forEach(i->{
//				ProfileDTO profile = ProfileDTO.builder()
//						.fname("face-" + i + ".jpg")
//						.currentYn(i==5?true:false)
//						.member(member)
//						.build();
//				pReop.save(profile);
//			});
			if(member2 != null) {
				log.info(member2.toString());
				IntStream.range(1, 6).forEach(i->{
					ProfileDTO profile = ProfileDTO.builder()
							.fname("face-" + i + ".jpg")
							.currentYn(i==5?true:false)
							.member(member2)
							.build();
					pReop.save(profile);
				});
			pReop.findAll().forEach(profile->log.info(profile.toString()));
		}
	}
	
	
	//@Test
	void memberSelectAll() {
		mRepo.findAll().forEach(member->log.info(member.toString()));
	}
	
	//memger table에 10명 입력하기
		//@Test
		public void memberInsert() {
			
			IntStream.range(0, 10).forEach(i->{
				MemberDTO member = MemberDTO.builder()
						.mid("user" + i)
						.mname("멤버" + i)
						.mpassword("1234")
						.build();
				if(i<=5) {
					member.setMrole(MemberRole.ADMIN);
				} else {
					member.setMrole(MemberRole.USER);
				}
				mRepo.save(member);
			});
		}

}
