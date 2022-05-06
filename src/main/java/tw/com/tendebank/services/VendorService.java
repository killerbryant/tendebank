package tw.com.tendebank.services;

import tw.com.tendebank.beans.Vendor;

/**
 * 供應商業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface VendorService {
	
	public String selectMaxVendorNo(String vendorNo);

	public boolean insert(Vendor vendor);
	
}
