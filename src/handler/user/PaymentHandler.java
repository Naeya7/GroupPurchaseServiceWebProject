package handler.user;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.inter.MemberDao;
import dao.inter.PaymentDao;
import dto.MemberDto;
import dto.PaymentDto;
import log.TestLog;

@Controller
public class PaymentHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="paymentDao")
	PaymentDao paymentDao;
	
	@Resource(name="memberDao")
	MemberDao memberDao;
	
	@RequestMapping("/paymentForm.do")
	public ModelAndView payment(HttpServletRequest request, HttpServletResponse response) {
		// payment table 에 넣을 값
		int total_price = Integer.parseInt(request.getParameter("total_price"));
		int application_id = Integer.parseInt(request.getParameter("application_id"));
		
		// 배송지 default로 불러오기 위해 member
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		MemberDto memberDto = memberDao.getMemberFromMypage(member_id);
		
		request.setAttribute("total_price", total_price);
		request.setAttribute("application_id", application_id);
		request.setAttribute("memberDto", memberDto);
			
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "paymentForm");
		
		//로그남기기
		TestLog.getSession().logging(request);
						
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}
	
	//결제 등록하기
	@RequestMapping("/paymentPro.do")
	public ModelAndView paymentPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		int application_id = Integer.parseInt(request.getParameter("application_id"));
		String receiver_name = request.getParameter("receiver_name");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		int total_price = Integer.parseInt(request.getParameter("total_price"));
		String method = request.getParameter("method");
		String req = request.getParameter("request");
		
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setApplication_id(application_id);
		paymentDto.setReceiver_name(receiver_name);
		paymentDto.setZipcode(zipcode);
		paymentDto.setAddress1(address1);
		paymentDto.setAddress2(address2);
		paymentDto.setTotal_price(total_price);
		paymentDto.setMethod(method);
		paymentDto.setRequest(req);
		
		int result = paymentDao.registerPaymentFromPayment(paymentDto);
		
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		
		return new ModelAndView("user/paymentPro");
	}	
	
	
	
	
	
	
}
