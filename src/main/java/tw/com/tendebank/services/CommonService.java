package tw.com.tendebank.services;

import tw.com.tendebank.beans.Member;

/**
 * 通用業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface CommonService {
	
	public String getOrdersNoByMemberNo(Member member);
}
