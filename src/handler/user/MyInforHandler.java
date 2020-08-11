package handler.user;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.inter.MemberDao;
import dto.MemberDto;
import log.TestLog;

@Controller
public class MyInforHandler {
	
	//자반 빈 객체 할당
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	//전역 변수
	int member_id;
	
	//내 정보 간략 보기
	@RequestMapping("/myInfor.do")
	public ModelAndView myInfor(HttpServletRequest request, HttpServletResponse response) {
		
		//세션 가져오기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//내 멤버 정보 가져오기
		MemberDto memberDto = memberDao.getMemberFromMypage(member_id);
		
		//멤버 정보 셋팅
		request.setAttribute("memberDto", memberDto);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "myInfor");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}
	
	//정보 수정 비밀번호 입력폼
	@RequestMapping("/modifyInforCheck")
	public ModelAndView modifyInforCheck(HttpServletRequest request, HttpServletResponse response) {
	
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "modifyInforCheck");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}
	
	//정보 수정 비밀번호 입력폼
	@RequestMapping("/modifyInforCheckPro")
	public ModelAndView modifyInforCheckPro(HttpServletRequest request, HttpServletResponse response) {
		
		//세션 가져오기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
				
		//패스워드 가져오기
		String password = request.getParameter("password");
				
		//비밀번호 체크 하기
		int result = memberDao.check(member_id, password);
		
		//결과셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//메인페이지로 가기
		return new ModelAndView("user/modifyInforCheckPro");
	}	
	
	//내 정보 수정하는 폼 보기
	@RequestMapping("/modifyInfor")
	public ModelAndView modifyInfor(HttpServletRequest request, HttpServletResponse response) {
		
		//세션 가져오기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//내정보 가쟈오기
		MemberDto memberDto = memberDao.getMemberFromMypage(member_id);
			
		//내정보 셋팅
		request.setAttribute("memberDto", memberDto);
	
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "modifyInfor");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");				
	}	

	//내 정보 수정 처리
	@RequestMapping("/modifyInforPro")
	public ModelAndView modifyInforPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//한글 처리
		request.setCharacterEncoding("utf-8");
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//파라미터 받기
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");	
		
		//내정보 가져오기
		MemberDto memberDto = memberDao.getMemberFromMypage(member_id);
		
		//필수사항 저장
		memberDto.setNickname(nickname);
		memberDto.setPassword(password);
		memberDto.setTel(tel);
		memberDto.setZipcode(zipcode);
		memberDto.setAddress1(address1);
		memberDto.setAddress2(address2);
		
		//수정하기	
		int result = memberDao.modifyInfor(memberDto);
		
		//세션 바꾸기
		request.getSession().setAttribute("nickname", nickname);

		//결과 셋팅
		request.setAttribute("result", result);
		
		//이름 셋팅
		request.setAttribute("nickname", nickname);
		
		//로그남기기
		TestLog.getSession().logging(request);
					
		//메인페이지로 가기
		return new ModelAndView("user/modifyInforPro");
	}	
	
	//회원 탈퇴 폼(비밀번호 확인)
	@RequestMapping("/signOutForm")
	public ModelAndView signOutForm(HttpServletRequest request, HttpServletResponse response) {
		
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "signOutForm");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}
	
	//회원 탈퇴 처리
	@RequestMapping("/signOutPro.do")
	public ModelAndView signOutPro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		String password = request.getParameter("password");
		
		//비밀번호 체크하기
		int resultCheck = memberDao.check(member_id, password);
		
		//비밀번호가 같으면
		if(resultCheck == 1) {
			
			//회원을 탈퇴처리(휴먼상태)
			int result = memberDao.deleteMemberFromMypage(member_id);
			
			//세션 날리기
			request.getSession().invalidate();
			
			//결과 셋팅
			request.setAttribute("result", result);
		}
		
		//체크 결과 셋팅
		request.setAttribute("resultCheck", resultCheck);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/signOutPro");
	}	
	
	
	
	
	
	
}
