package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = mapper.getList();
//		for(BoardVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testInsertList() {
//		BoardVO bvo = new BoardVO();
//		bvo.setTitle("제목ㅋㅋ");
//		bvo.setWriter("작성자ㅋㅋ");
//		bvo.setContent("내용ㅋㅋ");
//		int result = mapper.insertList(bvo);
//		log.info(result);
//	}
	
//	@Test
//	public void testReadList() {
//		int bno = 1;
//		BoardVO bvo = mapper.readList(bno);
//		log.info(bvo);
//	}
	
//	@Test
//	public void testDeleleList() {
//		int bno = 1;
//		int result = mapper.deleteList(bno);
//		log.info(result);
//	}
	
	@Test
	public void testUpdateList() {
		BoardVO bvo = new BoardVO();
		bvo.setBno(1);
		bvo.setTitle("수정 후 제목");
		bvo.setContent("수정 후 내용");
		bvo.setWriter("수정 후 작성자");
		int result = mapper.updateList(bvo);
		log.info(result);
	}
}
