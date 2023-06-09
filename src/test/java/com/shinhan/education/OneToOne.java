package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.UserCellPhoneVORepository2;
import com.shinhan.education.repository.UserVO3Repository;
import com.shinhan.education.vo2.UserCellPhoneVO;
import com.shinhan.education.vo2.UserCellPhoneVO2;
import com.shinhan.education.vo2.UserCellPhoneVO3;
import com.shinhan.education.vo2.UserVO;
import com.shinhan.education.vo2.UserVO2;
import com.shinhan.education.vo2.UserVO3;

@SpringBootTest
public class OneToOne {
	
	@Autowired
	UserCellPhoneVORepository2 pRepo;
	
	@Autowired
	UserVO3Repository uRepo3;
	
	//@Test
	void test3() {
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder()
				.phoneNumber("010-1234-5678")
				.model("갤럭시s2")
				.build();
		UserVO3 user = UserVO3.builder()
				.userid("zz")
				.username("zizi")
				.phone(phone)
				.build();
			phone.setUser(user);
			uRepo3.save(user);
	}
	
	//@Test
	void test2() {
		UserVO2 user = UserVO2.builder()
				.userid("psj9258")
				.username("박성진")
				.build();
		UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
				.phoneNumber("010-4040-9258")
				.model("아이폰14pro")
				.user(user)
				.build();
		pRepo.save(phone);
	}
	
	//@Test
//	void test1() {
//		UserCellPhoneVO phone = UserCellPhoneVO.builder()
//				.phoneNumber("010-7777-7777")
//				.model("아이폰12mini")
//				.build();
//		UserCellPhoneVO savedPhone = pRepo.save(phone);
//		
//		UserVO user = UserVO.builder()
//				.userid("GO")
//				.username("노홍철")
//				.phone(phone)
//				.build();
//		uRepo.save(user);
//	}
}
