// 首頁 Angular Controller
var app = angular.module('myApp', ['bsTable', 'nya.bootstrap.select']);
//select 修改語系
app.config(function (nyaBsConfigProvider) {
	 nyaBsConfigProvider.setLocalizedText('zh-TW', {
	        defaultNoneSelection: '請選擇',
	        noSearchResult: '沒有找到符合的結果',
	        numberItemSelected: '已經選取%d個項目'
	 });
	 nyaBsConfigProvider.useLocale('zh-TW');
});
app.controller('contentController', ['$scope','$element','$http','$window','$compile','$timeout',function($scope, $element, $http, $window, $compile, $timeout) {
	$scope.hotProducts = [];
	$scope.newFirstProducts = [];
	$scope.newSecondProducts = [];
	$scope.productDetail = [];
	var productDetailDialog;
	// 全域變數為undefined才去取Session會員
    if(typeof($window.memberInfo) == "undefined") {
        $.ajax({
            url : "getSessionMember.do",
            dataType : "json",
            type : "POST",
            data : {},
            async : false,
            timeout : 15000,
            success : function(data) {
                $window.memberInfo = data.member;
                $.ajax({
                    url : "getShoppingCartCount.do",
                    dataType : "json",
                    type : "POST",
                    data : {},
                    async : false,
                    timeout : 15000,
                    success : function(data) {
                        var itemCount = data.productTotalCount;
                        if(parseInt(itemCount) > 0) {
                        	$("#cartTotalCount").text("(" + itemCount + ")");
                        }
                    },
                    error: function (result) {
                        alert("Ajax request(getShoppingCartOrder) error");
                    }
                });
            },
            error: function (result) {
                alert("Ajax request(getSessionMember) error");
            }
        });
    }
    
    // 撈熱門商品
    $.ajax({
		type : "POST",
		url : "getProductByHot.do",
		data : {},
		async : false,
		timeout : 15000,
		dataType : "json",
		success : function(data) {
			if (data.products.length > 0) {
				$scope.hotProducts = data.products;
			}
		},
		complete : function() {

		}
	});
    
    // 撈最新商品
    $.ajax({
		type : "POST",
		url : "getProductByNew.do",
		data : {},
		async : false,
		timeout : 15000,
		dataType : "json",
		success : function(data) {
			if (data.firstList.length > 0) {
				$scope.newFirstProducts = data.firstList;
			}
			if (data.secondList.length > 0) {
				$scope.newSecondProducts = data.secondList;
			}
		},
		complete : function() {

		}
	});
	
    // 參數 產品編號 JSON字串 是否顯示加入購物車訊息
	$scope.addProduct = function (index, listNumber) {
		var dataObj = $scope.hotProducts[index];
		switch(listNumber) {
			case 1:
				dataObj = $scope.newFirstProducts[index];
				break;
			case 2:
				dataObj = $scope.newSecondProducts[index];
				break;
			default:
				
		}
		dataObj.productCount = 1;
		$.ajax({
			type : "POST",
			url : "addCartByProduct.do",
			data : JSON.stringify(dataObj) ,
			async : false,
			timeout : 15000,
			dataType : "json",
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				var resultStr = data.result;
				if (resultStr == "limit") {
					$(".shop-menu ul li").first().replaceWith($('<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>').on('click', function() {
						var _$this = $(this);
						changePageContent(_$this);
						return false;
					}));
					$("#cartTotalCount").text("");
					$("#memberLogin").find("a").click();
				} else {
					var productTotalCount = data.productTotalCount;
					$("#cartTotalCount").text("(" + productTotalCount + ")");
					$("#msg").show().animate({
						width : '250px'
					}, 200).fadeOut(1000);
				}
			},
			complete : function(data) {

			}
		});
	};
	
	$scope.addProductDetail = function (dataObj) {
		var productCount = $("#ordersDetail #productDetailCount").val();
		dataObj.productCount = productCount;
		console.log(dataObj.productCount);
		
		$.ajax({
			type : "POST",
			url : "addCartByProduct.do",
			data : JSON.stringify(dataObj) ,
			async : false,
			timeout : 15000,
			dataType : "json",
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				var resultStr = data.result;
				if (resultStr == "limit") {
					productDetailDialog.close();
					$(".shop-menu ul li").first().replaceWith($('<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>').on('click', function() {
						var _$this = $(this);
						changePageContent(_$this);
						return false;
					}));
					$("#cartTotalCount").text("");
					$("#memberLogin").find("a").click();
				} else {
					var productTotalCount = data.productTotalCount;
					$("#cartTotalCount").text("(" + productTotalCount + ")");
					$("#msg").show().animate({
						width : '250px'
					}, 200).fadeOut(1000);
				}
			},
			complete : function(data) {

			}
		});
	};
	
	// bootstrap modal 顯示商品內容
	$scope.openContents = function (index, listNumber) {
		
		var dataObj = $scope.hotProducts[index];
		switch(listNumber) {
			case 1:
				dataObj = $scope.newFirstProducts[index];
				break;
			case 2:
				dataObj = $scope.newSecondProducts[index];
				break;
			default:
				
		}
		$scope.productDetail = dataObj;
		
		$timeout(function() {
			var $textAndPic = $('<div id="ordersDetail"></div>').append($("#productDetail").html());
			$compile($textAndPic)($scope);
			productDetailDialog = BootstrapDialog.show({
	            title: "商品編號:" + dataObj.productNo + ", 商品名稱: " + dataObj.productName,
	            message: $textAndPic,
	            type: BootstrapDialog.TYPE_INFO,
	            size: BootstrapDialog.SIZE_WIDE,
	            closable: true,
	            closeByBackdrop: false,
	            closeByKeyboard: false,
	            buttons: [{
	                label: '確定',
	                cssClass: 'btn-primary',
	                action: function(dialog) {
	                	dialog.close();
	                }
	            }]
	        });
		},0);
	};
}]);

