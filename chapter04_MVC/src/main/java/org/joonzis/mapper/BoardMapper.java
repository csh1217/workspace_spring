package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardMapper {
	//전체 리스트
	public List<BoardVO> getList(Criteria cir);
	public int insertList(BoardVO bvo);
	public BoardVO readList(int bno);
	public int deleteList(int bno);
	public int updateList(BoardVO bvo);
	public int getTotal();
	// 데이터 삽입 insert
	// 단일 데이터 보기
	// 데이터 삭제 delete -- 기본키로 삭제
	// 데이터 수정 update -- 
}
