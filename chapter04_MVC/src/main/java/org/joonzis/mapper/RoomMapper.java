package org.joonzis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.joonzis.domain.RoomVO;

public interface RoomMapper {
	// 두 유저가 있는 채팅방 찾기(중복 방지)
	long findRoomId(@Param("user1") String user1,@Param("user2") String user2);
	
	// 채팅방 insert
	int insert(RoomVO room);
	
	// 채팅방 상세 조회
	RoomVO findById(Long room_id);
	
	// 채팅 목록 화면(내가 속한 모든 채팅방)
	List<RoomVO> findByUserId(String user_id);
}
