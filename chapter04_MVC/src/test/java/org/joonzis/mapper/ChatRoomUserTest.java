package org.joonzis.mapper;

import org.joonzis.domain.ChatRoomUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
public class ChatRoomUserTest {
	@Autowired
	private ChatRoomUserMapper mapper;
	
//    @Test
//    public void insertTest() {
//        ChatRoomUserVO vo = new ChatRoomUserVO();
//        vo.setRoom_id(8L);
//        vo.setUser_id("user1");
//        vo.setLast_read_msg_id(0L);
//
//        int inserted = mapper.insert(vo);
//        log.info("Inserted rows: " + inserted);
//    }

//    @Test
//    public void updateLastReadMsgIdTest() {
//        int updated = mapper.updateLastReadMsgId("user1", 5L, 5L);
//        log.info("Updated rows: " + updated);
//    }
//
//    @Test
//    public void findLastReadMsgIdTest() {
//        Long lastRead = mapper.findLastReadMsgId("user1", 5L);
//        log.info("Last read msg id: " + lastRead);
//    }
//
    @Test
    public void countUnreadTest() {
        int unreadCount = mapper.countUnread("user1", 5L);
        log.info("Unread messages: " + unreadCount);
    }
}
