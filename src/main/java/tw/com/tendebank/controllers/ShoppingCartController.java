package tw.com.tendebank.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.Orders;
import tw.com.tendebank.beans.OrdersDetail;
import tw.com.tendebank.beans.Product;
import tw.com.tendebank.services.CommonService;
import tw.com.tendebank.services.ShoppingCartService;
import tw.com.tendebank.utils.BeanUtils;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.DateJsonValueProcessor;

/**
 * 購物車控制器
 * @author Edison
 *
 */
@Controller
public class ShoppingCartController {
	private static Logger logger = Logger.getLogger(ShoppingCartController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping(value = "/getShoppingCartCount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getShoppingCartCount(HttpServletRequest request , HttpServletResponse response) {
		
		int itemCount = 0;
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		// Session購物車清單有資料
		if(!ObjectUtils.isEmpty(orders)) {
			itemCount = orders.size();
		}
		
		hashMap.put("productTotalCount", itemCount);
		
		return hashMap;
	}
	
	@RequestMapping(value = "/getShoppingCartOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getShoppingCartOrder(HttpServletRequest request , HttpServletResponse response) {
		
		String msg = "購物車內無任何商品";
		int itemCount = 0;
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		// Session購物車清單有資料
		if(!ObjectUtils.isEmpty(orders)) {
			// HashMap轉List
			List<Product> prodList = hashMapSortToList(orders);
					
			request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, orders);
			request.setAttribute("productTotalCount", orders.size());

			hashMap.put("orders", prodList);
			itemCount = prodList.size();
			msg = "目前購物車中共有 " + itemCount + " 項商品";
		}
		
		hashMap.put("productTotalCount", itemCount);
		hashMap.put("orderMsg", msg);
		
		
		return hashMap;
	}
	
