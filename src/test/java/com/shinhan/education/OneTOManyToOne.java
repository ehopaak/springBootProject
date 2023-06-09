package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepliesRepository;
import com.shinhan.education.vo.FreeBoard;
import com.shinhan.education.vo.FreeBoardReply;

//양방향Test
@SpringBootTest
public class OneTOManyToOne {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeRepliesRepository replyRepo;
	
	// board번호 200번 삭제
	//@Test
	void test7() {
		boardRepo.deleteById(200L);
//		boardRepo.findById(200L).ifPresent(b->{
//			boardRepo.delete(b);
//		});
	}
	
	
	//댓글 모두조회(n:1)...bno가 200번이상인 board조회...paging추가
	//@Test
	void test6() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.ASC, "bno");
		List<FreeBoard> blist = boardRepo.findByBnoGreaterThan(199L, paging);
		blist.forEach(board->{
			System.out.println(board.getTitle() + "==>" + board.getReplies().size());
		});
		
	}
	//댓글 모두조회(n:1)...bno가 170번인 댓글조회
	//@Test
	void test5() {
		boardRepo.findById(170L).ifPresent(board->{
			replyRepo.findByBoard(board).forEach(reply->{
				System.out.println(reply + "==>" + reply.getBoard().getBno());
			});
		}); 
			
	}
	//댓글 모두조회(n:1)
	//@Test
	void test4() {
		replyRepo.findAll().forEach(re->{
			System.out.println(re.getReply() + "==>" + re.getBoard().getWriter());
		});
	}
	
	//board모두 조회(1:n이용)
	//@Test
	void test3() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board);
			//board번호와 댓글개수
			System.out.println(board.getBno() + "==>" + board.getReplies().size());
		});
	}
	
	
	//댓글insert 10건
	//200번 board 댓글 insert 10건
	@Test
	void test2() {
		Long[] arr = {1L,10L,50L,100L};
		Arrays.stream(arr).forEach(index->{
			boardRepo.findById(index).ifPresent(b->{
				IntStream.rangeClosed(1, 10).forEach(i->{
					FreeBoardReply reply = FreeBoardReply.builder()
							.reply("화요일" + i)
							.replyer("user" + (i%2))
							.board(b)
							.build();
					replyRepo.save(reply);
				});
			});
		});
		
		
	}
	
	//board insert 100건
	//@Test
	void test1() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			FreeBoard board = FreeBoard.builder()
					.title("게시글제목" + i)
					.writer("user" + (i%10))
					.contnet("게시글...")
					.build();
			boardRepo.save(board);
		});
	}

}
