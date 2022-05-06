<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(function() {
			$('#queryPassword').click(function(){
				$.ajax({
		            type: "POST",
		            url: "queryMemberPassword.do",
		            data: $("#queryMemberPwd").serialize(),
		            async: false,
		            timeout: 15000,
		            dataType:"json",
		            success: function(data) {
		            	alert(data.successMsg);
		            },
		            complete: function() {
		            	
		            }
		        });
			});
		});
	</script>
	<style type="text/css">
		
	</style>
	<!-- 註冊表單開始 -->
	<div id="contentMembebr">
		<div class="container-fluid">
			<form id="queryMemberPwd" action="" method="post" class="form-horizontal" name="formQueryPassword" ng-controller="queryPasswordCtrl" novalidate>
				<div class="row">
					<h3 class="col-md-8 col-xs-8 page_title ">會員密碼查詢</h3>
				</div>
				<div class="row">
					<div class="col-md-8 col-xs-8 register">
						<div class="form-group">
							<label for="account" class="col-sm-3 control-label">帳號：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input type="text" class="form-control" name="account" placeholder="請輸入帳號" ng-model="account" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-user"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">Email：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input type="text" class="form-control" name="email" placeholder="請輸入有效的Email" ng-model="email" autocomplete="off" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-envelope"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 col-md-offset-3 col-xs-12">
								<button id="queryPassword" type="button" class="btn btn-info btn-block">
									<b>&nbsp;&nbsp;密碼查詢&nbsp;&nbsp;</b>
									<span class="glyphicon glyphicon-arrow-right"></span>
								</button>
							</div>
							<div class="col-md-3 col-xs-12">
								<button type="reset" class="btn btn-default btn-block">
									<b>&nbsp;&nbsp;清空&nbsp;&nbsp;</b>
									<span class="glyphicon glyphicon-remove"></span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 註冊表單結束 -->
