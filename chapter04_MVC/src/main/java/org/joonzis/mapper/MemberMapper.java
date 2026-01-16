package org.joonzis.mapper;

import org.joonzis.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
	public int validate(String userId);
	public int join(MemberVO vo);
	public void setAuth(String userId);
}
