package tw.com.tendebank.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.Product;

/**
 * 產品業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface ProductService {
	
	public String selectMaxProductNo(String productNo);

	public Product insert(Product product, MultipartFile file, String pathRoot);
	
	public List<Product> selectProductByType(String productType, Member member);
	
	public List<Product> selectProductByHot(Member member);

	public List<Product> selectProductByNew(Member member);

	public List<Product> selectProductByKeyword(Member member, String keyword);

	
	
}
