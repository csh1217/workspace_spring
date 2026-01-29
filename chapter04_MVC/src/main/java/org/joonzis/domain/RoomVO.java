package org.joonzis.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomVO {
	private Long room_id;
	private String  sender_id, receiver_id;
	private LocalDateTime created_at;
}
