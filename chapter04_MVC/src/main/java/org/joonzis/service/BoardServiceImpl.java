package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList(){
		log.info("getList...");
		return mapper.getList();
	}

	@Override
	public void register(BoardVO bvo) {
		log.info("register..." + bvo);
		mapper.insertList(bvo);
	}

	@Override
	public BoardVO get(int bno) {
		log.info("get..." + bno);
		return mapper.readList(bno);
	}

	@Override
	public boolean remove(int bno) {
		log.info("remove..." + bno);
		return mapper.deleteList(bno) == 1;
	}

	@Override
	public boolean modify(BoardVO bvo) {
		log.info("modify..." + bvo);
		return mapper.updateList(bvo) == 1;
	}
	
	
	
	
}
