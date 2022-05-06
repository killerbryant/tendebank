package tw.com.tendebank.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.tendebank.utils.ConstantUtils;

/**
 * 頁面控制器
 * @author Edison
 *
 */
@Controller
public class PageController {
	
	private static Logger logger = Logger.getLogger(PageController.class);
	
	@RequestMapping("/limit")
	@ResponseBody
	public Map<String, Object> limit(HttpServletRequest request , HttpServletResponse response) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("result", "limit");
    	return hashMap;
	}
	
	@RequestMapping("/limitJsp")
	public String limitJsp() {
    	return "redirect:/limit.jsp";
	}
	
	@RequestMapping("/error")
	public String error(HttpServletRequest request , HttpServletResponse response) {
		return "redirect:/error.jsp";
	}
	
	/*
	 * 登入頁面
	 */
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest request , HttpServletResponse response) {
		return "loginPage";
	}
	
	/*
	 * 關於天地
	 */
	@RequestMapping("/about")
	public String about(HttpServletRequest request , HttpServletResponse response) {
		return "about";
	}
	
	/*
	 * 會員申請頁面
	 */
	@RequestMapping("/member")
	public String member(HttpServletRequest request , HttpServletResponse response) {
		return "member/member";
	}
	
	/*
	 * 密碼查詢
	 */
	@RequestMapping("/queryPassword")
	public String queryPassword(HttpServletRequest request , HttpServletResponse response) {
		return "member/queryPassword";
	}
	
	/*
	 * 神明專區Q&A
	 */
	@RequestMapping("/goGods")
	public String gods(HttpServletRequest request , HttpServletResponse response) {
		request.setAttribute("pageName","gods");
		return "qanda/qanda";
	}
	
	/*
	 * 祖先專區Q＆A
	 */
	@RequestMapping("/goAncestors")
	public String ancestors(HttpServletRequest request , HttpServletResponse response) {
		request.setAttribute("pageName","ancestors");
		return "qanda/qanda";
	}
	
	/*
	 * 產品專區
	 */
	@RequestMapping("/goProduct")
	public String goProduct(HttpServletRequest request , HttpServletResponse response) {
		String productType = request.getParameter("productType");
		request.setAttribute("productType", productType);
		return "product/product";
	}
	
	/*
	 * 產品專區查詢
	 */
	@RequestMapping("/searchProduct")
	public String searchProduct(HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException {
		String productKeyword = new String(request.getParameter("keywords").getBytes("ISO-8859-1"), "UTF-8");
		logger.debug("keyword = " + productKeyword);
		request.setAttribute("keywords", productKeyword);
		return "product/product";
	}
	
	/*
	 * 購物車清單
	 */
	@RequestMapping("/goShoppingCartItem")
	public String goShoppingCartItem(HttpServletRequest request , HttpServletResponse response) {
		logger.debug("進入購物車清單");
		return "shopping/cart";
	}
	
	/*
	 * 購物車查詢
	 */
	@RequestMapping("/goShoppingCartOrder")
	public String goShoppingCartOrder(HttpServletRequest request , HttpServletResponse response) {
		logger.debug("進入購物車查詢");
		return "shopping/orderCart";
	}
	
	/*
	 * 訂單查詢
	 */
	@RequestMapping("/goHistoryOrder")
	public String goHistoryOrder(HttpServletRequest request , HttpServletResponse response) {
		return "forward:/goMemberOrder.do";
	}
	
	/*
	 * 管理員畫面
	 */
	@RequestMapping("/admin")
	public String admin(){
		logger.debug("管理員進入Admin");
		return "redirect:/admin.jsp";
	}
	
	/*
	 * 管理員畫面
	 */
	@RequestMapping("/manage")
	public String manage(){
		logger.debug("管理員進入管理畫面");
		return "manage/index";
	}
	
	/*
	 * 本月未出貨訂單
	 */
	@RequestMapping("/goManageOrdersByNotDeliver")
	public String manageOrdersByNotDeliver(HttpServletRequest request , HttpServletResponse response){
		logger.debug("管理員進入未出貨畫面");
		request.setAttribute("manageOrderStatus", ConstantUtils.TENDEBANK_N);
		return "orders/manageOrders";
	}
	
	/*
	 * 本月已出貨訂單
	 */
	@RequestMapping("/goManageOrdersByDeliver")
	public String manageOrdersByDeliver(HttpServletRequest request , HttpServletResponse response){
		logger.debug("管理員進入已出貨畫面");
		request.setAttribute("manageOrderStatus", ConstantUtils.TENDEBANK_Y);
		return "orders/manageOrders";
	}
	
	/*
	 * 歷史未出貨訂單
	 */
	@RequestMapping("/goManageHistoryOrdersByNotDeliver")
	public String manageHistoryOrdersByNotDeliver(HttpServletRequest request , HttpServletResponse response){
		logger.debug("管理員進入未出貨畫面");
		request.setAttribute("manageOrderStatus", ConstantUtils.TENDEBANK_N);
		return "orders/manageHistoryOrders";
	}
	
	/*
	 * 歷史已出貨訂單
	 */
	@RequestMapping("/goManageHistoryOrdersByDeliver")
	public String manageHistoryOrdersByDeliver(HttpServletRequest request , HttpServletResponse response){
		logger.debug("管理員進入已出貨畫面");
		request.setAttribute("manageOrderStatus", ConstantUtils.TENDEBANK_Y);
		return "orders/manageHistoryOrders";
	}
}
