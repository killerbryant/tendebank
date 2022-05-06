<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
	<%@include file="/taglib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>天地銀行香鋪</title>
	<link type="image/x-icon" rel="Shortcut Icon" href="img/icon/tdb.ico" />
	<!-- Facebook meta -->
	<meta property="og:title" content="天地銀行香鋪"/> <!-- 分享的TITLE  -->
	<meta property="og:type" content="website"/>
	<meta property="og:description" content="天地銀行香鋪擴大經營，從三重埔龍濱路轉戰到三重埔河邊北街來為大家服務，希望舊雨新知，能繼續支持天地銀行香鋪，我們的營業項目有，專業金紙販售，多樣蓮花紙藝,中元節拜拜普渡,天地銀行金紙香綜合百貨香鋪."/> <!-- 分享的內容描述 -->
	<meta property="og:url" content="http://www.edisondai.com/tendebank/"/> <!-- 分享網頁的連結 -->
	<meta property="og:image" content="http://www.edisondai.com/tendebank/img/tdb_logo.png"/> <!-- 圖片URL -->
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Bootstrap theme CSS -->
	<link rel="stylesheet" href="css/bootstrap-theme.css">
	<!-- Bootstrap dialog CSS -->
	<link rel="stylesheet" href="css/bootstrap-dialog.css">
	<!-- font-awesome CSS -->
	<link rel="stylesheet" href="css/font-awesome.css">
	<!-- Social Buttons for Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap-social.css">
	<!-- 分頁插件 -->
	<link type="text/css" rel="stylesheet" href="css/jqpagination.css">
	<!-- 彩色動態伸縮子選單 -->
	<link type="text/css" rel="stylesheet" href="css/color-menu.css">
	<link type="text/css" rel="stylesheet" href="css/tendebankStyle.css">
	<!-- jQuery JS -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<!-- Bootstrap JS -->
	<script src="js/bootstrap.js"></script>
	<!-- Bootstrap dialog JS -->
	<script src="js/bootstrap-dialog.js"></script>
	<!-- Angular JS -->
	<script src="js/angular.js"></script>
	<script src="js/tdb.angular.js"></script>
	<script src="js/config.js"></script>
	<script type="text/javascript">
		$(function() {
			
			// 登入清除資訊button
			$('#clearData').click(function() {
				$('#account').val("");
				$('#password').val("");
				$('#errorMsg').text("");
			});
	
			// div取代iframe
			$("[class^='sub-memu-'] li").click(function() {
				// 找出 li 中的超連結 href(#id)
				var sessionData = "${ sessionScope.member.account }";
				var _$this = $(this);
				var _clickTab = _$this.find('a').attr('href'); // 找到連結a中的target標籤值
				if ("0" == _clickTab.search("mailto:")) { // 電子郵件的url
					_$this.find('a').click();
				} else if (_clickTab == "#") { // 未開放
					alert("此功能尚未開放！");
				} else if ("-1" == _clickTab.search("mailto:")) { // 已開放功能
					angular.element(document).injector().invoke(function($compile, $http, $rootScope) {
		                $http.post(_clickTab).success(function (data) {
		                	if ($.trim(data).length == 0) {
								alert("請先登入會員！");
								window.location.href = "index.jsp";
							} else if (sessionData != null && sessionData != "") {
								data = $(data);
		                    	$("#content-body").html(data);
		                    	$compile(data)($rootScope);
							} else {
								var result = data.result;
								if (result == "limit") {
									alert("請先登入會員！");
								} else {
									data = $(data);
			                        $("#content-body").html(data);
			                        $compile(data)($rootScope);
								}
							}
			            });
		            });
				}
				return false;
			});
			initMenuBar();
		});
	
		function initMenuBar() {
			// 先取得相關選單元素及高度
			var $menuWrapper = $('#menu-wrapper'), $subMenuWrapper = $menuWrapper
					.find('.sub-menu-wrapper').add($menuWrapper.find('.sub-menu')), _height = $subMenuWrapper
					.height(), _animateSpeed = 200;
	
			// 先把 $subMenuWrapper 的高度歸 0
			// 並把 .sub-menu ul 先往上移動隱藏
			var $subMenu = $subMenuWrapper.height(0).find('.sub-menu ul').css({
				top : _height * -1
			});
	
			// 當滑鼠移入到 .main-menu ul li a 上時
			$('.main-menu ul li a').mouseover(
					function() {
						// 先取出被滑鼠移入的選單
						// 並取得該選單中第一個 span 的文字顏色
						var $this = $(this), $color = $this.find('span').css('color'), _no = $this.parent().index();
	
						// 改變 $subMenuWrapper 的顏色為 $color 並展開高度
						$subMenuWrapper.css({
							backgroundColor : $color,
							borderTopColor : $color
						}).stop().animate({
							height : _height
						}, _animateSpeed);
	
						// 移動相對應的子選單
						$subMenu.eq(_no).stop().animate({
							top : 0
						}, _animateSpeed).siblings().stop().animate({
							top : _height * -1
						}, _animateSpeed);
	
						// 讓被滑鼠移入的選單加上指定的效果
						$this.addClass('selected').parent().siblings().find('a.selected').removeClass('selected');
	
						return false;
					});
	
			// 當滑鼠移出 $menuWrapper 後把 $subMenuWrapper 的高度歸 0
			$menuWrapper.mouseleave(function() {
				$subMenuWrapper.stop().animate({
					height : 0
				}, _animateSpeed);
			});
		}
	</script>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="container">
				<div class="row">
					<div class="col-lg-3" style="margin-top:10px">
						<a href="login.do">
							<img alt="天地銀行香鋪" src="img/tdb_logo.png" />
						</a>
					</div>
					<div class="col-lg-7" style="margin-top:10px">
						
					</div>
					<div class="col-lg-2">
						<div class="text-right" style="margin-top:10px">
							<a class="btn btn-social-icon btn-xs btn-facebook" href="javascript: void(window.open('http://www.facebook.com/share.php?u='.concat(encodeURIComponent(location.href))));">
							  <span class="fa fa-facebook"></span>
							</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div id="menu-wrapper">
						<div class="main-menu-wrapper">
							<div class="main-menu">
								<ul>
									<li class="m1"><a href="#"><span>關</span>於天地</a></li>
									<li class="m2"><a href="#"><span>商</span>品專區</a></li>
									<li class="m3"><a href="#"><span>購</span>物管理</a></li>
									<li class="m4"><a href="#"><span>祭</span>祀Q＆A</a></li>
									<li class="m5"><a href="#"><span>會</span>員專區</a></li>
									<!-- <li class="m6"><a href="#"><span>後</span>台專區</a></li> -->
								</ul>
							</div>
						</div>
						<div class="sub-menu-wrapper">
							<div class="sub-menu">
								<ul class="sub-memu-0">
									<li><a href="about.do">天地銀行簡介</a></li>
									<li><a href="mailto:tendebank@yahoo.com.tw">聯絡天地銀行</a></li>
								</ul>
								<ul class="sub-memu-1">
									<li><a href="goProduct.do?productType=A">金紙</a></li>
									<li><a href="goProduct.do?productType=B">香品</a></li>
									<li><a href="goProduct.do?productType=C">蠟燭</a></li>
									<li><a href="goProduct.do?productType=D">油品</a></li>
									<li><a href="goProduct.do?productType=E">蓮花紙</a></li>
									<li><a href="goProduct.do?productType=F">紙紮</a></li>
									<li><a href="goProduct.do?productType=G">銅器</a></li>
									<li><a href="goProduct.do?productType=H">金爐</a></li>
									<li><a href="goProduct.do?productType=I">小紙</a></li>
								</ul>
								<ul class="sub-memu-2">
									<li><a href="goShoppingCartItem.do">購物車清單</a></li>
									<li><a href="goShoppingCartOrder.do">購物車結帳</a></li>
									<li><a href="goHistoryOrder.do">訂單查詢</a></li>
								</ul>
								<ul class="sub-memu-3">
									<li><a href="goGods.do">神明專區</a></li>
									<li><a href="goAncestors.do">祖先專區</a></li>
								</ul>
								<ul class="sub-memu-4">
									<li><a href="member.do">會員註冊/登入</a></li>
									<li><a href="queryPassword.do">密碼查詢</a></li>
								</ul>
								<!-- <ul class="sub-memu-5">
									<li><a href="addProduct">新增商品</a></li>
									<li><a href="addVendor">新增廠商</a></li>
								</ul> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3" id="sidebar">
					<c:choose>
						<c:when test="${ not empty sessionScope.member.lastname and not empty sessionScope.member.firstname }">
						    ${ sessionScope.member.lastname }${ sessionScope.member.firstname } 你好！
						    <a href="logout.do">登出</a>
						</c:when>
						<c:when test="${ not empty sessionScope.member.account }">
						    ${ sessionScope.member.account } 你好！
						    <a href="logout.do">登出</a>
						</c:when>
						<c:otherwise>
							<form id="loginMember" action="login.do" method="post">
								帳號:<input type="text" id="account" name="account" placeholder="請輸入帳號" size="15" />
									<font id="errorMsg" color="red" size="2" style="display: block;">${ errorMsg }</font> 
								密碼:<input type="password" id="password" name="password" placeholder="請輸入密碼" size="15" /><br>
									<input type="checkbox" id="rememberMe" name="rememberMe" value="true" />記住我<br>
									<input type="submit" value="登入" />
									<input id="clearData" type="button" value="清除" /><br>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-lg-9" id="content-body" ng-controller="contentController">
					<img src="img/product.jpg" class="img-thumbnail" />
					<h4 class="title">
						<span>公司簡介</span>
					</h4>
					<table style="width: 100%;" class="ke-zeroborder" border="0" bordercolor="#000000" cellpadding="2" cellspacing="0">
						<tr>
							<td><img width="160" height="120" hspace="2" align="left" vspace="2" src="img/qrcode.jpg" /></td>
							<td>
								<p>

									天地銀行(御)香鋪，自民國85年創立以來，已經歷經18個年頭，承蒙各界善信大德抬愛，讓天地銀行能繼續為您服務，去年天地銀行擴大經營，所以從三重埔龍濱路轉到三重埔河邊北街來為大家服務，希望舊雨新知，能繼續支持天地銀行香鋪，我們的營業項目有，專業金紙販售，多樣蓮花紙藝，本店免費教學。燒紙錢在傳統台灣宗教習俗中，是與神靈之間維繫關係一個重要且流傳已久的習俗，國人崇拜神之至高無上，不同的神，不同的祈求，得燒不同的金紙，而敬拜祖先則是要燒不同的銀紙。
 									<br>
									天地銀行Ⓡ香鋪 <br>
									【營業項目】 香燭紙料 蓮花紙藝 殯儀用品 <br>
									【營業電話】 (02)2975-4793 (02)2975-4794<br>
									【營業地址】 三重埔河邊北街68巷5號 ...</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">天地銀行香鋪 版權所有 (02)2975-4793~4 三重埔河邊北街68巷5號 CopyRight 2016</div>
	</div>
</body>
</html>
