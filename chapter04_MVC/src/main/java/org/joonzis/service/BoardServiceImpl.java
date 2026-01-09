package org.joonzis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.BoardAttachMapper;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper mapper;

	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired BoardAttachMapper attachMapper;
	
	@Override
	public List<BoardVO> getList(Criteria cri){
		log.info("getList...");
		return mapper.getList(cri);
	}

	@Transactional
	@Override
	public void register(BoardVO bvo) {
		
		
		
		log.info("register..." + bvo);
		// 게시글 등록
		mapper.insertList(bvo);
		
		List<BoardAttachVO> list = bvo.getAttachList();
		//bno 받기
		int bno = bvo.getBno(); // 1-1. SEQ.nextval로 받기
		log.info(bno);
//		int bno = bvo.getBno(); // 3. selectkey 사용해서 받기
		
		log.info("bno....." + bno);
		if(bvo.getAttachList() != null && bvo.getAttachList().size() > 0) {
			for(BoardAttachVO attach : list) {
				attach.setBno(bno);
				attachMapper.insert(attach);
			}
		}
	}

	@Override
	public BoardVO get(int bno) {
		log.info("get..." + bno);
		return mapper.readList(bno);
	}

	@Transactional
	@Override
	public boolean remove(int bno) {
		replyMapper.deleteReply(bno);
		log.info("remove..." + bno);
		return mapper.deleteList(bno) == 1;
	}

	@Override
	public boolean modify(BoardVO bvo) {
		log.info("modify..." + bvo);
		return mapper.updateList(bvo) == 1;
	}

	@Override
	public int getTotal() {
		log.info("getTotal");
		return mapper.getTotal();
	}

	@Override
	public void updateReplyCnt(int bno, int amount) {
		log.info("updateReplyCnt");
		mapper.updateReplyCnt(bno, amount);
	}


	
	
}
