package com.shinhan.education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo.WebBoard;
import com.shinhan.education.vo3.PageMaker;
import com.shinhan.education.vo3.PageVO;



@Controller
@RequestMapping("/webboard")
public class WebBoardController {
	@Autowired
	WebBoardRepository bRepo;
	
	@Autowired
	WebReplyRepository rRepo;
	

	@PostMapping("/register.do")
	public String registerPost(WebBoard board, RedirectAttributes attr) {
		WebBoard newBoard = bRepo.save(board);
		if(newBoard != null) {
			attr.addFlashAttribute("msg", "insertOK");
		} else {
			attr.addFlashAttribute("msg", "insertFail");
		}
		return "redirect:list.do";
	}
	@GetMapping("/register.do")
	public void registerGet() {
		
	}
	
	@PostMapping("/delete.do")
	public String delete(Long bno, PageVO pageVO, RedirectAttributes attr) {
		bRepo.deleteById(bno);
		//addFlashAttribute : 새로고침하면 없어짐(1회성)
		//addAttribute : 새로고침해도 유지
		attr.addFlashAttribute("msg", "DELETE COMPLETE!!");
		attr.addAttribute("page", pageVO.getPage());
		attr.addAttribute("size", pageVO.getSize());
		attr.addAttribute("keyword", pageVO.getKeyword());
		attr.addAttribute("type", pageVO.getType());
		return "redirect:list.do";
	}
	
	@PostMapping("/modify.do")
	public String updatePost(WebBoard board, PageVO pageVO, RedirectAttributes attr) {
		System.out.println(board);
		WebBoard saveBoard = bRepo.save(board);
		if(saveBoard != null) {
			attr.addFlashAttribute("msg", "Update Fail");
		}else {
			attr.addFlashAttribute("msg", "Update Complete");
		}
		attr.addAttribute("bno", board.getBno());
		attr.addAttribute("page", pageVO.getPage());
		attr.addAttribute("size", pageVO.getSize());
		attr.addAttribute("keyword", pageVO.getKeyword());
		attr.addAttribute("type", pageVO.getType());
		
		System.out.println(saveBoard);
		return "redirect:view.do";
	}
	
	@GetMapping("/modify.do")
	public void updateOrDelete(Long bno, Model model, PageVO pageVO) {
		bRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board", board);
			model.addAttribute("pageVO", pageVO);
		});
	}
	
	@GetMapping("/view.do")
	public void selectById(Long bno, Model model, PageVO pageVO) {
		System.out.println("view.do: " + bno);
		bRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board", board);
			model.addAttribute("pageVO", pageVO);
		});
	}
	
	
	
	@GetMapping("/list.do")
	public void selectAll(PageVO pageVO, Model model) {
		if(pageVO==null) {
			pageVO = new PageVO();
			pageVO.setPage(1);
		}
//		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
//		if(flashMap !=null ) {
//			Object message = flashMap.get("msg");
//			model.addAttribute("resultMessage",message);
//		}
		
		Predicate pre = bRepo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		Pageable paging = pageVO.makePageable(pageVO.getPage(), "bno");
		//Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno"); 
		Page<WebBoard> result = bRepo.findAll(pre, paging);
		//List<WebBoard> blist = result.getContent();
		
		//System.out.println("전체페이지수:" + result.getTotalPages());
		//System.out.println("전체 건수:" + result.getTotalElements());
		
		PageMaker<WebBoard> pageMaker = new PageMaker<>(result,pageVO.getSize());
		model.addAttribute("blist", pageMaker);
		
		//Page<WebBoard> p_result = pageMaker.getResult();
		//System.out.println(p_result.getContent());
		
	}
}
