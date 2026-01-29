package org.joonzis.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joonzis.domain.RoomVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
public class RoomMapperTest {

    @Autowired
    private RoomMapper roomMapper;

//    @Test
//    public void findRoomIdTest() {
//        Long roomId = roomMapper.findRoomId("user1", "user2");
//        log.info("roomId = " + roomId);
//    }
//
//    @Test
//    public void insertRoomTest() {
//        RoomVO room = new RoomVO();
//        room.setSender_id("user1");
//        room.setReceiver_id("user2");
//        roomMapper.insert(room);
//        log.info("Inserted room_id = " + room.getRoom_id());
//    }
//
//    @Test
//    public void findByIdTest() {
//        RoomVO room = roomMapper.findById(5L);
//        log.info("Room detail: " + room);
//    }
//
    @Test
    public void findByUserIdTest() {
        List<RoomVO> rooms = roomMapper.findByUserId("user1");
        rooms.forEach(r -> log.info("Room: " + r));
    }
}