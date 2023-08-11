package kr.co.coupang.member.model.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.coupang.member.domain.Member;
import kr.co.coupang.member.model.service.MemberService;
import kr.co.coupang.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MemberStore mStore;
	
	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(sqlSession, member);
		return result;
	}

	@Override
	public Member memberLogicCheck(Member member) {
		Member mOne = mStore.selectMemberLogin(sqlSession,member);
		return mOne;
	}

	@Override
	public Member showOneById(String memberId) {
		Member mOne = mStore.selectOneById(sqlSession, memberId);
		return mOne;
	}

	@Override
	public int removeMember(String memberId) {
		int result = mStore.deleteMember(sqlSession ,memberId);
		return result;
	}

	@Override
	public int modifyMember(Member member) {
		int result = mStore.modifyMember(sqlSession , member);
		return result;
	}
		
	

}
