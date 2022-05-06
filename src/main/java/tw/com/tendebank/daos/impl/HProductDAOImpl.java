package tw.com.tendebank.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.Product;
import tw.com.tendebank.daos.HProductDAO;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 產品Hibernate DAO 實作
 * @author Edison
 *
 */
public class HProductDAOImpl implements HProductDAO{
	
	private static Logger logger = Logger.getLogger(HProductDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Product select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Product product) throws SQLException {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void update(Product product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(String key) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findByKey(String key) throws SQLException {
		String hql = "from Product p where p.productNo=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return (Product)query.uniqueResult();
	}

	@Override
	public List<Product> findAll(HashMap<String, String> param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findByMaxKey(String key) throws SQLException {
		String hql = "Select max(p.productNo) from Product p where p.productNo like ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, key);
		return query.uniqueResult().toString();
	}

	@Override
	public List<Product> selectByType(String type, Member member) throws SQLException {
		
		String hql = "select new map(p.productNo as productNo, p.productName as productName, p.description as description, p.imgPath as imgPath) from Product p where p.onCart =:onCart";
		if(ObjectUtils.isObjNotBlank(member) && StringUtils.isNotBlank(member.getAccount())) {
			hql = "select new map(p.productNo as productNo, p.productName as productName, p.salesPrice as salesPrice, p.description as description, p.imgPath as imgPath) from Product p where p.onCart =:onCart";
		}
		if(StringUtils.isNotBlank(type)) {
			hql = hql + " and p.productType =:productType";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(StringUtils.isNotBlank(type)) {
			query.setString("productType", type);
		}
		query.setString("onCart", ConstantUtils.TENDEBANK_Y);
		List<Product> resultList = query.list();
		logger.debug("Product selectByType result = " + resultList.size());
		return resultList;
	}

	@Override
	public List<Product> selectByCartStatus(Member member, String cartStatus, int maxCount) throws SQLException {
		String hql = "select new map(p.productNo as productNo, p.productName as productName, p.description as description, p.imgPath as imgPath) from Product p where p.cartStatus = ? and p.onCart = ?";
		if(ObjectUtils.isObjNotBlank(member) && StringUtils.isNotBlank(member.getAccount())) {
			hql = "select new map(p.productNo as productNo, p.productName as productName, p.salesPrice as salesPrice, p.description as description, p.imgPath as imgPath) from Product p where p.cartStatus = ? and p.onCart = ?";
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, cartStatus); // 商品狀態
		query.setString(1, ConstantUtils.TENDEBANK_Y); // 是否上架
		query.setMaxResults(maxCount); // 查詢幾筆
		List<Product> resultList = query.list();
		logger.debug("Product selectByHot result = " + resultList.size());
		return resultList;
	}
	
	@Override
	public List<Product> selectProductByKeyword(Member member, String keyword) throws SQLException {
		String hql = "select distinct new map(p.productNo as productNo, p.productName as productName, p.description as description, p.imgPath as imgPath) from Product p where (p.productNo like :keyWord or p.productName like :keyWord) and p.onCart = :onCart";
		if(ObjectUtils.isObjNotBlank(member) && StringUtils.isNotBlank(member.getAccount())) {
			hql = "select distinct new map(p.productNo as productNo, p.productName as productName, p.salesPrice as salesPrice, p.description as description, p.imgPath as imgPath) from Product p where (p.productNo like :keyWord or p.productName like :keyWord) and p.onCart = :onCart";
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("keyWord", "%" + keyword + "%"); // 關鍵字
		query.setString("onCart", ConstantUtils.TENDEBANK_Y); // 是否上架
		List<Product> resultList = query.list();
		logger.debug("Product findProductByKeyword result = " + resultList.size());
		return resultList;
	}
	
}
