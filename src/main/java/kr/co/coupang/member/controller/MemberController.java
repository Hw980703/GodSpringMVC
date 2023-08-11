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

	// doPost - 데이터 저장용
	@RequestMapping(value = "/member/register.do", method = RequestMethod.POST)
	public String registerMember(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("memberId") String memberId, @RequestParam("memberPw") String memberPw,
			@RequestParam("memberName") String memberName, @RequestParam("memberAge") int memberAge,
			@RequestParam("memberGender") String memberGender, @RequestParam("memberEmail") String memberEmail,
			@RequestParam("memberPhone") String memberPhone, @RequestParam("memberAddress") String memberAddress,
			@RequestParam("memberHobby") String memberHobby,

			Model model) {
		// 필터로 한번에 할 수 있는게 있음
		// web.xml에서 함
//		request.setCharacterEncoding("UTF-8");
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone,
				memberAddress, memberHobby);

		try {
			int result = service.registerMember(member);
			if (result > 0) {
				// 성공
//				안씀
//				response.sendError("/index.jsp");
				return "redirect:/index.jsp";
			} else {
				// 실패 ㅋㅋ
				model.addAttribute("msg", "회원가입 실패");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace(); // 콘솔창 빨간색으로 뜨게함
			model.addAttribute("msg", e.getMessage()); // 콘솔창에 뜨는 메시지를 웹 페이지로 뜨게함
			return "common/errorPage";

		}

//		System.out.println(member.toString());
//		String memberId = request.getParameter("memberId");
		// 안써
//		request.setAttribute("", "");
//		request.getRequestDispatcher("/WEB-INF/views/member/register.jsp");
//			response.sendError(sc);

		// 그럼 뭘써
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
				// 성공하면 로그인 페이지로 이동
//				System.out.println(mOne.toString());
//				model.addAttribute("member",mOne);
//				HttpSession session = request.getSession();
//						session.setAttribute("memberId", mOne.getMemberId());
//						session.setAttribute("memberName", mOne.getMemberNAME());
				model.addAttribute("memberId", mOne.getMemberId());
				model.addAttribute("memberName", mOne.getMemberNAME());
				return "redirect:/index.jsp";

			} else {
				// 실패하면 실패메시지 출력
				model.addAttribute("msg", "로그인 실패");

				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 예외 발생시 예외 메시지 출력
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
			
			//SessionStatus 는 스프링의 어노테이션(SessionAttributes)로 지원되는 세션을 만료시킨다.
			//사용 된 메소드는 setComplete();
			session.setComplete();
			if(session.isComplete()) {
				//세션 완료 유효성 체크
			}
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg","로그아웃을 완료하지 못했습니다.");
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
				model.addAttribute("msg","마이페이지 실패");
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
			//성공~
			return "redirect:/member/logout.do";
		} else {
			//실패~~
			model.addAttribute("msg","실패함..");
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
			model.addAttribute("msg","실패함..");
			model.addAttribute("member",member);
			return "common/errorPage";
			
		}
}
}