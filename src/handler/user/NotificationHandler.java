package handler.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.inter.NotificationDao;
import dto.NotificationDto;
import log.TestLog;

@Controller
public class NotificationHandler {
	
	//자바 빈 객체 할당
	@Resource(name="notificationDao")
	private NotificationDao notificationDao;
	
	//안읽은 알림 얻기(상태가 0)
	@RequestMapping("/checkNotification.do")
	public ModelAndView checkNotification(HttpServletRequest request, HttpServletResponse response) {
				
		//결과변수 선언
		int result = 0;
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		
		//안 읽은 알림 숫자 체크
		int count = notificationDao.checkNotificationNotRead(member_id);
		
		if(count!=0) {
			result=1;
		}
			
		//결과셋팅
		request.setAttribute("result", result);
		request.setAttribute("count", count);
					
		return new ModelAndView("user/checkNotification");
	}
	
	//알림 얻기
	@RequestMapping("/getNotification.do")
	public ModelAndView getNotification(HttpServletRequest request, HttpServletResponse response) {
			
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		
		//알림 가져오기
		List<NotificationDto> notifications = notificationDao.getNotificationById(member_id);
		
		//알림 상태 읽기로 바꾸기
		notificationDao.updateReadStatus(member_id);
				
		//결과셋팅
		request.setAttribute("notifications", notifications);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/getNotification");
	}
	
	//알림 전부 삭제하기
	@RequestMapping("/deleteAllNotification.do")
	public ModelAndView deleteAllNotification(HttpServletRequest request, HttpServletResponse response) {
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		
		//알림 전부 지우기
		int result = notificationDao.deleteAllNotification(member_id);
		
		//결과셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//메인페이지로 가기
		return new ModelAndView("user/deleteAllNotification");
	}
	
}
