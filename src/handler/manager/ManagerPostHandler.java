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
import dao.inter.PostDao;
import dto.PostDto;
import dto.join.ManagerModifyPostDto;
import dto.join.ManagerPostListDto;

@Controller
public class ManagerPostHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="postDao")
	PostDao postDao;
	
	//종료된 게시글 리스트 보기
	@RequestMapping("/postList.do")
	public ModelAndView postList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
				
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(postDao.getFinishedPostCount(), request.getParameter("pageNum"));
				
		//종료된 게시글들이 존재하면
		if(info.getCnt()>0) {
			
			//종료된 게시글들 가져오기
			List<ManagerPostListDto> posts = postDao.getFinishedPostRangeList(info);
			
			//게시글들 셋팅
			request.setAttribute("posts",posts);
		}
				
		//페이지 정보 셋팅
		request.setAttribute("info", info);
				
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "postList");
				
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	
	//게시글 수정 폼
	@RequestMapping("/modifyPostForm.do")
	public ModelAndView modifyPostForm(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		//게시글 수정 폼에 사용될 정보들 받기
		ManagerModifyPostDto modifyPost = postDao.getModifyPostByPostId(post_id);
		
		//받은 정보들 셋팅
		request.setAttribute("modifyPost", modifyPost);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "modifyPostForm");
		
		//메인 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	
	//게시글 수정
	@RequestMapping("/modifyPostPro.do")
	public ModelAndView modifyPostPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int minimum_amount = Integer.parseInt(request.getParameter("minimum_amount"));
		int default_price = Integer.parseInt(request.getParameter("default_price"));
		String post_title = request.getParameter("post_title");
		String content = request.getParameter("content");
		int current_amount = Integer.parseInt(request.getParameter("current_amount"));
		
		//빈 셋팅
		PostDto post = new PostDto();
		post.setPost_id(post_id);
		post.setProduct_id(product_id);
		post.setPost_title(post_title);
		post.setMinimum_amount(minimum_amount);
		post.setDefault_price(default_price);
		post.setContent(content);
		post.setCurrent_amount(current_amount);
		
		if(post.getCurrent_amount() <= post.getMinimum_amount() ) {
			//게시글 업데이트 하기
			int result = postDao.updatePost(post);
			//결과 셋팅 
			request.setAttribute("result", result);
		} 
		
		//처리 페이지로 이동
		return new ModelAndView("manager/modifyPostPro");
	}
	
	public ModelAndView reRegisterPostPro(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView();
	}

	public ModelAndView crawlingPriceForm(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView();
	}
	public ModelAndView crawlingPricePro(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView();
	}
	
}
