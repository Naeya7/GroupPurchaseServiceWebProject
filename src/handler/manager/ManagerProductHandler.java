package handler.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CreateThumbnail;
import common.PageInfoDto;
import common.PageInfoProcess;
import common.UploadPath;
import dao.inter.PostDao;
import dao.inter.ProductDao;
import dto.ProductDto;

@Controller
public class ManagerProductHandler {
	
	//스프링 빈 객체 사용
	@Resource(name="productDao")
	ProductDao productDao;
	
	@Resource(name="postDao")
	PostDao postDao;

	@Resource(name="uploadPath")
	UploadPath uploadPath; //업로드 경로
	
	//업로드를 위한 멀티 요청
	MultipartRequest multi = null;	
	
	//제품 리스트
	@RequestMapping("productList.do")
	public ModelAndView productList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 셋팅
		PageInfoProcess.getInstance().setPageSize(5);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(productDao.getProductCount(), request.getParameter("pageNum"));
		
		//제품리스트가 있으면
		if(info.getCnt()>0) {
			
			//제품 리스트 가져오기
			List<ProductDto> products = productDao.getProductRangeList(info);
			
			//제품들 셋팅
			request.setAttribute("products",products);
		}
		
		//페이지 정보 셋팅
		request.setAttribute("info", info);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "productList");
		
		//탬플리 페이지로 가기
		return new ModelAndView("manager/managerPage");
	}
	
	//제품 수정 폼
	@RequestMapping("/modifyProductForm.do")
	public ModelAndView modifyProductForm(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		
		//ID에 따른 제품 얻기
		ProductDto product = productDao.getProductById(product_id);
		
		//제품 셋팅
		request.setAttribute("product", product);
		
		//모듈 컨트롤 셋팅
		request.setAttribute("pageControl", "modifyProductForm");
		
		//탬플릿 페이지로 가기
		return new ModelAndView("manager/managerPage");
	}
	
	//제품 수정
	@RequestMapping("/modifyProductPro.do")
	public ModelAndView modifyProductPro(HttpServletRequest request, HttpServletResponse response) {
		
		//저장소 경로
		String imagePath = uploadPath.getImagePath();
		String thumbImagePath = uploadPath.getThumbImagePath();
		
		//이미지 호출 경로
		String imageCallPath = uploadPath.getImageCallPath();
		String thumbImageCallPath = uploadPath.getThumbImageCallPath();
		
		//결과
		int result = 0;
		
		//폴더생성(없다면)
		new File (imagePath).mkdir();
		new File (thumbImagePath).mkdir();
		
		try {
			
			//이미지 생성
			multi = new MultipartRequest(request, imagePath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
			
			//빈생성
			ProductDto product = new ProductDto();
			
			//생성된 이미지 이름
			String imgName = multi.getFilesystemName("image");
			
			//이미지가 생성 되었다면 썸네일도 만들어 준다
			if(imgName != null) {
				//썸네일 생성
				String thumbName =  CreateThumbnail.getSession().create(imagePath, imgName, thumbImagePath);
				
				//생성된 이미지,썸네일 셋팅
				product.setImage(imageCallPath + imgName);
				product.setThumbnail(thumbImageCallPath + thumbName);
			}
			
			//나머지 빈 셋팅
			product.setProduct_id(Integer.parseInt(multi.getParameter("product_id")));
			product.setProduct_name(multi.getParameter("product_name"));
			product.setProduct_model(multi.getParameter("product_model"));
			product.setCategory_id(Integer.parseInt(multi.getParameter("category_id")));
			
			
			//(Not Null, 이미지가 있는 경우) (Null, 이미지가 없는경우)
			if(imgName != null) {
				//이미지 포함해서 수정
				result = productDao.updateProductChangeImage(product);
			}else {
				//이미지 그대로 사용해서 수정
				result = productDao.updateProduct(product);
			}
			
			//결과 반환
			request.setAttribute("result", result);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//처피 페이지로 이동
		return new ModelAndView("manager/modifyProductPro");		
	}
	

	//제품 삭제
	@RequestMapping("/deleteProductPro.do")
	public ModelAndView deleteProductPro(HttpServletRequest request, HttpServletResponse response) {
		
		//결과
		int result = 0;
		
		//파라미터 받기
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		
		//이미 작성된 게시글 체크
		int count = postDao.getPostCountByProductId(product_id);
		
		//작성된 게시글이 없는 경우에만
		if(count == 0) {
			result = productDao.deleteProduct(product_id);
		}
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/deleteProductPro");
	}
}
