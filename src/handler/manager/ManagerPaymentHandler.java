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
import dao.inter.PaymentDao;
import dao.inter.ReviewDao;
import dto.PaymentDto;

@Controller
public class ManagerPaymentHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="paymentDao")
	PaymentDao paymentDao;
	
	@Resource(name="reviewDao")
	ReviewDao reviewDao;
	
	//결제 리스트 보기
	@RequestMapping("/paymentList.do")
	public ModelAndView paymentList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 설정
		PageInfoProcess.getInstance().setPageSize(10);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(paymentDao.getPaymentCount(), request.getParameter("pageNum"));
		
		//결제 정보가 존재한다면
		if(info.getCnt()>0) {
			
			//결제 정보들 가져오기
			List<PaymentDto> payments = paymentDao.getPaymentRangeList(info);
			
			//결제 정보들 셋팅
			request.setAttribute("payments",payments);
		}
				
		//페이지 정보
		request.setAttribute("info", info);
				
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "paymentList");
				
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");		
	}
	
	//결제정보 삭제하기
	@RequestMapping("/deletePaymentPro.do")
	public ModelAndView deletePaymentPro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int payment_id = Integer.parseInt(request.getParameter("payment_id"));
		
		//이미 작성된 리뷰가 있는지 체크
		int result = reviewDao.getCheckReviewByPaymentID(payment_id);
		
		//(0, 삭제 가능) (1, 삭제 불가능)
		if(result == 0) {
			//삭제
			result = paymentDao.deletePaymentById(payment_id);
		}else {
			result = -1;
		}
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/deletePaymentPro");
	}
}


