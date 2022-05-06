<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	
</style>
<script type="text/javascript">
	$(function() {
		
		// 隱藏前往結帳按鈕
		$("#goCheckout").hide();

		$("[class='breadcrumbs']").hide();
		
		// 商品項目確認前檢查
		$("#checkItems").click(function(event) {
			var $thisBtn = $(this);
			if(collapseRemoveResult) {
				// 移除按鈕collapse的data-target屬性
				$thisBtn.removeAttr("data-target");
				// 取得購物車Angular controller
				var ngScope =  angular.element($("#cart_div_items")).scope();
				// 沒有商品數直接跳轉置商品頁
				if(ngScope.cart.length <= 0) {
					BootstrapDialog.show({
			            title: '網頁資訊',
			            message: '<h3>購物車中無任何商品，將自動跳轉至商品專區。</h3>',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable:false,
			            buttons: [{
			                label: '確定',
			                cssClass: 'btn-primary',
			                action: function(dialogItself){
			                	dialogItself.close();
			                	ngScope.continueCart();
			                }
			            }]
			        }).getModalBody().css('text-align', 'center');
					return false;
				} else {
					BootstrapDialog.show({
			            title: '網頁資訊',
			            message: '<h3>請確認商品項目及數量是否正確？</h3>',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable:false,
			            buttons: [{
			                label: '確定',
			                cssClass: 'btn-primary',
			                action: function(dialogItself){
			                	dialogItself.close();
			                	collapseTrigger($thisBtn, "#addwizard");
			                }
			            }, {
			                label: '取消',
			                action: function(dialogItself){
			                    dialogItself.close();
			                }
			            }]
			        }).getModalBody().css('text-align', 'center');
				}
			}
		});
		
		// 收件人資料確認前檢查
		$("#checkOrderPerson").click(function(event) {
			var $thisBtn = $(this);
			var $h3Msg = $("<h3>請確認收件人資料是否正確？</h3>");
			// 收件人欄位驗證檢查
			var validate = validateOrderPerson();
			validate?$h3Msg:$h3Msg.css({"color":"red"}).html("收件人資訊欄位不可為空白!");
			var buttons = validate?[{
                label: '確定',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                	dialogItself.close();
                	collapseTrigger($thisBtn, "#adjusthtml");
                }
            }, {
                label: '取消',
                action: function(dialogItself){
                    dialogItself.close();
                }
            }]:[{
                label: '確定',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                	dialogItself.close();
                }
            }];
			
			if(collapseRemoveResult) {
				// 移除按鈕collapse的data-target屬性
				$thisBtn.removeAttr("data-target");
				
				BootstrapDialog.show({
		            title: '網頁資訊',
		            message: $h3Msg,
		            type: BootstrapDialog.TYPE_WARNING,
		            closable:false,
		            buttons: buttons
		        }).getModalBody().css('text-align', 'center');
			}
		});
		
		// 訂單確認檢查
		$("#checkFinalOrder").click(function(event) {
			var $thisBtn = $(this);
			if(collapseRemoveResult) {
				// 移除按鈕collapse的data-target屬性
				$thisBtn.removeAttr("data-target");
				
				// clone購物清單並移除變更某些元素
				var orderItemlist = $("#cartItemDiv").clone();
				orderItemlist.show().find("#ordersTable").css({"width":"100%"});;
				/*orderItemlist.find("tr").last().remove();
				orderItemlist.find("th:eq(5)").remove();
				orderItemlist.find("tr").find("td:eq(5)").remove();
				orderItemlist.find(".quitityBtn").remove();
				orderItemlist.find("tr").find("td:eq(2)").each(function(k, v){
					var value = $(v).find("input[type=text]").val();
					$(v).find("input[type=text]").replaceWith("<label style='color:red;'>"+ value +"</label>");
				});
				orderItemlist.find("tr").last().find("td").first().attr({"colspan":"3"});
				orderItemlist.find("tr").last().find("td").last().css({"text-align":"right"});
				orderItemlist.find("table")*/
				
				var deliverInfo = $("#contentAddressee").clone();
				deliverInfo.find("#radioAddresseeDiv").remove();
				deliverInfo.find(".input-group").each(function(k, v){
					var value = $(v).find("input[type=text]").val();
					$(v).replaceWith("<label style='color:blue;'>"+ value +"</label>");
				});
				
				// 把訂單資訊包裝成另一個div
				var $textAndPic = $("<div id='finalOrder'></div>");
				$textAndPic.append('<div class="row"><h4 class="col-md-6 col-xs-10 page_title">訂單明細</h4></div>');
		        $textAndPic.append(orderItemlist.html());
		        $textAndPic.append('<hr style="border: 1px dashed #ccc; width: 100%;" />');
		        $textAndPic.append(deliverInfo.html());
		        
				BootstrapDialog.show({
		            title: '訂單最後確認',
		            message: $textAndPic,
		            type: BootstrapDialog.TYPE_SUCCESS,
		            closable:false,
		            buttons: [{
		                label: '確定',
		                cssClass: 'btn-primary',
		                action: function(dialogItself){
		                	var addresseeName = $("input[name='addresseeName']").val();
		            		var addresseeTelephone = $("input[name='addresseeTelephone']").val();
		            		var addresseeMobile = $("input[name='addresseeMobile']").val();
		            		var addresseeAddress = $("input[name='addresseeAddress']").val();
		            		var totalPrice = $("#finalOrder #orderTotalprice").text();
		            		var formData = new FormData();
		            		formData.append("addresseeName", addresseeName);
		            		formData.append("addresseeTelephone", addresseeTelephone);
		            		formData.append("addresseeMobile", addresseeMobile);
		            		formData.append("addresseeAddress", addresseeAddress);
		            		formData.append("totalPrice", totalPrice);
		                    
		                    $.ajax({
		                        url : "createMemberOrder.do",
		                        type : "POST",
		                        async: false,  
		                        cache: false,  
		                        contentType: false,  
		                        processData: false,
		                        data : formData,
		                        async : false,
		                        timeout : 15000,
		                        success : function(data) {
		                        	$("#cartTotalCount").text("");
		                        },
		                        error: function (result) {
		                            alert("Ajax request(getSessionMember) error");
		                        }
		                    });
		                	dialogItself.close();
		                	collapseTrigger($thisBtn, "#viewpage");
		                }
		            }, {
		                label: '取消',
		                action: function(dialogItself){
		                    dialogItself.close();
		                }
		            }]
		        });
			}
		});
	});

	// 移除collapse的data-target屬性使它不觸發摺疊事件
	function collapseTrigger(btnObj, btnName) {
		// 下面觸發Click事件不打開BootstrapDialog
    	collapseRemoveResult = false;
    	// 加入collapse的data-target屬性
    	btnObj.attr("data-target", btnName);
    	// 觸發click事件
    	btnObj.trigger('click');
    	// 還原是否移除collapse的data-target屬性
    	collapseRemoveResult = true;
	}
	
	// 確認收件人欄位是否填寫
	function validateOrderPerson() {
		var checkResult = true;
		$("input[name^='addressee']").each(function() {
			if($(this).val().length == 0) {
				checkResult = false;
				return false;
			}
		});
		return checkResult;
	}
