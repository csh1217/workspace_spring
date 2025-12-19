package org.joonzis.DI_1;

public class LgTV {
	public LgTV() {
		System.out.println("LgTV 객체 생성");
	}
	public void powerOn() {
		System.out.println("LgTV 전원 켜짐");
	}
	public void powerOff() {
		System.out.println("LgTV 전원 꺼짐");
	}
	public void volumeUp() {
		System.out.println("LgTV 볼륨 올리기");
	}
	public void volumeDown() {
		System.out.println("LgTV 볼륨 내리기");
	}
}
