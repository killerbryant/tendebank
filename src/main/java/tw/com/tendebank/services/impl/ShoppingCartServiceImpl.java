package tw.com.tendebank.services.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tendebank.beans.Orders;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.ShoppingCartService;
import tw.com.tendebank.utils.DateUtils;

public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private static Logger logger = Logger.getLogger(ShoppingCartServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Transactional
	@Override
	public boolean insert(Orders orders) {
		boolean result = true;
		try {
			getDaoFactory().gethOrdersDao().insert(orders);
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

	@Override
	public List<Orders> selectMonthOrdersByMemberNo(String memberNo) {
		List<Orders> orderList = null;
		Date firstDate = DateUtils.getMonthFirstDate();
		Date lastDate = DateUtils.getMonthLastDate();
		logger.debug("Service 本月起" + firstDate+ " == 迄 " + lastDate);
		try {
			orderList = getDaoFactory().gethOrdersDao().selectMonthOrdersByMemberNo(memberNo, firstDate, lastDate);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return orderList;
	}
	
	@Override
	public List<Orders> selectHistoryOrdersByMemberNo(String memberNo) {
		List<Orders> orderList = null;
		Date firstDate = DateUtils.getMonthFirstDate();
		try {
			orderList = getDaoFactory().gethOrdersDao().selectHistoryOrdersByMemberNo(memberNo, firstDate);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return orderList;
	}
	
	@Override
	public boolean updateOrderByBankInfo(String orderNo, String afterFiveNumber) {
		boolean result = true;
		try {
			int updateCount = getDaoFactory().gethOrdersDao().updateOrderByBankInfo(orderNo, afterFiveNumber);
			logger.debug("更新筆數" + updateCount);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
			result = false;
		}
		return result;
	}

}
