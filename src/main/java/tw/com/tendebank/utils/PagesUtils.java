package tw.com.tendebank.utils;

import org.apache.log4j.Logger;

/**
 * Struts2 Utils類. 實現獲取Request/Response/Session與繞過jsp/freemaker直接輸出文本的簡化函數.
 */
public class PagesUtils {

	private static Logger logger = Logger.getLogger(PagesUtils.class);
	
	/**
	 * 不管有無登入過濾排除的頁面可以正常瀏覽
	 */
	public static boolean excludePage(String uri) {
		boolean result = false;
		if(uri.endsWith("index.jsp") ||  // 首頁
		   uri.endsWith("indexPage") ||
		   uri.endsWith("admin.jsp") ||  // 關於天地
		   uri.endsWith("admin") ||
		   uri.endsWith("error.jsp") || 
		   uri.endsWith("error") ||
		   uri.endsWith("limit.jsp") ||
		   uri.endsWith("limit") || 
		   uri.endsWith("about") ||  // 關於天地
		   uri.endsWith("loginPage") || // 會員登入頁面
		   uri.endsWith("login") || // 會員登入
		   uri.endsWith("logout") || // 會員登出
		   uri.endsWith("loginAdmin") || // 管理員登入
		   uri.endsWith("logoutAdmin") || // 管理員登出
		   uri.endsWith("/") ||  // 根目錄
		   uri.endsWith("member") || // 會員申請
		   uri.endsWith("queryPassword") || // 密碼查詢
		   uri.endsWith("resetPage") || // 密碼重置
		   uri.endsWith("saveMember") || // 儲存會員
		   uri.endsWith("queryMemberPassword") || 
		   uri.endsWith("limitJsp") ||
		   uri.endsWith("getSessionMember") ||
		   uri.endsWith("checkMember") ||
		   uri.endsWith("resetMemberPassword") ||
		   uri.endsWith("goProduct") ||
		   uri.endsWith("getProductByType") ||
		   uri.endsWith("getProductByHot") ||
		   uri.endsWith("getProductByNew") ||
		   uri.endsWith("searchProduct") ||
		   uri.endsWith("getProductBykeyword") ||
		   uri.endsWith("getCity") ||
		   uri.endsWith("getCountry") ||
		   uri.endsWith("getRoad") ||
		   uri.endsWith("getShoppingCartCount")){
			result = true;
		}
		return result;
	}
	
	/**
	 * 管理員過濾不排除的頁面
	 */
	public static boolean includePage(String uri) {
		boolean result = false;
		if(uri.endsWith("addProduct") ||
		   uri.endsWith("addVendor") ||
		   uri.endsWith("goMonthOrdersByDeliver")){
			result = true;
		}
		return result;
	}
	
	/**
	 * 會員過濾不排除的頁面
	 */
	public static boolean hasLoginPage(String uri) {
		boolean result = false;
		if(uri.endsWith("goGods") ||
		   uri.endsWith("goAncestors") ||
		   uri.endsWith("goShoppingCartOrder") ||
		   uri.endsWith("goShoppingCartItem") ||
		   uri.endsWith("goHistoryOrder") ||
		   uri.endsWith("addCartByProduct")){
			result = true;
			logger.debug("頁面限制");
		}
		return result;
	}
}
