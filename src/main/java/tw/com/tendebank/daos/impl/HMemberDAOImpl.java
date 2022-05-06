package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.daos.HMemberDAO;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 會員Hibernate DAO 實作
 * @author Edison
 *
 */
public class HMemberDAOImpl implements HMemberDAO{
	
	private static Logger logger = Logger.getLogger(HMemberDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Member select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Member member) throws SQLException {
		sessionFactory.getCurrentSession().save(member);
	}

	@Override
	public void update(Member member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(String key) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member findByKey(String key) throws SQLException {
		String hql = "from Member m where m.account = :account";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("account", key);
		return (Member)query.uniqueResult();
	}

	@Override
	public List<Member> findAll(HashMap<String, String> param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String findByMaxKey(String key) throws SQLException {
		String hql = "Select max(m.memberNo) from Member m where m.memberNo like ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return ObjectUtils.isObjNotBlank(query.uniqueResult())?query.uniqueResult().toString():"";
	}

	@Override
	public Member findByAccountAndMail(Member member) throws SQLException {
		String hql = "from Member m where m.account = ? and m.email = ?";
		logger.debug("DAO Query password !");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, member.getAccount());
		query.setString(1, member.getEmail());
		return (Member)query.uniqueResult();
	}
	
}
