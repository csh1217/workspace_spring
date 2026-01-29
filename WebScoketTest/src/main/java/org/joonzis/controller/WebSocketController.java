package org.joonzis.controller;

import org.joonzis.domain.ChatMessageVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
	private final SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat.send")
		public void send(ChatMessageVO msg){
		messagingTemplate.convertAndSend(
				"/queue/chat/" + msg.getReceiver(),
				msg
				);
	}
}
