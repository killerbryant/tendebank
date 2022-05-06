package tw.com.tendebank.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tendebank.beans.QAndA;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.QAndAService;

public class QAndAServiceImpl implements QAndAService {
private static Logger logger = Logger.getLogger(QAndAServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Transactional
	@Override
	public List<QAndA> selectByCategory(String key) {
		logger.debug("QAndA Service == selectByCategory");
		return getDaoFactory().gethQAndADao().selectByCategory(key);
	}
}
