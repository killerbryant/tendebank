package tw.com.tendebank.services.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tendebank.beans.Orders;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.OrdersService;
import tw.com.tendebank.services.ShoppingCartService;
import tw.com.tendebank.utils.DateUtils;

public class OrdersServiceImpl implements OrdersService {
	
	private static Logger logger = Logger.getLogger(OrdersServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	// 本月訂單查詢
	@Override
	public List<Orders> selectMonthOrdersByDeliver(String deliverStatus) {
		List<Orders> orderList = null;
		Date firstDate = DateUtils.getMonthFirstDate();
		Date lastDate = DateUtils.getMonthLastDate();
		logger.debug("Service 本月起" + firstDate+ " == 迄 " + lastDate);
		try {
			orderList = getDaoFactory().gethOrdersDao().selectMonthOrders(firstDate, lastDate, null, null, deliverStatus);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return orderList;
	}
	
	// 歷史訂單查詢
	@Override
	public List<Orders> selectHistoryOrdersByDeliver(String deliverStatus) {
		List<Orders> orderList = null;
		Date firstDate = DateUtils.getMonthFirstDate();
		try {
			orderList = getDaoFactory().gethOrdersDao().selectHistoryOrders(firstDate, null, null, deliverStatus);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return orderList;
	}
}
