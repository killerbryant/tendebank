// 首頁 Angular Controller
var app = angular.module('myApp', ['bsTable', 'nya.bootstrap.select']);
// select 修改語系
app.config(function (nyaBsConfigProvider) {
	 nyaBsConfigProvider.setLocalizedText('zh-TW', {
	        defaultNoneSelection: '沒有選取任何項目',
	        noSearchResult: '沒有找到符合的結果',
	        numberItemSelected: '已經選取%d個項目'
	 });
	 nyaBsConfigProvider.useLocale('zh-TW');
});

/* Manage */
// 管理員首頁
app.controller('contentAdminController',['$scope','$window',function($scope, $window) {
    console.log("contentAdminController");
}]);

//新增商品 Angular Controller
app.controller('addProductCtrl', function($scope, $element, $http){
    console.log("addProductCtrl loaded");
    $scope.productTypes = [
            	    {productType : "金紙類", productTypeVal : "A"},
            	    {productType : "香品類", productTypeVal : "B"},
            	    {productType : "蠟燭類", productTypeVal : "C"},
            	    {productType : "油品類", productTypeVal : "D"},
            	    {productType : "蓮花紙類", productTypeVal : "E"},
            	    {productType : "紙紮類", productTypeVal : "F"},
            	    {productType : "銅器類", productTypeVal : "G"},
            	    {productType : "金爐類", productTypeVal : "H"},
            	    {productType : "小紙類", productTypeVal : "I"},
            	    {productType : "組合類", productTypeVal : "J"},
            	   ];
    $scope.onCart = "Y"; // 預設上架
    // function to submit the form after all validation has occurred            
    $scope.productSubmit = function(isValid) {
        // check to make sure the form is completely valid
        if (isValid) { 
        	var fd = new FormData();
			fd.append("file", $('input[type=file]')[0].files[0]);
			
			var dataObj = new Object();
			dataObj.productNo = $scope.productNo;
			dataObj.productName = $scope.productName;
			dataObj.salesPrice = $scope.salesprice;
			dataObj.cost = $scope.cost;
			dataObj.vendor = $scope.vendor;
			dataObj.onCart = $scope.onCart;
			dataObj.productType = $scope.productType;
			
			fd.append("product", new Blob([JSON.stringify(dataObj)], {
                type : "application/json"  // ** specify that this is JSON**
            })); 
            
			$.ajax({
	            type: "POST",
	            url: "saveProduct.do",
	            data: fd,
	            async: false,
	            timeout: 15000,
	            dataType:"json",
	            contentType: false,
                processData: false,
                cache: false,
	            success: function(data) {
	            	var resultMsg = data.result;
		           	alert(data.successMsg);
		           	if(resultMsg) {
		           		$scope.reset($scope.formProduct);
				    }
	            },
	            complete: function() {
	            	
	            }
	        });
        } else {
            BootstrapDialog.show({
                title: '網頁資訊',
                message: '<h3 class="word-error">欄位未填寫完畢或未填寫正確！！</h3>',
                type: BootstrapDialog.TYPE_WARNING,
                closable:false,
                buttons: [{
                    label: '確定',
                    cssClass: 'btn-primary',
                    action: function(dialogItself){
                        dialogItself.close();
                    }
                }]
            }).getModalBody().css('text-align', 'center');
        }
    };
    
    $scope.reset = function(form) {
        $scope.productNo = "";
        $scope.productName = "";
        $scope.cost = "";
        $scope.salesprice = "";
        $scope.vendor = "";
        $scope.onCart = "Y";
        $scope.productType = "0";
        $('.fileinput').fileinput('clear');
        form.$setPristine();
        form.$setUntouched();
    };
    
});

