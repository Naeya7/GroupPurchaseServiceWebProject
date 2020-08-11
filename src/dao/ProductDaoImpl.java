package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.ProductDao;
import dto.ProductDto;
import mybatis.SqlMapClient;

public class ProductDaoImpl implements ProductDao {
	//세션 가져오기
	SqlSession session = SqlMapClient.getSession();
	
	//제품 등록
	public int insertProduct(ProductDto product) {		
		return	session.insert("manager.insertProduct", product);
	}
	
	//모든 제품 리스트
	public List<ProductDto> getAllProduct() {
		return session.selectList("manager.getAllProduct");
	}

	//전체 제품 개수
	public int getProductCount() {
		return session.selectOne("manager.getProductCount");
	}
	
	//전체 제품 리스트(범위)
	public List<ProductDto> getProductRangeList(PageInfoDto range) {
		return session.selectList("manager.getProductRangeList", range);
	}
	
	//ID로 제품 하나 얻기
	public ProductDto getProductById(int product_id) {
		return session.selectOne("manager.getProductById", product_id);
	}
	
	//이미지 빼고 제품 업데이트 하기
	public int updateProduct(ProductDto product) {
		return session.update("manager.updateProduct", product);
	}
	
	//이미지 포함 제품 업데이트 하기
	public int updateProductChangeImage(ProductDto product) {
		return session.update("manager.updateProductChangeImage", product);
	}
	
	//ID로 제품 삭제하기
	public int deleteProduct(int product_id) {
		return session.delete("manager.deleteProduct", product_id);
	}
	
	//제품이름이 포함된 제품 개수
	public int getProductCountByProductName(String product_name) {
		return session.selectOne("manager.getProductCountByProductName", product_name);
	}
	
	//제품 이름이 포함된 제품리스트 가져오기
	public List<ProductDto> getProductRangeListByProductName(PageInfoDto info) {
		return session.selectList("manager.getProductRangeListByProductName", info);
	}

}
