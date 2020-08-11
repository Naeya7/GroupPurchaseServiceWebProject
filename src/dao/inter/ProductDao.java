package dao.inter;

import java.util.List;

import common.PageInfoDto;

import dto.ProductDto;

public interface ProductDao {
	public List<ProductDto> getProductRangeListByProductName(PageInfoDto info);
	public List<ProductDto> getAllProduct();
	public List<ProductDto> getProductRangeList(PageInfoDto range);
	public ProductDto getProductById(int product_id);
	public int insertProduct(ProductDto product);
	public int updateProductChangeImage(ProductDto product);
	public int updateProduct(ProductDto product);
	public int getProductCountByProductName(String product_name);
	public int deleteProduct(int product_id);
	public int getProductCount();
	
}
