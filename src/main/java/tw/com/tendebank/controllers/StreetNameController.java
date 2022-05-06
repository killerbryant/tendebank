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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.StreetName;
import tw.com.tendebank.services.StreetNameService;

@Controller
public class StreetNameController extends MultiActionController {
	
	private static Logger logger = Logger.getLogger(StreetNameController.class);
	
	@Autowired
	private StreetNameService streetNameService;
	
	@RequestMapping("/getCity")
	@ResponseBody
    public Map<String, Object> getCity(HttpServletRequest request , HttpServletResponse response, Member member) {
		List<StreetName> cityList = streetNameService.selectCity();
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("cityList", cityList);
    	return hashMap;
    }
	
	@RequestMapping("/getCountry")
	@ResponseBody
    public Map<String, Object> getCountry(HttpServletRequest request , HttpServletResponse response, Member member) {
    	String city = request.getParameter("city");
    	List<StreetName> countryList = streetNameService.selectCountry(city);
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("countryList", countryList);
    	return hashMap;
    }
	
	@RequestMapping("/getRoad")
	@ResponseBody
    public Map<String, Object> getRoad(HttpServletRequest request , HttpServletResponse response, Member member) {
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		List<StreetName> roadList = streetNameService.selectRoad(city, country);
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("roadList", roadList);
    	return hashMap;
    }

}
