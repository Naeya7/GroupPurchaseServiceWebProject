package handler.user;

import java.util.HashMap;
import java.util.Map;

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
public class LogInHandler {
	
	
	@Resource(name="memberDao")
	private MemberDao memberDao;
	//로그인폼
	@RequestMapping("/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse respons) {
			
		//로그인 폼으로 이동
		return new ModelAndView("user/loginForm");
		
	}
	
	@RequestMapping("/loginPro.do")
	public ModelAndView logInPro(HttpServletRequest request, HttpServletResponse respons) {
		String member_email = request.getParameter("member_email");
		String password = request.getParameter("password");
		MemberDto memberDto = memberDao.getMemberFromLogin(member_email);
		Map<String, String> memSession  = new HashMap<String, String>();
		int result = memberDao.check(member_email, password);
		
		if(result == 0) {
			// 로그인 정보가 맞지 않음
			memSession.put("result", "0");
		} else if(result == 1) {
			// 활동 회원
			memSession.put("result", "1");
			memSession.put("nickname", memberDto.getNickname());
			memSession.put("gender", memberDto.getGender());
			memSession.put("member_id", Integer.toString(memberDto.getMember_id()));
		} else if(result == 2) {
			// 활동 정지 회원
			memSession.put("result", "2");
		} else if(result == 3) {
			// 휴면상태 회원
			memSession.put("result", "3");
			memSession.put("member_id", Integer.toString(memberDto.getMember_id()));
		}
		
		
		request.setAttribute("memSession", memSession);	
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/loginPro");
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logOutPro(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		//로그남기기
		TestLog.getSession().logging(request);
		return new ModelAndView("user/logout");
	}
	
	@RequestMapping("/activateStatus.do")
	public ModelAndView activateStatus(HttpServletRequest request, HttpServletResponse response) {
		//로그남기기
		TestLog.getSession().logging(request);
		return new ModelAndView("user/activateStatus");
	}
	
	@RequestMapping("/activateStatusPro.do")
	public ModelAndView activateStatusPro(HttpServletRequest request, HttpServletResponse response) {
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		String password = request.getParameter("password");
		int result = memberDao.check(member_id, password);
		if(result == 1) {
			// 비밀번호 같다
			memberDao.activateStatusFromLogin(member_id);
			result = 1;
		} else {
			// 비밀번호 다르다
			result = 0;
		}
		
		//결과셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/activateStatusPro");
	}
}
