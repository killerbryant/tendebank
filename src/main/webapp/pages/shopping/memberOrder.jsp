<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/tdb.cart.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	var memberOrdersData = ${memberOrdersData};
	var memberHistoryOrdersData = ${memberHistoryOrdersData};
	$(function() {
		
	});
</script>
<div class="container" ng-controller="historyOrderCtrl">
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			 <li><a href="index.jsp">首頁</a></li>
			 <li class="active">訂單查詢</li>
		</ol>
	</div>
	<!-- Workspaces nav -->
    <ul class="nav nav-tabs">
        <li role="presentation" ng-repeat="wk in workspaces" ng-class="{active: currentWorkspace == wk}" ng-click="changeCurrentWorkspace(wk)"><a href="#">{{wk.name}}</a></li>
    </ul>

    <!-- Workspaces containers -->
    <div class="workspaceContainer" ng-repeat="wk in workspaces" ng-show="currentWorkspace == wk">
        <table  bs-table-control="wk.bsTableControl" data-mobile-responsive="true"></table>
    </div>
</div>