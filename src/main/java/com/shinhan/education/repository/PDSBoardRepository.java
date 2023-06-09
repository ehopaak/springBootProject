package com.shinhan.education.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	
	//@Query는 select만 가능, DML사용시 @Modifying를 반드시 함께사용한다.
	@Transactional
	@Modifying
	@Query("update from PDSFile f set f.pdsfilename=?2 where f.fno=?1")
	public int upedateFile(Long fno, String newFileName);
	
	//fetch = FetchType.LAZY인 경우 JPQL를 이용할 수 있다.
	@Query("select b.pname, b.pwriter, f.pdsfilename "
			+ " from PDSBoard b left outer join b.files2 f "
			+ " where b.pid = ?1 order by b.pid ")
			public List<Object[]> getFilesInfo(long pid);
			
	//fetch = FetchType.LAZY인 경우 nativeQuery를 이용할 수 있다.
	@Query(value = "select * "
			+ " from tbl_pdsboard board left outer join tbl_pdsfiles file "
			+ " on(board.pid = file.pdsno) group by board.pname ",
			nativeQuery = true)
			public List<Object[]> getFilesInfo2();
}
