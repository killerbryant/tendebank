package tw.com.tendebank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.Product;
import tw.com.tendebank.services.ProductService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 產品控制器
 * @author Edison
 *
 */
@Controller
public class ProductController {
	private static Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/addProduct")
	public String addProduct(){
    	return "product/addProduct";
    }
    
	@RequestMapping(value="/saveProduct", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveProduct(HttpServletRequest request , HttpServletResponse response,@RequestPart(value="product") Product product ,@RequestParam(value="file",required=false) MultipartFile file) throws Exception{
		
		String pathRoot = request.getSession().getServletContext().getRealPath("");
		
		String resultMsg = "";
		
		boolean result = true;
		
		// 新增商品回傳資料庫中新增的商品資料
		Product resultProd = productService.insert(product, file, pathRoot);		
		
		// 新增商品最後為null表新增失敗
        if(ObjectUtils.isObjBlank(resultProd)) {
    		resultMsg = "商品編號:" + product.getProductNo() + "新增失敗！！";
    		result = false;
    	} else {
    		resultMsg = "商品編號:" + resultProd.getProductNo() + "新增成功";
    	}
        
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("successMsg", resultMsg);
    	hashMap.put("result", result);
    	
    	return hashMap;
    }
	
	@RequestMapping("/getProductByType")
	@ResponseBody
	public Map<String, Object> getProductByType(HttpServletRequest request , HttpServletResponse response) {
		String productType = request.getParameter("productType");
		logger.debug("取得參數" + productType);
		Member member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		List<Product> resultList = productService.selectProductByType(productType, member);
		logger.debug("結果大小" + resultList.size());
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("products", resultList);
		hashMap.put("totalCount", resultList.size());
		hashMap.put("productTotalCount", ObjectUtils.isObjNotBlank(orders)?orders.size():0);
		return hashMap;
	}
	
	@RequestMapping("/getProductByHot")
	@ResponseBody
	public Map<String, Object> getProductByHot(HttpServletRequest request , HttpServletResponse response) {
		
		Member member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		List<Product> resultList = productService.selectProductByHot(member);
		logger.debug("結果大小" + resultList.size());
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("products", resultList);
		return hashMap;
	}
	
	@RequestMapping("/getProductByNew")
	@ResponseBody
	public Map<String, Object> getProductByNew(HttpServletRequest request , HttpServletResponse response) {
		
		Member member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		List<Product> resultList = productService.selectProductByNew(member);
		logger.debug("結果大小" + resultList.size());
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		List<Product> firstList = resultList.subList(0, 3);
		
		List<Product> secondList = resultList.subList(3, 6);
		logger.debug("first List = " + firstList.size());
		logger.debug("secondList List = " + secondList.size());
		hashMap.put("firstList", firstList);
		hashMap.put("secondList", secondList);
		return hashMap;
	}
	
	@RequestMapping("/getProductBykeyword")
	@ResponseBody
	public Map<String, Object> searchProduct(HttpServletRequest request , HttpServletResponse response) {
		String keyword = request.getParameter("keyword");
		logger.debug("取得參數" + keyword);
		
		Member member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		List<Product> resultList = productService.selectProductByKeyword(member, keyword);
		logger.debug("結果大小" + resultList.size());
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("products", resultList);
		hashMap.put("totalCount", resultList.size());
		hashMap.put("productTotalCount", ObjectUtils.isObjNotBlank(orders)?orders.size():0);
		return hashMap;
	}
}
