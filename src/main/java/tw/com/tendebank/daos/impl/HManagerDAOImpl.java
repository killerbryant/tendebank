package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tw.com.tendebank.beans.Manager;
import tw.com.tendebank.daos.HManagerDAO;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 管理員Hibernate DAO 實作
 * @author Edison
 *
 */
public class HManagerDAOImpl implements HManagerDAO{
	
	private static Logger logger = Logger.getLogger(HManagerDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Manager select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Manager manager) throws SQLException {
		sessionFactory.getCurrentSession().save(manager);
	}

	@Override
	public void update(Manager manager) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(String key) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Manager findByKey(String key) throws SQLException {
		String hql = "from Manager m where m.account = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return (Manager)query.uniqueResult();
	}

	@Override
	public List<Manager> findAll(HashMap<String, String> param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String findByMaxKey(String key) throws SQLException {
		String hql = "Select max(m.managerNo) from Manager m where m.managerNo like ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return ObjectUtils.isObjNotBlank(query.uniqueResult())?query.uniqueResult().toString():"";
	}

	@Override
	public Manager findByAccountAndMail(Manager manager) throws SQLException {
		String hql = "from Manager m where m.account = ? and m.email = ?";
		logger.debug("DAO Query password !");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, manager.getAccount());
		query.setString(1, manager.getEmail());
		return (Manager)query.uniqueResult();
	}
	
}
