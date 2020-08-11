package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import common.UploadPath;
import dao.ApplicationDaoImpl;
import dao.MemberDaoImpl;
import dao.NotificationDaoImpl;
import dao.PaymentDaoImpl;
import dao.PostDaoImpl;
import dao.ProductDaoImpl;
import dao.ReplyDaoImpl;
import dao.ReviewDaoImpl;
import dao.inter.ApplicationDao;
import dao.inter.MemberDao;
import dao.inter.NotificationDao;
import dao.inter.PaymentDao;
import dao.inter.PostDao;
import dao.inter.ProductDao;
import dao.inter.ReplyDao;
import dao.inter.ReviewDao;
import log.TestLog;

@Configuration
public class CreateBean {
	
	@Bean
	public ViewResolver viewResolver(){
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public UploadPath uploadPath() {
		UploadPath uploadPath = new UploadPath();
		//저장소 path
		uploadPath.setImagePath("C:\\Expert Java\\imageBase\\image");
		uploadPath.setThumbImagePath("C:\\Expert Java\\imageBase\\thumbnail");
		uploadPath.setEditorImagePath("C:\\Expert Java\\imageBase\\editor");
		
		//불러오기 path
		uploadPath.setImageCallPath( "http://localhost:8080/MyProject/imageBase/image/");
		uploadPath.setThumbImageCallPath("http://localhost:8080/MyProject/imageBase/thumbnail/");
		uploadPath.setEditorImageCallPath("http://localhost:8080/MyProject/imageBase/editor/");
		return uploadPath;
	}
	
	@Bean
	public ApplicationDao applicationDao() {
		return new ApplicationDaoImpl();
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDaoImpl();
	}
	
	@Bean
	public NotificationDao notificationDao() {
		return new NotificationDaoImpl();
	}
	
	@Bean
	public PaymentDao paymentDao() {
		return new PaymentDaoImpl();
	}
	
	@Bean
	public PostDao postDao() {
		return new PostDaoImpl();
	}
	
	@Bean
	public ProductDao productDao() {
		return new ProductDaoImpl();
	}
	
	@Bean
	public ReplyDao replyDao() {
		return new ReplyDaoImpl();
	}
	
	@Bean
	public ReviewDao reviewDao() {
		return new ReviewDaoImpl();
	}
	
	@Bean
	public TestLog testLog() {
		return new TestLog();
	}
	
	
}
