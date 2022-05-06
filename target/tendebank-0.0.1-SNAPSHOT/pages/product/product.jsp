<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" href="css/jqpagination.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="js/jquery.jqpagination.js"></script>
<script src="js/jquery.fly.js"></script>
<script src="js/requestAnimationFrame.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<style type="text/css">
#product_div {
	margin-left: 8%;
	display: inline-block;
	height: 650px
}

.div-left,.div-right {
	float: left;
	width: 45%;
	min-height: 300px;
	padding: 10px;
}

.div-right-content {
	width: 280px;
	min-height: 250px;
	border: 0px solid #FF6699;
}

.div-right-button {
	width: 280px;
	min-height: 50px;
	border: 0px solid #FF6699;
	text-align: center;
}

.box {
	float: left;
	width: 198px;
	height: 320px;
	margin-left: 5px;
	border: 1px solid #e0e0e0;
	text-align: center;
}

.box p {
	line-height: 20px;
	padding: 4px 4px 10px 4px;
	text-align: left
}

.box:hover {
	border: 1px solid #f90
}

.box h4 {
	line-height: 32px;
	font-size: 14px;
	color: #ff3300;
	font-weight: 500
}

.box h4 span {
	font-size: 20px
}

.u-flyer {
	display: block;
	width: 50px;
	height: 50px;
	border-radius: 50px;
	position: fixed;
	z-index: 9999;
}

.m-sidebar {
	position: fixed;
	top: 0;
	right: 0;
	background: #000000;
	z-index: 2000;
	width: 35px;
	height: 100%;
	font-size: 12px;
	color: #000000;
}

.cart {
	color: #000000;
	text-align: center;
	line-height: 20px;
	padding: 200px 0 0 0px;
	background: #000000;
}

.cart span {
	color: #ffffff;
	display: block;
	width: 20px;
	margin: 0 auto;
	cursor: pointer;
}

.cart i {
	color: #000000;
	width: 35px;
	height: 35px;
	display: block;
	background: url(img/car.png) no-repeat;
}

