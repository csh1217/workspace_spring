package org.joonzis.service;

import org.joonzis.domain.MemberVO;
import org.joonzis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper mapper;
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public int validate(String userId) {
		log.info("검증할 아이디 : " + userId);
		return mapper.validate(userId);
	}

	@Transactional
	@Override
	public int join(MemberVO vo) {
		String userPw = vo.getUserPw();
		String encodedPw = encoder.encode(userPw);
		vo.setUserPw(encodedPw);
		log.info("회원가입");
		int result = mapper.join(vo);
		mapper.setAuth(vo.getUserId());
		return result;
	}
	
}
