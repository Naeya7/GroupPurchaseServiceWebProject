package handler.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.ManagerDto;
import mybatis.SqlMapClient;

@Controller
public class ManagerLogInHandler {
	
	//SQL 세션 받기
	SqlSession session = SqlMapClient.getSession();
	
	//로그인 폼
	@RequestMapping("/managerLogInForm.do")
	public ModelAndView managerLogInForm(HttpServletRequest request, HttpServletResponse response) {	
		
		//폼 페이지로 이동
		return new ModelAndView("manager/managerLogInForm");
	}
	
	//로그인 프로
	@RequestMapping("/managerLogInPro.do")
	public ModelAndView managerLogInPro(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		
		//파라미터 받기
		String manager_id = request.getParameter("manager_id");
		String password = request.getParameter("password");
		
		//맵 셋팅
		ManagerDto manager =  new ManagerDto();
		manager.setManager_id(manager_id);
		manager.setPassword(password);
	
		//(Not Null, 로그인 성공) (Null, 로그인 실패)
		manager = session.selectOne("manager.managerLoginCheck", manager);
		
		//로그인 되었을 경우
		if(manager != null) {
			
			//성공 여부
			result = 1;
			
			//세션 등록
			request.getSession().setAttribute("manager_id", manager.getManager_id());
			request.getSession().setAttribute("manager_name", manager.getManager_name());	
		}	
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/managerLogInPro");
	}
	
	//로그아웃 프로
	@RequestMapping("/mamgerLogOutPro.do")
	public ModelAndView managerLogOutPro(HttpServletRequest request, HttpServletResponse response) {
		
		//세션 모두 지우기
		request.getSession().invalidate();
		
		//로그인 페이지로 이동
		return new ModelAndView("manager/managerLogInForm");
	}
}