app.controller('loginCtrl', ['$scope','$element','$http','$window','$compile',function($scope, $element, $http, $window, $compile) {
	console.log("loginCtrl loaded");
	$scope.loginSubmit = function() {
		$.ajax({
            type: "POST",
            url: "login.do",
            data: $element.serialize(),
            async: false,
            timeout: 15000,
            dataType:"json",
            success: function(data) {
                var resultMsg = data.errorMsg;
                if(resultMsg.length > 0) {
                	$("#errorMsg").html(resultMsg);
                } else {
                	window.location.href="index.jsp";
                }
            },
            complete: function() {
                
            }
        });
    };
    
    $scope.loginReset = function(form) {
        $scope.account = "";
        $scope.password = "";
        $scope.rememberMe = "";
        form.$setPristine();
        form.$setUntouched();
    };
    
}]);

//商品目錄 Angular Controller
app.controller('productShowCtrl',['$scope','$element','$http','$window','$compile','$timeout',function($scope, $element, $http, $window, $compile, $timeout) {
	console.log("productShowCtrl loaded");
	$scope.products = [];
	$scope.productDetail = [];
	$scope.pageSize = $window.pageItem;
	$scope.curPage = 0;
	$scope.pageCount = 0;
	var productDetailDialog;
	if($window.memberAcc.length == 0) {
		var prodBtn = $(".shop-menu ul li").first().replaceWith($('<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>').on('click', function(e) {
			var _$this = $(this);
			changePageContent(_$this);
			return false;
		}));
		$("#cartTotalCount").text("");
		$compile(prodBtn)($scope);
	}
	
	if(productType != "") {
		$.ajax({
			type : "POST",
			url : "getProductByType.do",
			data : {
				"productType": productType
			},
			async : false,
			timeout : 15000,
			dataType : "json",
			success : function(data) {
				$window.jsonProductsData = data.products;
				$window.totalCount = data.totalCount;
				$window.productTotalCount = data.productTotalCount;
				var productTotalCount = data.productTotalCount;
				
				if(data.totalCount > 0) {
					$scope.pageCount = Math.ceil(data.totalCount / $scope.pageSize) - 1;
				}
				
				if (parseInt(productTotalCount) != 0) {
					$("#cartTotalCount").html("(" + productTotalCount + ")");
				}

				//$window.maxPage = Math.ceil($window.totalCount / $window.pageItem);
				if ($window.jsonProductsData.length > 0) {
					//drawPageTable(jsonProductsData, 1, pageItem);
					$scope.products = $window.jsonProductsData;
				}
			},
			complete : function() {

			}
		});
	} else {
		console.log("key = " + productKeyword);
		$.ajax({
			type : "POST",
			url : "getProductBykeyword.do",
			data : {
				"keyword": productKeyword
			},
			async : false,
			timeout : 15000,
			dataType : "json",
			success : function(data) {
				$window.jsonProductsData = data.products;
				$window.totalCount = data.totalCount;
				$window.productTotalCount = data.productTotalCount;
				var productTotalCount = data.productTotalCount;
				
				if(data.totalCount > 0) {
					$scope.pageCount = Math.ceil(data.totalCount / $scope.pageSize) - 1;
				}
				
				if (parseInt(productTotalCount) != 0) {
					$("#cartTotalCount").html("(" + productTotalCount + ")");
				}

				//$window.maxPage = Math.ceil($window.totalCount / $window.pageItem);
				if ($window.jsonProductsData.length > 0) {
					//drawPageTable(jsonProductsData, 1, pageItem);
					$scope.products = $window.jsonProductsData;
				}
			},
			complete : function() {

			}
		});
	}
	
	
	// bootstrap modal 顯示商品內容
	$scope.openContents = function (index) {
		var dataObj = $scope.products[index];
		dataObj.prodIndex = index;
		$scope.productDetail = dataObj;
		$timeout(function() {
			var $textAndPic = $('<div id="ordersDetail"></div>').append($("#productDetail").html());
			$compile($textAndPic)($scope);
			productDetailDialog = BootstrapDialog.show({
	            title: "商品編號:" + dataObj.productNo + ", 商品名稱: " + dataObj.productName,
	            message: $textAndPic,
	            type: BootstrapDialog.TYPE_INFO,
	            size: BootstrapDialog.SIZE_WIDE,
	            closable: true,
	            closeByBackdrop: false,
	            closeByKeyboard: false,
	            buttons: [{
	                label: '確定',
	                cssClass: 'btn-primary',
	                action: function(dialog) {
	                	dialog.close();
	                }
	            }]
	        });
		},0);
	};
	
	// 參數 產品編號 JSON字串 是否顯示加入購物車訊息
	$scope.addProduct = function (index) {
		var dataObj = $scope.products[index];
		dataObj.productCount = 1;
		$.ajax({
			type : "POST",
			url : "addCartByProduct.do",
			data : JSON.stringify(dataObj) ,
			async : false,
			timeout : 15000,
			dataType : "json",
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				var resultStr = data.result;
				if (resultStr == "limit") {
					$(".shop-menu ul li").first().replaceWith($('<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>').on('click', function() {
						var _$this = $(this);
						changePageContent(_$this);
						return false;
					}));
					$("#cartTotalCount").text("");
					$("#memberLogin").find("a").click();
				} else {
					var productTotalCount = data.productTotalCount;
					$("#cartTotalCount").text("(" + productTotalCount + ")");
					$("#msg").show().animate({
						width : '250px'
					}, 200).fadeOut(1000);
				}
			},
			complete : function(data) {

			}
		});
	};
	
	$scope.addProductDetail = function (dataObj) {
		var productCount = $("#ordersDetail #productDetailCount").val();
		dataObj.productCount = productCount;
		console.log(dataObj.productCount);
		
		$.ajax({
			type : "POST",
			url : "addCartByProduct.do",
			data : JSON.stringify(dataObj) ,
			async : false,
			timeout : 15000,
			dataType : "json",
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				var resultStr = data.result;
				if (resultStr == "limit") {
					productDetailDialog.close();
					$(".shop-menu ul li").first().replaceWith($('<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>').on('click', function() {
						var _$this = $(this);
						changePageContent(_$this);
						return false;
					}));
					$("#cartTotalCount").text("");
					$("#memberLogin").find("a").click();
				} else {
					var productTotalCount = data.productTotalCount;
					$("#cartTotalCount").text("(" + productTotalCount + ")");
					$("#msg").show().animate({
						width : '250px'
					}, 200).fadeOut(1000);
				}
			},
			complete : function(data) {

			}
		});
	};
	
	$scope.prev = function() {
		return curPage = 0;
	};
}]).filter('pageStartFrom', function() {
	return function(input, start) {
	    start = +start;
	    return input.slice(start);
	  };
});

