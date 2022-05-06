package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tw.com.tendebank.beans.StreetName;
import tw.com.tendebank.daos.HStreetNameDAO;

/**
 * 產品Hibernate DAO 實作
 * @author Edison
 *
 */
public class HStreetNameDAOImpl implements HStreetNameDAO{
	
	private static Logger logger = Logger.getLogger(HStreetNameDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public StreetName select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(StreetName t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StreetName t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(String k) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StreetName findByKey(String k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StreetName> findAll(HashMap<String, String> param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StreetName> selectCity() throws SQLException {
		String hql = "select distinct s.city from StreetName s";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<StreetName> cityList = query.list();
		logger.debug("Street City result = " + cityList.size());
		return cityList;
	}

	@Override
	public List<StreetName> selectCountry(String city) throws SQLException {
		String hql = "select distinct s.country from StreetName s where s.city = :city";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("city", city);
		List<StreetName> countryList = query.list();
		logger.debug("Street Country result = " + countryList.size());
		return countryList;
	}

	@Override
	public List<StreetName> selectRoad(String city, String country) throws SQLException {
		String hql = "select new map(s.mailcode as mailcode, s.road as road) from StreetName s where s.city = :city and s.country = :country";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("city", city);
		query.setString("country", country);
		List<StreetName> roadList = query.list();
		logger.debug("Street Road result = " + roadList.size());
		return roadList;
	}
	
}
