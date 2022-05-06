// 首頁 Angular Controller
var app = angular.module('myApp', []);
app.controller('contentController',['$scope','$window',function($scope, $window) {
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
            },
            error: function (result) {
                alert("Ajax request(getSessionMember) error");
            }
        });
    }
    
}]);

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
                        window.location.href="login.do";
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
            total += item.salePrice * item.productCount;
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
        $("[class='sub-memu-1'] li").eq(0).find("a").click();
    };
    
    // 前往結帳
    $scope.payCart = function() {
        $("[class='sub-memu-2'] li:eq(1)").find("a").click();
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
    $scope.member = $window.memberInfo;
    // 前往結帳
    $scope.memberOrder = function() {
        $("[class='sub-memu-2'] li:eq(2)").find("a").click();
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
    		$scope.addresseeName = $scope.member.lastname + $scope.member.firstname;
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