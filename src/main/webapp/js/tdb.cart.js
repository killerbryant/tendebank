// 更新數量
function updateCartByProduct(productNo, quantity) {
	// 更新Session中商品數量
	$.ajax({
		url : "updateCartByProduct.do",
		dataType : "json",
		type : "POST",
		data : {
			productNo: productNo,
			quantity: quantity
		},
		async : false,
		timeout : 15000,
		success : function(data) {
			var itemCount = data.productTotalCount;
			$("#cartTotalCount").text("("+itemCount+")");
       	},
        error: function (result) {
            alert("Ajax request(updateCartByProduct) error");
        }
	});
}

// 移除商品
function removeProdctByCart(productNo) {
	// 移除Session中的商品
    $.ajax({
		url : "removeOrderByProductNo.do",
		dataType : "json",
		type : "POST",
		data : {
			productNo: productNo
		},
		async : false,
		timeout : 15000,
		success : function(data) {
			var itemCount = data.productTotalCount;
       	},
        error: function (result) {
            alert("Ajax request(removeOrderByProductNo) error");
        }
	});
}

// 清空購物車
function clearByCart() {
	// 清空Session中的商品
	$.ajax({
		url : "clearShoppingCartOrder.do",
		type : "POST",
		data: {},
		async : false,
		timeout : 15000,
		success : function(data) {
			$("#cartTotalCount").text("");
       	},
        error: function (result) {
            alert("Ajax request(clearShoppingCartOrder) error");
        }
	});
}

//更新匯款資訊
function updateOrderByBankInfo(orderNo, afterFiveNumber) {
	console.log("匯款後5碼 = " + afterFiveNumber);
	// 更新Session中商品數量
	$.ajax({
		url : "updateOrderByBankInfo.do",
		dataType : "json",
		type : "POST",
		data : {
			orderNo: orderNo,
			afterFiveNumber: afterFiveNumber
		},
		async : false,
		timeout : 15000,
		success : function(data) {
			var msg = data.orderMsg;
			alert(msg);
       	},
        error: function (result) {
            alert("Ajax request(updateOrderByBankInfo) error");
        }
	});
}