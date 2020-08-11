package handler.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.PageInfoDto;
import common.PageInfoProcess;
import common.UploadPath;
import dao.inter.PostDao;
import dao.inter.ProductDao;
import dto.PostDto;
import dto.ProductDto;


@Controller
public class ManagerPostInputHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="productDao")
	ProductDao productDao;
	
	@Resource(name="postDao")
	PostDao postDao;
	
	@Resource(name="uploadPath")
	UploadPath uploadPath; //업로드 경로
	
	//이미지 업로드용 멀티 요청
	MultipartRequest multi = null;
	
	//게시글 등록 폼
	@RequestMapping("/postForm.do")
	public ModelAndView postForm(HttpServletRequest request, HttpServletResponse response) {
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "postForm");
		
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	
	//제품명으로 검색하기
	@RequestMapping("/serchProductForm.do")
	public ModelAndView serchProductForm(HttpServletRequest request, HttpServletResponse response){
		
		//검색어 받기
		String product_name = request.getParameter("product_name");
		
		//페이지 사이즈 셋팅
		PageInfoProcess.getInstance().setPageSize(5);
		
		//페이지 정보
		PageInfoDto info = null;
		
		//검색어 미입력
		if(product_name == null || product_name.equals("")) {
			
			//페이지 정보 갱신
			info = PageInfoProcess.getInstance().process(productDao.getProductCount(), request.getParameter("pageNum"));
			
			if(info.getCnt()>0) {
				
				//전체 제품 리스트 가져오기
				List<ProductDto> products = productDao.getProductRangeList(info);
				
				//제품 셋팅
				request.setAttribute("products", products);
			}	
		//검색어 입력
		}else {
			//페이지 정보 갱신
			info = PageInfoProcess.getInstance().process(productDao.getProductCountByProductName(product_name), request.getParameter("pageNum"));
			
			if(info.getCnt()>0) {
				
				//검색어 셋팅
				info.setParam(product_name);
				
				//검색된 제품 리스트 가져오기
				List<ProductDto> products = productDao.getProductRangeListByProductName(info);
				
				//제품 셋팅
				request.setAttribute("products", products);
			}
		}
		
		//페이지 정보 셋팅
		request.setAttribute("info", info);
		
		//검색 폼으로 이동
		return new ModelAndView("manager/serchProductForm");
	}
	
	//게시글 등록
	@RequestMapping("/postInputPro.do")
	public ModelAndView postInputPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//한글 처리
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int minimum_amount = Integer.parseInt(request.getParameter("minimum_amount"));
		int default_price = Integer.parseInt(request.getParameter("default_price"));
		String post_title = request.getParameter("post_title");
		String content = request.getParameter("content");
		
		//빈 셋팅
		PostDto post = new PostDto();
		post.setProduct_id(product_id);
		post.setMinimum_amount(minimum_amount);
		post.setDefault_price(default_price);
		post.setPost_title(post_title);		
		post.setContent(content);
		
		//게시글 등록하기
		int result = postDao.insertPost(post);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/postInputPro");
	}
	
	//CKEDITOR4 이미지 업로드
	@RequestMapping("/fileupload.do")
	public String fileupload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//한글 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//제이슨 출력용 
		PrintWriter printWriter = null;
		
		//저장소 경로
		String imagePath = uploadPath.getEditorImagePath();
		
		//이미지 호출 경로
		String imageCallPath = uploadPath.getEditorImageCallPath();
		
		try {
			
			//이미지 생성
			multi = new MultipartRequest(request, imagePath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
			
			//생성된 이미지 이름
			String imgName = multi.getFilesystemName("upload");	
		
			//URL
			String url = imageCallPath + imgName;
			
			//Writer 설정
			printWriter = response.getWriter();
			
			//맵 셋팅
			Map<String, String> map = new HashMap<String, String>();
			map.put("uploaded", "1");
			map.put("fileName", imgName);
			map.put("url", url);
			
			//제이슨 셋팅
			JSONObject json = new JSONObject(map);
			
			//에디터로 제이슨 출력
			printWriter.println(json);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/crawlingPricePro.do")
	public ModelAndView crawlingPriceForm(HttpServletRequest request, HttpServletResponse response) {
		
		//가격 처리
		String product_model = request.getParameter("product_model");
		int result = 0;
	
		try {
			Document dc=Jsoup.connect("https://search.shopping.naver.com/search/all?query=" 
						+ product_model + "&cat_id=&frm=NVSHATC").timeout(6000).get();
			
			if(dc != null) {
				String default_price = dc.select("span.price_num__2WUXn").get(0).text();	
				default_price = default_price.replaceAll(",", "");
				default_price = default_price.replaceAll("원", "");
				request.setAttribute("default_price", default_price);
				result = 1;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/crawlingPricePro");
	}
	
}
