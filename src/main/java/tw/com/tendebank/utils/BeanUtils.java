package tw.com.tendebank.utils;

import tw.com.tendebank.beans.OrdersDetail;
import tw.com.tendebank.beans.Product;

/**
 * Bean轉換工具
 * @author Edison
 *
 */
public class BeanUtils {

	// 商品轉訂單明細
	public static OrdersDetail productToOrdersDetail(Product product, String orderNo) {
		
		OrdersDetail ordersDetail = new OrdersDetail();
		ordersDetail.setOrderNo(orderNo);
		ordersDetail.setProductNo(product.getProductNo());
		ordersDetail.setProductSalesprice(product.getSalesPrice());
		ordersDetail.setProductTotalprice(product.getSalesPrice()*Integer.parseInt(product.getProductCount()));
		ordersDetail.setProductQuantity(Integer.parseInt(product.getProductCount()));
		
		return ordersDetail;
	}
}
