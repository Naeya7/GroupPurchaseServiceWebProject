package handler.manager;

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
import dao.inter.PaymentDao;
import dto.join.ManagerApplicationListDto;

@Controller
public class ManagerApplicationHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="applicationDao")
	ApplicationDao applicationDao;
	
	@Resource(name="paymentDao")
	PaymentDao paymentDao;
	
	//신청 리스트 보기
	@RequestMapping("/applicationList.do")
	public ModelAndView applicationList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 설정
		PageInfoProcess.getInstance().setPageSize(10);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(applicationDao.getApplicationCount(), request.getParameter("pageNum"));
		
		//방어 코드
		if(info.getCnt()>0) {
			List<ManagerApplicationListDto> applications = applicationDao.getApplicationRangeList(info);
			request.setAttribute("applications",applications);
		}
				
		//페이지 정보
		request.setAttribute("info", info);
				
		//모듈 컨트롤
		request.setAttribute("pageControl", "applicationList");
		
		//메인 페이지로 이동
		return new ModelAndView("manager/managerPage");		
	}
	
	//신청정보 삭제하기
	@RequestMapping("/deleteApplicationPro.do")
	public ModelAndView deleteApplicationPro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int application_id = Integer.parseInt(request.getParameter("application_id"));
		
		//이미 결제된 신청 정보인지 체크
		int result = paymentDao.getCheckPaymentByApplicationId(application_id);
		
		//(0, 삭제 가능) (-1, 삭제 불가능)
		if(result == 0) {
			//신청 정보 삭제
			result = applicationDao.deleteApplicationById(application_id);
		}else {
			result = -1;
		}
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/deleteApplicationPro");
	}	

}
