package tw.com.tendebank.services;

import tw.com.tendebank.beans.Manager;

/**
 * 管理員業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface ManagerService {
	
	public boolean insert(Manager member);
	
	public boolean updatePassword(Manager member, String resetPassword);
	
	public Manager selectManagerByAccount(Manager member);
	
	public String selectMaxManagerNo();
	
	public String queryManagerService(Manager member, String ctxPath);
}
