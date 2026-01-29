package org.joonzis.service;

public interface ChatService {
	public String roomCreate(String sender);
	public void roomJoin(String roomNumber, String sender);
	public void roomRemove(String roomNumber, String sender);
}
