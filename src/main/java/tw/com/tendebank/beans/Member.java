package tw.com.tendebank.beans;

import java.io.Serializable;

/**
 * 會員Bean
 * @author Edison
 *
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 會員編號
	private String memberNo;
	
	// 會員帳號
	private String account;
	
	// 會員MD5密碼
	private String password;
	
	// 會員原始密碼
	private String originalPassword;
	
	// 姓
	private String lastname;
	
	// 名
	private String firstname;
	
	// 電話
	private String telephone;
	
	// 地址
	private String address;
	
	// 郵遞區號
	private String zipCode;
	
	// 電子郵件
	private String email;
	
	// 行動電話
	private String mobile;
	
	// 是否為認證會員
	private String approve;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", account=" + account
				+ ", password=" + password + ", originalPassword="
				+ originalPassword + ", lastname=" + lastname + ", firstname="
				+ firstname + ", telephone=" + telephone + ", address="
				+ address + ", zipCode=" + zipCode + ", email=" + email
				+ ", mobile=" + mobile + ", approve=" + approve + "]";
	}
	
}
