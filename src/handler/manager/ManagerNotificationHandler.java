package handler.manager;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.PageInfoDto;
import common.PageInfoProcess;
import dao.inter.ApplicationDao;
import dao.inter.NotificationDao;
import dto.NotificationDto;
import dto.join.ManagerNotificationListDto;

@Controller
public class ManagerNotificationHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="notificationDao")
	NotificationDao notificationDao;
	
	@Resource(name="applicationDao")
	ApplicationDao applicationDao;
	
	//알림 리스트 출력
	@RequestMapping("/notificationList.do")
	public ModelAndView notificationList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(10);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(notificationDao.getNotificationCount() ,request.getParameter("pageNum"));
		
		//알림이 존재하면 리스트 가져오기
		if(info.getCnt()>0) {
			
			//전체 알림들 가져오기
			List<ManagerNotificationListDto> notifications = notificationDao.getNotificationRangeList(info);
			
			//알림 셋팅
			request.setAttribute("notifications",notifications);
		} 
		
		//페이지 정보
		request.setAttribute("info", info);
		
		//모듈 컨트롤
		request.setAttribute("pageControl", "notificationList");
		
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	
	//알림 삭제 하기
	@RequestMapping("/deleteNotificationPro.do")
	public ModelAndView deleteNotificationPro(HttpServletRequest request, HttpServletResponse response){
		
		//파라미터 받기
		int notify_id = Integer.parseInt(request.getParameter("notify_id"));
		
		//알림 삭제하기
		int result = notificationDao.deleteNotification(notify_id);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/deleteNotificationPro");
	}
	
	//개인 알림폼
	@RequestMapping("/sendNotificationForm.do")
	public ModelAndView sendNotificationForm(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 받기
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		
		//그대로 넘겨주기
		request.setAttribute("member_id", member_id);
		return new ModelAndView("manager/sendNotificationForm");
	}
	
	//개인 알림 보내기
	@RequestMapping("/sendNotificationPro.do")
	public ModelAndView sendNotificationPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		
		//content 한글 사용
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		String content = request.getParameter("content");
		String manager_id = (String)request.getSession().getAttribute("manager_id");
		
		//빈 셋팅
		NotificationDto notification = new NotificationDto();
		notification.setManager_id(manager_id);
		notification.setContent(content);
		notification.setMember_id(member_id);
		
		//알림 등록하기
		int result = notificationDao.insertNotificationByMemberId(notification);
				
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/sendNotificationPro");
	}		
	
	//전체 알림폼
	@RequestMapping("/sendAllNotificationForm.do")
	public ModelAndView allSendNotificationForm(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		//포스트 아이디 셋팅
		request.setAttribute("post_id", post_id);
		
		//전체 알림 폼으로 이동
		return new ModelAndView("manager/sendAllNotificationForm");
	}
	
	
	//전체 알림 보내기
	@RequestMapping("/sendAllNotificationPro.do")
	public ModelAndView allSendNotificationPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		
		//content 한글 사용
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		String content = request.getParameter("content");
		String manager_id = (String)request.getSession().getAttribute("manager_id");
		int cnt = 0;
		
		//신청한 멤버 ID 리스트
		List<Integer> members_id = applicationDao.getAllMemberIdbyPostId(post_id);
		
		//빈 셋팅
		NotificationDto notification = new NotificationDto();
		notification.setManager_id(manager_id);
		notification.setContent(content);
		notification.setPost_id(post_id);

		//멤버들에게 알림 보내기
		for(int member_id : members_id) {
			notification.setMember_id(member_id);
			notificationDao.insertAllNotificationByMemberId(notification);
			cnt++;
		} 
		
		//보낸 숫자 셋팅
		request.setAttribute("cnt", cnt);
			
		return new ModelAndView("manager/sendAllNotificationPro");
	}	
}
