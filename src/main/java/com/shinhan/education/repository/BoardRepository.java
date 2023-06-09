package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.shinhan.education.vo.BoardVO;

//interface설계...CRUD작업을 위해
//구현은 JPA가 한다.
//기본은 : findAll(), findById(), save(), count(), exist()
//규칙에 맞는 메서드 추가 




@Repository
public interface BoardRepository extends CrudRepository<BoardVO, Long>,
								QuerydslPredicateExecutor<BoardVO>
{
	
	//조건조회 추가
	public List<BoardVO> findByTitle(String title);
	public List<BoardVO> findByContent(String aa);
	public List<BoardVO> findByWriter(String writer);
	public List<BoardVO> findByWriterAndTitle(String writer, String title);
	
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title);
	//where Tilte like ? order by Title desc
	
	
	public List<BoardVO> findByTitleContaining(String title);	//select * t_boards from where title like '%?%'
	public List<BoardVO> findByTitleStartingWith(String title); //select * t_boards from where title like '?%'
	public List<BoardVO> findByTitleEndingWith(String title); //select * t_boards from where title like '%?'
	
	public List<BoardVO> findByTitleContainingAndBnoGreaterThanEqual(String writer, Long bno);
	
	public List<BoardVO> findByBnoBetweenOrderByBnoDesc(Long bno1, Long bno2);
	public List<BoardVO> findBycontentNull(); //IsNull()또는 Null
	
	public List<BoardVO> findByContentIgnoreCase(String content);
	
	//title로 조회, sort desc, paging => page, size
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title,Pageable paging);
	public List<BoardVO> findByTitleContaining(String title,Pageable paging);
	public Page<BoardVO> findByBnoGreaterThanEqual(Long bno,Pageable paging);
	
	//JPQL(JPL Query Language)...*지원 안됨
	@Query("select b from BoardVO b where b.title like %?1%"
			+ "and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle2(String title,String content);
	
	@Query("select b from BoardVO b where b.title like %:tt%"
			+ " and b.content like %:cc% order by b.bno desc")
	public List<BoardVO> findByTitle3(@Param("tt") String title,@Param("cc") String content);

	@Query("select b from #{#entityName} b where b.title like %?1%"
			+ "and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle4(String title,String content);

	@Query("select b.title, b.content,b.writer from #{#entityName} b where b.title like %?1%"
			+ "and b.content like %?2% order by b.bno desc")
	public List<Object[]> findByTitle5(String title,String content);
	
	//직접sql입력 남용하지말자
	@Query(value="select * from t_boards b where b.title like '%'||?1||'%'"
			+ "and b.content like '%'||?2||'%' order by b.bno desc", nativeQuery = true)
	public List<BoardVO> findByTitle6(String title,String content);
	
}