// 註冊會員 Angular Controller
app.controller('memberCtrl', function($scope, $element, $http){
    console.log("memberCtrl loaded");
    // function to submit the form after all validation has occurred            
    $scope.memberSubmit = function(isValid) {
        console.log("result = = " + isValid);
        // check to make sure the form is completely valid
        if (isValid) { 
            $.ajax({
                type: "POST",
                url: "saveMember.do",
                data: $element.serialize(),
                async: false,
                timeout: 15000,
                dataType:"json",
                success: function(data) {
                    var resultMsg = data.result;
                    alert(data.successMsg);
                    if(resultMsg) {
                        window.location.href="index.jsp";
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
    /*
    $.ajax({
        type: "POST",
        url: "getCity.do",
        data: {},
        async: false,
        timeout: 15000,
        dataType:"json",
        success: function(data) {
            $scope.addressCitys = data.cityList;
        },
        complete: function() {
            
        }
    });
    
    $scope.changeCity = function() {
    	$.ajax({
            type: "POST",
            url: "getCountry.do",
            data: {city: $scope.addressCity},
            async: false,
            timeout: 15000,
            dataType:"json",
            success: function(data) {
            	console.log(data.countryList);
                $scope.addressCountrys = data.countryList;
            },
            complete: function() {
                
            }
        });
    };
    
    $scope.changeCountry = function() {
    	$.ajax({
            type: "POST",
            url: "getRoad.do",
            data: {city: $scope.addressCity, country: $scope.addressCountry},
            async: false,
            timeout: 15000,
            dataType:"json",
            success: function(data) {
            	console.log(data.roadList);
                $scope.addressRoads = data.roadList;
            },
            complete: function() {
                
            }
        });
    };*/
    
    $scope.reset = function(form) {
        $scope.account = "";
        $scope.password = "";
        $scope.originalPassword = "";
        $scope.email = "";
        $scope.lastname = "";
        $scope.firstname = "";
        $scope.telephone = "";
        $scope.mobile = "";
        $scope.address = "";
        form.$setPristine();
        form.$setUntouched();
    };
    
    $scope.checkAccount = function() {
        var memberAcc = $scope.account;
        if(typeof(memberAcc) == 'undefined') {
            BootstrapDialog.show({
                title: '網頁資訊',
                message: '<h3 class="word-error">請輸入欲申請會員的帳號！！</h3>',
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
        } else {
            $.ajax({
                url : "checkMember.do",
                dataType : "json",
                type : "POST",
                data : $element.serialize(),
                async : false,
                timeout : 15000,
                success : function(data) {
                    var checkResult = data.checkResult;
                    if(checkResult) {
                        BootstrapDialog.show({
                            title: '網頁資訊',
                            message: '<h3>該帳號未申請！！</h3>',
                            type: BootstrapDialog.TYPE_WARNING,
                            closable:false,
                            buttons: [{
                                label: '確定',
                                cssClass: 'btn-primary',
                                action: function(dialogItself){
                                	$('#formMember .row:eq(1) button').prop('disabled', false);
                                	dialogItself.close();
                                }
                            }]
                        }).getModalBody().css('text-align', 'center');
                    } else {
                        BootstrapDialog.show({
                            title: '網頁資訊',
                            message: '<h3>該帳號已申請，請更換帳號！</h3>',
                            type: BootstrapDialog.TYPE_WARNING,
                            closable:false,
                            buttons: [{
                                label: '確定',
                                cssClass: 'btn-primary',
                                action: function(dialogItself){
                                    $scope.formMember.account.$setViewValue("");
                                    $scope.formMember.account.$render();
                                    $('#formMember .row:eq(1) button').prop('disabled', true);
                                    dialogItself.close();
                                }
                            }]
                        }).getModalBody().css('text-align', 'center');
                    }
                },
                error: function (result) {
                    alert("Ajax request(checkMember) error");
                }
            });
        }
        
    };
}).directive('validPassword', function () { // 密碼規則檢查
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
                var isBlank = viewValue === ''; // 是空白 true 非空白 false
                var invalidLen = !isBlank && (viewValue.length < 8 || viewValue.length > 20);
                var isWeak = !isBlank && !invalidLen && !/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,21}$/.test(viewValue);
                ctrl.$setValidity('isBlank', !isBlank);
                ctrl.$setValidity('invalidLen', !invalidLen);
                ctrl.$setValidity('isWeak', !isWeak);
                scope.passwordGood = !isBlank && !invalidLen && !isWeak;
                return scope.passwordGood;
            });
        }
    };
}).directive('checkPassword', function () { // 確認密碼一致性檢查
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.checkPassword;
            elem.add(firstPassword).on('keyup', function () {
                scope.$apply(function () {
                    var isBlank = elem.val() === '';
                    var originalPasswordGood = !isBlank && (elem.val()===$(firstPassword).val());
                    ctrl.$setValidity('passwordMatch', originalPasswordGood);
                    scope.originalPasswordGood = !isBlank && originalPasswordGood;
                    return scope.originalPasswordGood;
                });
            });
        }
    };
}).directive('validEmail', function () { // EMAIL檢查
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
                var isBlank = viewValue === '';
                var invalidate = !isBlank && !/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(viewValue);
                ctrl.$setValidity('isBlank', !isBlank);
                ctrl.$setValidity('invalidate', !invalidate);
                scope.emailGood = !invalidate;
                return scope.emailGood;
            });
        }
    };
});

