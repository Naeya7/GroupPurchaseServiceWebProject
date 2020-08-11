package handler.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.PageInfoDto;
import common.PageInfoProcess;
import dao.inter.ApplicationDao;
import dao.inter.PostDao;
import dao.inter.ReviewDao;
import dto.ReviewDto;
import dto.join.HitPostDto;
import dto.join.ManagerPostListDto;
import dto.join.PostContentDto;
import log.TestLog;


@Controller
@Aspect
public class PostHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="postDao")
	private PostDao postDao;
	
	@Resource(name="applicationDao")
	private ApplicationDao applicationDao;
	
	@Resource(name="reviewDao")
	private ReviewDao reviewDao;
		
	@RequestMapping("/mainPostList.do")
	public ModelAndView mainPostList(HttpServletRequest request, HttpServletResponse respons) throws Exception {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
		PageInfoProcess.getInstance().setPageBlock(5);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(postDao.getPostCount(), request.getParameter("pageNum"));
		
		//히트 게시물이 존재한다면
		if(info.getCnt()>0) {
			
			//변수 설정
			String textByContent = null;
			String reg = "<[^>]*>";

			//메뉴바에 검색기능 추가
			String param = request.getParameter("nav_search");
			info.setParam(param);			
			
			//히트 게시물 리스트를 가져온다
			List<HitPostDto> postListDto = postDao.getPostFromPostList(info);			
			
			//HTML 태그 제거
			for(int i=0; i<postListDto.size(); i++) {
				
				//태그 제거
				textByContent= postListDto.get(i).getContent().replaceAll(reg, "");
				
				//공백 하나로 변환
				textByContent = textByContent.trim().replaceAll("&nbsp;", "");

				//결과 셋팅
				postListDto.get(i).setContent(textByContent);
			}
	
			

			
			//포스트 리스트 셋팅
			request.setAttribute("postListDto", postListDto);
		}
		
	
		//페이지 정보 셋팅
		request.setAttribute("info", info);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "mainPostList");
		
		//로그남기기
		TestLog.getSession().logging(request);		
						
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}
	
	@RequestMapping("/catePostList")
	public ModelAndView catePostList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 받기
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
		PageInfoProcess.getInstance().setPageBlock(3);
		
		//조건검색
		String select = request.getParameter("select");
		String keyword = request.getParameter("keyword");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category_id", category_id);
		map.put("select", select);
		map.put("keyword", keyword);
		
		//페이지 정보 갱신
		PageInfoDto info = null;
		if(keyword == null || keyword.equals("")) { //검색어가 없으면 전체 출력
			info = PageInfoProcess.getInstance().process(postDao.getCatePostCount(category_id), request.getParameter("pageNum"));
		}else {	//검색어가 있으면 검색어들만 출력
			info = PageInfoProcess.getInstance().process(postDao.getSearchCatePostCount(map), request.getParameter("pageNum"));
		}
		
		if(info.getCnt()>0) {
			
			//변수 설정
			String textByContent = null;
			String reg = "<[^>]*>";
			
			//맵 셋팅
			Map<String, Object> mapp = new HashMap<String, Object>();
			mapp.put("category_id", category_id);
			mapp.put("start", info.getStart());
			mapp.put("end", info.getEnd());
			mapp.put("select", select);
			mapp.put("keyword", keyword);
			
			List<HitPostDto> catePostListDto = null; 
			if(keyword == null || keyword.equals("")) { //검색어가 없으면 전체 출력
				catePostListDto = postDao.getCatePostFromPostList(mapp);
			}else {	//검색어가 있으면 검색어들만 출력
				catePostListDto = postDao.getSearchCatePostFromPostList(mapp);
			}
			//HTML 태그 제거
			for(int i=0; i<catePostListDto.size(); i++) {
				
				//태그 제거
				textByContent= catePostListDto.get(i).getContent().replaceAll(reg, "");
				
				//공백 하나로 변환
				textByContent = textByContent.trim().replaceAll("&nbsp;", "");

				//결과 셋팅
				catePostListDto.get(i).setContent(textByContent);
			}
			
			//카테고리 포스트 리스트 셋팅
			request.setAttribute("catePostListDto", catePostListDto);
		}
		//검색어
		request.setAttribute("keyword", keyword);
		
		//페이지 정보 셋팅
		request.setAttribute("info", info);
		
		//카테고리 아이디 셋팅
		request.setAttribute("category_id", category_id);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "catePostList");
		
		//로그남기기
		TestLog.getSession().logging(request);	
				
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}	

	//게시글 콘텐트
	@RequestMapping("/postContent")
	public ModelAndView postContent(HttpServletRequest request, HttpServletResponse response) {
		
		//변수 설정
		String textByContent = null;
		String reg = "<[^>]*>";
		
		//파라미터 받기	
		int post_id = Integer.parseInt(request.getParameter("post_id"));
			
		//게시글 페이지 정보 얻기
		PostContentDto postContentDto = postDao.getPostContentFromContent(post_id);
		
		//게시글 페이지 정보 셋팅
		request.setAttribute("postContentDto", postContentDto);
		
		//제품 ID 얻기
		int product_id = postContentDto.getProduct_id();
		
		//게시글에 대한 리뷰 얻기
		List<ReviewDto> postContentReview = reviewDao.getReviewFromContent(product_id);
		
		if(postContentReview != null) {		
			
			//HTML 태그 제거
			for(int i=0; i<postContentReview.size(); i++) {
				
				//태그 제거
				textByContent= postContentReview.get(i).getContent().replaceAll(reg, "");
				
				//공백 하나로 변환
				textByContent = textByContent.trim().replaceAll("&nbsp;", "");

				//결과 셋팅
				postContentReview.get(i).setContent(textByContent);
			}
		}
			
		//게시글에 대한 리뷰 셋팅
		request.setAttribute("postContentReview", postContentReview);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "postContent");
		
		//로그남기기
		TestLog.getSession().logging(request);
			
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}	
	
	
	//신청하기
	@RequestMapping("/applyPro")
	public ModelAndView applyPro(HttpServletRequest request, HttpServletResponse response) {
		
		//결과 셋팅
		int result = 0;
		
		//세션 받기
		String mem_id = (String) request.getSession().getAttribute("member_id");
		
		//파라미터 받기
		int amount = Integer.parseInt(request.getParameter("amount"));
		int member_id = Integer.parseInt(mem_id);
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		//맵셋팅
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("member_id", member_id);
		map.put("post_id", post_id);
		map.put("amount", amount);
		
		//게시글의 신청 정보를 가져온다
		int curamount = postDao.getCurrentamountFromApply(post_id);
		int minamount = postDao.getMinamountFromApply(post_id);
		int amountCheck = minamount-curamount-amount;
			
		// 진행 수량 이상으로 신청시 신청 막는 방어코드
		if(amountCheck < 0) {
			// 진행 수량보다 초과되는 경우
			result = -1;			
		} else {
			result = applicationDao.registerAppFromPostContent(map);
			if(result==1) {
				int resultUpdate = applicationDao.updateAppFromPostPostContent(map);
				request.setAttribute("resultUpdate", resultUpdate);
			}
		}
				
		//결과 셋팅
		request.setAttribute("result", result);
		
		//로그남기기
		TestLog.getSession().logging(request);
		
		return new ModelAndView("user/applyPro");
	}	
	
	//종료된 게시글 리스트 보기
	@RequestMapping("/endPostList.do")
	public ModelAndView endPostList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
		PageInfoProcess.getInstance().setPageBlock(3);
		
		//조건검색
		String select = request.getParameter("select");
		String keyword = request.getParameter("keyword");		
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("select", select);
		map.put("keyword", keyword);
		
		//페이지 정보 갱신
		PageInfoDto info = null;
		if(keyword == null || keyword.equals("")) { //검색어가 없으면 전체 출력
			info = PageInfoProcess.getInstance().process(postDao.getFinishedPostCount(), request.getParameter("pageNum"));
		}else {	//검색어가 있으면 검색어들만 출력
			info = PageInfoProcess.getInstance().process(postDao.getSearchFinishedPostCount(map), request.getParameter("pageNum"));			
		}
				
		//종료된 게시글들이 존재하면
		if(info.getCnt()>0) {
			
			//종료된 게시글들 가져오기
			List<ManagerPostListDto> posts = null;
			if(keyword == null || keyword.equals("")) { //검색어가 없으면 전체 출력
				posts = postDao.getFinishedPostRangeList(info);
			}else {	//검색어가 있으면 검색어들만 출력
				info.setParam(select);
				info.setParam2(keyword);
				posts = postDao.getSearchFinishedPostRangeList(info);
			}
			
			//게시글들 셋팅
			request.setAttribute("posts",posts);
		}
		//검색어
		request.setAttribute("keyword", keyword);
		
		//페이지 정보 셋팅
		request.setAttribute("info", info);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "endPostList");
		
		//로그남기기
		TestLog.getSession().logging(request);
				
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}

}
