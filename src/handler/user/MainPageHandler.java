package handler.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.inter.PostDao;
import dto.join.HitPostDto;
import dto.join.MainPagePostDto;


@Controller
public class MainPageHandler {
	
	//스프링 빈 객체 할당
	@Resource
	private PostDao postDao;
	
	//메인 페이지
	@RequestMapping("/mainTemplate.do")
	public ModelAndView mainTemplate(HttpServletRequest request, HttpServletResponse respons) {
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "mainPage");
		
		//메인 탬플릿으로 이동 
		return new ModelAndView("user/mainTemplate");
	}
	
	//메인 페이지
	@RequestMapping("/mainPage.do")
	public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse respons) {
		
		//히트 게시물 가져오기
		List<HitPostDto> hitPostDto = postDao.getHitPostFromMain();
		
		//최근에 업로드된 게시물 가져오기
		List<MainPagePostDto> mainPagePostDto = postDao.getRecentlyPostFromMain();
		
		//히트 게시글 셋팅(5개)
		request.setAttribute("hitPostDto", hitPostDto);
		
		//최근 게시글 셋팅(6개)
		request.setAttribute("mainPagePostDto", mainPagePostDto);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "mainPage");
		
		//메인페이지로 가기
		return new ModelAndView("user/mainTemplate");
	}
}
