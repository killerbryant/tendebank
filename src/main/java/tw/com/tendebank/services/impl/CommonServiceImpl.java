package tw.com.tendebank.services.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tendebank.beans.Member;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.CommonService;
import tw.com.tendebank.utils.CommonUtils;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.DateUtils;

public class CommonServiceImpl implements CommonService {
	
	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public String getOrdersNoByMemberNo(Member member) {
		String resultNo = "";
		try {
			String memberNo = member.getMemberNo();
			String maxOrders = getDaoFactory().gethOrdersDao().findByMaxKey(memberNo);
			resultNo = DateUtils.getNowTime(DateUtils.DATE_PATTERN_YMD) + memberNo + CommonUtils.addZeroForStr(maxOrders, 6);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return resultNo;
	}
}
