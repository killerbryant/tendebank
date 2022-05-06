<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style type="text/css">
	
	</style>
	<script type="text/javascript">
		var manageOrdersStatus = "${manageOrderStatus}";
		$(function () {
			
		});
	</script>
	<div class="container" ng-controller="manageHistoryOrdersCtrl">
		<h2>歷史管理會員訂單</h2>
	
	    <!-- 會員訂單 container -->
	    <div class="manageOrdersContainer">
	        <table bs-table-control="bsTableControl" data-mobile-responsive="true"></table>
	    </div>
	</div>