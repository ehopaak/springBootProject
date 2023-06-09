package com.shinhan.education.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shinhan.education.vo.JobVO;

//interface설계...CRUD작업을 위해
//구현은 JPA가 한다.
@Repository
public interface JobsRepository extends CrudRepository<JobVO, String>{

}
