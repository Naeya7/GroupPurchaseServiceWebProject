package handler.manager;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.PageInfoDto;
import common.PageInfoProcess;
import dao.inter.ReviewDao;
import dto.ReviewDto;
import dto.join.ManagerReviewListDto;
import mybatis.SqlMapClient;

@Controller
public class ManagerReviewHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="reviewDao")
	ReviewDao reviewDao;
	
	//SQL 세션
	SqlSession session = SqlMapClient.getSession();

	@RequestMapping("/reviewList.do")
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response){
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(10);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(reviewDao.getReviewCount() ,request.getParameter("pageNum"));
		
		//가공된 리뷰 리스트가 있으면
		if(info.getCnt()>0) {
			
			//가공된 리뷰 리스트 가져오기
			List<ManagerReviewListDto> reviews = reviewDao.getReviewRangeList(info);
			
			//리뷰들 할당
			request.setAttribute("reviews",reviews);
		}
		
		//페이지 정보 셋팅
		request.setAttribute("info", info);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "reviewList");
		
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	

	//리뷰 따로 저장 및 삭제
	@RequestMapping("/deleteReviewPro.do")
	public ModelAndView deleteReviewPro(HttpServletRequest request, HttpServletResponse response) {
		
		//결과
		int result = 0;
		
		//파라미터 받기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		//삭제할 리뷰 가져오기
		ReviewDto review = reviewDao.getReviewById(review_num);
		
		//리뷰가 있으면 
		if(review != null) {
			
			//삭제 리뷰 테이블에 저장
			session.insert("manager.insertDeletedReview", review);
			
			//리뷰 삭제
			result = reviewDao.deleteReview(review_num);
		}
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/deleteReviewPro");
	}
}
