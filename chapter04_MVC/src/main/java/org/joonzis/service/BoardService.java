package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;

public interface BoardService {
	public List<BoardVO> getList();
	public void register(BoardVO bvo);
	public BoardVO get(int bno);
	public boolean remove(int bno);
	public boolean modify(BoardVO bvo);
}
