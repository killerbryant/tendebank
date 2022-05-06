<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script src="js/tdb.cart.js"></script>
	<style type="text/css">
		#ordersTable {
	    	width:70%;
		}
		
		#cartMsg {
			width:70%;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			// 關閉警示訊息回到第一類的購物車
			$("#cartMsg").bind('closed.bs.alert', function () {
				$("[class='sub-memu-1'] li").eq(0).find("a").click();
			});
		});
	</script>
	<div id="cartItemDiv" class="container" ng-controller="cartController">
        <table id="ordersTable" class="table table-hover" ng-show="cart.length">
            <!-- <caption class="text-center">
                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;歡迎使用購物車
            </caption> -->
            <thead>
                <tr>
                    <th ng-class="{dropup:order==='-'}" ng-click="changeOrder('productNo')">商品編號<span class="caret active" ng-class="{active:orderType==='productNo'}"></span></th>
                    <th ng-class="{dropup:order==='-'}" ng-click="changeOrder('productName')">商品名稱<span class="caret active" ng-class="{active:orderType==='productName'}"></span></th>
                    <th>購買數量</th>
                    <th>商品單價</th>
                    <th>商品總價</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="item in cart | filter:search | orderBy:order+orderType">
                    <td>{{item.productNo}}</td>
                    <td>{{item.productName}}</td>
                    <td class="col-xs-2">
                        <div class="input-group col-xs-10">
                          <span class="input-group-btn">
                            <button class="quitityBtn btn btn-info btn-sm" type="button" ng-click="qreduce(item.productNo)">-</button>
                          </span>
                          <input type="text" class="form-control input-sm" value="{{item.productCount}}" ng-change="qchange(item.productNo)" ng-model="item.productCount" >
                          <span class="input-group-btn">
                            <button class="quitityBtn btn btn-info btn-sm" type="button" ng-click="qadd(item.productNo)">+</button>
                          </span>
                        </div>
                    </td>
                    <td class="row col-xs-2">{{item.salePrice | number:2}}</td>
                    <td class="row col-xs-2">{{item.salePrice * item.productCount | number:2}}</td>
                    <td>
                        <button type="button" class="removeBtn btn btn-danger" ng-click="remove(item.productNo)"><span class="fa fa-close"></span>移除</button>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <strong id="buyItemCount">總購買數:{{totalQuantity()}}</strong><br>
                    </td>
                    <td colspan="2">
                    	<strong>合計金額:{{totalPrice() | number:2}}</strong>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
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
            </tbody>
        </table>
        <div id="cartMsg" class="alert alert-warning alert-dismissible text-center" role="alert" ng-show="!cart.length">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            	<span aria-hidden="true">&times;</span>
            </button>
            <strong>您的購物車為空！</strong>
        </div>
    </div>