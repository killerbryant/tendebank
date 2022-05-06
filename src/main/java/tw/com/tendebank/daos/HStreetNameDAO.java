package tw.com.tendebank.daos;

import java.sql.SQLException;
import java.util.List;

import tw.com.tendebank.beans.StreetName;

/**
 * 產品Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HStreetNameDAO extends BaseDAO<StreetName,String> {
	
	public List<StreetName> selectCity() throws SQLException;
	
	public List<StreetName> selectCountry(String city) throws SQLException;
	
	public List<StreetName> selectRoad(String city, String country) throws SQLException;
}
