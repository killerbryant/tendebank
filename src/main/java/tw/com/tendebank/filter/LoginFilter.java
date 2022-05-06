package tw.com.tendebank.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.tendebank.beans.Manager;
import tw.com.tendebank.beans.Member;
import tw.com.tendebank.services.ManagerService;
import tw.com.tendebank.services.MemberService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;
import tw.com.tendebank.utils.PagesUtils;

/**
 * SpringMVC controller 和 jsp 過濾器
 * @author Edison
 *
 */
public class LoginFilter implements Filter{
	
	private static Logger logger = Logger.getLogger(LoginFilter.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ManagerService managerService;
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("doFilter start");
		//把ServletRequest和ServletResponse轉換成真正的類型
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        
        //由於web.xml中設置Filter過濾全部請求，可以排除不需要過濾的url
        String requestURI = req.getRequestURI();
        logger.debug("過濾的URL = " + requestURI);
        
        // 
        if(requestURI.endsWith(".do")) {
        	logger.debug(".do取代掉");
        	requestURI = requestURI.replace(".do", "");
        }
        
        // 排除不包含例外的所有jsp
        if(requestURI.endsWith(".jsp") && !PagesUtils.excludePage(requestURI)) {
        	logger.debug("不可直接訪問jsp");
        	req.getRequestDispatcher("/limitJsp.do").forward(request, response);
            return;
        }
        
        Cookie[] cookies = req.getCookies();
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
        
        boolean memberResult = ObjectUtils.isObjBlank(session.getAttribute(ConstantUtils.USER_SESSION_KEY)) ;
        
        boolean managerResult = ObjectUtils.isObjBlank(session.getAttribute(ConstantUtils.USER_SESSION_MANAGER_KEY));
        
        logger.debug("LoginFilter 會員Session = " + memberResult);
        //判斷使用者是否登錄，進行頁面的處理
        if(!memberResult || !managerResult){
        	logger.debug(memberResult + " == " + PagesUtils.includePage(requestURI));
        	// session中有會員
        	if(!memberResult && PagesUtils.includePage(requestURI)) {
        		logger.debug("有會員身分且有不可使用的頁面");
        		// 表只有會員登入的session 無管理員登入
        		if(managerResult) {
        			logger.debug("會員身分不可使用的頁面");
        			req.getRequestDispatcher("/error.do").forward(request, response);
            		return;
        		}
        	}
        }
        
        // 排除過濾的action
        if(PagesUtils.excludePage(requestURI)) {
            chain.doFilter(req, res);
            logger.debug("直接排除過濾");
        }
	}

}
