package handler.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import dao.inter.PostDao;
import dao.inter.ReplyDao;
import dao.inter.ReviewDao;
import dto.ApplicationDto;
import dto.ReplyDto;
import dto.ReviewDto;
import dto.join.PayablePostDto;
import dto.join.PayedPostDto;
import log.TestLog;


@Controller
public class MyPageHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="postDao")
	private PostDao postDao;
	
	@Resource(name="reviewDao")
	private ReviewDao reviewDao;
	
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	
	@Resource(name="paymentDao")
	private PaymentDao paymentDao;
	
	@Resource(name="applicationDao")
	private ApplicationDao applicationDao;
	
	//전역변수
	int member_id;
	
	//마이페이지 탬플릿 페이지
	@RequestMapping("/myPageTemplate.do")
	public ModelAndView myPageTemplate(HttpServletRequest request, HttpServletResponse response) {
	
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "payablePost");
		
		//로그남기기
		TestLog.getSession().logging(request);
			
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
			
	}

	//결제 가능한 게시글
	@RequestMapping("/payablePost.do")
	public ModelAndView payablePost(HttpServletRequest request, HttpServletResponse response) {

		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//결제 가능
		List<PayablePostDto> payablePostDto = postDao.getPayablePostFromMypage(member_id);
		
		//HTML 태그 제거
		for(int i=0; i<payablePostDto.size(); i++) {
					
			//태그 제거
			textByContent= payablePostDto.get(i).getContent().replaceAll(reg, "");
					
			//공백 하나로 변환
			textByContent = textByContent.trim().replaceAll("&nbsp;", "");

			//결과 셋팅
			payablePostDto.get(i).setContent(textByContent);
		}		
		
		//결제 가능한 포스트 셋팅
		request.setAttribute("payablePostDto", payablePostDto);
			
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "payablePost");
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
		
	}	
	
	//인원 모집중인 게시글
	@RequestMapping("/notPayablePost.do")
	public ModelAndView notPayablePost(HttpServletRequest request, HttpServletResponse response) {
		
		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//결제 불가능
		List<PayablePostDto> notPayablePostDto = postDao.getNotPayablePostFromMypage(member_id);
		
		//HTML 태그 제거
		for(int i=0; i<notPayablePostDto.size(); i++) {
			
			//태그 제거
			textByContent= notPayablePostDto.get(i).getContent().replaceAll(reg, "");
			
			//공백 하나로 변환
			textByContent = textByContent.trim().replaceAll("&nbsp;", "");
			
			//결과 셋팅
			notPayablePostDto.get(i).setContent(textByContent);
		}		
		
		//결제 불가능한 포스트 셋팅
		request.setAttribute("notPayablePostDto", notPayablePostDto);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "notPayablePost");
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
		
	}	

	//신청 취소하기
	@RequestMapping("/cancelApp.do")
	public ModelAndView cancelApp(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 받기
		int application_id = Integer.parseInt(request.getParameter("application_id"));
				
		// App ID 찾기
		ApplicationDto applicationDto = applicationDao.getappId(application_id);
				
		//신청 취소하기
		int result = applicationDao.cancelAppFromMypage(application_id);
				
		if(result == 1) {
			//댓글수 감소
			applicationDao.deleteupdateAppFromPostPostContent(applicationDto);
		}
				
		//결과 셋팅
		request.setAttribute("result", result);
				
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "cancelApp");
				
		// 설문조사 
		String cancel_price = request.getParameter("cancel_price");
		String cancel_function = request.getParameter("cancel_function");
		String cancel_product = request.getParameter("cancel_product");
		String cancel_site = request.getParameter("cancel_site");
		String cancel_mind = request.getParameter("cancel_mind");
		String cancel_content = request.getParameter("cancel_content"); // 기타사유 텍스트
				      
		//파일 패스 생성
		File file = new File("C:\\ExpertJava/logs/canscelLog.log");
		      
		//현재 날짜 받기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		String date = sdf.format(Calendar.getInstance().getTime());
			         
		try {      
			//버퍼 셋팅
		    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));    
		     //파일이 쓰기 가능한 상태면
		     if(file.isFile() && file.canWrite()) {        
				bw.write(
						"{ "
					+ 		"\"date\":\"" 				+ date 				+ "\", "
					+ 		"\"cancel_price\":\"" 		+ cancel_price		+ "\", "
					+ 		"\"cancel_function\":\"" 	+ cancel_function	+ "\", "
					+ 		"\"cancel_product\":\"" 	+ cancel_product	+ "\", "
					+ 		"\"cancel_site\":\"" 		+ cancel_site 		+ "\", "	
					+ 		"\"cancel_mind\":\"" 		+ cancel_mind 		+ "\", "
					+ 		"\"cancel_content\":\"" 	+ cancel_content	+ "\", "
					+ 	" },"	
			);		        
		        
		        bw.newLine();
		        bw.close();
		     }   
		}catch (IOException e) {
		     e.printStackTrace();
		}	
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}	
	
	//내가 결제한 포스트 리스트
	@RequestMapping("/myPayedList.do")
	public ModelAndView myPayedList(HttpServletRequest request, HttpServletResponse response) {
			
			
		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
					
		//세션 가져오기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
			
		//페이지 셋팅
		PageInfoProcess.getInstance().setPageSize(5);
		PageInfoProcess.getInstance().setPageBlock(5);
			
		//페이지 정보 처리
		PageInfoDto info = PageInfoProcess.getInstance().process(paymentDao.getPaymentCount(member_id), request.getParameter("pageNum"));
			
		//결제 내역이 존재하면
		if(info.getCnt()>0) {
			//맵 셋팅
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("member_id", member_id);
			map.put("start", info.getStart());
			map.put("end", info.getEnd());
				
			//결제한 리스트 가져오기
			List<PayedPostDto> payedPostDto = postDao.getPayedPostListFromMypage(map); 
				
				//HTML 태그 제거
				for(int i=0; i<payedPostDto.size(); i++) {
						
					//태그 제거
					textByContent= payedPostDto.get(i).getContent().replaceAll(reg, "");
						
					//공백 하나로 변환
					textByContent = textByContent.trim().replaceAll("&nbsp;", "");
						
					//결과 셋팅
					payedPostDto.get(i).setContent(textByContent);
				}	
				
			//결제한 리스트 셋팅
			request.setAttribute("payedPostDto", payedPostDto);
		}
			
		//페이지 정보 셋팅
		request.setAttribute("info", info);
			
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "myPayedList");
			
		//로그남기기
		TestLog.getSession().logging(request);
					
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	 	}	
	
		
	//내 리뷰 쓴 보기
	@RequestMapping("/myReviewList.do")
	public ModelAndView myReviewList(HttpServletRequest request, HttpServletResponse response) {
	
		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//내리뷰 가져오기
		List<ReviewDto> reviewDto = reviewDao.getReviewListFromMypage(member_id);
		
		//HTML 태그 제거
		for(int i=0; i<reviewDto.size(); i++) {
			
			//태그 제거
			textByContent= reviewDto.get(i).getContent().replaceAll(reg, "");
			
			//공백 하나로 변환
			textByContent = textByContent.trim().replaceAll("&nbsp;", "");
			
			//결과 셋팅
			reviewDto.get(i).setContent(textByContent);
		}			
		
		//리뷰 셋팅하기
		request.setAttribute("reviewDto", reviewDto);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "myReviewList");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}
	
	//내가 쓴 댓글 보기
	@RequestMapping("/myReplyList.do")
	public ModelAndView myReplyList(HttpServletRequest request, HttpServletResponse response) {
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		member_id = Integer.parseInt(mem_id);
		
		//결제한 리뷰 가져오기
		List<ReplyDto> replyDto = replyDao.getReplyListFromMypage(member_id);
		
		//결제한 리뷰들 셋팅
		request.setAttribute("replyDto", replyDto);
	
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "myReplyList");
					
		//로그남기기
		TestLog.getSession().logging(request);
		
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}

	
}







