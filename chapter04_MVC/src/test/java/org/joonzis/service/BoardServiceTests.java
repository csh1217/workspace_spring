package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.mapper.BoardMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
	@Autowired
	private BoardService service; 
	
//	@Test
//	public void getList() {
//		List<BoardVO> list = service.getList();
//		service.getList().forEach(vo -> log.info(vo))
//	}
	
	@Test
	public void insert() {
		BoardVO bvo = new BoardVO();
		bvo.setTitle("title");
		bvo.setContent("content");
		bvo.setWriter("writer");
		service.register(bvo);
	}

//	@Test
//	public void getOne() {
//		int bno = 3;
//		BoardVO bvo = service.get(bno);
//		log.info(bvo);
//	}
	
//	@Test
//	public void remove() {
//		int bno = 2;
//		boolean result = service.remove(bno);
//		log.info("result : " + result);
//	}
	
//	@Test
//	public void update() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(3);
//		bvo.setTitle("fixed title");
//		bvo.setContent("fixed content");
//		bvo.setWriter("fixed writer");
//		boolean result = service.modify(bvo);
//		log.info("result : " + result);
//	}
}
	

