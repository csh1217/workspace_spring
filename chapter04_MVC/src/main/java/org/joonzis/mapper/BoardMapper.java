package org.joonzis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	public void updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
	// 1. 위 updateReplyCnt 메소드에 해당하는 쿼리 작성
	// 2. 댓글 삽입/삭제 시 updateReplyCnt 실행
	// => 트랜잭션 처리
	// 3. 게시글 리스트에서 댓글 개수 변경 확인
	public int findBno();
}
