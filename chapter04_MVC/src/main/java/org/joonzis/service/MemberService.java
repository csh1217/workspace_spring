package org.joonzis.service;

import org.joonzis.domain.MemberVO;

public interface MemberService {
	public int validate(String userId);
	public int join(MemberVO vo);
}
