package kr.co.coupang.member.model.service;

import kr.co.coupang.member.domain.Member;

public interface MemberService {
	/**
	 * ��� ��� Service
	 * @param member
	 * @return
	 */
	public int registerMember(Member member);

	
	/**
	 * ��� �α��� Service
	 * @param ���̵�,���
	 * @return member ��ü
	 */
	public Member memberLogicCheck(Member member);

	/**
	 * ����������
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);

	/**
	 * ȸ��Ż��
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId);


	public int modifyMember(Member member);

}
