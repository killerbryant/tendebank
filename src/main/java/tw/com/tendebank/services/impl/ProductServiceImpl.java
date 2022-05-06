package tw.com.tendebank.services.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.Product;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.ProductService;
import tw.com.tendebank.utils.CommonUtils;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 產品業務邏輯 Service 實作
 * @author Edison
 *
 */
public class ProductServiceImpl implements ProductService {
	
	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Transactional
	@Override
	public Product insert(Product product, MultipartFile file, String pathRoot) {
		boolean result = true;
		Product finalProduct = null;
		try {
			// 取得輸入的商品編號
			String productNo = product.getProductNo();
			
			// 查詢商品編號是否重複
			Product qryProduct = getDaoFactory().gethProductDao().findByKey(productNo);
			
			// 抓新編號
			if(ObjectUtils.isObjNotBlank(qryProduct)){
				String newProdNo = selectMaxProductNo(productNo);
				logger.debug("新的商品編號 = " + newProdNo);
				product.setProductNo(newProdNo);
			}
			
			// 儲存商品至SQL
			getDaoFactory().gethProductDao().insert(product);
			
			// 儲存圖片路徑
        	if(ObjectUtils.isObjNotBlank(file) && !file.isEmpty()){
    	    	// 獲得檔案類型（可以判斷如果不是圖片，禁止上傳）  
    	        String contentType = file.getContentType();
    	        
    	        logger.debug(contentType);
    	        
    	        // 獲得檔尾碼名稱  
    	        String imageName = contentType.substring(contentType.indexOf("/")+1);
    	        
    	        String fileName = file.getOriginalFilename();
    	        
                // 獲得圖片附檔名
                String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
    	        
    	        String path = "img/product/" + product.getProductNo() + "." + extensionName;
    	        
    	        logger.debug("圖檔路徑" + path);
    	        file.transferTo(new File(pathRoot + "/" + path));
    	        product.setImgPath(path);
    	        // 更新SQL中圖片路徑
    	        getDaoFactory().gethProductDao().update(product);
        	}
        	finalProduct = getDaoFactory().gethProductDao().findByKey(product.getProductNo());
		} catch (Exception e) {
			finalProduct = null;
		}
		return finalProduct;
	}

	@Transactional
	@Override
	public String selectMaxProductNo(String productNo) {
		String resultNo = "";
		int maxNum = 0;
		try {
			productNo = productNo.substring(0,1);
			String maxProduct = getDaoFactory().gethProductDao().findByMaxKey(productNo+"%");
			maxNum = Integer.parseInt(maxProduct.substring(1))+1;
			resultNo = productNo + CommonUtils.addZeroForStr(String.valueOf(maxNum), ConstantUtils.PRODUCT_NUMBER_LENGTH);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return resultNo;
	}

	@Override
	public List<Product> selectProductByType(String productType,Member member) {
		List<Product> productList = null;
		try {
			productList = getDaoFactory().gethProductDao().selectByType(productType, member);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return productList;
	}

	@Override
	public List<Product> selectProductByHot(Member member) {
		List<Product> productList = null;
		try {
			productList = getDaoFactory().gethProductDao().selectByCartStatus(member, ConstantUtils.CART_STATUS_HOT, 6);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return productList;
	}
	
	@Override
	public List<Product> selectProductByNew(Member member) {
		List<Product> productList = null;
		try {
			productList = getDaoFactory().gethProductDao().selectByCartStatus(member, ConstantUtils.CART_STATUS_NEW, 6);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return productList;
	}

	@Transactional
	@Override
	public List<Product> selectProductByKeyword(Member member, String keyword) {
		List<Product> productList = null;
		try {
			productList = getDaoFactory().gethProductDao().selectProductByKeyword(member, keyword);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return productList;
	}
}
