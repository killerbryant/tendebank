package tw.com.tendebank.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.services.MemberService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

@Controller
public class MemberController extends MultiActionController {
	
	private static Logger logger = Logger.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
    
	@RequestMapping("/saveMember")
	@ResponseBody
    public Map<String, Object> saveMember(HttpServletRequest request , HttpServletResponse response, Member member) {
    	boolean result = memberService.insert(member);
    	logger.debug("註冊帳號為" + member.getAccount());
    	String resultMsg = "會員帳號: " + member.getAccount() + " 註冊成功";
    	if(result) {
    		member = memberService.selectMemberByAccount(member);
    		request.getSession().setAttribute(ConstantUtils.USER_SESSION_KEY, member);
    	} else {
    		resultMsg = "會員帳號: " + member.getAccount() + " 註冊失敗";
    	}
    	
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("successMsg", resultMsg);
    	hashMap.put("result", result);
    	return hashMap;
    }
    
	@RequestMapping("/queryMemberPassword")
	@ResponseBody
    public Map<String, Object> queryMemberPassword(HttpServletRequest request , HttpServletResponse response, Member member) {
    	String ctxPath = request.getContextPath();// 得到web應用程式的上下文路徑
    	logger.debug("web path" + ctxPath);
    	String result = memberService.queryMemberService(member, ctxPath);
    	logger.debug("密碼查詢的結果 " + result);
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	if(StringUtils.equals(result, "success")) {
    		hashMap.put("successMsg", "請至申請的電子郵件收取密碼修改的信件，並重新設定密碼");
    	} else if(StringUtils.equals(result, "Not find")) {
    		hashMap.put("successMsg", "查無此帳號 或 與申請的郵件不符");
    	} else if(StringUtils.equals(result, "apply")) {
    		hashMap.put("successMsg", "重新寄送修改密碼電子郵件");
    	} else {
    		hashMap.put("successMsg", "系統錯誤請致電客服人員");
    	}
    	return hashMap;
    }
    
	@RequestMapping("/resetPage")
    public String resetPage(HttpServletRequest request , HttpServletResponse response, Member member) {
    	String result = "member/resetPassword";
    	String account = request.getParameter("acc");
    	String email = request.getParameter("email");
    	String resetPwd = request.getParameter("resetPwd");
    	
    	// 亂數產生的密碼不是空白才導向重置頁面
    	if(StringUtils.isNotBlank(resetPwd)) {
    		member = new Member();
        	member.setAccount(account);
        	member.setEmail(email);
        	request.setAttribute("account", account);
        	request.setAttribute("email", email);
        	request.setAttribute("resetPwd", resetPwd);
    	} else {
    		result = "redirect:/index.jsp";
    	}
    	
    	logger.debug("重置密碼參數" + account + "-" + email + "-" + resetPwd );
    	return result;
    }
    
	@RequestMapping("/resetMemberPassword")
	@ResponseBody
    public Map<String, Object> resetMemberPassword(HttpServletRequest request , HttpServletResponse response, Member member) {
    	logger.debug("START TO RESET PWD");
    	String resetPassword = request.getParameter("resetPassword");
    	logger.debug("帳號 : " + member.getAccount() +  "reset Pwd = " + resetPassword);
    	boolean result = memberService.updatePassword(member, resetPassword);
    	String resultMsg = "會員帳號: " + member.getAccount() + " 密碼更新成功";
    	if(result) {
    		member = memberService.selectMemberByAccount(member);
    		request.getSession().setAttribute(ConstantUtils.USER_SESSION_KEY, member);
    	} else {
    		resultMsg = "會員帳號: " + member.getAccount() + " 密碼更新失敗";
    	}
    	
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("successMsg", resultMsg);
    	hashMap.put("result", result);
    	return hashMap;
    }
	
	@RequestMapping("/getSessionMember")
	@ResponseBody
    public Map<String, Object> getSessionMember(HttpServletRequest request , HttpServletResponse response, Member member) {

		member = (Member) request.getSession().getAttribute(ConstantUtils.USER_SESSION_KEY);
		
		if(ObjectUtils.isObjNotBlank(member)) {
			member.setOriginalPassword("");
			member.setPassword("");
		}
		
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("member", member);
    	
    	return hashMap;
    }
	
	@RequestMapping("/checkMember")
	@ResponseBody
    public Map<String, Object> checkMember(HttpServletRequest request , HttpServletResponse response, Member member) {

		boolean checkResult = true;
		
		String account = member.getAccount();
		
		logger.debug("account = " + account);
		
		member = memberService.selectMemberByAccount(member);
		
		if(ObjectUtils.isObjNotBlank(member)) {
			checkResult = false;
		}
		
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("checkResult", checkResult);
    	
    	return hashMap;
    }
}
