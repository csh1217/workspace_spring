package org.joonzis.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUserVO {
	private Long room_id, last_read_msg_id;
	private String user_id;
	private LocalDateTime joined_at;
}
