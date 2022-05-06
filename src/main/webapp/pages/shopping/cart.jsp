<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script src="js/tdb.cart.js"></script>
	<style type="text/css">
		
	</style>
	<script type="text/javascript">
		$(function() {
			// 關閉警示訊息回到第一類的購物車
			$("#cartMsg").bind('closed.bs.alert', function () {
				$("[class='sub-menu'] li").eq(0).find("a").click();
			});
		});
	</script>
	<section id="cart_items">
		<div id="cart_div_items" class="container" ng-controller="cartController">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					 <li><a href="index.jsp">首頁</a></li>
					 <li class="active">購物車</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed" ng-show="cart.length">
					<thead>
						<tr class="cart_menu">
							<td class="image" ng-class="{dropup:order==='-'}" ng-click="changeOrder('productNo')">項目<span class="caret active" ng-class="{active:orderType==='productNo'}"></span></td>
							<td class="description"></td>
							<td class="price">價格</td>
							<td class="quantity">數量</td>
							<td class="total">總價</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in cart | filter:search | orderBy:order+orderType">
							<td class="cart_product">
								<img ng-src="{{item.imgPath || 'img/withoutImage.jpg'}}" style="width: 90px;height: 90px;">
							</td>
							<td class="cart_description">
								<h4><a href="">{{item.productName}}</a></h4>
								<p>No: {{item.productNo}}</p>
							</td>
							<td class="cart_price">
								<p>{{item.salesPrice | number:2}}</p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<a class="cart_quantity_up" href="javascript:void(0);" ng-click="qadd(item.productNo)"> + </a>
									<input class="cart_quantity_input" type="text" name="quantity" value="{{item.productCount}}" ng-change="qchange(item.productNo)" ng-model="item.productCount" autocomplete="off" size="5">
									<a class="cart_quantity_down" href="javascript:void(0);" ng-click="qreduce(item.productNo)"> - </a>
								</div>
							</td>
							<td class="cart_total">
								<p class="cart_total_price">{{item.salesPrice * item.productCount | number:2}}</p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="javascript:void(0);" ng-click="remove(item.productNo)"><i class="fa fa-times"></i></a>
							</td>
						</tr>
						<tr>
							<td colspan="1">
		                        
		                    </td>
		                    <td colspan="3">
		                        <strong>總購買數:</strong><strong id="buyItemCount">{{totalQuantity()}}</strong>
		                    </td>
		                    <td colspan="2">
		                    	<strong>合計金額:</strong><strong id="orderTotalprice">{{totalPrice() | number:2}}</strong>
		                    </td>
		                </tr>
		                <tr>
		                	<td colspan="1">
		                        
		                    </td>
		                    <td colspan="1">
		                        <button id="clearCart" type="button" class="btn btn-danger" ng-click="removeAll()"><span class="fa fa-trash-o"></span>清空購物車</button>
		                    </td>
		                    <td colspan="2"></td>
		                    <td>
		                    	<button id="continueCart" type="button" class="btn btn-primary" ng-click="continueCart()"><span class="fa fa-arrow-left"></span>繼續購物</button>
		                    </td>
		                    <td>
		                    	<button id="goCheckout" type="button" class="btn btn-success" ng-click="payCart()"><span class="fa fa-shopping-cart"></span>前往結帳</button>
		                    </td>
		                </tr>
						<tr>
							<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
								<div class="modal-content">
									<ul class="list-inline item-details">
										<li><a href="http://themifycloud.com">Ecommerce templates</a></li>
										<li><a href="http://themescloud.org">Ecommerce themes</a></li>
									</ul>
								</div>
							</div>
						</tr>
					</tbody>
				</table>
				<div class="row-fluid">
					<div id="cartMsg" class="alert alert-warning alert-dismissible text-center" role="alert" ng-show="!cart.length">
				    	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
					    </button>
					    <strong>您的購物車為空！</strong>
					</div>
				</div>
			</div>
			<div id="cartItemDiv" class="table-responsive cart_info" style="display: none;">
		        <table id="ordersTable" class="table table-condensed">
		            <thead>
		                <tr>
		                    <th>商品名稱</th>
		                    <th>購買數量</th>
		                    <th>商品單價</th>
		                    <th>商品總價</th>
		                </tr>
		            </thead>
		            <tbody>
		                <tr ng-repeat="item in cart | filter:search | orderBy:order+orderType">
		                    <td class="cart_description">
								<h4 style="color: blue;">{{item.productName}}</h4>
								<p>No: {{item.productNo}}</p>
							</td>
		                    <td class="cart_quantity">
		                    	<p style="color:red;">{{item.productCount}}</p>
		                    </td>
		                    <td class="cart_price">
								<p>{{item.salesPrice | number:2}}</p>
							</td>
		                    <td class="cart_total">
								<p class="cart_total_price">{{item.salesPrice * item.productCount | number:2}}</p>
							</td>
		                </tr>
		                <tr>
		                    <td colspan="3">
		                        <strong>總購買數:</strong><strong id="buyItemCount">{{totalQuantity()}}</strong>
		                    </td>
		                    <td colspan="3" style="text-align: right;">
		                    	<strong>合計金額:</strong><strong id="orderTotalprice" style="color:red;">{{totalPrice() | number:2}}</strong>
		                    </td>
		                </tr>
		            </tbody>
		        </table>
		    </div>
		</div>
	</section>
	