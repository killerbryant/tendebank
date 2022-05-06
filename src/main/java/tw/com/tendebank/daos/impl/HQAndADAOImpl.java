package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tw.com.tendebank.beans.QAndA;
import tw.com.tendebank.daos.HQAndADAO;

public class HQAndADAOImpl implements HQAndADAO {
	
	private static Logger logger = Logger.getLogger(HQAndADAOImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public QAndA select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(QAndA t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(QAndA t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByKey(String k) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public QAndA findByKey(String k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QAndA> findAll(HashMap<String, String> param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QAndA> selectByCategory(String key) {
		String hql = "from QAndA q where q.category=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		List<QAndA> resultList = query.list();
		logger.debug("QAndA selectByCategory result = " + resultList.size());
		return resultList;
	}

}
