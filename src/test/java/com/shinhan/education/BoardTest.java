package com.shinhan.education;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.CarVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class BoardTest {
	Logger logger = LoggerFactory.getLogger(BoardTest.class);
	
	@Autowired
	BoardRepository brepo;
	
//	@Test
//	void dynamicSQLTest() {
//		String title ="제목9";	//where title like '%제목9%'
//		Long bno2 = 150L;		//where bno > 150
//		BooleanBuilder builder = new BooleanBuilder();
//		QBoardVO board = QBoardVO.boardVO;
//		builder.and(board.title.like("%"+title+"%"));
//		builder.and(board.bno.gt(bno2));
//		System.out.println(builder);
//		
//		List<BoardVO> blist = (List<BoardVO>)brepo.findAll(builder);
//		blist.forEach(b->{
//			log.info(b.toString());
//		});
//	}
	
	//@Test
	public void dynamicSQLTest() {
		String title = "제목"; // and title like '%제목9%'
		Long bno = 17L; // and bno > 42
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		builder.and(board.title.like("%" + title + "%"));
		builder.and(board.bno.gt(bno));
		builder.and(board.writer.eq("작성자8"));
		System.out.println(builder);

		// findAll() => CrudRepository에서 제공
		// findAll(predicate) => QuerydslPredicateExecutor에서 제공
		List<BoardVO> blist = (List<BoardVO>) brepo.findAll(builder);
		log.info("--------------------------------------------------------------");
		blist.forEach(b -> {
			log.info(b.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample7() {
//		List<Object[]> blist = boardRepos.findByTitle5("제목8", "내용");
//		
//		log.info("--------------------------------------------------------------");
//		blist.forEach(arr -> {
//			log.info(Arrays.toString(arr));
//		});
//		log.info("--------------------------------------------------------------");

		List<BoardVO> blist = brepo.findByTitle6("제목8", "내용");

		log.info("--------------------------------------------------------------");
		blist.forEach(b -> {
			log.info(b.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample6() {
//		List<BoardVO> blist = boardRepos.findByTitle2("제목8", "내용");
//		List<BoardVO> blist = boardRepos.findByTitle3("제목8", "내용");
		List<BoardVO> blist = brepo.findByTitle4("제목8", "내용");

		log.info("--------------------------------------------------------------");
		blist.forEach(record -> {
			log.info(record.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample5() {
		Sort sort = Sort.by(Sort.Direction.DESC, new String[] { "writer", "bno" });
		Pageable paging = PageRequest.of(0, 5, sort); // (몇 페이지, 한 페이지의 사이즈)

		Page<BoardVO> rst = brepo.findByBnoGreaterThanEqual(38L, paging);
		log.info("page당 건 수:" + rst.getSize());
		log.info("pages 총 수:" + rst.getTotalPages());
		log.info("전체 건 수:" + rst.getTotalElements());
		log.info("다음 page 정보:" + rst.nextPageable());

		List<BoardVO> blist = rst.getContent();
		log.info("--------------------------------------------------------------");
		blist.forEach(record -> {
			log.info(record.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample4() {
//		Sort sort = Sort.by("bno").descending();
		Sort sort = Sort.by(Sort.Direction.DESC, new String[] { "writer", "bno" });
		Pageable paging = PageRequest.of(0, 20, sort); // (몇 페이지, 한 페이지의 사이즈)
		List<BoardVO> blist = brepo.findByTitleContaining("제목", paging);

		log.info("--------------------------------------------------------------");
		blist.forEach(record -> {
			log.info(record.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample3() {
		Pageable paging = PageRequest.of(0, 10); // (몇 페이지, 한 페이지의 사이즈)
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목", paging);

		log.info("--------------------------------------------------------------");
		blist.forEach(record -> {
			log.info(record.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample2() {
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목8");

		log.info("--------------------------------------------------------------");
		blist.forEach(record -> {
			log.info(record.toString());
		});
		log.info("--------------------------------------------------------------");
	}

//	@Test
	public void sample1() {
		long rowCount = brepo.count();
		logger.info(rowCount + "건");

		boolean rst = brepo.existsById(22L);
		logger.info(rst ? "존재함" : "부재함");
	}
	
	//@Test
	public void retrieve12() {
		List<BoardVO> blist = brepo.findByContentIgnoreCase("abc");
		blist.forEach(board->{
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve11() {
		List<BoardVO> blist = brepo.findBycontentNull();
		blist.forEach(board->{
			System.out.println(board);
			
		});
	}
	
	//@Test
	public void retrieve8() {
		List<BoardVO> blist = brepo.findByTitleContainingAndBnoGreaterThanEqual("제목", 10L);
	}
	
	//@Test
	public void retrieve1() {
		List<BoardVO> blist = brepo.findByTitle("카지노");
		blist.forEach(board->{
			System.out.println(board);
		});
}
	
	//@Test
	public void test6() {
		BoardVO board = brepo.findById(100L).orElse(null); 
			if(board != null) {
				board.setContent("계장인지 간장게장인지 난 시팔 모르겟고");
				board.setTitle("카지노");
				board.setWriter("차무식");
				brepo.save(board);	//이미잇는 데이터는 update
			}
		logger.info(board.toString());
	}
	
	//@Test
	public void test5() {
		BoardVO board = brepo.findById(101L).orElse(null); 
			System.out.println(board);
		
	}
	
	//@Test
	public void test4() {
		brepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	
	//@Test
	public void test3() {
		
		IntStream.rangeClosed(1, 100).forEach(i->{
			BoardVO board = BoardVO.builder()
					.title("게시글제목" + i)
					.content("게시글내용..." + i)
					.writer("작성자" + (i%10))
					.build();
			brepo.save(board);	//save함수에 insert된다.
		});
		
	}
	
	//@Test
	void test2() {
		CarVO car1 = CarVO.builder()
				.model("B모델")
				.price(3000)
				.build();
		logger.info(car1.toString());
	}
	
	//@Test
	void test1() {
		CarVO car1 = new CarVO();
		car1.setModel("A모델");
		car1.setPrice(1000);
		
		CarVO car2 = new CarVO();
		car2.setModel("A모델");
		car2.setPrice(1000);
		
		logger.info(car1.toString());
		logger.info("내성격 까먹었나보네?" + car1.equals(car2));
	}

	//@Test
	void contextLoads() {
	}

}
