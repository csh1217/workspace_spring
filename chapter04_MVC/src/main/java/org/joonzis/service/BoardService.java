package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardService {
	public List<BoardVO> getList(Criteria cri);
	public void register(BoardVO bvo);
	public BoardVO get(int bno);
	public boolean remove(int bno);
	public boolean modify(BoardVO bvo, List<String> deleteUuid);
	public int getTotal();
	public void updateReplyCnt(int bno, int amount);
	public List<BoardAttachVO> getAttachList(int bno);
}
