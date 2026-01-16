package org.joonzis.controller;

import org.joonzis.domain.MemberVO;
import org.joonzis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	// 계정 중복 확인
	@PostMapping(
		    value = "/validate",
		    produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Integer> validate(@RequestParam String userId) {
	    int result = service.validate(userId);
	    return ResponseEntity.ok(result);
	}
	
	// 회원 가입
	@PostMapping("/join")
	public String join(MemberVO vo){
		if(service.join(vo)==1) {
			log.info("회원가입 성공!");
			return "redirect:/";
		}else {
			log.info("회원가입 실패!");
			return "redirect:/member/join";
		}
	}
	
}
