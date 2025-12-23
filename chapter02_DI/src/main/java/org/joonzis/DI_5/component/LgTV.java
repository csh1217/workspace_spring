package org.joonzis.DI_5.component;

import org.springframework.stereotype.Component;

/*
 * @Component와 @Configuration/@Bean은 기능이 유사
 * @Component는 클래스 단위
 * @Configuration/Bean은 메소드 단위
 */
@Component("tv")
public class LgTV implements TV{
	public LgTV() {
		System.out.println("LgTV 객체 생성");
	}
	@Override
	public void powerOn() {
		System.out.println("LgTV 전원 켜짐");
	}
	@Override
	public void powerOff() {
		System.out.println("LgTV 전원 꺼짐");
	}
	@Override
	public void volumeUp() {
		System.out.println("LgTV 볼륨 올리기");
	}
	@Override
	public void volumeDown() {
		System.out.println("LgTV 볼륨 내리기");
	}
}