</script>
<section>
<div id="accordion-demo" class="container panel-group" ng-controller="checkoutCartCtrl">
	<div class="row panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				商品及數量確認
			</h4>
		</div>
		<div id="prerequisites" class="panel-collapse collapse in" style="height: auto;">
			<div class="row panel-body">
				<div id="contentOrder">
					<%@include file="cart.jsp"%>
				</div>
				<div class="col-sm-2 acc-wizard-step">
					<button id="checkItems" class="btn btn-primary" type="button" data-toggle="collapse" aria-expanded="false" aria-controls="addwizard" data-parent="#accordion-demo">
						繼續
					</button>
				</div>
			</div>
			<!--/.row panel-body -->
		</div>
		<!-- /#prerequisites -->
	</div>
	<!-- /.panel.panel-default -->

	<div class="row panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				收件人資訊
			</h4>
		</div>
		<div id="addwizard" class="panel-collapse collapse" style="height: 36.4px;">
			<div class="row panel-body">
				<!-- 訂購人資料開始 -->
				<div class="tendebank-content">
					<div class="container">
						<div class="row">
							<h4 class="col-md-6 col-xs-10 page_title">訂購人資訊</h4>
						</div>
						<div class="col-md-6 col-xs-10 register">
							<div class="form-group">
									<label class="control-label">訂購人名：</label>
									<label class="control-label">{{ member.lastname + member.firstname }}</label>
							</div>
							<div class="form-group">
									<label for="telephone" class="control-label">市內電話：</label>
									<label class="control-label">{{ member.telephone }}</label>
							</div>
							<div class="form-group">
									<label for="mobile" class="control-label">行動電話：</label>
									<label class="control-label">{{ member.mobile }}</label>
							</div>
							<div class="form-group">
									<label for="address" class="control-label">常用地址：</label>
									<label class="control-label">{{ member.address }}</label>
							</div>
						</div>
					</div>
				</div>
				<!-- 訂購人資料結束 -->
				<hr style="border: 1px dashed #ccc; width: 100%;" />
				<!-- 收件人資料開始 -->
				<div id="contentAddressee">
					<div class="container">
						<div class="row">
							<h4 class="col-sm-2 col-xs-2 page_title">收件人資訊</h4>
					        <div id="radioAddresseeDiv" class="col-sm-10">
					            <div class="btn-group" data-toggle="buttons">
					                <label class="checkbox-inline">
					                    <input type="radio" name="radioAddressee" ng-model="addresseeInfo" value="same" ng-click="changeAddressee(this)" />同訂購人
					                </label> 
					                <label class="checkbox-inline">
					                    <input type="radio" name="radioAddressee" ng-model="addresseeInfo" value="renew" ng-click="changeAddressee(this)" />重新填寫
					                </label>
					            </div>
					        </div>
						</div>
						<div class="col-md-6 col-xs-10 register">
							<div class="form-group">
								<label for="lastName" class="col-sm-3 control-label">收件人名：</label>
								<div class="col-sm-9">
									<div class="input-group">
										<input type="text" class="form-control" name="addresseeName" placeholder="請輸入姓名" ng-model="addresseeName" />
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-pencil"></span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="telephone" class="col-sm-3 control-label">市內電話：</label>
								<div class="col-sm-9">
									<div class="input-group">
										<input type="text" class="form-control" name="addresseeTelephone" placeholder="請輸入電話" ng-model="addresseeTelephone" />
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-phone-alt"></span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="mobile" class="col-sm-3 control-label">行動電話：</label>
								<div class="col-sm-9">
									<div class="input-group">
										<input type="text" class="form-control" name="addresseeMobile" placeholder="請輸入行動電話號碼" ng-model="addresseeMobile" />
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-phone"></span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="address" class="col-sm-3 control-label">運送地址：</label>
								<div class="col-sm-9">
									<div class="input-group">
										<input type="text" class="form-control" name="addresseeAddress" placeholder="請輸入地址" ng-model="addresseeAddress" />
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-home"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 收件人資料結束 -->
				<hr style="border: 1px dashed #ccc; width: 100%;" />
				<!-- 貨運方式開始 -->
					
				<!-- 貨運方式結束 -->
				<div class="col-sm-2 acc-wizard-step">
					<button class="returnBtn btn" type="button" data-toggle="collapse" data-target="#prerequisites" aria-expanded="false" aria-controls="prerequisites" data-parent="#accordion-demo">
						返回
					</button>
					<button id="checkOrderPerson" class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adjusthtml" aria-expanded="false" aria-controls="adjusthtml" data-parent="#accordion-demo">
						繼續
					</button>
				</div>
			</div>
			<!--/.row panel-body -->
		</div>
		<!-- /#addwizard -->
	</div>
	<!-- /.panel.panel-default -->

	<div class="row panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				訂單確認
			</h4>
		</div>
		<div id="adjusthtml" class="panel-collapse collapse" style="height: 36.4px;">
			<div class="row panel-body">
				<div id="finalOrder">
					<div class="alert alert-warning text-center">訂購人請注意，確認訂單後將不可在進行修改！！</div>
				</div>
				<div class="col-sm-2 acc-wizard-step">
					<button class="returnBtn btn" type="button" data-toggle="collapse" data-target="#addwizard" aria-expanded="false" aria-controls="addwizard" data-parent="#accordion-demo">
						返回
					</button>
					<button id="checkFinalOrder" class="btn btn-primary" type="button" data-toggle="collapse" data-target="#viewpage" aria-expanded="false" aria-controls="viewpage" data-parent="#accordion-demo">
						確認訂單
					</button>
				</div>
			</div>
			<!--/.row panel-body -->
		</div>
		<!-- /#adjusthtml -->
	</div>
	<!-- /.panel.panel-default -->

	<div class="row panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				訂單完成
			</h4>
		</div>
		<div id="viewpage" class="panel-collapse collapse" style="height: 36.4px;">
			<div class="row panel-body">
				<div class="col-sm-2 acc-wizard-step">
					<button type="button" class="btn btn-primary" ng-click="memberOrder()"><span class="fa fa-arrow-left"></span>訂單查詢</button>
				</div>
			</div>
			<!--/.row panel-body -->
		</div>
		<!-- /#adjusthtml -->
	</div>
	<!-- /.panel.panel-default -->
</div>
</section>