#msg {
	position: fixed;
	top: 300px;
	right: 35px;
	z-index: 10000;
	width: 1px;
	height: 52px;
	line-height: 52px;
	font-size: 20px;
	text-align: center;
	color: #ffffff;
	background: #FF8C00;
	display: none
}
</style>
<script type="text/javascript">
	var jsonProductsData;
	var totalCount;
	var maxPage; // 最多幾頁
	var productType = "${productType}";
	$(function() {

		$.ajax({
			type : "POST",
			url : "getProductByType.do",
			data : {
				"productType" : productType
			},
			async : false,
			timeout : 15000,
			dataType : "json",
			success : function(data) {
				jsonProductsData = data.products;
				totalCount = data.totalCount;
				productTotalCount = data.productTotalCount;

				if (parseInt(productTotalCount) != 0) {
					$("#shoppingCartSpan").html("購物車 " + productTotalCount);
				}

				maxPage = Math.ceil(totalCount / pageItem);
				if (jsonProductsData.length > 0) {
					drawPageTable(jsonProductsData, 1, pageItem);
				}
			},
			complete : function() {

			}
		});

		// 分頁
		$('.pagination').jqPagination({
			max_page : maxPage,
			page_string : '當前第{current_page}頁,共{max_page}頁',
			paged : function(page) {
				drawPageTable(jsonProductsData, page, pageItem);
			}
		});

		$('body').on("click", '.addcar', function(event) {

			var addcar = $(this);
			var img = addcar.parent().find('img').attr('src');

			var flyer = $('<img class="u-flyer" src="' + img + '">');
			//console.log(event.pageX + " == " + event.pageY);
			//console.log(addcar.offset().left + " == " + addcar.offset().top);
			//console.log($("#end").position().left + " == " + $("#end").position().top);
			flyer.fly({
				start : {
					left : event.pageX, //開始位置（必填）#fly元素會被設置成position: fixed 
					top : event.pageY
				//開始位置（必填） 
				},
				end : {
					left : $("#end").offset().left, //結束位置（必填）
					top : $("#end").offset().top, //結束位置（必填） 
					width : 0, //結束時寬度 
					height : 0
				//結束時高度 
				},
				speed : 1.1, //越大越快，默認1.2
				vertex_Rtop : 100, //運動軌跡最高點top值，默認20
				onEnd : function() { //結束回檔
					flyer.remove(); //移除dom
				}
			});
		});

		$("#shoppingCartSpan").click(function() {
			var productTotalCount = $(this).text().trim().replace("購物車", "");
			if (parseInt(productTotalCount) > 0) {
				$("[class='sub-memu-2'] li").first().find("a").click();
			} else {
				alert("未選購任何商品！！");
			}
		});

		initSelect();
	});
	/*
		data = JSON DATA;
		page = 第幾頁;
		onePageCount = 一頁顯示幾筆;
	 */
	function drawPageTable(data, page, onePageCount) {
		var count = 1;
		var itemMinCount = page; // 1 3 5、1 4 7
		var itemMaxCount = onePageCount; // 2 4 6、3 6 9
		if (page != 1) {
			itemMinCount = page * onePageCount - (onePageCount - 1);
			itemMaxCount = page * onePageCount;
		}
		$("#product_div").empty();
		var divBox = $("<div class='box'>");
		var p = $("<p>");
		var img = $("<img style='width: 180px;height: 180px;'>");
		var h4 = $("<h4>");
		var span = $("<span>");
		var a = $("<a>");

		$.each(data, function(key, product) {
			// 分頁的第幾筆
			if (count >= itemMinCount && count <= itemMaxCount) {
				// 繪出內容
				var productNo = product.productNo;
				var productName = product.productName;
				var saleprice = product.salePrice;
				var imgPath = product.imgPath;
				var newDivBox = divBox.clone().append(
						p.clone().text(productName)).append(img.clone().attr({
					"src" : imgPath,
				})).append(
						h4.clone().html(span.clone().text("NT. " + saleprice)))
						.append(a.clone().attr(
										{
											"href" : "javascript: void(0);",
											"data-toggle" : "modal",
											"data-target" : "#myModal",
											"onclick" : "openContents('"
													+ productName + "','"
													+ JSON.stringify(product)
													+ "')"
										}).text("商品詳細資訊")).append("<br>")
						.append(a.clone().attr(
										{
											"href" : "javascript: void(0);",
											"class" : "button orange addcar",
											"onclick" : "addProduct('"
													+ productNo + "','"
													+ JSON.stringify(product)
													+ "')"
										}).text("加入購物車"));
				$("#product_div").append(newDivBox);
				if (count == itemMaxCount) {
					return false;
				}
			}
			count++;
		});
	}

	// bootstrap modal 顯示商品內容
	function openContents(title, dataStr) {
		// Convert String to Object
		var data = JSON.parse(dataStr);
		var img = $('<img>').attr({
			"alt" : "商品圖片",
			"style" : "height:250px;width:250px;",
			"src" : data.imgPath
		});
		var productDescript = "產品內容<br>" + data.description;

		// 設置內容高度
		$(".modal-body").css({
			"height" : "350px"
		});

		// 設置產品內容
		if (data != "undefined") {

			$("#myModalLabel").html(title);

			// 顯示圖片
			$(".div-left").html(img);

			// 產品描述
			$(".div-right-content").html(productDescript);

			// 產生加入購物車連結
			$('#addProductLink').attr({
				"href" : "javascript: void(0);",
				"onclick" : "addProduct('" + data.productNo + "','" + dataStr + "')"
			}).text("加入購物車");
		} else {
			$(".modal-body").html("<font style='color:red;'>此商品停售中...請來電洽詢!</font>");
		}
	}

	// 參數 產品編號 JSON字串 是否顯示加入購物車訊息
	function addProduct(productNo, dataStr) {
		// Convert String to JSONObject
		var jsonData = JSON.parse(dataStr);
		jsonData["productCount"] = $("#productCount").val();
		//console.log(jsonData.productNo + " 加入購物車 " + JSON.stringify(jsonData));

		$.ajax({
			type : "POST",
			url : "addCartByProduct.do",
			data : JSON.stringify(jsonData),
			async : false,
			timeout : 15000,
			dataType : "json",
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				var productTotalCount = data.productTotalCount;
				$("#shoppingCartSpan").text("購物車 " + productTotalCount);
				$("#msg").show().animate({
					width : '250px'
				}, 200).fadeOut(1000);
			},
			complete : function(data) {

			}
		});
	}

	// 產生下拉選單
	function initSelect() {
		var optionHtml = "";
		for (var i = 1; i <= 10; i++) {
			optionHtml += '<option value="'+ i +'">' + i + '</option>';
		}
		$("#productCount").html(optionHtml);
	}
</script>
</head>
<body>
	商品專區
	<br>
	<div id="product_div">
		<div class="box">
			<p></p>
			<img src="img/lg.jpg" width="180" height="180">
			<h4>
				NT.<span>3499.00</span>
			</h4>
			<a href="javascript: void(0);" class="button orange addcar">加入購物車</a>
		</div>
	</div>
	<br>
	<div class="pagination" style="margin-left: 35%;">
		<a href="#" class="first" data-action="first">&laquo;</a> <a href="#"
			class="previous" data-action="previous">&lsaquo;</a> <input
			type="text" readonly="readonly" data-max-page="1" /> <a href="#"
			class="next" data-action="next">&rsaquo;</a> <a href="#" class="last"
			data-action="last">&raquo;</a>
	</div>
	<div class="m-sidebar">
		<div class="cart">
			<i id="end"></i><span id="shoppingCartSpan">購物車</span>
		</div>
	</div>
	<div id="msg">已成功加入購物車！</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div class="div-left">
						<img alt="商品圖片" height="200" width="200" src="" />
					</div>
					<div class="div-right">
						<div class="div-right-content"></div>
						<div class="div-right-button">
							數量: <select class="productCount" id="productCount">

							</select> <br> <a id="addProductLink" href=""></a>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
