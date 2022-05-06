package tw.com.tendebank.daos;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * 基礎DAO
 * @author Edison
 *
 */
public interface BaseDAO<T ,K> {
	public T select() throws SQLException;
	public void insert(T t) throws SQLException;
	public void update(T t) throws SQLException;
	public void deleteByKey(K k) throws SQLException;
	public T findByKey(K k) throws SQLException;
	public List<T> findAll(HashMap<String,String> param) throws SQLException;
}
