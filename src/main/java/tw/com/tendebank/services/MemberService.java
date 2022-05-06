package tw.com.tendebank.services;

import tw.com.tendebank.beans.Member;

/**
 * 會員業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface MemberService {
	
	public boolean insert(Member member);
	
	public boolean updatePassword(Member member, String resetPassword);
	
	public Member selectMemberByAccount(Member member);
	
	public String selectMaxMemberNo();
	
	public String queryMemberService(Member member, String ctxPath);
}
