
package tw.com.tendebank.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.services.MemberService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.MD5Utils;
import tw.com.tendebank.utils.ObjectUtils;

@Controller
public class LoginController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login")
	@ResponseBody
    public Map<String, Object> login(HttpServletRequest request , HttpServletResponse response, Member member) {
		
		String errorMsg = "";
		logger.debug("login start" + member.toString());
		String remember = request.getParameter("rememberMe");
		logger.debug("remember" + remember);
		boolean rememberMe = StringUtils.equals(remember, "true")?true:false;
		logger.debug("rememberMe" + rememberMe);
		// 判斷 member是否為空值
		if(ObjectUtils.isObjBlank(member)) {
			logger.debug("Member is null");
			errorMsg = "未輸入帳號密碼";
		} else {
			if( StringUtils.isBlank(member.getAccount())) {
				logger.debug("Member Account is null");
				member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
				// 抓取session還是null直接導向index頁面
				if(ObjectUtils.isObjBlank(member)) {
					logger.debug("Session Member is null");
					errorMsg = "未輸入帳號或密碼";
				}
			} else {
				logger.debug("Member == " + member.toString());
				logger.debug("Member and Account is not null " + rememberMe);
				// UI輸入的密碼MD5加密
				member.setPassword(MD5Utils.md5Encryption(member.getPassword()));
				
				// 尋找是否有此帳號
				Member checkMember = memberService.selectMemberByAccount(member);
				
				// 判斷輸入的帳號密碼
				if(ObjectUtils.isObjNotBlank(checkMember)) {
					// 比對密碼
					if(StringUtils.equals(checkMember.getPassword(), member.getPassword())) {
						// 是否為認證會員
						if(StringUtils.equals(checkMember.getApprove(), ConstantUtils.TENDEBANK_Y)) {
							// 勾選記住我
							if (rememberMe){
								request.getSession().setAttribute(ConstantUtils.COOKIE_REMEMBERME_KEY, rememberMe);
								Cookie cookie = new Cookie(ConstantUtils.COOKIE_REMEMBERME_KEY, checkMember.getAccount() + "==" + checkMember.getPassword());
								cookie.setMaxAge(60 * 60 * 24 * 14);// 保存2週
								response.addCookie(cookie);
							}
							request.getSession().setAttribute(ConstantUtils.USER_SESSION_KEY, checkMember);
						} else {
							logger.error("非認證會員，請來電洽詢！！");
							errorMsg = "非認證會員，請來電洽詢！！";
						}
					} else {
						logger.error("帳號或密碼錯誤");
						errorMsg = "帳號或密碼錯誤";
					}
				} else {
					logger.error("查無此帳號");
					errorMsg =  "查無此帳號";
				}
			}
		}
		logger.debug(errorMsg);
		request.setAttribute("errorMsg", errorMsg);
		logger.debug("login end");
		Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("errorMsg", errorMsg);
    	return hashMap;
	}
	
}
