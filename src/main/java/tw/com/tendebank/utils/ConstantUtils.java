package tw.com.tendebank.utils;

/**
 * 常數工具
 * @author Edison
 *
 */
public class ConstantUtils {
	
	public static final String TENDEBANK_Y = "Y";
	
	public static final String TENDEBANK_N = "N";

	public static final int PRODUCT_NUMBER_LENGTH = 5; // 產品編號長度
	
	public static final int VENDOR_NUMBER_LENGTH = 5; // 供應商編號長度
	
	public static final int MEMBER_NUMBER_LENGTH = 9; // 會員編號長度
	
	public static final String USER_SESSION_KEY = "member"; // session會員屬性
	
	public static final String USER_SESSION_MANAGER_KEY = "manager"; // session管理員屬性
	
	public static final String COOKIE_REMEMBERME_KEY = "rememberMember"; // session會員記住我
	
	public static final String COOKIE_REMEMBERME_MANAGER_KEY = "rememberManager"; // session管理員記住我
	
	public static final String GOING_TO_URL_KEY = "GOING_TO";
	
	public static final String MEMBER_NO_FIRST_WORD = "M"; // 會員編號起始字元
	
	public static final String JSON = "json"; // JSON 字樣
	
	public static final String SMTP_AUTH_USER = "tendebank@gmail.com"; // 天地銀行寄件者
	
	public static final String COMPANY_TITLE = "天地銀行"; // 商店名稱
	
	//	public static final String DOMAIN_NAME = "http://114.32.136.89:9090"; // URL網域名
	public static final String DOMAIN_NAME = "http://localhost:8080"; // URL網域名
	
	public static final String SHOPPING_CART = "shoppingCart";
	
	// 商品狀態
	public static final String CART_STATUS_HOT = "HOT"; // 熱門
	public static final String CART_STATUS_NEW = "NEW"; // 新品
}
