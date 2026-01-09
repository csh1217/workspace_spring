package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardAttachMapperTests {
	@Autowired
	BoardAttachMapper mapper;
	
//	@Test
//	public void testInsert() {
//		BoardAttachVO vo = new BoardAttachVO();
//		vo.setUuid("asdf");
//		vo.setUploadPath("C:\\");
//		vo.setFileName("asd.txt");
//		vo.setBno(290);
//		mapper.insert(vo);
//	}
	
//	@Test
//	public void testDelete() {
//		mapper.delete("asdf");
//	}
	
	@Test
	public void testSelect() {
		List<BoardAttachVO> list = mapper.findByBno(290);
		for (BoardAttachVO vo : list) {				// 가져온 데이터들을 하나씩 log.info로 찍어보기
			log.info(this.getClass().toString()+vo.getBno()+" " + vo.toString()); // 실행 위치 및 결과 확인
		}
		log.info("");
	}
	
}
