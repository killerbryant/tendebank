package tw.com.tendebank.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 訂單編號
	private String orderNo;
	
	// 會員編號
	private String memberNo;
	
	// 收件人
	private String addressee;
	
	// 收件人電話
	private String addresseeTelephone;
	
	// 收件人手機
	private String addresseeMobile;
	
	// 收件人地址
	private String addresseeAddress;
	
	// 購買時間
	private Date buyDate;
	
	// 訂單建立時間
	private Date createDate;
	
	// 訂單狀態
	private String orderStatus;
	
	// 付款狀態
	private String payStatus;
	
	// 運送狀態
	private String deliverStatus;
		
	// 訂單總計
	private float orderTotalprice;
	
	// 應付金額
	private float orderPayprice;
	
	// 匯款帳號
	private String remittanceAccount;
	
	// 明細
	private Set<OrdersDetail> ordersSet = new HashSet<OrdersDetail>();

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getAddresseeTelephone() {
		return addresseeTelephone;
	}

	public void setAddresseeTelephone(String addresseeTelephone) {
		this.addresseeTelephone = addresseeTelephone;
	}

	public String getAddresseeMobile() {
		return addresseeMobile;
	}

	public void setAddresseeMobile(String addresseeMobile) {
		this.addresseeMobile = addresseeMobile;
	}

	public String getAddresseeAddress() {
		return addresseeAddress;
	}

	public void setAddresseeAddress(String addresseeAddress) {
		this.addresseeAddress = addresseeAddress;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public float getOrderTotalprice() {
		return orderTotalprice;
	}

	public void setOrderTotalprice(float orderTotalprice) {
		this.orderTotalprice = orderTotalprice;
	}

	public float getOrderPayprice() {
		return orderPayprice;
	}

	public void setOrderPayprice(float orderPayprice) {
		this.orderPayprice = orderPayprice;
	}

	public String getRemittanceAccount() {
		return remittanceAccount;
	}

	public void setRemittanceAccount(String remittanceAccount) {
		this.remittanceAccount = remittanceAccount;
	}

	public Set<OrdersDetail> getOrdersSet() {
		return ordersSet;
	}

	public void setOrdersSet(Set<OrdersDetail> ordersSet) {
		this.ordersSet = ordersSet;
	}

	@Override
	public String toString() {
		return "Orders [orderNo=" + orderNo + ", memberNo=" + memberNo
				+ ", addressee=" + addressee + ", addresseeTelephone="
				+ addresseeTelephone + ", addresseeMobile=" + addresseeMobile
				+ ", addresseeAddress=" + addresseeAddress + ", buyDate="
				+ buyDate + ", createDate=" + createDate + ", orderStatus="
				+ orderStatus + ", payStatus=" + payStatus + ", deliverStatus="
				+ deliverStatus + ", orderTotalprice=" + orderTotalprice
				+ ", orderPayprice=" + orderPayprice + ", remittanceAccount="
				+ remittanceAccount + ", ordersSet=" + ordersSet + "]";
	}
	
}
