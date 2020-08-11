package handler.user;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.MailSend;
import common.randomCode;
import dao.inter.MemberDao;
import dto.MemberDto;
import log.TestLog;

@Controller
public class SignUpHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="memberDao")
	MemberDao memberDao;
	
	//회원 가입 폼
	@RequestMapping("/signUpForm.do")
	public ModelAndView signUp(HttpServletRequest request, HttpServletResponse response) {
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/signUpForm");
	}
	
	
	//이메일 중복 검사
	@RequestMapping("/checkEmail.do")
	public ModelAndView checkEmail(HttpServletRequest request, HttpServletResponse response) {
			
		//파라미터 받기
		String member_email = request.getParameter("member_email");
		
		//이메일 중복 체크
		int result = memberDao.checkEmail(member_email);
		
		//결과셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/checkEmail");
	}
	
	//메일 인증
	@RequestMapping("/mailConfirmForm.do")
	public ModelAndView mailConfirmForm(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String member_email = request.getParameter("member_email");
		
		//랜덤 코드 생성
		randomCode radom = new randomCode();
		String codeMsg = radom.excuteGenerate();
			
		
		@SuppressWarnings("unused")
		MailSend send = new MailSend(member_email, codeMsg);
	
		request.setAttribute("codeMsg", codeMsg);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/mailConfirmForm");
	}
	
	
	//닉네임 중복 체크
	@RequestMapping("/checkNickname.do")
	public ModelAndView checkNickname(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String nickname = request.getParameter("nickname");
		
	    //닉네임 검사
	    int result = memberDao.checkNickname(nickname);
	   	    
	    //결과 셋팅
	    request.setAttribute("result", result);
	    
		//로그남기기
		TestLog.getSession().logging(request);
	  
	    //결과 반환	
	    return new ModelAndView("user/checkNickname");
	}

	
	
	//회원 가입
	@RequestMapping("/signUpPro.do")
	public ModelAndView signUpPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	
		//한글 처리
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		String member_email = request.getParameter("member_email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String tel = request.getParameter("tel");
		
		//선택사항
		String gender = request.getParameter("gender");
		String birth_date = request.getParameter("birth_date");
		String visit_path = request.getParameter("visit_path");
		
		
		//성별 처리
		if(gender.equals("0")) {
			gender = "남자";
		}else if(gender.equals("1")) {
			gender = "여자";
		}
		
		//빈할당
		MemberDto member = new MemberDto();
		member.setMember_email(member_email);
		member.setNickname(nickname);
		member.setPassword(password);
		member.setZipcode(zipcode);
		member.setAddress1(address1);
		member.setAddress2(address2);
		member.setTel(tel);
		member.setGender(gender);
		member.setBirth_date(birth_date);
		member.setVisit_path(visit_path);
	
		//멤버 등록
		int result = memberDao.insertMember(member);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/signUpPro");
	}	
}
