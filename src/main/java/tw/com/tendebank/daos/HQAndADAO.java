package tw.com.tendebank.daos;

import java.util.List;

import tw.com.tendebank.beans.QAndA;

/**
 * 問答Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HQAndADAO extends BaseDAO<QAndA,String>{
	public List<QAndA> selectByCategory(String key);
}
