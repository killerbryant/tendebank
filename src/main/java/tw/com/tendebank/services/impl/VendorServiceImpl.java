package tw.com.tendebank.services.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tendebank.beans.Vendor;
import tw.com.tendebank.daos.DaoFactory;
import tw.com.tendebank.services.VendorService;
import tw.com.tendebank.utils.CommonUtils;
import tw.com.tendebank.utils.ConstantUtils;
import tw.com.tendebank.utils.ObjectUtils;

/**
 * 供應商業務邏輯 Service 實作
 * @author Edison
 *
 */
public class VendorServiceImpl implements VendorService {
	
	private static Logger logger = Logger.getLogger(VendorServiceImpl.class);
	
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Transactional
	@Override
	public boolean insert(Vendor vendor) {
		boolean result = true;
		try {
			String vendorNo = vendor.getVendorNo();
			Vendor qryVendor = getDaoFactory().gethVendorDao().findByKey(vendorNo);
			
			
			if(ObjectUtils.isObjNotBlank(qryVendor)){
				String newVendorNo = selectMaxVendorNo(vendorNo);
				logger.debug("新的供應商編號 = " + newVendorNo);
				vendor.setVendorNo(newVendorNo);
			}
			getDaoFactory().gethVendorDao().insert(vendor);
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

	@Transactional
	@Override
	public String selectMaxVendorNo(String vendorNo) {
		String resultNo = "";
		int maxNum = 0;
		try {
			vendorNo = vendorNo.substring(0,1);
			String maxvendor = getDaoFactory().gethVendorDao().findByMaxKey(vendorNo+"%");
			maxNum = Integer.parseInt(maxvendor.substring(1))+1;
			resultNo = vendorNo + CommonUtils.addZeroForStr(String.valueOf(maxNum), ConstantUtils.VENDOR_NUMBER_LENGTH);
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return resultNo;
	}

}
