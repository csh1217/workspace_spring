package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper bmapper;
	
	@Override
	public ReplyVO get(int rno) {
		ReplyVO vo = mapper.read(rno);
		return vo;
	}
	
	@Override
	public List<ReplyVO> getList(int bno) {
		List<ReplyVO> list = mapper.getList(bno);
		return list;
	}
	
	
	@Override
	public boolean modify(ReplyVO vo) {
		int result = mapper.update(vo);
		if(result > 0) return true;
		else return false;
	}
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		int result = mapper.insert(vo);
		if(result > 0) bmapper.updateReplyCnt(vo.getBno(), 1);
		return result;
	}
	
	@Transactional
	@Override
	public boolean remove(int rno) {
		ReplyVO vo = mapper.read(rno);
		if(vo == null) return false;
		if(mapper.delete(rno) > 0 ) {
			bmapper.updateReplyCnt(vo.getBno(), -1);
			return true;
		}
		else return false;
	}
}
