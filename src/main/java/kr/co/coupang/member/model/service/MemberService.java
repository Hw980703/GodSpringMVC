package kr.co.coupang.member.model.service;

import kr.co.coupang.member.domain.Member;

public interface MemberService {
	/**
	 * 멤버 등록 Service
	 * @param member
	 * @return
	 */
	public int registerMember(Member member);

	
	/**
	 * 멤버 로그인 Service
	 * @param 아이디,비번
	 * @return member 객체
	 */
	public Member memberLogicCheck(Member member);

	/**
	 * 마이페이지
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);

	/**
	 * 회원탈퇴
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId);


	public int modifyMember(Member member);

}
