package org.joonzis.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.joonzis.dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// @RequestMapping : 어노테이션을 붙인다.
	// value="/" : 컨텍스트 패스를 의미, 서버 : 포트번호/디폴트 패키지
	// method = RequestMethod.Get : get/post 방식
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "member/input";
	}
	
	// @RequestParam : 리퀘스트를 통해 파라미터를 받아서 변수에 저장
	// Model model : 데이터를 저장해서 이동하기 위한 객체
	// => request.setAttribute == model.addAttribute
	@RequestMapping(value = "member/result", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/output";
	}
	// 주소(url)에 http://local
	@RequestMapping("a/b/c/d/e")
	public String goView01() {
		return "/view01";
		// 1. 리턴타입 : 뷰(view)를 리턴하기 떄문에 언제나 String
		// 2. 메소드 명 : goView01은 아무런 의미가 없다(메소드끼리 이름만 다르면 된다)
		// 3. 리턴 : "/view01", "view01"의 차이점은 없다
		
	}
	
	@RequestMapping("admin/view02")
		public String goView02(Model model) {
			model.addAttribute("id", "admin");
			model.addAttribute("pw", "1234");
			return "admin/view02";
	}
	
	@RequestMapping("index")
		public String goIndex() {
			return "index";
	}
	
	@RequestMapping(value = "v01", method = RequestMethod.POST)
	public String goResult1(StudentDTO sDto, Model model){
		model.addAttribute("sDto", sDto);
		return "result";
	}
	
	@RequestMapping(value = "v02", method = RequestMethod.POST)
	public String goResult2(@ModelAttribute("s") StudentDTO dto){
		return "result";
	}
	
}
