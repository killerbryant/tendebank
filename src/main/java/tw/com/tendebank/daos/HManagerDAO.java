package tw.com.tendebank.daos;

import java.sql.SQLException;

import tw.com.tendebank.beans.Manager;

/**
 * 管理員Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HManagerDAO extends BaseDAO<Manager,String>{
	public String findByMaxKey(String key) throws SQLException;
	
	public Manager findByAccountAndMail(Manager manager) throws SQLException;
}
