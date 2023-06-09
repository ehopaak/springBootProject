package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MultiKeyBRepository;
import com.shinhan.education.vo2.MultiKeyB;
import com.shinhan.education.vo2.MultiKeyBDTO;
import com.shinhan.education.repository.MultiKeyARepository;

@SpringBootTest
public class MultiKeyTest {
	
	@Autowired
	MultiKeyARepository aRepo;
	
	@Autowired
	MultiKeyBRepository bRepo;
	
	@Test
	void test2() {
		MultiKeyB id = MultiKeyB.builder()
				.id1(10)
				.id2(20)
				.build();
				
				
		MultiKeyBDTO b = MultiKeyBDTO.builder()
				.id(id)
				.userName("노홍철")
				.address("신도림")
				.build();
		bRepo.save(b);
	}
	 

}
