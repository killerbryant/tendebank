package tw.com.tendebank.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import tw.com.tendebank.beans.StreetName;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.StreetNameService;

public class StreetNameServiceImpl implements StreetNameService {
	
	private static Logger logger = Logger.getLogger(StreetNameServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<StreetName> selectCity() {
		List<StreetName> cityList = null;
		try {
			cityList = getDaoFactory().gethStreetNameDao().selectCity();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return cityList;
	}

	@Override
	public List<StreetName> selectCountry(String city) {
		List<StreetName> countryList = null;
		try {
			countryList = getDaoFactory().gethStreetNameDao().selectCountry(city);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return countryList;
	}

	@Override
	public List<StreetName> selectRoad(String city, String country) {
		List<StreetName> roadList = null;
		try {
			roadList = getDaoFactory().gethStreetNameDao().selectRoad(city, country);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return roadList;
	}

}
