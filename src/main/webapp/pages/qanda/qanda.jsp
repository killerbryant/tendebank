<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style type="text/css">
	
	</style>
	<script type="text/javascript">
	var jsonGodsData;
	var pageName = "${pageName}";
		$(function() {
			$.ajax({
	            type: "POST",
	            url: "getQAndAByPageName.do",
	            data: {"pageName":pageName},
	            async: false,
	            timeout: 15000,
	            dataType:"json",
	            success: function(data) {
	            	jsonGodsData = data.gods;
	            },
	            complete: function() {
	            	
	            }
	        });
		});
	</script>
	<div class="container" ng-controller="qAndaCtrl">
		<h2>祭祀常見問題</h2>
	
	    <!-- Q & A container -->
	    <div class="qAndaContainer">
	        <table bs-table-control="bsTableControl" data-mobile-responsive="true"></table>
	    </div>
	</div>

