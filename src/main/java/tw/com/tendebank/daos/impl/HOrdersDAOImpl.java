package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.tendebank.beans.Orders;
import tw.com.tendebank.daos.HOrdersDAO;

public class HOrdersDAOImpl implements HOrdersDAO {
	
	private static Logger logger = Logger.getLogger(HOrdersDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Orders select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Orders t) throws SQLException {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void update(Orders t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByKey(String k) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Orders findByKey(String k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAll(HashMap<String, String> param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findByMaxKey(String key) throws SQLException {
		String hql = "select count(*) from Orders orders where orders.memberNo=:memberNo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("memberNo", key);
		Long count = (Long) query.uniqueResult();
		
		return String.valueOf(count+1);
	}
	
	@Override
	public List<Orders> selectMonthOrdersByMemberNo(String memberNo, Date firstDate, Date lastDate) throws SQLException {
		String hql = "from Orders orders where orders.memberNo = :memberNo and orders.buyDate >= :firstDate and orders.buyDate <= :lastDate";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("memberNo", memberNo);
		query.setDate("firstDate", firstDate);
		query.setDate("lastDate", lastDate);
		List<Orders> ordersList = (List<Orders>) query.list();
		return ordersList;
	}
	
	@Override
	public List<Orders> selectHistoryOrdersByMemberNo(String memberNo, Date firstDate) throws SQLException {
		String hql = "from Orders orders where orders.memberNo=:memberNo and orders.buyDate < :firstDate";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("memberNo", memberNo);
		query.setDate("firstDate", firstDate);
		List<Orders> ordersList = (List<Orders>) query.list();
		return ordersList;
	}
	
	@Override
	public int updateOrderByBankInfo(String orderNo, String afterFiveNumber) throws SQLException {
		Transaction trans= sessionFactory.getCurrentSession().beginTransaction();
		String hql ="update Orders orders set orders.remittanceAccount=:afterFiveNumber, orders.payStatus=:payStatus where orders.orderNo=:orderNo";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("afterFiveNumber", afterFiveNumber);
		query.setString("orderNo", orderNo);
		query.setString("payStatus", "C");
		int ret = query.executeUpdate();
		trans.commit();
		return ret;
	}
	
	@Override
	public List<Orders> selectMonthOrders(Date firstDate, Date lastDate, String orderStatus, String payStatus, String deliverStatus) throws SQLException {
		String hql = "from Orders orders where orders.buyDate >= :firstDate and orders.buyDate <= :lastDate";
		if(StringUtils.isNotBlank(orderStatus)) {
			hql += " and orders.orderStatus = :orderStatus";
		}
		if(StringUtils.isNotBlank(payStatus)) {
			hql += " and orders.payStatus = :payStatus";
		}
		if(StringUtils.isNotBlank(deliverStatus)) {
			hql += " and orders.deliverStatus = :deliverStatus";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setDate("firstDate", firstDate);
		query.setDate("lastDate", lastDate);
		if(StringUtils.isNotBlank(orderStatus)) {
			query.setString("orderStatus", orderStatus);
		}
		if(StringUtils.isNotBlank(payStatus)) {
			query.setString("payStatus", payStatus);
		}
		if(StringUtils.isNotBlank(deliverStatus)) {
			query.setString("deliverStatus", deliverStatus);
		}
		List<Orders> ordersList = (List<Orders>) query.list();
		return ordersList;
	}
	
	@Override
	public List<Orders> selectHistoryOrders(Date firstDate, String orderStatus, String payStatus, String deliverStatus) throws SQLException {
		String hql = "from Orders orders where orders.buyDate < :firstDate";
		if(StringUtils.isNoneBlank(orderStatus)) {
			hql += " and orders.orderStatus = :orderStatus";
		}
		if(StringUtils.isNoneBlank(payStatus)) {
			hql += " and orders.payStatus = :payStatus";
		}
		if(StringUtils.isNoneBlank(deliverStatus)) {
			hql += " and orders.deliverStatus = :deliverStatus";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setDate("firstDate", firstDate);
		if(StringUtils.isNoneBlank(orderStatus)) {
			query.setString("orderStatus", orderStatus);
		}
		if(StringUtils.isNoneBlank(payStatus)) {
			query.setString("payStatus", payStatus);
		}
		if(StringUtils.isNoneBlank(deliverStatus)) {
			query.setString("deliverStatus", deliverStatus);
		}
		List<Orders> ordersList = (List<Orders>) query.list();
		return ordersList;
	}

}
