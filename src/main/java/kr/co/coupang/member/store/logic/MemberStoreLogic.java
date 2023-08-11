package kr.co.coupang.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.coupang.member.domain.Member;
import kr.co.coupang.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{
	
	@Override
	public int insertMember(SqlSession sqlSession, Member member) {
	
		int result = sqlSession.insert("MemberMapper.insertMember",member);
		
		return result;
	}

	@Override
	public Member selectMemberLogin(SqlSession sqlSession, Member member) {
		Member mOne = sqlSession.selectOne("MemberMapper.selectMemberLogin",member);
		return mOne;
	}

	@Override
	public Member selectOneById(SqlSession sqlSession,String memberId) {
		
		Member mOne = sqlSession.selectOne("MemberMapper.selectOneById",memberId);
		return mOne;
	}

	@Override
	public int deleteMember(SqlSession sqlSession, String memberId) {
		int result = sqlSession.delete("MemberMapper.deleteOneById",memberId);
		
		return result;
	}

	@Override
	public int modifyMember(SqlSession sqlSession, Member member) {
		int result = sqlSession.update("MemberMapper.modifyMember",member);
		return result;
		
	}

}
