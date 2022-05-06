package tw.com.tendebank.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 登出控制器
 * @author Edison
 *
 */
@Controller
public class LogoutController extends MultiActionController {
	
	private static Logger logger = Logger.getLogger(LogoutController.class);

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request , HttpServletResponse response, Member member){
		logger.debug("logOut start");
		HttpSession httpSession = request.getSession(false);
		
		if (ObjectUtils.isObjNotBlank(httpSession)) {
			logger.debug("移除session");
			httpSession.removeAttribute(ConstantUtils.USER_SESSION_KEY);
			httpSession.removeAttribute(ConstantUtils.COOKIE_REMEMBERME_KEY);
			httpSession.removeAttribute(ConstantUtils.SHOPPING_CART);
			httpSession.removeAttribute("sessionMemberLose");
		}
		
		Cookie[] cookies = request.getCookies();
		if (ObjectUtils.isObjNotBlank(cookies)) {
			for (Cookie cookie : cookies) {
				if (ConstantUtils.COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {
					logger.debug("移除cookies");
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		logger.debug("logOut end");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/logoutAdmin")
	public String logoutAdmin(HttpServletRequest request , HttpServletResponse response, Member member){
		logger.debug("logOutAdmin start");
		HttpSession httpSession = request.getSession(false);
		
		if (ObjectUtils.isObjNotBlank(httpSession)) {
			logger.debug("移除manager session");
			httpSession.removeAttribute(ConstantUtils.USER_SESSION_MANAGER_KEY);
			httpSession.removeAttribute(ConstantUtils.COOKIE_REMEMBERME_MANAGER_KEY);
		}
		
		Cookie[] cookies = request.getCookies();
		if (ObjectUtils.isObjNotBlank(cookies)) {
			for (Cookie cookie : cookies) {
				if (ConstantUtils.COOKIE_REMEMBERME_MANAGER_KEY.equals(cookie.getName())) {
					logger.debug("移除manager cookies");
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		logger.debug("logOutAdmin end");
		return "redirect:/admin.do";
	}

}
