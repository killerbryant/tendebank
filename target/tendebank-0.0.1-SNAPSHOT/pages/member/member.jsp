<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(function() {
			
		});
	</script>
	
	<style type="text/css">
		
		/* 如果已經被輸入過且驗證失敗，加入粉紅色背景 */
		input.ng-dirty.ng-invalid {
			background: #ffecec;
		}
		
	</style>
	<!-- 註冊表單開始 -->
	<div id="contentMembebr">
		<div class="container">
			<form id="formMember" action="" method="post" class="form-horizontal" name="formMember" ng-controller="memberCtrl" novalidate>
				<div class="row">
					<h3 class="col-md-6 col-xs-10 page_title ">註冊為天地銀行會員</h3>
				</div>
				<div class="col-md-6 col-xs-10 register">
					<div class="form-group">
						<input type="hidden" id="memberNo" name="memberNo" />
						<label for="account" class="col-sm-3 control-label">帳號：</label>
						<div class="col-sm-8"  ng-class="{ 'has-error' : formMember.account.$invalid && !formMember.account.$pristine , 'has-success' : formMember.account.$valid && !formMember.account.$pristine }">
							<div class="input-group">
								<input type="text" class="form-control" name="account" placeholder="請輸入帳號" ng-model="account" required />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</div>
							</div>
							<p ng-show="formMember.account.$invalid && !formMember.account.$pristine" class="help-block">＊帳號為必填欄位</p>
						</div>
						<div class="col-sm-1">
							<button class="btn btn-info btn-sm" type="button" ng-click="checkAccount()"><span class="glyphicon glyphicon-check">檢查帳號</span></button>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-3 control-label">密碼：</label>
						<div class="col-sm-8" ng-class="{ 'has-error' : formMember.password.$invalid && !formMember.password.$pristine, 'has-success' : formMember.password.$valid && !formMember.password.$pristine }">
							<div class="input-group">
								<input id="pwd" type="password" class="form-control" name="password" placeholder="密碼必須由數位和字母組成（8~20碼）" ng-model="password" valid-password />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</div>
							</div>
							<p ng-show="!!formMember.password.$error.isBlank && !formMember.password.$pristine" class="help-block">＊密碼為必填欄位</p>
			                <p ng-show="!!formMember.password.$error.isWeak && !formMember.password.$pristine" class="help-block">＊密碼不符合規範</p> 
			                <p ng-show="!!formMember.password.$error.invalidLen && !formMember.password.$pristine" class="help-block">＊密碼長度密需介於8~20之間</p>
						</div>
					</div>
					<div class="form-group">
						<label for="originalPassword" class="col-sm-3 control-label">確認密碼：</label>
						<div class="col-sm-8" ng-class="{ 'has-error' : formMember.originalPassword.$invalid && (!formMember.originalPassword.$pristine || !formMember.password.$pristine), 'has-success' : formMember.originalPassword.$valid && !formMember.originalPassword.$pristine }">
							<div class="input-group">
								<input type="password" class="form-control" name="originalPassword" placeholder="請確認密碼" ng-model="originalPassword" check-password="pwd" />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-exclamation-sign"></span>
								</div>
							</div>
							<!-- <p ng-show="!!formMember.originalPassword.$error.isBlank" class="help-block">＊確認密碼為必填欄位</p> -->
							<p ng-show="!!formMember.originalPassword.$error.passwordMatch && !formMember.originalPassword.$pristine" class="help-block">＊密碼輸入不一致</p>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">Email：</label>
						<div class="col-sm-8" ng-class="{ 'has-error' : formMember.email.$invalid && !formMember.email.$pristine, 'has-success' : formMember.email.$valid && !formMember.email.$pristine }">
							<div class="input-group">
								<input type="text" class="form-control" name="email" placeholder="請輸入有效的Email" ng-model="email" autocomplete="off" valid-email />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-envelope"></span>
								</div>
							</div>
							<p ng-show="!!formMember.email.$error.isBlank && !formMember.email.$pristine" class="help-block">＊E-Mail為必填欄位</p>
			                <p ng-show="!!formMember.email.$error.invalidate && !formMember.email.$pristine" class="help-block">＊E-Mail不符合規範</p> 
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-3 control-label">姓氏：</label>
						<div class="col-sm-8">
							<div class="input-group">
								<input type="text" class="form-control" name="lastname" placeholder="請輸入姓氏" ng-model="lastname" />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-pencil"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-3 control-label">名字：</label>
						<div class="col-sm-8">
							<div class="input-group">
								<input type="text" class="form-control" name="firstname" placeholder="請輸入名字" ng-model="firstname" />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-pencil"></span>
								</div>
							</div>
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
					<div class="row">
						<div class="col-md-3 col-md-offset-3 col-xs-12">
							<button type="button" class="btn btn-primary btn-block" ng-click="memberSubmit(formMember.$valid)">
								<b>&nbsp;&nbsp;提交&nbsp;&nbsp;</b>
								<span class="glyphicon glyphicon-arrow-right"></span>
							</button>
						</div>
						<div class="col-md-3 col-xs-12">
							<button type="button" class="btn btn-default btn-block" ng-click="reset(formMember)">
								<b>&nbsp;&nbsp;清空&nbsp;&nbsp;</b>
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>