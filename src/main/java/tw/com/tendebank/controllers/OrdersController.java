package tw.com.tendebank.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.tendebank.beans.Orders;
import tw.com.tendebank.services.OrdersService;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.DateJsonValueProcessor;

/**
 * 管理員訂單控制器
 * 
 * @author Edison
 * 
 */
@Controller
public class OrdersController {
	private static Logger logger = Logger.getLogger(OrdersController.class);

	@Autowired
	private OrdersService ordersService;

	// 未出貨
	@RequestMapping(value = "/selectMonthOrdersByNotDeliver", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectMonthOrdersByNotDeliver(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("管理員查詢訂單");

		Map<String, Object> hashMap = new HashMap<String, Object>();

		// 初始化工具
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());

		// 本月未出貨訂單
		List<Orders> ordersList = ordersService.selectMonthOrdersByDeliver(ConstantUtils.TENDEBANK_N);

		JSONArray ordersJson = JSONArray.fromObject(ordersList, jsonConfig);
		logger.debug("JSON Format == " + ordersJson.toString());
		hashMap.put("manageOrdersData", ordersJson);

		return hashMap;
	}

	// 已出貨
	@RequestMapping(value = "/selectMonthOrdersByDeliver", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectMonthOrdersByDeliver(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("管理員查詢訂單");

		Map<String, Object> hashMap = new HashMap<String, Object>();

		// 初始化工具
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());

		// 本月已出貨訂單
		List<Orders> ordersList = ordersService.selectMonthOrdersByDeliver(ConstantUtils.TENDEBANK_Y);

		JSONArray ordersJson = JSONArray.fromObject(ordersList, jsonConfig);
		logger.debug("JSON Format == " + ordersJson.toString());
		hashMap.put("manageOrdersData", ordersJson);

		return hashMap;
	}

	// 歷史未出貨
	@RequestMapping(value = "/selectHistoryOrdersByNotDeliver", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectHistoryOrdersByNotDeliver(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("管理員歷史未出貨查詢訂單");

		Map<String, Object> hashMap = new HashMap<String, Object>();

		// 初始化工具
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());

		// 歷史未出貨訂單
		List<Orders> ordersList = ordersService.selectHistoryOrdersByDeliver(ConstantUtils.TENDEBANK_N);

		JSONArray ordersJson = JSONArray.fromObject(ordersList, jsonConfig);
		logger.debug("JSON Format == " + ordersJson.toString());
		hashMap.put("manageOrdersData", ordersJson);

		return hashMap;
	}

	// 歷史已出貨
	@RequestMapping(value = "/selectHistoryOrdersByDeliver", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectHistoryOrdersByDeliver(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("管理員歷史已出貨查詢訂單");

		Map<String, Object> hashMap = new HashMap<String, Object>();

		// 初始化工具
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());

		// 歷史已出貨訂單
		List<Orders> ordersList = ordersService.selectHistoryOrdersByDeliver(ConstantUtils.TENDEBANK_Y);

		JSONArray ordersJson = JSONArray.fromObject(ordersList, jsonConfig);
		logger.debug("JSON Format == " + ordersJson.toString());
		hashMap.put("manageOrdersData", ordersJson);

		return hashMap;
	}
}
