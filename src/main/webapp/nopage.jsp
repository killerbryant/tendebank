<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
	<%@include file="/taglib.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>天地銀行 | Tendebank</title>
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
	<!-- Bootstrap table CSS -->
	<link rel="stylesheet" href="css/bootstrap-table.css"/>
	<!-- Bootstrap select CSS -->
	<link rel="stylesheet" href="css/nya-bs-select.css"/>
	<!-- font-awesome CSS -->
	<link rel="stylesheet" href="css/font-awesome.css">
	<!-- Social Buttons for Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap-social.css">
	<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="img/icon/tdb.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="img/ico/apple-touch-icon-57-precomposed.png">
	<!-- Tendebank CSS -->
	<link rel="stylesheet" href="css/prettyPhoto.css">
	<link rel="stylesheet" href="css/price-range.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/responsive.css">
	<link rel="stylesheet" href="css/tendebankStyle.css">
	<!-- jQuery JS -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<!-- Bootstrap JS -->
	<script src="js/bootstrap.js"></script>
	<!-- Bootstrap dialog JS -->
	<script src="js/bootstrap-dialog.js"></script>
	<!-- Angular JS -->
	<script src="js/angular.js"></script>
	<!-- Bootstrap table JS -->
	<script src="js/bootstrap-table.js"></script>
	<script src="js/bootstrap-table-angular.js"></script>
    <script src="js/bootstrap-table-zh-TW.js"></script>
    <script src="js/bsTableDirective.js"></script>
    <!-- Bootstrap select JS -->
    <script src="js/nya-bs-select.js"></script>
	<!-- jQuery scrollUp JS -->
    <script src="js/jquery.scrollUp.js"></script>
    <!-- TDB JS -->
	<script src="js/tdb.angular.js"></script>
	<script src="js/config.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
	<script type="text/javascript">
		$(function() {
	
			// div取代iframe
			$("[class^='sub-menu'] li").click(function() {
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

			$("[class^='top-menu']").click(function() {
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
		});
	</script>
</head><!--/head-->

<body>
	<div class="container text-center">
		<div class="logo-404">
			<a href="index.jsp"><span>回首頁</span></a>
		</div>
		<div class="content-404">
			<img src="img/404/404.png" class="img-responsive" alt="" />
			<h1><b>哎呀!</b> 我們找不到這個頁面</h1>
			<p>嗯... 您查找的頁面可能不存在。</p>
			<h2><a href="index.jsp">回首頁</a></h2>
		</div>
	</div>
</body>
</html>