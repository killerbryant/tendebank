<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(function() {
			
		});
	</script>
	<!-- 註冊表單開始 -->
	<div class="tendebank-content">
		<div class="container-fluid">
			<form id="formVendor" action="" method="post" class="form-horizontal" name="formVendor" ng-controller="vendorCtrl" novalidate>
				<div class="row">
					<h3 class="col-md-8 col-xs-8 page_title ">新增供應商</h3>
				</div>
				<div class="row">
					<div class="col-md-2 col-xs-2"></div>
					<div class="col-md-8 col-xs-8 register">
						<div class="form-group">
							<label for="vendorNo" class="col-sm-3 control-label">供應商編號：</label>
							<div class="col-sm-8" ng-class="{ 'has-error' : formVendor.vendorNo.$invalid && !formVendor.vendorNo.$pristine , 'has-success' : formVendor.vendorNo.$valid && !formVendor.vendorNo.$pristine }">
								<div class="input-group">
									<input type="text" class="form-control" name="vendorNo" placeholder="請輸入供應商編號" ng-model="vendorNo" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-user"></span>
									</div>
								</div>
								<p ng-show="formVendor.vendorNo.$invalid && !formVendor.vendorNo.$pristine" class="help-block">＊供應商編號為必填欄位</p>
							</div>
						</div>
						<div class="form-group">
							<label for="companyName" class="col-sm-3 control-label">供應商名稱：</label>
							<div class="col-sm-8" ng-class="{ 'has-error' : formVendor.companyName.$invalid && !formVendor.companyName.$pristine , 'has-success' : formVendor.companyName.$valid && !formVendor.companyName.$pristine }">
								<div class="input-group">
									<input type="text" class="form-control" name="companyName" placeholder="請輸入供應商名稱" ng-model="companyName" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-pencil"></span>
									</div>
								</div>
								<p ng-show="formVendor.companyName.$invalid && !formVendor.companyName.$pristine" class="help-block">＊供應商編號為必填欄位</p>
							</div>
						</div>
						<div class="form-group">
							<label for="telephone" class="col-sm-3 control-label">電話：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input type="text" class="form-control" name="telephone" placeholder="請輸入電話號碼" ng-model="telephone" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-phone-alt"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="mobile" class="col-sm-3 control-label">行動電話：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input type="text" class="form-control" name="mobile" placeholder="請輸入行動電話號碼" ng-model="mobile" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-phone"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="col-sm-3 control-label">地址：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input type="text" class="form-control" name="address" placeholder="請輸入地址" ng-model="address" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-home"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							 <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
		                         <button type="button" class="btn btn-primary" ng-click="vendorSubmit(formVendor.$valid)">
									<b>&nbsp;&nbsp;提交&nbsp;&nbsp;</b>
									<span class="glyphicon glyphicon-arrow-right"></span>
								 </button>
		                         <button type="button" class="btn btn-default" ng-click="reset(formVendor)">
									 <b>&nbsp;&nbsp;清空&nbsp;&nbsp;</b>
									 <span class="glyphicon glyphicon-remove"></span>
								 </button>
	                        </div>
						</div>
						<div class="row">
						</div>
					</div>
					<div class="col-md-2 col-xs-2"></div>
				</div>
			</form>
		</div>
	</div>
