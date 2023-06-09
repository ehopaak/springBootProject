package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo.WebBoard;
import com.shinhan.education.vo.WebReply;

@SpringBootTest
public class WebBoardReplyTest {
	
	@Autowired
	WebBoardRepository bRepo;
	
	@Autowired
	WebReplyRepository rRepo;
	
	
	//특정board댓글(Reply에서 시작)
	WebBoard board = bRepo.findById(502L).get();
	List<WebReply> replyList = rRepo.findByBoard(board);
	
	
	
	//모든 board조회
	//@Test
	void test3() {
		bRepo.findAll().forEach(board->System.out.println(board));
	}
	//특정 board댓글조회
	//@Test
	void test4() {
		bRepo.findById(501L).ifPresent(board->{
			List<WebReply> replyList = board.getReplies();
			for(WebReply reply:replyList) {
				System.out.println(reply);
			}
		});
	}
	
	
	
	//board에 insert 100건
	//@Test
	void test1() {
		IntStream.rangeClosed(100, 200).forEach(i->{
			WebBoard entity = WebBoard.builder()
					.title("WebBoard" + i)
					.writer("user" + (i/100))
					.content("SpringBoot Project")
					.build();
			bRepo.save(entity);
		});
		
	}
	//5개의 board에 댓글 10개 넣기
	//@Test
	void test2() {
		Long[] arr = {500L, 501L, 502L, 503L, 504L};
		Arrays.stream(arr).forEach(bno->{
			bRepo.findById(bno).ifPresent(board->{
				IntStream.rangeClosed(1, 10).forEach(index->{
					WebReply reply = WebReply.builder()
							.replyText("날씨가 더워요...30도" + index)
							.replyer("댓글작성자-" + index)
							.board(board)
							.build();
					rRepo.save(reply);
				});
			});
		});
		
	}
}
