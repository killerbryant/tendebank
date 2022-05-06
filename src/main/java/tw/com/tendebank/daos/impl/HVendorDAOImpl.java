package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tw.com.tendebank.beans.Vendor;
import tw.com.tendebank.daos.HVendorDAO;

/**
 * 供應商Hibernate DAO 實作
 * @author Edison
 *
 */
public class HVendorDAOImpl implements HVendorDAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Vendor select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Vendor vendor) throws SQLException {
		sessionFactory.getCurrentSession().save(vendor);
	}

	@Override
	public void update(Vendor vendor) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(String key) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendor findByKey(String key) throws SQLException {
		String hql = "from Vendor v where v.vendorNo=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return (Vendor)query.uniqueResult();
	}

	@Override
	public List<Vendor> findAll(HashMap<String, String> param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findByMaxKey(String key) throws SQLException {
		String hql = "Select max(v.vendorNo) from Vendor v where v.vendorNo like ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return query.uniqueResult().toString();
	}
	
}
