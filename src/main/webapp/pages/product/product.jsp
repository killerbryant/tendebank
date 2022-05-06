<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<script src="js/jquery.fly.js"></script>
		<script type="text/javascript">
			var jsonProductsData;
			var totalCount;
			var maxPage; // 最多幾頁
			var productType = "${productType}";
			var memberAcc = "${sessionScope.member.account}";
			var productKeyword = "${keywords}";
			$(function() {
				// 關閉警示訊息回到第一類的購物車
				$("#cartMsg").bind('closed.bs.alert', function () {
					$("[class='sub-menu'] li").eq(0).find("a").click();
				});
			});
		</script>
		<div class="container" ng-controller="productShowCtrl">
			<div class="row">
				<div class="col-sm-1">
					<div class="left-sidebar">
						
					</div>
				</div>
				<div class="col-sm-10 padding-right" style="height:800px;">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">商品目錄</h2>
						<div class="col-sm-4" ng-repeat="item in products | pageStartFrom: curPage * pageSize | limitTo: pageSize">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img ng-src="{{item.imgPath || 'img/withoutImage.jpg'}}" style="width: 180px;height: 180px;">
										<h2 ng-show="item.salesPrice">NT. {{item.salesPrice}}</h2>
										<h2 ng-show="!item.salesPrice">請來店洽詢</h2>
										<a href="javascript: void(0);" ng-click="openContents($index)"><p>{{item.productName}}</p></a>
										<a ng-disabled="!item.salesPrice" href="javascript: void(0);" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>加入購物車</a>
									</div>
									<div class="product-overlay" ng-show="item.salesPrice">
										<div class="overlay-content">
											<h2 ng-show="item.salesPrice">NT. {{item.salesPrice}}</h2>
											<h2 ng-hide="item.salesPrice">請來電洽詢或加入付費會員</h2>
											<a href="javascript: void(0);" ng-click="openContents($index)"><p>{{item.productName}}</p></a>
											<a href="javascript: void(0);" class="btn btn-default add-to-cart" ng-click="addProduct($index)"><i class="fa fa-shopping-cart"></i>加入購物車</a>
										</div>
									</div>
									<img src="img/home/sale.png" class="new" alt="" />
								</div>
							</div>
						</div>
					</div><!--features_items-->
					<div class="row">
						<div class="col-sm-1">
						</div>
						<div class="text-center col-sm-10" style="margin-bottom:5px;" ng-show="pageCount != 0">
							<button class="btn btn-primary btn-sm glyphicon glyphicon-fast-backward" ng-click="curPage = 0" ng-disabled="curPage == 0">首頁</button>
						    <button class="btn btn-primary btn-sm glyphicon glyphicon-step-backward" ng-click="curPage = curPage - 1" ng-disabled="curPage <= 0">上一頁</button>
						    第{{curPage + 1}}頁/共{{pageCount + 1}}頁
						    <button class="btn btn-primary btn-sm glyphicon glyphicon-step-forward" ng-click="curPage = curPage + 1" ng-disabled="curPage >= pageCount">下一頁</button>
						    <button class="btn btn-primary btn-sm glyphicon glyphicon-fast-forward" ng-click="curPage = pageCount" ng-disabled="curPage == pageCount">尾頁</button>
						</div>
					</div>
					<div class="row">
						<div id="cartMsg" class="alert alert-warning alert-dismissible text-center" role="alert" ng-show="!products.length">
						   	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
						    </button>
						    <strong>尚未有任何商品上架！</strong>
						</div>
					</div>
				</div>
			</div>
			<section id="productDetail" style="display: none;">
				<div class="container">
					<div class="row">
						<div class="padding-right">
							<div class="product-details" ng-model="productDetail"><!--product-details-->
								<div class="col-sm-4">
									<div class="view-product">
										<img ng-src="{{productDetail.imgPath || 'img/withoutImage.jpg'}}" style="width: 350px;height: 350px;" onError="this.onerror=null;this.src='img/withoutImage.jpg';">
									</div>
								</div>
								<div class="col-sm-5">
									<div class="product-information"><!--/product-information-->
										<img src="img/product-details/new.jpg" class="newarrival" alt="" />
										<h2>{{productDetail.productName}}</h2>
										<p>No: {{productDetail.productNo}}</p>
										<span ng-show="productDetail.salesPrice">
											<span>NT. {{productDetail.salesPrice}}</span>
											<label>數量:</label>
											<input type="text" id="productDetailCount" value="{{productDetail.productCount || 1}}" />
											<button type="button" class="btn btn-fefault cart" ng-click="addProductDetail(productDetail)">
												<i class="fa fa-shopping-cart"></i>加入購物車
											</button>
										</span>
										<span ng-show="!productDetail.salesPrice">
											<span>請來店洽詢</span>
										</span>
										<p><b>產品描述:</b> {{productDetail.description}}</p>
									</div><!--/product-information-->
								</div>
							</div><!--/product-details-->
						</div>
					</div>
				</div>
				<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-content">
						<ul class="list-inline item-details">
							<li><a href="http://themifycloud.com">Ecommerce templates</a></li>
							<li><a href="http://themescloud.org">Ecommerce themes</a></li>
						</ul>
					</div>
				</div>
			</section>
		</div>
