package com.shinhan.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo.WebBoard;
import com.shinhan.education.vo.WebReply;

@RestController
@RequestMapping("/replies")
public class WebReplyController {
	
	@Autowired
	WebReplyRepository rRepo;
	
	@Autowired
	WebBoardRepository bRepo;
	
	public ResponseEntity<List<WebReply>> makeReturn(Long bno, HttpStatus status) {
		WebBoard board = WebBoard.builder()
				.bno(bno)
				.title("")
				.build();
		List<WebReply> replies = rRepo.findByBoard(board);
		return new ResponseEntity<List<WebReply>>(replies, status);
	}
	
	//삭제
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> deleteReply(@PathVariable("bno") Long bno,
			@PathVariable("rno") Long rno){
		rRepo.deleteById(rno);
		return makeReturn(bno, HttpStatus.OK);
	}
	//수정
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebReply>> updateReply(@RequestBody WebReply reply,
			@PathVariable("bno") Long bno) {
		WebBoard board = bRepo.findById(bno).get();
		reply.setBoard(board);
		rRepo.save(reply);
		List<WebReply> replies = rRepo.findByBoard(board);
		return makeReturn(bno, HttpStatus.OK);
	}
	//입력
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebReply>> insertReply(@RequestBody WebReply reply,
			@PathVariable("bno") Long bno) {
		WebBoard board = bRepo.findById(bno).get();
		reply.setBoard(board);
		rRepo.save(reply);
		return makeReturn(bno, HttpStatus.OK);
	}
	
	@GetMapping("/{bno}")
	public ResponseEntity<List<WebReply>> selectAllReply(@PathVariable("bno") Long bno) {
		return makeReturn(bno, HttpStatus.OK);
	}
}