//新增供應商 Angular Controller
app.controller('vendorCtrl', function($scope, $element, $http){
    console.log("vendorCtrl loaded");
    // function to submit the form after all validation has occurred            
    $scope.vendorSubmit = function(isValid) {
        // check to make sure the form is completely valid
        if (isValid) { 
        	$.ajax({
	            type: "POST",
	            url: "saveVendor.do",
	            data: $("#formVendor").serialize(),
	            async: false,
	            timeout: 15000,
	            dataType:"json",
	            success: function(data) {
		           	var resultMsg = data.result;
		           	alert(data.successMsg);
		           	if(resultMsg) {
		           		$scope.reset($scope.formVendor);
				    }
	            },
	            complete: function() {
		            
	            }
	        });
        } else {
            BootstrapDialog.show({
                title: '網頁資訊',
                message: '<h3 class="word-error">欄位未填寫完畢或未填寫正確！！</h3>',
                type: BootstrapDialog.TYPE_WARNING,
                closable:false,
                buttons: [{
                    label: '確定',
                    cssClass: 'btn-primary',
                    action: function(dialogItself){
                        dialogItself.close();
                    }
                }]
            }).getModalBody().css('text-align', 'center');
        }
    };
    
    $scope.reset = function(form) {
        $scope.vendorNo = "";
        $scope.companyName = "";
        $scope.cost = "";
        $scope.salesprice = "";
        $scope.vendor = "";
        form.$setPristine();
        form.$setUntouched();
    };
    
});

// 管理員訂單處理
app.controller('manageOrdersCtrl',['notDeliverService','$scope','$element','$http','$window','$compile',function(notDeliverService, $scope, $element, $http, $window, $compile) {
    console.log("manageOrdersCtrl");
    var url = "selectMonthOrdersByNotDeliver.do";
    
    switch($window.manageOrdersStatus){
		case 'Y':
			url = "selectMonthOrdersByDeliver.do";
		  break;
		default:
		
	}
    
    $.ajax({
        url : url,
        type : "POST",
        async: false,  
        cache: false,  
        contentType: false,  
        processData: false,
        data : {},
        dataType:"json",
        async : false,
        timeout : 15000,
        success : function(data) {
        	$scope.jsonData = data.manageOrdersData;
        },
        error: function (result) {
            alert("Ajax request(" + url + ") error");
        }
    });
    
    $scope.bsTableControl = {
    		options: {
                data: $scope.jsonData,
                rowStyle: function (row, index) {
                    return { classes: 'none' };
                },
                cache: false,
                striped: true,
                pagination: true,
                pageSize: 10,
                pageList: [5, 10, 25, 50, 100, 200],
                search: true,
                showColumns: true,
                showRefresh: false,
                minimumCountColumns: 2,
                clickToSelect: false,
                showToggle: false,
                maintainSelected: true,
                columns: [{
                    field: 'orderNo',
                    title: '訂單編號',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: orderNoFormatter,
                }, {
                    field: 'buyDate',
                    title: '訂購日期',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'orderStatus',
                    title: '訂單狀態',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: orderStatusWord,
                }, {
                    field: 'orderPayprice',
                    title: '應付金額',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                }, {
                    field: 'payStatus',
                    title: '付款狀態',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: payStatusWord,
                }, {
                    field: 'deliverStatus',
                    title: '配送狀態',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: deliverStatusWord,
                }, {
                    field: 'remittanceAccount',
                    title: '訂單操作',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: buttonFormatter,
                }]
            }
        };
    
    
    // 訂單詳細資料
    function orderNoFormatter(value, row, index) {
    	return '<a href="javascript: void(0);" ng-click="$parent.itemDetail(this,'+ index +')">'+ row.orderNo +'</a>';
    }
    
    // 訂單狀態
    function orderStatusWord(value, row, index) {
    	var formatter = '<article><p>未完成交易</p></article>';
    	switch(value){
        	case 'Y':
        		formatter = '<article><p style="color:blue;">已完成交易</p></article>';
        	  break;
        	default:
    		
    	}
    	return formatter;
    }
    
    // 付款狀態
    function payStatusWord(value, row, index) {
    	var formatter = '<article><p>未付款</p></article>';
    	switch(value){
        	case 'Y':
        		formatter = '<article><p style="color:blue;">已付款</p></article>';
        	  break;
        	case 'C':
        		formatter = '<article><p style="color:red;">款項確認中</p></article>';
        	  break;
        	default:
    		
    	}
    	return formatter;
    }
    
    // 配送狀態
    function deliverStatusWord(value, row, index) {
    	var formatter = '<article><p>未出貨</p></article>';
    	switch(value){
        	case 'Y':
        		formatter = '<article><p>已出貨</p></article>';
        	  break;
        	default:
    		
    	}
    	return formatter;
    }
    
    // 出貨資訊樣式
    function buttonFormatter(value, row, index) {
    	var rowVal = row.payStatus;
    	var formatter = '<button type="button" class="btn btn-xs btn-primary" ng-click="$parent.writeBankInfo(\''+ row.orderNo +'\')"><span class="fa fa-pencil"></span>填寫出貨資訊</button>';
    	switch(rowVal){
        	case 'C':
        		formatter = '<article><p>'+ row.remittanceAccount + '</p></article>';
        	  break;
        	case 'N':
        		formatter = '';
        	  break;
        	default:
    		
    	}
        return formatter;
    }
    
 // 訂單詳細資訊
    $scope.itemDetail = function(item, index) {
    	
    	$scope.detailOrders = item.$parent.jsonData[index].ordersSet;
		var trContent = $('<tr></tr>');
		trContent.append($('<th ng-class="{dropup:detailOrder===\'-\'}" ng-click="changeDetailOrder(\'productNo\')">商品編號<span class="caret active" ng-class="{active:detailOrderType===\'productNo\'}"></span></th>'));
		trContent.append($('<th ng-class="{dropup:detailOrder===\'-\'}" ng-click="changeDetailOrder(\'productQuantity\')">訂購數量<span class="caret active" ng-class="{active:detailOrderType===\'productQuantity\'}"></span></th>'));
		trContent.append($('<th>商品單價</th>'));
		trContent.append($('<th>商品總計</th>'));
		
		var tdContent = $('<tr ng-repeat="detailItem in detailOrders | filter:search | orderBy:detailOrder+detailOrderType">');
		tdContent.append($('<td>{{detailItem.productNo}}</td>'));
		tdContent.append($('<td>{{detailItem.productQuantity}}</td>'));
		tdContent.append($('<td>{{detailItem.productSalesprice}}</td>'));
		tdContent.append($('<td>{{detailItem.productTotalprice}}</td>'));
		
		var tableContent = $('<table id="detailOrdersTable" class="table table-hover" ng-show="detailOrders.length"></table>').append($('<thead></thead>').append(trContent)).append($('<tbody></tbody>').append(tdContent));
		
		var $textAndPic = $('<div id="ordersDetail"></div>').append($('<div class="row"></div>').append($('<div class="col-md-12"></div>').append(tableContent)));
		
		
		$compile($textAndPic)($scope);
		
    	BootstrapDialog.show({
            title: '訂單資訊',
            message: $textAndPic,
            type: BootstrapDialog.TYPE_SUCCESS,
            closable:false,
            buttons: [{
                label: '確定',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                    dialogItself.close();
                }
            }]
        });
    };
}]);

