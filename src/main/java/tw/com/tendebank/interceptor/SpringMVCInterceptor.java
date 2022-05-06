package tw.com.tendebank.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tw.com.tendebank.beans.Manager;
import tw.com.tendebank.beans.Member;
import tw.com.tendebank.services.ManagerService;
import tw.com.tendebank.services.MemberService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;
import tw.com.tendebank.utils.PagesUtils;

/**
 * Spring MVC controller攔截器
 * @author Edison
 *
 */
public class SpringMVCInterceptor implements HandlerInterceptor {
	
	private static Logger logger = Logger.getLogger(SpringMVCInterceptor.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ManagerService managerService;

	/**
	 * preHandle方法是進行處理器攔截用的，顧名思義，該方法將在Controller處理之前進行調用，SpringMVC中的Interceptor攔截器是鏈式的，可以同時存在
	 * 多個Interceptor，然後SpringMVC會根據聲明的前後順序一個接一個的執行，而且所有的Interceptor中的preHandle方法都會在
	 * Controller方法調用之前調用。SpringMVC的這種Interceptor鏈式結構也是可以進行中斷的，這種中斷方式是令preHandle的返
	 * 回值為false，當preHandle的返回值為false的時候整個請求就結束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("intercept start");
//		String ctxPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		String methodURI = request.getMethod();
		
		logger.debug("攔截的url " + requestURI + " Method = " + methodURI);
		
		HttpSession session = request.getSession();
		
		Cookie[] cookies = request.getCookies();
		if (ObjectUtils.isObjNotBlank(cookies)) {
			for (Cookie cookie : cookies) {
				// cookies 會員
				if (StringUtils.equals(ConstantUtils.COOKIE_REMEMBERME_KEY, cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split("==");
						String userName = split[0];
						String password = split[1];
						logger.debug("Session UserName : " + userName + " Password : " + password);
						Member member = new Member();
						member.setAccount(userName);
						member.setPassword(password);
						if(ObjectUtils.isObjNotBlank(member)){
							Member checkMember = memberService.selectMemberByAccount(member);
							session.setAttribute(ConstantUtils.USER_SESSION_KEY, checkMember);
						}
					}
					
				// cookies 管理員
				} else if(StringUtils.equals(ConstantUtils.COOKIE_REMEMBERME_MANAGER_KEY, cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split("==");
						String userName = split[0];
						String password = split[1];
						logger.debug("Session UserName : " + userName + " Password : " + password);
						Manager manager = new Manager();
						manager.setAccount(userName);
						manager.setPassword(password);
						if(ObjectUtils.isObjNotBlank(manager)){
							Manager checkManager = managerService.selectManagerByAccount(manager);
							session.setAttribute(ConstantUtils.USER_SESSION_MANAGER_KEY, checkManager);
						}
					}
				}
			}
		}
		
		boolean memberResult = ObjectUtils.isObjNotBlank(session.getAttribute(ConstantUtils.USER_SESSION_KEY));
		
		boolean managerResult = ObjectUtils.isObjNotBlank(session.getAttribute(ConstantUtils.USER_SESSION_MANAGER_KEY));
		
		
		// 管理員登入可以瀏覽全部的頁面
		if(ObjectUtils.isObjNotBlank(session) && managerResult) {
			logger.debug("有管理員Session");
			return true;
		} else if(ObjectUtils.isObjNotBlank(session) && memberResult ) { // 會員登入不可以使用管理員的頁面
			logger.debug("有會員Session");
			// 限制存取管理員的頁面
			if(PagesUtils.includePage(requestURI)) {
				logger.debug("管理員限制頁面");
				return false;
			} else {
				logger.debug("非管理員限制頁面");
				return true;
			}
		}
		
		// 排除無需過濾的do
        if(PagesUtils.excludePage(requestURI.replace(".do", ""))) {
        	logger.debug("直接排除過濾");
        	return true;
        }
        
        // 未登入限制頁面不可使用
        if(!memberResult && !managerResult && PagesUtils.hasLoginPage(requestURI.replace(".do", ""))) {
        	session.removeAttribute(ConstantUtils.USER_SESSION_KEY);
        	session.removeAttribute(ConstantUtils.COOKIE_REMEMBERME_KEY);
        	session.removeAttribute(ConstantUtils.SHOPPING_CART);
        	// 頁面POST未登入不可瀏覽
        	if(StringUtils.equals(methodURI, "POST")) {
        		request.getRequestDispatcher("/limit.do").forward(request, response);
        	}
        	// 自行輸入網址不可瀏覽
        	if(StringUtils.equals(methodURI, "GET")) {
        		request.getRequestDispatcher("/error.do").forward(request, response);
        	}
    	}
        
		logger.debug("intercept end");
		return false;
	}
	
	/**
	 * 這個方法只會在當前這個Interceptor的preHandle方法返回值為true的時候才會執行。postHandle是進行處理器攔截用的，它的執行時間是在處理器進行處理之
	 * 後，也就是在Controller的方法調用之後執行，但是它會在DispatcherServlet進行視圖的渲染之前執行，也就是說在這個方法中你可以對ModelAndView進行操
	 * 作。這個方法的鏈式結構跟正常訪問的方向是相反的，也就是說先聲明的Interceptor攔截器該方法反而會後調用，這跟Struts2裡面的攔截器的執行過程有點像，
	 * 只是Struts2裡面的intercept方法中要手動的調用ActionInvocation的invoke方法，Struts2中調用ActionInvocation的invoke方法就是調用下一個Interceptor
	 * 或者是調用action，然後要在Interceptor之前調用的內容都寫在調用invoke之前，要在Interceptor之後調用的內容都寫在調用invoke方法之後。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 該方法也是需要當前對應的Interceptor的preHandle方法的返回值為true時才會執行。該方法將在整個請求完成之後，也就是DispatcherServlet渲染了視圖執行，
	 * 這個方法的主要作用是用於清理資源的，當然這個方法也只能在當前這個Interceptor的preHandle方法的返回值為true時才會執行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
