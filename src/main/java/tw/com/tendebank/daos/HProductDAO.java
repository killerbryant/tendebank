package tw.com.tendebank.daos;

import java.sql.SQLException;
import java.util.List;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.beans.Product;

/**
 * 產品Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HProductDAO extends BaseDAO<Product,String>{
	public String findByMaxKey(String key) throws SQLException;
	
	public List<Product> selectByType(String type, Member member) throws SQLException;
	
	public List<Product> selectByCartStatus(Member member, String cartStatus, int maxCount) throws SQLException;

	public List<Product> selectProductByKeyword(Member member, String keyword) throws SQLException;
}
