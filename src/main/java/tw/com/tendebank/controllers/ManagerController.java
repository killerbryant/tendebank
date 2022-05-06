package tw.com.tendebank.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.tendebank.beans.Manager;
import tw.com.tendebank.services.ManagerService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.MD5Utils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 管理員控制器
 * @author Edison
 *
 */
@Controller
public class ManagerController {
	private static Logger logger = Logger.getLogger(ManagerController.class);
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/loginAdmin")
	public String login(HttpServletRequest request , HttpServletResponse response, Manager manager)throws Exception{
		
		logger.debug("login start");
		String remember = request.getParameter("rememberMe");
		logger.debug("remember" + remember);
		boolean rememberMe = StringUtils.equals(remember, "true")?true:false;
		logger.debug("rememberMe" + rememberMe);
		// 判斷 manager是否為空值
		if(ObjectUtils.isObjBlank(manager)) {
			logger.debug("Manager is null");
			return "redirect:/admin.do";
		} else {
			if( StringUtils.isBlank(manager.getAccount())) {
				logger.debug("Manager Account is null");
				manager = (Manager) request.getSession().getAttribute(ConstantUtils.USER_SESSION_MANAGER_KEY);
				// 抓取session還是null直接導向index頁面
				if(ObjectUtils.isObjBlank(manager)) {
					logger.debug("Manager is null");
					return "redirect:/admin.do";
				}
			} else {
				logger.debug("Manager and Account is not null");
				// UI輸入的密碼MD5加密
				manager.setPassword(MD5Utils.md5Encryption(manager.getPassword()));
				
				// 尋找是否有此帳號
				Manager checkManager = managerService.selectManagerByAccount(manager);
				
				// 判斷輸入的帳號密碼
				if(ObjectUtils.isObjNotBlank(checkManager)) {
					// 比對密碼
					if(StringUtils.equals(checkManager.getPassword(), manager.getPassword())) {
						// 勾選記住我
						if (rememberMe){
							request.getSession().setAttribute(ConstantUtils.COOKIE_REMEMBERME_MANAGER_KEY, rememberMe);
							Cookie cookie = new Cookie(ConstantUtils.COOKIE_REMEMBERME_MANAGER_KEY, checkManager.getPassword() + "==" + checkManager.getPassword());
							cookie.setMaxAge(60 * 60 * 24 * 14);// 保存2週
							response.addCookie(cookie);
						}
						request.getSession().setAttribute(ConstantUtils.USER_SESSION_MANAGER_KEY, checkManager);
					} else {
						logger.error("帳號或密碼錯誤！");
					}
				}
			}
		}
		
		logger.debug("login end");
		return "redirect:/manage.do";
	}

}

