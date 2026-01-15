package org.joonzis.controller;

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
	
	@PostMapping(
		    value = "/validate",
		    produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Integer> validate(@RequestParam String userId) {
	    int result = service.validate(userId);
	    return ResponseEntity.ok(result);
	}
}
