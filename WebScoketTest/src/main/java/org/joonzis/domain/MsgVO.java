package org.joonzis.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgVO {
	private long msg_id;
	private long room_id;
	private long sender_id;
	private String content;
	private String msg_type;
	private LocalDateTime created_at;
}