// 密碼查詢 Angular Controller
app.controller('queryPasswordCtrl', function($scope){
    console.log("queryPasswordCtrl loaded");
});

// 繪製購物車訂購清單
app.controller('cartController', ['$scope','$window',function($scope, $window) {
    console.log("cartController loaded");
    $scope.orderType = 'productNo';
    // 這一行控制預設情況下是以什麼方式排序
    $scope.order = '';
    $scope.cart = [];
    
    $.ajax({
        url : "getShoppingCartOrder.do",
        dataType : "json",
        type : "POST",
        data : {},
        async : false,
        timeout : 15000,
        success : function(data) {
            var itemCount = data.productTotalCount;
            // 有商品才繪出購物車
            if(parseInt(itemCount) > 0) {
                $scope.cart = data.orders;
            }
        },
        error: function (result) {
            alert("Ajax request(getShoppingCartOrder) error");
        }
    });
    
    // 計算合計金額
    $scope.totalPrice = function() {
        var total = 0;
        angular.forEach($scope.cart,function(item){
            total += item.salesPrice * item.productCount;
        });
        return total;
    };
    // 計算商品總數
    $scope.totalQuantity = function() {
        var totalq = 0;
        angular.forEach($scope.cart,function(item) {
            if(item.productCount===''||item.productCount===null||item.productCount<0){
                item.productCount=1;
            }
            totalq +=  parseInt(item.productCount);
        });
        return totalq;
    };
    // 找到索引
    var findIndex = function(productNo) {
        angular.forEach($scope.cart,function(item,key) {
            if(item.productNo === productNo) {
                index = key;
                return;
            }
        });
        return index;
    };
    // 移除操作
    $scope.remove = function(productNo) {
        var index =-1;
        index =findIndex(productNo);
        angular.forEach($scope.cart,function(item,key) {
            if(item.productNo === productNo){
                index = key;
            }
        });
        if(index !== -1) {
            var returnkey = confirm('是否從購物車中移除該商品？');
            if(returnkey) {
                $scope.cart.splice(index,1);
                removeProdctByCart(productNo);
            }
        }
    };
    
    // 為商品添加購買數量
    $scope.qadd = function(productNo) {
        var index =-1;
        index =findIndex(productNo);
        if(index !== -1) {
            var quantity = parseInt($scope.cart[index].productCount)+1;
            $scope.cart[index].productCount = quantity;
            updateCartByProduct(productNo, quantity);
        }
    };
    
    // 為商品減少購買數量
    $scope.qreduce = function(productNo) {
        var index =-1;
        index =findIndex(productNo);
        if(index !== -1) {
            if($scope.cart[index].productCount>1) {
                var quantity = parseInt($scope.cart[index].productCount)-1;
                $scope.cart[index].productCount = quantity;
                updateCartByProduct(productNo, quantity);
            }else{
                    $scope.remove(productNo);
            }
        }
    };
    
    // 為商品更新購買數量
    $scope.qchange = function(productNo) {
        var index =-1;
        index =findIndex(productNo);
        if(index !== -1) {
            var quantity = parseInt($scope.cart[index].productCount);
            if(!isNaN(quantity)) {
                updateCartByProduct(productNo, quantity);
            }
        }
    };
    
    // 清空購物車
    $scope.removeAll = function() {
        var returnkey = confirm('是否清空購物車？');
        if(returnkey) {
            $scope.cart = [];
            clearByCart();
        }
    };
    
    //繼續購物
    $scope.continueCart = function() {
        $("[class='sub-menu'] li").eq(0).find("a").click();
    };
    
    // 前往結帳
    $scope.payCart = function() {
        $("#checkout").find("a").click();
    };
    
    // 排序
    $scope.changeOrder = function(type){
        $scope.orderType = type;
        if($scope.order === '') {
            $scope.order = '-';
        } else {
            $scope.order = '';
        }
    };
}]);

