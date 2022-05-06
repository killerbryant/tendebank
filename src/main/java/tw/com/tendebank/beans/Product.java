package tw.com.tendebank.beans;

import java.io.Serializable;

/**
 * 產品Bean
 * @author Edison
 *
 */
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// 商品編號
	private String productNo;
	
	// 商品名稱
	private String productName;
	
	// 售價
	private int salesPrice;
	
	// 成本
	private int cost;
	
	// 供應商
	private String vendor;
	
	// 商品類型
	private String productType;
	
	// 數量
	private String productCount;
	
	// 是否上架
	private String onCart;
	
	// 產品描述
	private String description;
	
	// 圖片路徑
	private String imgPath;
	
	// 商品狀態
	private String cartStatus;
	
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public String getOnCart() {
		return onCart;
	}
	public void setOnCart(String onCart) {
		this.onCart = onCart;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getCartStatus() {
		return cartStatus;
	}
	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	}
}
