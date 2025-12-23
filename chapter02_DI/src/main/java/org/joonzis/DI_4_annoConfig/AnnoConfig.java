package org.joonzis.DI_4_annoConfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *sts3 
 * 
 * 
 * 
 * 
 * 
 */
@Configuration // == applicationContext와 같은 역할을 하는 자바 클래스
public class AnnoConfig {

	// <bean id="human1" class="org.joonzis.DI_4.Person></bean>
	@Bean // Bean을 만드는 메서드
	public Person human1() {
		Set<String> hobbies = new HashSet<String>();
		hobbies.add("여행");
		hobbies.add("낚시");
		hobbies.add("독서");
		
		Person person = new Person();
		person.setName("김씨");
		person.setAge(10);
		person.setHobbies(hobbies);
		
		return person;
	}
	
	// <bean id="human2" class="org.joonzis.DI_4.Person></bean>
	@Bean(name = "human2")
	public Person abc() {
		Set<String> hobbies = new HashSet<String>();
		hobbies.add("잠자기");
		hobbies.add("먹기");
		hobbies.add("싸기");
		
		Person person = new Person();
		person.setName("박씨");
		person.setAge(20);
		person.setHobbies(hobbies);
		
		return person;
	}
	@Bean
	public PersonInfo pInfo() {
		Set<String> hobbies = new HashSet<String>();
		hobbies.add("게임");
		hobbies.add("달리기");
		hobbies.add("웨이트");

		PersonInfo info = new PersonInfo();
		info.setPerson(new Person("이씨", 30, hobbies));
		
		return info;
	}
}
