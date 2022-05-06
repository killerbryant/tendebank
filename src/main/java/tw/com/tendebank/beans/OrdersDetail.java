package tw.com.tendebank.beans;

import java.io.Serializable;
import java.util.Date;

public class OrdersDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 訂單編號
	private String orderNo;
	
	// 產品編號
	private String productNo;
	
	// 產品單價
	private float productSalesprice;
	
	// 產品總價
	private float productTotalprice;
	
	// 產品數量
	private int productQuantity;
	
	// 物流廠商
	private String deliverVendor;
	
	// 物流編號
	private String deliverPackageNo;
	
	// 出貨時間
	private Date deliverDate;
	
	// 退貨數量
	private int returnQuantity;
	
	// 退貨時間
	private Date returnDate;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public float getProductSalesprice() {
		return productSalesprice;
	}

	public void setProductSalesprice(float productSalesprice) {
		this.productSalesprice = productSalesprice;
	}

	public float getProductTotalprice() {
		return productTotalprice;
	}

	public void setProductTotalprice(float productTotalprice) {
		this.productTotalprice = productTotalprice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getDeliverVendor() {
		return deliverVendor;
	}

	public void setDeliverVendor(String deliverVendor) {
		this.deliverVendor = deliverVendor;
	}

	public String getDeliverPackageNo() {
		return deliverPackageNo;
	}

	public void setDeliverPackageNo(String deliverPackageNo) {
		this.deliverPackageNo = deliverPackageNo;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public int getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(int returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "OrdersDetail [orderNo=" + orderNo + ", productNo=" + productNo
				+ ", productSalesprice=" + productSalesprice
				+ ", productTotalprice=" + productTotalprice
				+ ", productQuantity=" + productQuantity + ", deliverVendor="
				+ deliverVendor + ", deliverPackageNo=" + deliverPackageNo
				+ ", deliverDate=" + deliverDate + ", returnQuantity="
				+ returnQuantity + ", returnDate=" + returnDate + "]";
	}
}
