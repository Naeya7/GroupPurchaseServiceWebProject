package handler.manager;

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
import dao.inter.PostDao;
import dto.PostDto;
import dto.join.ManagerPostListDto;

@Controller
public class ManagerStarPostHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="postDao")
	PostDao postDao;
	
	@Resource(name="applicationDao")
	ApplicationDao applicationDao;
	
	//진행중인 제품 게시글
	@RequestMapping("/starPostList.do")
	public ModelAndView starPostList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(5);
				
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(postDao.getActivedPostCount() ,request.getParameter("pageNum"));
				
		//가공된 게시글들이 있으면
		if(info.getCnt()>0) {
			
			//가공된 게시글 리스트 가져오기
			List<ManagerPostListDto> posts = postDao.getActivedPostRangeList(info);
			
			//가공된 게시글 셋팅
			request.setAttribute("posts",posts);
		}
				
		//페이지 정보 셋팅
		request.setAttribute("info", info);
				
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "starPostList");
				
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	
	//협상 가격 입력 폼 열기
	@RequestMapping("/inputFixedPriceForm.do")
	public ModelAndView inputFixedPriceForm(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String post_id = request.getParameter("post_id");
		
		//그대로 보내기
		request.setAttribute("post_id", post_id);
		
		//가격 입력 폼으로이동
		return new ModelAndView("manager/inputFixedPriceForm");
	}
	
	//협상 가격 변경하기
	@RequestMapping("/inputFixedPricePro.do")
	public ModelAndView inputFixedPricePro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int post_id =  Integer.parseInt(request.getParameter("post_id"));
		int fixed_price = Integer.parseInt(request.getParameter("fixed_price"));
		
		//빈 셋팅
		PostDto post = new PostDto();
		post.setPost_id(post_id);
		post.setFixed_price(fixed_price);
		
		//협상 가격 업데이트
		int result =  postDao.updatePostFixed_price(post);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/inputFixedPricePro");
	}
	
	//게시글 상태창 열기
	@RequestMapping("/setPostStatus.do")
	public ModelAndView setPostStatus(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String post_id = request.getParameter("post_id");
		
		//그대로 넘겨주기
		request.setAttribute("post_id", post_id);
		
		//게시글 상태 페이지로 이동
		return new ModelAndView("manager/setPostStatus");
	}
	
	//상태 업데이트 하기
	@RequestMapping("/setPostStatusPro.do")
	public ModelAndView setPostStatusPro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String post_id = request.getParameter("post_id");
		String post_status = request.getParameter("post_status");
		
		//맵 셋팅
		Map<String, String> map = new HashMap<String, String>();
		map.put("post_id", post_id);
		map.put("post_status", post_status);
		
		//게시글 상태 업데이트 하기
		int result = postDao.updatePostStatus(map);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/setPostStatusPro");
	}
	
	//상태 업데이트 하기
	@RequestMapping("/deletePostPro.do")
	public ModelAndView deletePost(HttpServletRequest request, HttpServletResponse response) {
		
		//결과
		int result = 0;
		
		//파라미터 받기
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		//이미 작성된 신청 정보가 있는지 체크
		int count = applicationDao.getCountApllicationByPostId(post_id);
	
		//없으면 삭제 가능
		if(count == 0) {
			//진행중인 게시글 삭제
			result = postDao.deleteActivePost(post_id);
		}
		
		//0이면 신청이 존재해서 삭제 불가, 1이면 삭제됨
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/deletePostPro");
	}	
}
