package kr.co.coupang.member.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.coupang.member.domain.Member;
import kr.co.coupang.member.model.service.MemberService;

@Controller
@SessionAttributes({ "memberId", "memberName" })
public class MemberController {
	@Autowired
	MemberService service;

	// doGet
	@RequestMapping(value = "/member/register.do", method = RequestMethod.GET)
	public String showResgisterForm(Model model) {
		return "member/register";
	}

	// doPost - ������ �����
	@RequestMapping(value = "/member/register.do", method = RequestMethod.POST)
	public String registerMember(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("memberId") String memberId, @RequestParam("memberPw") String memberPw,
			@RequestParam("memberName") String memberName, @RequestParam("memberAge") int memberAge,
			@RequestParam("memberGender") String memberGender, @RequestParam("memberEmail") String memberEmail,
			@RequestParam("memberPhone") String memberPhone, @RequestParam("memberAddress") String memberAddress,
			@RequestParam("memberHobby") String memberHobby,

			Model model) {
		// ���ͷ� �ѹ��� �� �� �ִ°� ����
		// web.xml���� ��
//		request.setCharacterEncoding("UTF-8");
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone,
				memberAddress, memberHobby);

		try {
			int result = service.registerMember(member);
			if (result > 0) {
				// ����
//				�Ⱦ�
//				response.sendError("/index.jsp");
				return "redirect:/index.jsp";
			} else {
				// ���� ����
				model.addAttribute("msg", "ȸ������ ����");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace(); // �ܼ�â ���������� �߰���
			model.addAttribute("msg", e.getMessage()); // �ܼ�â�� �ߴ� �޽����� �� �������� �߰���
			return "common/errorPage";

		}

//		System.out.println(member.toString());
//		String memberId = request.getParameter("memberId");
		// �Ƚ�
//		request.setAttribute("", "");
//		request.getRequestDispatcher("/WEB-INF/views/member/register.jsp");
//			response.sendError(sc);

		// �׷� ����
	}

	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, @RequestParam("memberId") String memberId,
			@RequestParam("memberPw") String memberPw, Model model) {

		// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?

		try {

			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			Member mOne = service.memberLogicCheck(member);
			if (mOne != null) {
				// �����ϸ� �α��� �������� �̵�
//				System.out.println(mOne.toString());
//				model.addAttribute("member",mOne);
//				HttpSession session = request.getSession();
//						session.setAttribute("memberId", mOne.getMemberId());
//						session.setAttribute("memberName", mOne.getMemberNAME());
				model.addAttribute("memberId", mOne.getMemberId());
				model.addAttribute("memberName", mOne.getMemberNAME());
				return "redirect:/index.jsp";

			} else {
				// �����ϸ� ���и޽��� ���
				model.addAttribute("msg", "�α��� ����");

				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			// ���� �߻��� ���� �޽��� ���
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}
	
	//HttpServletRequest request ,
	@RequestMapping(value="/member/logout.do" ,method=RequestMethod.GET)
	public String memberLogout(HttpSession sessionPrev, SessionStatus session , Model model) {
		if(session != null) {
//			session.invalidate();
			
			//SessionStatus �� �������� ������̼�(SessionAttributes)�� �����Ǵ� ������ �����Ų��.
			//��� �� �޼ҵ�� setComplete();
			session.setComplete();
			if(session.isComplete()) {
				//���� �Ϸ� ��ȿ�� üũ
			}
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg","�α׾ƿ��� �Ϸ����� ���߽��ϴ�.");
			return "common/errorPage";
			
		}
	}
	
	@RequestMapping(value="/member/mypage.do",method=RequestMethod.GET)
	public String memberMypage(HttpServletRequest request , HttpSession sessionPrev, SessionStatus session,Model model) {
		String memberId = request.getParameter("memberId");
		Member member = new Member();
		member.setMemberId(memberId);
		Member mOne = service.showOneById(memberId);
		
		
		try {
			
			if ( mOne != null) {
				model.addAttribute("member",mOne);
				return "member/mypage";
			} else {
				model.addAttribute("msg","���������� ����");
				return "common/errorPage";
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
		} 
		
		
	}
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String removeMember(@RequestParam("memberId")String memberId,Model model) {
		
		int result = service.removeMember(memberId);
		if ( result > 0) {
			//����~
			return "redirect:/member/logout.do";
		} else {
			//����~~
			model.addAttribute("msg","������..");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/updateView.do",method=RequestMethod.GET)
	public String modifyMemberShow(@RequestParam("memberId")String memberId, Model model) {
		Member member = new Member();
		Member mOne = service.showOneById(memberId);
		
		if ( mOne != null) {
			model.addAttribute("member",mOne);
		
			return "member/modify";
		} else {
			return "redirect:/index.jsp";
		}
	}
	
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String modifyMember(@RequestParam("memberId")String memberId,
			@RequestParam("memberDate") Timestamp memberDate,
			@RequestParam("memberName") String memberName,
			@RequestParam("memberAge") int memberAge,
			@RequestParam("memberGender") String memberGender,
			@RequestParam("memberEmail") String memberEmail,
			@RequestParam("memberPhone") String memberPhone,
			@RequestParam("memberAddress") String memberAddress,
			@RequestParam("memberHobby") String memberHobby,
			Model model)
	{
		
		Member member = new Member(memberId, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby, memberDate);
	
		int result = service.modifyMember(member);
		if (result > 0) {
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("msg","������..");
			model.addAttribute("member",member);
			return "common/errorPage";
			
		}
}
}