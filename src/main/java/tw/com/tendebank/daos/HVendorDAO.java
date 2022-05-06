package tw.com.tendebank.daos;

import java.sql.SQLException;

import tw.com.tendebank.beans.Vendor;

/**
 * 供應商Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HVendorDAO extends BaseDAO<Vendor,String>{
	public String findByMaxKey(String key) throws SQLException;
}
