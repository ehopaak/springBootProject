package com.shinhan.education.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.vo.WebBoard;
import com.shinhan.education.vo3.PageMaker;
import com.shinhan.education.vo3.PageVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/rest/webboard")
public class WebBoardRestController {

	@Autowired
	WebBoardRepository boardRepo;
	
	@ApiOperation(value = "게시판 등록화면", notes = "게시판 등록화면!!")
	@GetMapping("/register.do")
	public void registerGet() {
		
	}
	@ApiOperation(value = "게시판 등록", notes = "게시판 등록!!")
	@ApiImplicitParam(
	        name = "WebBoard"
	        , value = "WebBoard 정보를 입력합니다."
	        , defaultValue = "None")
	@PostMapping(value = "/register.do", consumes="application/json")
	public WebBoard registerPost(@RequestBody WebBoard board ) {
		WebBoard newBoard = boardRepo.save(board);	 
		return newBoard;
	}
	
	
	@DeleteMapping(value = "/delete.do/{bno}", produces="text/plain;charset=utf-8")
	public String deletePost(@PathVariable Long bno) {
		//boardRepo.delete(board);
		boardRepo.deleteById(bno);
	    return "삭제작업완료delete";
	}
	
	@PutMapping(value = "/modify.do", consumes = "application/json")
	public WebBoard updatePost(@RequestBody WebBoard board) {
		WebBoard savedBoard = boardRepo.save(board); 
		return savedBoard;
	}
	
	
	@GetMapping("/modify.do/{bno}")
	public WebBoard updateOrDelete(@PathVariable Long bno) {
		return boardRepo.findById(bno).orElse(null); 
	}
	
	@GetMapping("/view.do/{bno}")
	public WebBoard selectById(@PathVariable Long bno) {
		return boardRepo.findById(bno).orElse(null); 
	}
	
	
	@GetMapping("/list.do")
	public List<WebBoard> selectAll(PageVO pageVO, Model model, 
			HttpServletRequest request) {
		if(pageVO==null) {
			pageVO = new PageVO();
			pageVO.setPage(1);
		}

		Predicate pre = 
				boardRepo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		Pageable paging = pageVO.makePageable(pageVO.getPage(), "bno");
		Page<WebBoard>  result = boardRepo.findAll(pre, paging);		
		PageMaker<WebBoard> pageMaker = new PageMaker<>(result, pageVO.getSize());
		model.addAttribute("blist", pageMaker);
		
		return (List<WebBoard>)boardRepo.findAll(Sort.by(Direction.DESC, "bno"));
		
	}
	
}