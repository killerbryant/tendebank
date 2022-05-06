package tw.com.tendebank.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.tendebank.beans.Vendor;
import tw.com.tendebank.services.VendorService;

/**
 * 供應商控制器
 * @author Edison
 *
 */
@Controller
public class VendorController {
	private static Logger logger = Logger.getLogger(VendorController.class);
	
	@Autowired
	private VendorService vendorService;
	
	@RequestMapping("/addVendor")
	public String addVendor() {
		return "vendor/vendor";
	}
	
	@RequestMapping("/saveVendor")
	@ResponseBody
	public Map<String, Object> saveVendor(HttpServletRequest request , HttpServletResponse response, Vendor vendor){
    	boolean result = vendorService.insert(vendor);
    	logger.debug("VendorAction 新編號" + vendor.getVendorNo());
    	String resultMsg = "供應商編號: " + vendor.getVendorNo() + " 新增成功";
    	if(!result) {
    		resultMsg = "供應商編號: " + vendor.getVendorNo() + " 新增失敗";
    	}
    	Map<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap.put("successMsg", resultMsg);
    	hashMap.put("result", result);
    	return hashMap;
    }
}