	@RequestMapping(value = "/addCartByProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCartByProduct(HttpServletRequest request , HttpServletResponse response, @RequestBody Product product) {
		
		logger.debug("product == " + product.toString());
		
		String productNo = product.getProductNo();
		
		String productCount = (StringUtils.isBlank(product.getProductCount()) || StringUtils.equals("0", product.getProductCount()))?"0":product.getProductCount();
		
		logger.debug("增加的數量 = " + productCount);
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		// Session中無訂單
		if(ObjectUtils.isEmpty(orders)) {
			orders = new HashMap<String, Product>();
			product.setProductCount(productCount);
			logger.debug("New Session Product" + product.toString());
			// 直接塞入product
			orders.put(productNo, product);
		} else {
			logger.debug(orders.size());
			// 取出Product
			Product prod = orders.get(productNo);
			// Product是空的把頁面傳過來的值塞入map
			if(ObjectUtils.isEmpty(prod)) {
				product.setProductCount(productCount);
				orders.put(productNo, product);
				logger.debug("No old Product Session Product" + product.toString());
			} else {
				String oldProductCount = prod.getProductCount();
				prod.setProductCount(String.valueOf(Integer.parseInt(oldProductCount) + Integer.parseInt(productCount)));
				orders.put(productNo, prod);
				logger.debug("Old Session Product" + prod.toString());
			}
		}
		
		request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, orders);
		request.setAttribute("productTotalCount", orders.size());
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("productTotalCount", orders.size());
		logger.debug("將商品加入購物車");
		return hashMap;
	}
	
	@RequestMapping(value = "/updateCartByProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateCartByProduct(HttpServletRequest request , HttpServletResponse response) {
		
		String productNo = request.getParameter("productNo");
		
		String quantity = request.getParameter("quantity");
		
		logger.debug("更新的數量 = " + quantity);
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		String msg = "目前購物車中共有 " + orders.size() + " 項商品";
		
		int itemCount = 0;
		
		// Session中有訂單
		if(!ObjectUtils.isEmpty(orders)) {
			logger.debug(orders.size());
			// 取出Product
			Product prod = orders.get(productNo);
			// Product不是空的把頁面傳過來的值塞入map
			if(!ObjectUtils.isEmpty(prod)) {
				// 更新數量>0才更新，如果<0則移除
				if(Integer.parseInt(quantity) > 0) {
					prod.setProductCount(quantity);
					orders.put(productNo, prod);
				} else {
					orders.remove(productNo);
				}
				
				logger.debug("Old Session Product" + prod.toString());
			}
		}

		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<Product> prodList = hashMapSortToList(orders);
		
		request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, orders);
		request.setAttribute("productTotalCount", orders.size());
		
		if(prodList.size() > 0) {
			hashMap.put("orders", prodList);
			itemCount = prodList.size();
			msg = "目前購物車中共有 " + itemCount + " 項商品";
		}
		
		hashMap.put("productTotalCount", itemCount);
		hashMap.put("orderMsg", msg);
		
		logger.debug("將商品更新數量後加入購物車");
		return hashMap;
	}
	
	@RequestMapping("/deleteShoppingOrderByProductNo")
	public String deleteShoppingOrderByProductNo(HttpServletRequest request , HttpServletResponse response, @RequestBody Product product) {
		logger.debug("刪除購物車Session內容" + product.toString());
		
		String productNo = product.getProductNo();
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		// Session中有訂單
		if(!ObjectUtils.isEmpty(orders)) {
			// 移除Product
			orders.remove(productNo);
		}
		request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, orders);
		return "product/product";
	}
	
	@RequestMapping(value = "/removeOrderByProductNo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeOrderByProductNo(HttpServletRequest request , HttpServletResponse response) {
		
		String msg = "購物車內無任何商品";
		
		int itemCount = 0;
		
		String productNo = request.getParameter("productNo");
		
		HashMap<String, Product> orders = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		// Session購物車清單有資料
		if(!ObjectUtils.isEmpty(orders)) {
			// 移除Product
			orders.remove(productNo);
			
			List<Product> prodList = hashMapSortToList(orders);
			
			request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, orders);
			request.setAttribute("productTotalCount", orders.size());
			
			if(prodList.size() > 0) {
				hashMap.put("orders", prodList);
				itemCount = prodList.size();
				msg = "目前購物車中共有 " + itemCount + " 項商品";
			}
		}

		hashMap.put("productTotalCount", itemCount);
		hashMap.put("orderMsg", msg);
		
		return hashMap;
	}
	
	@RequestMapping("/clearShoppingCartOrder")
	public String clearShoppingCartOrder(HttpServletRequest request , HttpServletResponse response) {
		logger.debug("清空購物車Session內容");
		HashMap<String, Product> orders = new HashMap<String, Product>();
		request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, orders);
		return "product/product";
	}
	
	@RequestMapping("/goMemberOrder")
	public String goMemberOrder(HttpServletRequest request , HttpServletResponse response, Map<String, Object> map) {
		logger.debug("查詢訂單");
		
		Member member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		//初始化工具
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new DateJsonValueProcessor());
		
		// 本月訂單
		List<Orders> ordersList = shoppingCartService.selectMonthOrdersByMemberNo(member.getMemberNo());
		
		JSONArray ordersJson = JSONArray.fromObject(ordersList, jsonConfig);
		logger.debug("JSON Format == " + ordersJson.toString());
		map.put("memberOrdersData", ordersJson);
		
		// 歷史訂單排除當月份
		List<Orders> ordersHistoryList = shoppingCartService.selectHistoryOrdersByMemberNo(member.getMemberNo());
		
		JSONArray ordersHistoryJson = JSONArray.fromObject(ordersHistoryList, jsonConfig);
		logger.debug("JSON Format == " + ordersHistoryList.toString());
		map.put("memberHistoryOrdersData", ordersHistoryJson);
		
		return "shopping/memberOrder";
	}
	
	@RequestMapping("/createMemberOrder")
	public String createMemberOrder(HttpServletRequest request , HttpServletResponse response) {
		logger.debug("購物車Session內容轉資料庫訂單");
		
		String addresseeName = request.getParameter("addresseeName");
		String addresseeTelephone = request.getParameter("addresseeTelephone");
		String addresseeMobile = request.getParameter("addresseeMobile");
		String addresseeAddress = request.getParameter("addresseeAddress");
		String totalPrice = request.getParameter("totalPrice");
		
		Member member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		Date date = new Date();
		
		// 取得訂單單號 年月日+會員編號＋6碼流水號
		String ordersNo = commonService.getOrdersNoByMemberNo(member);
		
		logger.debug("取得訂單單號 = " + ordersNo);
		
		// 訂單主檔
		Orders orders = new Orders();
		orders.setOrderNo(ordersNo);
		orders.setMemberNo(member.getMemberNo());
		orders.setAddressee(addresseeName);
		orders.setAddresseeTelephone(addresseeTelephone);
		orders.setAddresseeMobile(addresseeMobile);
		orders.setAddresseeAddress(addresseeAddress);
		orders.setBuyDate(date);
		orders.setCreateDate(date);
		orders.setOrderStatus(ConstantUtils.TENDEBANK_N);
		orders.setPayStatus(ConstantUtils.TENDEBANK_N);
		orders.setDeliverStatus(ConstantUtils.TENDEBANK_N);
		orders.setOrderTotalprice(Float.parseFloat(totalPrice.replaceAll(",","")));
		orders.setOrderPayprice(Float.parseFloat(totalPrice.replaceAll(",","")));
		
		// Session訂單轉換
		HashMap<String, Product> ordersDetailBySession = (HashMap<String, Product>) request.getSession().getAttribute(ConstantUtils.SHOPPING_CART);
		Iterator iterator = ordersDetailBySession.entrySet().iterator();
        while (iterator.hasNext()) {
             Map.Entry productEntry = (Map.Entry) iterator.next();
             Product product = (Product) productEntry.getValue();
             // 購物資訊轉訂單資訊
             OrdersDetail ordersDetail = BeanUtils.productToOrdersDetail(product, ordersNo);
             orders.getOrdersSet().add(ordersDetail);
        }

        boolean result = shoppingCartService.insert(orders);
        logger.debug("insert result = " + result);
		
		// 訂單儲存完成清空購物車
		HashMap<String, Product> ordersClear = new HashMap<String, Product>();
		request.getSession().setAttribute(ConstantUtils.SHOPPING_CART, ordersClear);
		return null;
	}
	
	@RequestMapping(value = "/updateOrderByBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOrderByBankInfo(HttpServletRequest request , HttpServletResponse response) {
		
		String orderNo = request.getParameter("orderNo");
		String afterFiveNumber = request.getParameter("afterFiveNumber");
		String msg = "訂單匯款資訊更新成功";
		
		logger.debug("Update orderNo = " + orderNo + " 後5碼 = " + afterFiveNumber);
		
		boolean updateResult = shoppingCartService.updateOrderByBankInfo(orderNo, afterFiveNumber);
		
		if(!updateResult) {
			msg = "訂單匯款資訊更新失敗";
		}
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderMsg", msg);
		
		return hashMap;
	}
	
	private List<Product> hashMapSortToList(HashMap<String, Product> orders) {
		// HashMap轉List
		List<Product> prodList = new ArrayList<Product>();
		for (Object key : orders.keySet()) {
			   logger.debug("Key : " + key.toString() + " Value : " + orders.get(key).toString());
			   prodList.add(orders.get(key));
		}
		
		// 排序
		Collections.sort(prodList,
			new Comparator<Product>() {
				public int compare(Product p1, Product p2) {
					return p1.getProductNo().compareTo(p2.getProductNo());
				}
		});
		return prodList;
	}
}
