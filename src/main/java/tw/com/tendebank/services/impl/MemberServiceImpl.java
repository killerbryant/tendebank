package tw.com.tendebank.services.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.MemberService;
import tw.com.tendebank.utils.CommonUtils;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.MD5Utils;
import tw.com.tendebank.utils.MailUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 會員業務邏輯 Service 實作
 * @author Edison
 *
 */
public class MemberServiceImpl implements MemberService {
	
	private static Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	private MailUtils mailUtils;

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public MailUtils getMailUtils() {
		return mailUtils;
	}

	public void setMailUtils(MailUtils mailUtils) {
		this.mailUtils = mailUtils;
	}

	@Transactional
	@Override
	public boolean insert(Member member) {
		boolean result = true;
		try {
			String memberAccount = member.getAccount();
			String password = member.getPassword();
			logger.debug("UI 傳入帳號 = " + memberAccount + " 密碼 = " + password);
			Member qryMember = getDaoFactory().gethMemberDao().findByKey(memberAccount);
			
			if(ObjectUtils.isObjNotBlank(qryMember)) {
				result = false;
			} else {
				// 系統取編號
				String memberNo = selectMaxMemberNo();
				logger.debug("系統配給會員編號 = " + memberNo);
				// 有取到編號則新增
				if(StringUtils.isNotBlank(memberNo)) {
					member.setMemberNo(memberNo);
					// 密碼MD5加密
					member.setPassword(MD5Utils.md5Encryption(password));
					getDaoFactory().gethMemberDao().insert(member);
				} else {
					result = false;
				}
			}
			
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

	@Transactional
	@Override
	public Member selectMemberByAccount(Member member) {
		Member newMember = null;
		try {
			String memberAccount = member.getAccount();
			logger.debug("輸入的帳號:" + memberAccount);
			newMember = getDaoFactory().gethMemberDao().findByKey(memberAccount);
		} catch (SQLException e) {
			logger.debug("SQL Error");
		}
		return newMember;
	}
	
	@Transactional
	@Override
	public String selectMaxMemberNo() {
		String resultNo = "";
		int maxNum = 0;
		try {
			String maxMemberNo = getDaoFactory().gethMemberDao().findByMaxKey(ConstantUtils.MEMBER_NO_FIRST_WORD+"%");
			logger.debug("最大的會員編號 " + maxMemberNo);
			if(StringUtils.isNotBlank(maxMemberNo)) {
				maxNum = Integer.parseInt(maxMemberNo.substring(1));
			}
			resultNo = ConstantUtils.MEMBER_NO_FIRST_WORD + CommonUtils.addZeroForStr(String.valueOf(maxNum+1), ConstantUtils.MEMBER_NUMBER_LENGTH);
			
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return resultNo;
	}

	@Transactional
	@Override
	public String queryMemberService(Member member, String ctxPath) {
		Member qryMember = null;
		String email = "", result = "success";
		try {
			String memberAccount = member.getAccount();
			logger.debug("查詢的帳號:" + memberAccount);
			email = member.getEmail();
			logger.debug("查詢的email:" + email);
			// 尋找輸入帳號密碼存在
			qryMember = getDaoFactory().gethMemberDao().findByAccountAndMail(member);
			logger.debug("查詢的值" + qryMember.toString());
			if(ObjectUtils.isObjNotBlank(qryMember)) {
				// 比對資料庫PWD和OPWD是否一樣，一樣表密碼查詢，不一樣代表已經申請更換密碼
				if(StringUtils.equals(qryMember.getPassword(), MD5Utils.md5Encryption(qryMember.getOriginalPassword()))) {
					// 產生20碼的新密碼
					String newPwd = CommonUtils.randomString(20);
					
					String msg = "<a href='" + ConstantUtils.DOMAIN_NAME + ctxPath + "/resetPage.do?acc=" + memberAccount + "&email=" + email + "&resetPwd=" + newPwd + "'>密碼重置</a>";
					// 發送更新密碼的mail
					getMailUtils().sendMail(ConstantUtils.SMTP_AUTH_USER, email, "找回密碼", "請點選重置密碼連結更新您的密碼 -> " + msg);
					// member 設定新的密碼
					qryMember.setOriginalPassword(newPwd);
					// 更新密碼
					getDaoFactory().gethMemberDao().update(qryMember);
				} else {
					String oPwd = qryMember.getOriginalPassword();
					String msg = "<a href='" + ConstantUtils.DOMAIN_NAME + ctxPath + "/resetPage.do?acc=" + memberAccount + "&email=" + email + "&resetPwd=" + oPwd + "'>密碼重置</a>";
					// 發送更新密碼的mail
					getMailUtils().sendMail(ConstantUtils.SMTP_AUTH_USER, email, "找回密碼", "請點選重置密碼連結更新您的密碼 -> " + msg);
					
					result = "apply";
				}
			} else {
				result = "No find";
			}
		} catch (SQLException e) {
			logger.debug("SQL Error");
			result = "Error";
		}
		return result;
	}

	@Transactional
	@Override
	public boolean updatePassword(Member member, String resetPassword) {
		Member qryMember = null;
		String email = "";
		boolean result = true;
		try {
			String memberAccount = member.getAccount();
			logger.debug("更新的帳號:" + memberAccount);
			email = member.getEmail();
			logger.debug("更新的email:" + email);
			// 尋找輸入帳號密碼存在
			qryMember = getDaoFactory().gethMemberDao().findByAccountAndMail(member);
			logger.debug("qryMember = " + qryMember);
			logger.debug(qryMember.getOriginalPassword() + " 亂數產生的密碼 = " + resetPassword);
			if(ObjectUtils.isObjNotBlank(qryMember) && StringUtils.equals(qryMember.getOriginalPassword(), resetPassword)) {
				qryMember.setPassword(MD5Utils.md5Encryption(member.getOriginalPassword()));
				qryMember.setOriginalPassword(member.getOriginalPassword());
				// 更新密碼
				getDaoFactory().gethMemberDao().update(qryMember);
			} else {
				result = false;
			}
		} catch (SQLException e) {
			logger.debug("SQL Error");
			result = false;
		}
		return result;
	}

}
