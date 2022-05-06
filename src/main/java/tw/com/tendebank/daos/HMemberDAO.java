package tw.com.tendebank.daos;

import java.sql.SQLException;

import tw.com.tendebank.beans.Member;

/**
 * 會員Hibernate DAO 介面
 * @author Edison
 *
 */
public interface HMemberDAO extends BaseDAO<Member,String>{
	public String findByMaxKey(String key) throws SQLException;
	
	public Member findByAccountAndMail(Member member) throws SQLException;
}
