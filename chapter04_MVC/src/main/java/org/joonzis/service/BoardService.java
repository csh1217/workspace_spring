package org.joonzis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardService {
	public List<BoardVO> getList(Criteria cri);
	public void register(BoardVO bvo);
	public BoardVO get(int bno);
	public boolean remove(int bno);
	public boolean modify(BoardVO bvo);
	public int getTotal();
	public void updateReplyCnt(int bno, int amount);
}
