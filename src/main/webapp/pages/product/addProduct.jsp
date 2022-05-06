<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style type="text/css">
		
	</style>
	<script type="text/javascript">
		$(function() {
			
		});
	</script>
	<div class="tendebank-content">
		<div class="container-fluid">
			<form id="formProduct" action="" method="post" class="form-horizontal" name="formProduct" enctype="multipart/form-data" ng-controller="addProductCtrl" novalidate>
				<div class="row">
					<h3 class="col-md-8 col-xs-8 page_title ">新增商品</h3>
				</div>
				<div class="row">
					<div class="col-md-2 col-xs-2"></div>
					<div class="col-md-8 col-xs-8 register">
						<div class="form-group">
							<label for="productNo" class="col-sm-3 control-label">商品編號：</label>
							<div class="col-sm-8"  ng-class="{ 'has-error' : formProduct.productNo.$invalid && !formProduct.productNo.$pristine , 'has-success' : formProduct.productNo.$valid && !formProduct.productNo.$pristine }">
								<div class="input-group">
									<input id="productNo" type="text" class="form-control" name="productNo" placeholder="請輸入商品編號" ng-model="productNo" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-briefcase"></span>
									</div>
								</div>
								<p ng-show="formProduct.productNo.$invalid && !formProduct.productNo.$pristine" class="help-block">＊商品編號為必填欄位</p>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">商品名稱：</label>
							<div class="col-sm-8" ng-class="{ 'has-error' : formProduct.productName.$invalid && !formProduct.productName.$pristine, 'has-success' : formProduct.productName.$valid && !formProduct.productName.$pristine }">
								<div class="input-group">
									<input id="productName" type="text" class="form-control" name="productName" placeholder="請輸入商品名稱" ng-model="productName" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-lock"></span>
									</div>
								</div>
								<p ng-show="!!formProduct.productName.$error.isBlank && !formProduct.productName.$pristine" class="help-block">＊商品名稱為必填欄位</p>
							</div>
						</div>
						<div class="form-group">
							<label for="cost" class="col-sm-3 control-label">商品成本：</label>
							<div class="col-sm-8" ng-class="{ 'has-error' : formProduct.cost.$invalid && !formProduct.cost.$pristine, 'has-success' : formProduct.cost.$valid && !formProduct.cost.$pristine }">
								<div class="input-group">
									<input id="cost" type="text" class="form-control" name="cost" placeholder="請輸入商品成本" ng-model="cost" autocomplete="off" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-usd"></span>
									</div>
								</div>
								<p ng-show="!!formProduct.cost.$error.isBlank && !formProduct.cost.$pristine" class="help-block">＊商品成本為必填欄位</p>
							</div>
						</div>
						<div class="form-group">
							<label for="salesprice" class="col-sm-3 control-label">商品售價：</label>
							<div class="col-sm-8" ng-class="{ 'has-error' : formProduct.salesprice.$invalid && !formProduct.salesprice.$pristine, 'has-success' : formProduct.salesprice.$valid && !formProduct.salesprice.$pristine }">
								<div class="input-group">
									<input id="salesprice" type="text" class="form-control" name="salesprice" placeholder="請輸入商品售價" ng-model="salesprice" required />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-usd"></span>
									</div>
								</div>
								<p ng-show="!!formProduct.salesprice.$error.isBlank && !formProduct.salesprice.$pristine" class="help-block">＊商品售價為必填欄位</p>
							</div>
						</div>
						<div class="form-group">
							<label for="vendor" class="col-sm-3 control-label">供應商：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input id="vendor" type="text" class="form-control" name="vendor" placeholder="請輸入供應商" ng-model="vendor" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-transfer"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="productType" class="col-sm-3 control-label">商品分類：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<ol class="nya-bs-select btn-primary" ng-model="productType">
										<li nya-bs-option="x in productTypes" data-value="x.productTypeVal"><a>{{x.productTypeVal}}. {{x.productType}}<span class="glyphicon glyphicon-ok check-mark"></span></a></li>
									</ol>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="onCart" class="col-sm-3 control-label">是否上架：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<div class="radio">
						                <label>
						                    <input type="radio" name="onCart" value="Y" ng-model="onCart"/>販售
						                </label>
						                <label>
						                    <input type="radio" name="onCart" value="N" ng-model="onCart"/>停售
						                </label>
						            </div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="file" class="col-sm-3 control-label">圖片上傳：</label>
							<div class="col-sm-8">
								<div class="fileinput fileinput-new" data-provides="fileinput">
								  <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
								  <div>
								    <span class="btn btn-default btn-file"><span class="fileinput-new">選擇圖片</span><span class="fileinput-exists">更換圖片</span><input id="file" type="file" name="file" ng-model="file" /></span>
								    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
								  </div>
								</div>
							</div>
						</div>
						<div class="form-group">
							 <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	                          <button type="button" class="btn btn-primary" ng-click="productSubmit(formProduct.$valid)">
									<b>&nbsp;&nbsp;新增&nbsp;&nbsp;</b>
									<span class="glyphicon glyphicon-arrow-right"></span>
							  </button>
	                          <button type="button" class="btn btn-default" ng-click="reset(formProduct)">
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
