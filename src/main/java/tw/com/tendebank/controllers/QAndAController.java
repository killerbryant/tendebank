package tw.com.tendebank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.tendebank.beans.QAndA;
import tw.com.tendebank.services.QAndAService;

@Controller
public class QAndAController {
	private static Logger logger = Logger.getLogger(QAndAController.class);

	@Autowired
	private QAndAService qAndAService;
	
	@RequestMapping("getQAndAByPageName")
	@ResponseBody
	public Map<String, Object> getQAndAByPageName(HttpServletRequest request , HttpServletResponse response) {
		String key = request.getParameter("pageName");
		logger.debug("取得參數" + key);
		List<QAndA> resultList = qAndAService.selectByCategory(key);
		logger.debug("結果大小" + resultList.size());
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("gods", resultList);
		hashMap.put("totalCount", resultList.size());
		return hashMap;
	}
}
