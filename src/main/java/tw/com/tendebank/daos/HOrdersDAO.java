package tw.com.tendebank.daos;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import tw.com.tendebank.beans.Orders;

/**
 * 產品Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HOrdersDAO extends BaseDAO<Orders, String>{
	public String findByMaxKey(String key) throws SQLException;

	public List<Orders> selectMonthOrdersByMemberNo(String memberNo, Date firstDate, Date lastDate) throws SQLException;
	
	public List<Orders> selectHistoryOrdersByMemberNo(String memberNo, Date firstDate) throws SQLException;

	public int updateOrderByBankInfo(String orderNo, String afterFiveNumber) throws SQLException;

	public List<Orders> selectMonthOrders(Date firstDate, Date lastDate, String orderStatus, String payStatus, String deliverStatus) throws SQLException;
	
	public List<Orders> selectHistoryOrders(Date firstDate, String orderStatus, String payStatus, String deliverStatus) throws SQLException;
}