// 購物車結帳
app.controller('checkoutCartCtrl',['$scope','$window',function($scope, $window) {
    console.log("checkoutCartCtrl load");
    
    $scope.addresseeInfo = "renew";
    
    $scope.member = [];
    $.ajax({
        url : "getSessionMember.do",
        dataType : "json",
        type : "POST",
        data : {},
        async : false,
        timeout : 15000,
        success : function(data) {
        	$scope.member = data.member;
        },
        error: function (result) {
            alert("Ajax request(getSessionMember) error");
        }
    });
    // 前往結帳
    $scope.memberOrder = function() {
        $("#searchOrders").find("a").click();
        $.ajax({
            url : "getSessionMember.do",
            dataType : "json",
            type : "POST",
            data : {},
            async : false,
            timeout : 15000,
            success : function(data) {
                $window.memberInfo = data.member;
            },
            error: function (result) {
                alert("Ajax request(getSessionMember) error");
            }
        });
    };
    
    $scope.changeAddressee = function(radioVal) {
    	var radioResult = radioVal.addresseeInfo;
    	if(radioResult == "same") {
    		$scope.addresseeName = (typeof($scope.member.lastname) != 'undefined'?$scope.member.lastname:"") + (typeof($scope.member.firstname) != 'undefined'?$scope.member.firstname:"");
    		$scope.addresseeTelephone = $scope.member.telephone;
    		$scope.addresseeMobile = $scope.member.mobile;
    		$scope.addresseeAddress = $scope.member.address;
    	} else if(radioResult == "renew") {
    		$scope.addresseeName = "";
    		$scope.addresseeTelephone = "";
    		$scope.addresseeMobile = "";
    		$scope.addresseeAddress = "";
    	}
    };
}]);

