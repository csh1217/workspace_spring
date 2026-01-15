package org.joonzis.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVO {
	String userId, userPw, userName;
	Date regDate, updateDate;
	boolean enabled;
	
	private List<AuthVO> authList;
}
