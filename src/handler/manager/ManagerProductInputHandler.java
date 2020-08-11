package handler.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CreateThumbnail;
import common.UploadPath;
import dao.inter.ProductDao;
import dto.CategoryDto;
import dto.ProductDto;
import mybatis.SqlMapClient;

@Controller
public class ManagerProductInputHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="productDao")
	ProductDao productDao;
	
	@Resource(name="uploadPath")
	UploadPath uploadPath;

	//SQL 세션 
	SqlSession session = SqlMapClient.getSession();
	
	//멀티 요청 
	MultipartRequest multi = null;
	
	//제품 등록 폼
	@RequestMapping("/productForm.do")
	public ModelAndView productForm(HttpServletRequest request, HttpServletResponse response) {
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "productForm");
		
		//탬플릿 페이지로
		return new ModelAndView("manager/managerPage");
	}
	
	//카테고리 선택 폼 열기
	@RequestMapping("/pickCtegory.do")
	public ModelAndView pickCtegory(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String control = request.getParameter("control");
		
		//카테고리 리스트 가져오기
		List<CategoryDto> categoryDtos = session.selectList("manager.getAllCatroty");
		
		//컨트롤 셋팅(등록,수정)
		request.setAttribute("control", control);
		
		//카테고리들 셋팅
		request.setAttribute("categoryDtos", categoryDtos);
		
		//카테고리 폼으로 이동
		return new ModelAndView("manager/pickCtegory");
	}

	//제품 등록하기
	@RequestMapping("/productInputPro.do")
	public ModelAndView productPro(HttpServletRequest request, HttpServletResponse response) {
		
		//저장소 경로
		String imagePath = uploadPath.getImagePath();
		String thumbImagePath = uploadPath.getThumbImagePath();
		
		//이미지 호출 경로
		String imageCallPath = uploadPath.getImageCallPath();
		String thumbImageCallPath = uploadPath.getThumbImageCallPath();
		
		//폴더생성(없다면)
		new File (imagePath).mkdir();
		new File (thumbImagePath).mkdir();
		
		try {
			
			//이미지 생성
			multi = new MultipartRequest(request, imagePath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
			
			//생성된 이미지 이름
			String imgName = multi.getFilesystemName("image");
			
			//썸네일 생성
			String thumbName =  CreateThumbnail.getSession().create(imagePath, imgName, thumbImagePath);
			
			//빈 셋팅
			ProductDto product = new ProductDto();
			product.setProduct_name(multi.getParameter("product_name"));
			product.setProduct_model(multi.getParameter("product_model"));
			product.setCategory_id(Integer.parseInt(multi.getParameter("category_id")));
			product.setImage(imageCallPath + imgName);
			product.setThumbnail(thumbImageCallPath + thumbName);
			product.setProduct_status("0");
			
			//제품 등록
			int result = productDao.insertProduct(product);
			
			//결과 반환
			request.setAttribute("result", result);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//처리 페이지로 이동
		return new ModelAndView("manager/productInputPro");
	}

	
	
}