//訂單查詢 Angular Controller
app.controller('historyOrderCtrl',['$scope','$element','$http','$window','$compile',function($scope, $element, $http, $window, $compile) {
    console.log("historyOrderCtrl loaded");
    $scope.detailOrders;
    
    // 詳細預設排序
    $scope.detailOrderType = 'productNo';
    // 排序
    $scope.detailOrder = "";
    
    $scope.workspaces = [];
    $scope.workspaces.push({ name: '本月訂單', datas:$window.memberOrdersData });
    $scope.workspaces.push({ name: '歷史訂單', datas:$window.memberHistoryOrdersData });
    
    $scope.workspaces.forEach(function (wk,index) {
        wk.bsTableControl = {
            options: {
                data: wk.datas,
                rowStyle: function (row, index) {
                    return { classes: 'none' };
                },
                cache: false,
                height: 600,
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
                    title: '匯款帳號後5碼',
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
        
        // 匯款資訊樣式
        function buttonFormatter(value, row, index) {
        	var rowVal = row.payStatus;
        	var formatter = '<button type="button" class="btn btn-xs btn-primary" ng-click="$parent.writeBankInfo(\''+ row.orderNo +'\')"><span class="fa fa-pencil"></span>填寫匯款資訊</button>';
        	switch(rowVal){
	        	case 'C':
	        		formatter = '<article><p>'+ row.remittanceAccount + '</p></article>';
	        	  break;
	        	case 'Y':
	        		formatter = '<article><p>'+ row.remittanceAccount + '</p></article>';
	        	  break;
	        	default:
        		
        	}
            return formatter;
        }
    });


    $scope.changeCurrentWorkspace = function (wk) {
        $scope.currentWorkspace = wk;
    };
    
    // 訂單詳細資料排序
    $scope.changeDetailOrder = function(type) {
        $scope.detailOrderType = type;
        if($scope.detailOrder === "") {
            $scope.detailOrder = "-";
        } else {
            $scope.detailOrder = "";
        }
    };
    
    // 訂單詳細資訊
    $scope.itemDetail = function(item, index) {
    	
    	$scope.detailOrders = item.$parent.wk.datas[index].ordersSet;
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
    
    // 填寫匯款資訊
    $scope.writeBankInfo = function(orderNo) {
    	var $button;
    	var bankForm = $('<form name="bankInfoForm" novalidate><div ng-class="{ \'has-error\' : bankInfoForm.bankAfterFiveNumber.$invalid && !bankInfoForm.bankAfterFiveNumber.$pristine, \'has-success\' : bankInfoForm.bankAfterFiveNumber.$valid && !bankInfoForm.bankAfterFiveNumber.$pristine }"><input type="text" class="form-control" name="bankAfterFiveNumber" placeholder="請輸入匯款帳號後5碼" ng-model="afterFiveNumber" ng-minlength=5 ng-maxlength=5 required /><p ng-show="bankInfoForm.bankAfterFiveNumber.$invalid && !bankInfoForm.bankAfterFiveNumber.$pristine" class="help-block has-error">＊匯款帳號長度不正確</p></div></form>');
    	$compile(bankForm)($scope);
    	BootstrapDialog.show({
            title: '訂單:' + orderNo + '匯款資訊',
            message: bankForm,
            type: BootstrapDialog.TYPE_SUCCESS,
            size: BootstrapDialog.SIZE_SMALL,
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            buttons: [{
                label: '確定',
                cssClass: 'btn-primary',
                action: function(dialog) {
                	$button = this;
                	var fiveNumber = $scope.afterFiveNumber;
                	var validResult = $scope.bankInfoForm.bankAfterFiveNumber.$valid;
                	if(validResult) {
                		updateOrderByBankInfo(orderNo, fiveNumber);
                		$("[class='sub-menu'] li:eq(2)").find("a").click();
                	} else {
                		$scope.afterFiveNumber = "";
                	}
                	dialog.close();
                }
            }],
            onhide: function(dialog){
            	if(typeof($button) == 'undefined') {
            		$scope.afterFiveNumber = "";
                	$scope.bankInfoForm.$setPristine();
                	$scope.bankInfoForm.$setUntouched();
            	}
            }
        });
    };


    //Select the workspace in document ready event
    $(function () {
        $scope.changeCurrentWorkspace($scope.workspaces[0]);
    });
}]);

//Q & A Angular Controller
app.controller('qAndaCtrl',['$scope','$element','$http','$window','$compile',function($scope, $element, $http, $window, $compile) {
    console.log("qAndaCtrl loaded");
    
    $scope.bsTableControl = {
    		options: {
                data: $window.jsonGodsData,
                rowStyle: function (row, index) {
                    return { classes: 'none' };
                },
                cache: false,
                height: 550,
                striped: true,
                pagination: true,
                pageSize: 10,
                pageList: [5, 10, 25, 50, 100, 200],
                search: true,
                showColumns: false,
                showRefresh: false,
                minimumCountColumns: 2,
                clickToSelect: false,
                showToggle: false,
                maintainSelected: true,
                columns: [{
                    field: 'qandaNo',
                    title: '問題編號',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                }, {
                    field: 'title',
                    title: '常見問題',
                    align: 'left',
                    valign: 'middle',
                    sortable: true,
                    formatter: contentFormatrer,
                }]
            }
        };
    
    // Q & A資料
    function contentFormatrer(value, row, index) {
    	return '<a href="javascript: void(0);" ng-click="$parent.qAndaDetail(\''+ row.contents +'\')">'+ row.title +'</a>';
    }
    
    // Q & A詳細資訊
    $scope.qAndaDetail = function(contents) {
    	BootstrapDialog.show({
            title: '詳細說明',
            message: contents,
            type: BootstrapDialog.TYPE_PRIMARY,
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

function changePageContent(_$this) {
	var _clickTab = _$this.find('a').attr('href'); // 找到連結a中的target標籤值
	
	if (_clickTab == "#") { // 未開放
		alert("此功能尚未開放！");
	} else if ("-1" == _clickTab.search("mailto:")) { // 已開放功能
		angular.element(document).injector().invoke(function($compile, $http, $rootScope, $window) {
            $http.post(_clickTab).success(function (data) {
            	var resultStr = data.result;
				if (resultStr == "limit") {
					$(".shop-menu ul li").first().replaceWith($('<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>').on('click', function() {
						var _$this = $(this);
						changePageContent(_$this);
						return false;
					}));
					$("#cartTotalCount").text("");
					$("#memberLogin").find("a").click();
				} else {
					data = $(data);
                    $("#content-body").html(data);
                    $compile(data)($rootScope);
				}
            });
        });
	}
}