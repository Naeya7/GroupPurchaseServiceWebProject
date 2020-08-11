package handler.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
;

@Controller
public class ManagerPageHandler {
	@RequestMapping("/managerPage.do")
	public ModelAndView managerPage(HttpServletRequest request, HttpServletResponse response) {
		
		//모듈 컨트롤 셋팅 (Default 값)
		request.setAttribute("pageControl", "starPostList");
		
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
}
