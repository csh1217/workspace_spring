package org.joonzis.controller;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.PageDTO;
import org.joonzis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*") // board가 선행되어 있는 URL들은 현재 컨트롤러 클래스에서 매핑
public class BoardController {
	@Autowired
	private BoardService service;
	
	//전체 데이터 조회
//	@GetMapping("/list")
//	public String list (Model model) { 
//		log.info("list");
//		model.addAttribute("list", service.getList());
//		return "/board/list"; // 들어오는 경로와 return 경로가 같을 시 void 로 return 생략 가능
//	}
	@GetMapping("/list")
	public String list (Criteria cri, Model model) { 
		log.info("list..." + cri);

		// 데이터가 잘못 들어왔을 경우
		if(cri.getPageNum() == 0 || cri.getAmount() == 0) {
			cri.setPageNum(1);
			cri.setAmount(12);
		}
		
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal();
		log.info("total..." + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
		return "/board/list"; // 들어오는 경로와 return 경로가 같을 시 void 로 return 생략 가능
	}
	
	// 게시글 등록 페이지 이동
	@GetMapping("/register")
	public void registerPage(){}
	
	// 게시글 등록
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info("register..." + bvo);
		service.register(bvo);
		return "redirect:/board/list"; // 경로 앞에 redirect: 붙이면 redirect 방식 실행
	}
	
	
	// 게시글 조회
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") int bno, Model model) {
		log.info("get or modify..." + bno);
		model.addAttribute("vo", service.get(bno));
	}
	
	// 게시글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno) {
		log.info("remove..." + bno);
		service.remove(bno);
		return "redirect:/board/list";
	}
	
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		log.info("modify..." + bvo);
		service.modify(bvo);
		return "redirect:/board/list";
	}
	
}
