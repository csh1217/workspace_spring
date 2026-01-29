package org.joonzis.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUserVO {
	private Long room_id;
	private Long user_id;
	private Long last_read_msg_id;
	private LocalDateTime joined_at;
	private char is_active;
}
