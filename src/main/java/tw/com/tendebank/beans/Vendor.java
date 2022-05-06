package tw.com.tendebank.beans;

import java.io.Serializable;
/**
 * 供應商Bean
 * @author Edison
 *
 */
public class Vendor implements Serializable{

	private static final long serialVersionUID = 1L;

	// 供應商代號
	private String vendorNo;
	
	// 供應商名稱
	private String companyName;
	
	// 地址
	private String address;
	
	// 郵遞區號
	private String zipCode;
	
	// 電話
	private String telephone;
	
	// 行動電話
	private String mobile;
	
	// 負責人
	private String vendorPerson;

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVendorPerson() {
		return vendorPerson;
	}

	public void setVendorPerson(String vendorPerson) {
		this.vendorPerson = vendorPerson;
	}
	
}
