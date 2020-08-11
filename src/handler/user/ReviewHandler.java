package handler.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.PageInfoDto;
import common.PageInfoProcess;
import dao.inter.MemberDao;
import dao.inter.PaymentDao;
import dao.inter.PostDao;
import dao.inter.ReplyDao;
import dao.inter.ReviewDao;
import dto.ReviewDto;
import dto.join.PayedPostDto;
import dto.join.ReplyContentDto;
import dto.join.ReviewContentDto;
import log.TestLog;
import mybatis.SqlMapClient;

@Controller
public class ReviewHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="reviewDao")
	private ReviewDao reviewDao;
	
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	
	@Resource(name="postDao")
	private PostDao postDao;
	
	@Resource(name="paymentDao")
	private PaymentDao paymentDao; 
	
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	//SQL 세션 사용
	SqlSession session = SqlMapClient.getSession();
	
	//리뷰 리스트보기
	@RequestMapping("/reviewBoard.do")
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
		
		// 페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
		PageInfoProcess.getInstance().setPageBlock(5);
		
		//조건검색
		String select = request.getParameter("select");
		String keyword = request.getParameter("keyword");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("keyword", keyword);	
		
		// 페이지 정보 갱신
		PageInfoDto info = null;
		if(keyword == null || keyword.equals("")) { //검색어가 없으면 전체 출력
			info = PageInfoProcess.getInstance().process(reviewDao.getReviewCount(), request.getParameter("pageNum"));			
		}else {	//검색어가 있으면 검색어들만 출력		
			info = PageInfoProcess.getInstance().process(reviewDao.getSearchReviewCount(map), request.getParameter("pageNum"));			
		}
		
		// 방어 코드
		if(info.getCnt()>0) {
						
			//리뷰 가져오기
			List<ReviewContentDto> reviewContentDtos = null;
			if(keyword == null || keyword.equals("")) {  
				//검색어가 없으면
				reviewContentDtos = reviewDao.getReviewListFromBoard(info);				
			}else { 
				//검색어가 있으면
				info.setParam(select);
				info.setParam2(keyword);
				reviewContentDtos = reviewDao.getSearchReviewListFromBoard(info);
			}
			
			//HTML 태그 제거
			for(int i=0; i<reviewContentDtos.size(); i++) {
						
				//태그 제거
				textByContent= reviewContentDtos.get(i).getContent().replaceAll(reg, "");
						
				//공백 하나로 변환
				textByContent = textByContent.trim().replaceAll("&nbsp;", "");

				//결과 셋팅
				reviewContentDtos.get(i).setContent(textByContent);
			}
						
			//리뷰들 셋팅
			request.setAttribute("reviewContentDtos", reviewContentDtos);		
		}
		//검색어
		request.setAttribute("keyword", keyword);	
		
		//페이지 정보
		request.setAttribute("info", info);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "reviewBoard");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");	
	}
	
	//리뷰 콘텐트 보기
	@RequestMapping("/reviewContent.do")
	public ModelAndView reviewContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		//리뷰 콘텐트 정보 하나 받기
		ReviewContentDto reviewContentDto = reviewDao.getReviewContent(review_num);
		
		//조회수 증가
		reviewDao.addCountFromReview(review_num);
		
		//리뷰 정보 가져오기
		request.setAttribute("reviewContentDto", reviewContentDto);
		
		// 구매 상품 정보
		int payment_id = reviewContentDto.getPayment_id();
		
		//리뷰에 대한 포스트 정보 따로받기
		PayedPostDto reviewPostDto = postDao.getPayedPostFromReview(payment_id);
		
		//리뷰에 대한 포스트 정보 셋팅
		request.setAttribute("reviewPostDto", reviewPostDto);
		
		//댓글 정보 받기
		List<ReplyContentDto> replyContentDtos = replyDao.getReplyContentFromReview(review_num);
		
		//댓글 정보 셋팅
		request.setAttribute("replyContentDtos", replyContentDtos);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "reviewContent");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}
	
	//리뷰 작성 폼 가기
	@RequestMapping("/writeReview.do")
	public ModelAndView writeReview(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int payment_id = Integer.parseInt(request.getParameter("payment_id"));
		
		//그대로 넘기기
		request.setAttribute("payment_id", payment_id);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("myPageControl", "writeReview");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/myPageTemplate");
	}	
	
	//리뷰 작성하기
	@RequestMapping("/writeReviewPro.do")
	public ModelAndView writeReviewPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
		
		//파라미터 받기
		int payment_id = Integer.parseInt(request.getParameter("payment_id")); 
		String title =  request.getParameter("title");
		String content = request.getParameter("content");
		
		//빈 셋팅
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setMember_id(member_id);
		reviewDto.setPayment_id(payment_id);
		reviewDto.setTitle(title);
		reviewDto.setContent(content);
		
		//리뷰 등록
		int result = reviewDao.uploadReview(reviewDto);
		
		if(result == 1) {
			//리뷰 작성 상태 변경
			int resultUpdate = paymentDao.updateWritableFromReview(payment_id);
			
			//상태 변경 결과 셋팅
			request.setAttribute("resultUpdate", resultUpdate);
		}
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//처리 페이지로 이동
		return new ModelAndView("user/writeReviewPro");
	}	
	
	
	//리뷰 수정 폼
	@RequestMapping("/modifyReview.do")
	public ModelAndView modifyReview(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		// 글번호에 맞는 게시글 찾기
		ReviewDto reviewDto = reviewDao.getReviewById(review_num);
		
		//리뷰 셋팅
		request.setAttribute("reviewDto", reviewDto);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "modifyReview");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}
	
	//리뷰 수정 하기
	@RequestMapping("/modifyReviewPro.do")
	public ModelAndView modifyReviewPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 설정
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//빈 셋팅
		ReviewDto reviewDto = reviewDao.getReviewById(review_num);
		reviewDto.setReview_num(review_num);
		reviewDto.setTitle(title);
		reviewDto.setContent(content);
		
		//리뷰 수정
		int result = reviewDao.modifyReview(reviewDto);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//리뷰 번호 셋팅
		request.setAttribute("review_num", review_num);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//처리 페이지로 이동
		return new ModelAndView("user/modifyReviewPro");
	}	
	
	//리뷰 삭제하기
	@RequestMapping("/deleteUserReviewPro.do")
	public ModelAndView deleteReviewPro(HttpServletRequest request, HttpServletResponse response) {
		
		//변수 선언
		int result = 0;
		
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		// 삭제할 리뷰 가져오기
		ReviewDto reviewDto = reviewDao.getReviewById(review_num);
		
		// deleted_review 테이블에 저장
		int resultInsert = reviewDao.insertReviewintoDeletedReview(reviewDto);
		
		//저장이 성공했으면
		if(resultInsert == 1) {
			//리뷰 삭제
			result = reviewDao.deleteReview(review_num);
		}
		
		//결과셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//처리페이지로 이동
		return new ModelAndView("user/deleteReviewPro");
	}	
	
	
	//좋아요 누르기
	@RequestMapping("/likeCountPro.do")
	public ModelAndView likeCountPro(HttpServletRequest request, HttpServletResponse respons) {
		
		//변수 선언
		int result = 0;
		
		//세변 가져오기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		int member_id = Integer.parseInt(mem_id);
			
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num")); 
		
		//맵셋팅
		Map<String, Integer> like = new HashMap<String, Integer>();
		like.put("review_num", review_num);
		like.put("member_id", member_id);
		
		//이미 좋아요 눌렀는지 체크
		int likeResult = (Integer) reviewDao.check(like); 
		
		//안눌었으면
		if( likeResult == 0 ) {
			
			//좋아요 증가
			session.insert("user.likeinsert",like); 
			
			//성공여부
			result = reviewDao.addlike(review_num); 
		} 
		
		//결과셋팅
		request.setAttribute("result", result);
		
		//리뷰번호 셋팅
		request.setAttribute("review_num", review_num);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		//처리페이지로 이동
		return new ModelAndView("user/likeCountPro");
	}	
	
	
	//검색어로 리뷰 리스트 보기
	@RequestMapping("/searchReviewBoard.do")
	public ModelAndView searchReviewBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//한글 처리
		request.setCharacterEncoding("utf-8");
		
		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
						
		// 페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
		PageInfoProcess.getInstance().setPageBlock(5);
			
		// 페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(reviewDao.getReviewCount(), request.getParameter("pageNum"));
			
		// 방어 코드
		if(info.getCnt()>0) {
				
			//파라미터 받기
			String param = request.getParameter("keyword");//검색어
			String select = request.getParameter("select");//구분
			String param2 = null;
			int test = 0;
			
			System.out.println(param);
			System.out.println(select);
			
			//member_id로 변환
			if(select.equals("nickname")){
				param2 = Integer.toString(memberDao.getMemberIdByNickname(param));
				test = memberDao.getMemberIdByNickname(param);
			}
			
			System.out.println(param);
			System.out.println(test);
			System.out.println(param2);
			
			//keyword 셋팅
			info.setParam(param);
			
			//검색 부분 셋팅
			info.setParam2(param2);
				
			//리뷰 가져오기
			List<ReviewContentDto> reviewContentDtos = reviewDao.getReviewListFromBoardByKeyword(info);
					
			//HTML 태그 제거
			for(int i=0; i<reviewContentDtos.size(); i++) {
							
				//태그 제거
				textByContent= reviewContentDtos.get(i).getContent().replaceAll(reg, "");
							
				//공백 하나로 변환
				textByContent = textByContent.trim().replaceAll("&nbsp;", "");

				//결과 셋팅
				reviewContentDtos.get(i).setContent(textByContent);
			}
							
			//리뷰들 셋팅
			request.setAttribute("reviewContentDtos", reviewContentDtos);		
		}
						
		// 페이지 정보
		request.setAttribute("info", info);
			
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "reviewBoard");
		
		//로그남기기
		TestLog.getSession().logging(request);
					
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");	
	}
	
	

	
	
}