//管理員訂單處理
app.controller('manageHistoryOrdersCtrl',['notDeliverService','$scope','$element','$http','$window','$compile',function(notDeliverService, $scope, $element, $http, $window, $compile) {
    console.log("manageHistoryOrdersCtrl");
    var url = "selectHistoryOrdersByNotDeliver.do";
    
    switch($window.manageOrdersStatus){
		case 'Y':
			url = "selectHistoryOrdersByDeliver.do";
		  break;
		default:
		
	}
    
    $.ajax({
        url : url,
        type : "POST",
        async: false,  
        cache: false,  
        contentType: false,  
        processData: false,
        data : {},
        dataType:"json",
        async : false,
        timeout : 15000,
        success : function(data) {
        	$scope.jsonData = data.manageOrdersData;
        },
        error: function (result) {
            alert("Ajax request(" + url + ") error");
        }
    });
    
    $scope.bsTableControl = {
    		options: {
                data: $scope.jsonData,
                rowStyle: function (row, index) {
                    return { classes: 'none' };
                },
                cache: false,
                striped: true,
                pagination: true,
                pageSize: 10,
                pageList: [5, 10, 25, 50, 100, 200],
                search: true,
                showColumns: true,
                showRefresh: false,
                minimumCountColumns: 2,
                clickToSelect: false,
                showToggle: false,
                maintainSelected: true,
                columns: [{
                    field: 'orderNo',
                    title: '訂單編號',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: orderNoFormatter,
                }, {
                    field: 'buyDate',
                    title: '訂購日期',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'orderStatus',
                    title: '訂單狀態',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: orderStatusWord,
                }, {
                    field: 'orderPayprice',
                    title: '應付金額',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                }, {
                    field: 'payStatus',
                    title: '付款狀態',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: payStatusWord,
                }, {
                    field: 'deliverStatus',
                    title: '配送狀態',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: deliverStatusWord,
                }, {
                    field: 'remittanceAccount',
                    title: '訂單操作',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter: buttonFormatter,
                }]
            }
        };
    
    
    // 訂單詳細資料
    function orderNoFormatter(value, row, index) {
    	return '<a href="javascript: void(0);" ng-click="$parent.itemDetail(this,'+ index +')">'+ row.orderNo +'</a>';
    }
    
    // 訂單狀態
    function orderStatusWord(value, row, index) {
    	var formatter = '<article><p>未完成交易</p></article>';
    	switch(value){
        	case 'Y':
        		formatter = '<article><p style="color:blue;">已完成交易</p></article>';
        	  break;
        	default:
    		
    	}
    	return formatter;
    }
    
    // 付款狀態
    function payStatusWord(value, row, index) {
    	var formatter = '<article><p>未付款</p></article>';
    	switch(value){
        	case 'Y':
        		formatter = '<article><p style="color:blue;">已付款</p></article>';
        	  break;
        	case 'C':
        		formatter = '<article><p style="color:red;">款項確認中</p></article>';
        	  break;
        	default:
    		
    	}
    	return formatter;
    }
    
    // 配送狀態
    function deliverStatusWord(value, row, index) {
    	var formatter = '<article><p>未出貨</p></article>';
    	switch(value){
        	case 'Y':
        		formatter = '<article><p>已出貨</p></article>';
        	  break;
        	default:
    		
    	}
    	return formatter;
    }
    
    // 出貨資訊樣式
    function buttonFormatter(value, row, index) {
    	var rowVal = row.payStatus;
    	var formatter = '<button type="button" class="btn btn-xs btn-primary" ng-click="$parent.writeBankInfo(\''+ row.orderNo +'\')"><span class="fa fa-pencil"></span>填寫出貨資訊</button>';
    	switch(rowVal){
        	case 'C':
        		formatter = '<article><p>'+ row.remittanceAccount + '</p></article>';
        	  break;
        	case 'N':
        		formatter = '';
        	  break;
        	default:
    		
    	}
        return formatter;
    }
    
 // 訂單詳細資訊
    $scope.itemDetail = function(item, index) {
    	
    	$scope.detailOrders = item.$parent.jsonData[index].ordersSet;
		var trContent = $('<tr></tr>');
		trContent.append($('<th ng-class="{dropup:detailOrder===\'-\'}" ng-click="changeDetailOrder(\'productNo\')">商品編號<span class="caret active" ng-class="{active:detailOrderType===\'productNo\'}"></span></th>'));
		trContent.append($('<th ng-class="{dropup:detailOrder===\'-\'}" ng-click="changeDetailOrder(\'productQuantity\')">訂購數量<span class="caret active" ng-class="{active:detailOrderType===\'productQuantity\'}"></span></th>'));
		trContent.append($('<th>商品單價</th>'));
		trContent.append($('<th>商品總計</th>'));
		
		var tdContent = $('<tr ng-repeat="detailItem in detailOrders | filter:search | orderBy:detailOrder+detailOrderType">');
		tdContent.append($('<td>{{detailItem.productNo}}</td>'));
		tdContent.append($('<td>{{detailItem.productQuantity}}</td>'));
		tdContent.append($('<td>{{detailItem.productSalesprice}}</td>'));
		tdContent.append($('<td>{{detailItem.productTotalprice}}</td>'));
		
		var tableContent = $('<table id="detailOrdersTable" class="table table-hover" ng-show="detailOrders.length"></table>').append($('<thead></thead>').append(trContent)).append($('<tbody></tbody>').append(tdContent));
		
		var $textAndPic = $('<div id="ordersDetail"></div>').append($('<div class="row"></div>').append($('<div class="col-md-12"></div>').append(tableContent)));
		
		
		$compile($textAndPic)($scope);
		
    	BootstrapDialog.show({
            title: '訂單資訊',
            message: $textAndPic,
            type: BootstrapDialog.TYPE_SUCCESS,
            closable:false,
            buttons: [{
                label: '確定',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                    dialogItself.close();
                }
            }]
        });
    };
}]);

app.factory('notDeliverService', ['$http', '$q', function ($http, $q) { 
	return { 
		query : function() { 
			var deferred = $q.defer();//聲明承諾
			$http({method: 'POST', url: 'selectMonthOrdersByNotDeliver.do'}).success(function(data) { 
				deferred.resolve(data);//請求成功
			}).error(function(data) { 
				deferred.reject(data); //請求失敗
			}); 
			return deferred.promise; // 返回承諾，這裡返回的不是資料而是API
		}
	}; 
}]);

