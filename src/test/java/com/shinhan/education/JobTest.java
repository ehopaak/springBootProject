package com.shinhan.education;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.JobsRepository;
import com.shinhan.education.vo.JobVO;

@SpringBootTest
public class JobTest {
	
	@Autowired
	JobsRepository repo;
	
	//CRUD...delete
	//@Test
	
		
	//CRUD...delete
	//@Test
	public void test6() {
			repo.deleteAll();
	}
	
	//CRUD...delete
	//@Test
	public void test5() {
		repo.findById("직책6").ifPresent(job->{
			System.out.println(job + "존재한다.");
			repo.delete(job);
			
		});
	}
	
	//CRUD...Update
	//@Test
	public void test4() {
		repo.findById("직책1").ifPresent(job->{
			job.setJobTitle("마케팅--수정");
			job.setMinSalary(123456);
			job.setMaxSalary(789456);
			repo.save(job);
			
		});
	}
	
	//CRUD...Read
	//@Test
	public void test3() {
		Optional<JobVO> jobOptional = repo.findById("직책1");
		if(jobOptional.isPresent()) {
			JobVO job = jobOptional.get();
			System.out.println(job);
		} else {
			System.out.println("존재하는 직책이 없다");
		}
		JobVO job = repo.findById("직책코드!!!").orElse(null);
		if(job == null) {
			System.out.println("존재하는직책없음");
		} else {
			System.out.println(job);
		}
	}
	
	//CRUD...Read
	//@Test
	public void test2() {
		Iterable<JobVO> datas = repo.findAll();
		List<JobVO> joblist = (List<JobVO>)datas;
		for(JobVO job:joblist) {
			System.out.println(job);
		}
		
	}
	
	//CRUD...Creat
	@Test
	public void test1() {
		String[] arr = {"마케팅", "SI개발자","SM개발자","매니저",
						"AA","무야호~","ㅇㅇ","a","b","CC"};
		IntStream.range(0, arr.length).forEach(i->{
			JobVO job = JobVO.builder()
					.jobId("직책"+(i+1))
					.jobTitle(arr[i])
					.minSalary(1000)
					.maxSalary(5000)
					.build();
				repo.save(job);
					
		});
	}

}
