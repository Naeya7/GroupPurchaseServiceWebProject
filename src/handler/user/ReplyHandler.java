package handler.user;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.inter.ReplyDao;
import dao.inter.ReviewDao;
import dto.ReplyDto;
import log.TestLog;

@Controller
public class ReplyHandler {
	
	//스프링 빈 객체 할당
	@Resource
	ReplyDao replyDao;
	
	@Resource
	ReviewDao reviewDao;
	
	//댓글 작성
	@RequestMapping("/writeReplyPro.do")
	public ModelAndView writeReplyPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//한글 셋팅
		request.setCharacterEncoding("utf-8");
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		//빈 셋팅
		ReplyDto replyDto = new ReplyDto();
		replyDto.setReview_num(review_num);
		replyDto.setMember_id(member_id);
		replyDto.setContent(request.getParameter("content"));
		
		//댓글 등록
		int result = replyDao.uploadReplyFromReview(replyDto);
		
		//등록 성공시
		if(result == 1) {
			//댓글수 증가
			int resultUpdate = reviewDao.updateReplycountFromReview(review_num);
			
			//댓글수 증가 결과 셋팅
			request.setAttribute("resultUpdate", resultUpdate);
		}
		
		//결과 셋팅
		request.setAttribute("result", result);
		request.setAttribute("review_num", review_num);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//처리 페이지로 이동
		return new ModelAndView("user/writeReplyPro");
	}
	
	
	//댓글 삭제하기
	@RequestMapping("/deleteReplyPro")
	public ModelAndView deleteReplyPro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		int review_num = Integer.parseInt(request.getParameter("review_num"));
	
		//댓글 삭제하기
		int result = replyDao.deleteReplyFromReview(reply_num);
		System.out.println(result);
		//삭제 성공시
		if(result == 1) {
			//댓글수 감소
			int resultUpdate = reviewDao.deleteupdateReplycountFromReview(review_num);
			
			//댓글수 감소 결과 셋팅
			request.setAttribute("resultUpdate", resultUpdate);
		}

		
		//결과셋팅
		request.setAttribute("result", result);
		request.setAttribute("review_num", review_num);
	
		//로그남기기
		TestLog.getSession().logging(request);
		
		//처리 페이지로 이동
		return new ModelAndView("user/deleteReplyPro");
	}
}
