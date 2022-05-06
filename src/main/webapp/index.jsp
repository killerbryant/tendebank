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
	<script src="js/bootstrap-table-mobile.js"></script>
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
			
			// category div取代iframe
			$("[class^='panel-title']").on('click', function() {
				// 找出 li 中的超連結 href(#id)
				var _$this = $(this);
				changePageContent(_$this);
				return false;
			});
			
			// 第二層選單 div取代iframe
			$("[class^='sub-menu'] li").on('click', function() {
				// 找出 li 中的超連結 href(#id)
				var _$this = $(this);
				changePageContent(_$this);
				return false;
			});

			// 第一層選單 div取代iframe
			$("[class^='top-menu']").on('click', function() {
				var _$this = $(this);
				changePageContent(_$this);
				return false;
			});  

			// 第一層選單 div取代iframe
			$("#searchProduct").click(function (e) {
				var _keywords = $("#productName").val();
				if($.trim(_keywords).length > 0) {
					var _clickTab = "searchProduct.do?keywords="+_keywords;
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
				return false;
			});
		});
	</script>
</head><!--/head-->

<body>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="javascript:void(0);"><i class="fa fa-phone"></i>(02)2975-4793</a></li>
								<li><a href="mailto:tendebank@yahoo.com.tw"><i class="fa fa-envelope"></i>tendebank@yahoo.com.tw</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="javascript: void(window.open('http://www.facebook.com/share.php?u='.concat(encodeURIComponent(location.href))));"><i class="fa fa-facebook"></i></a></li>
								<!-- <li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li> -->
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="index.jsp">
								<img alt="天地銀行香鋪" src="img/tdb_logo.png" />
							</a>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<c:choose>
									<c:when test="${ not empty sessionScope.member.lastname and not empty sessionScope.member.firstname }">
									    <li><span>${ sessionScope.member.lastname }${ sessionScope.member.firstname } 你好！</span><a href="logout.do"><i class="fa fa-lock"></i> 登出</a></li>
									</c:when>
									<c:when test="${ not empty sessionScope.member.account }">
										<li><span>${ sessionScope.member.account } 你好！</span><a href="logout.do"><i class="fa fa-lock"></i> 登出</a></li>
									</c:when>
									<c:otherwise>
										<li id="memberLogin" class="top-menu"><a href="loginPage.do"><i class="fa fa-lock"></i> 登入</a></li>
									</c:otherwise>
								</c:choose>
								<li id="searchOrders" class="top-menu"><a href="goHistoryOrder.do"><i class="fa fa-list"></i> 訂單查詢</a></li>
								<li id="checkout" class="top-menu"><a href="goShoppingCartOrder.do"><i class="fa fa-crosshairs"></i> 結帳</a></li>
								<li id="cart" class="top-menu"><a href="goShoppingCartItem.do"><i class="fa fa-shopping-cart"></i> 購物車<strong id="cartTotalCount"></strong></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li class="top-menu"><a href="about.do" class="active">關於天地</a></li>
								<li><a href="https://www.facebook.com/groups/1515994701976812">粉絲專頁</a></li>
								<li class="dropdown"><a href="#">商品專區<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
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
                                </li>
								<li class="dropdown"><a href="#">問與答<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="goGods.do">神明專區</a></li>
										<li><a href="goAncestors.do">祖先專區</a></li>
                                    </ul>
                                </li>
							</ul>
						</div>
					</div>
					<div class="col-md-3 col-sm-3 col-xs-12 form-group pull-right top_search search_box_new">
		                <div class="input-group">
			                <input type="text" id="productName" class="form-control" placeholder="商品查詢...">
			                <span class="input-group-btn">
			                	<button id="searchProduct" class="btn btn-default" type="button">查詢!</button>
			                </span>
		                </div>
	                </div>
                </div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	<section id="content-body" ng-controller="contentController">
		<!--活動slider-->
		<!-- <section id="slider">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div id="slider-carousel" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
								<li data-target="#slider-carousel" data-slide-to="1"></li>
								<li data-target="#slider-carousel" data-slide-to="2"></li>
							</ol>
							
							<div class="carousel-inner">
								<div class="item active">
									<div class="col-sm-6">
										<h1><span>T</span>-Shop</h1>
										<h2>Free E-Commerce Template</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
										<button type="button" class="btn btn-default get">活動內容</button>
									</div>
									<div class="col-sm-6">
										<img src="img/home/girl1.jpg" class="girl img-responsive" alt="" />
									</div>
								</div>
								
								<div class="item">
									<div class="col-sm-6">
										<h1><span>T</span>-Shop</h1>
										<h2>100% Responsive Design</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
										<button type="button" class="btn btn-default get">活動內容</button>
									</div>
									<div class="col-sm-6">
										<img src="img/home/girl2.jpg" class="girl img-responsive" alt="" />
									</div>
								</div>
								
								<div class="item">
									<div class="col-sm-6">
										<h1><span>T</span>-Shop</h1>
										<h2>Free Ecommerce Template</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
										<button type="button" class="btn btn-default get">活動內容</button>
									</div>
									<div class="col-sm-6">
										<img src="img/home/girl3.jpg" class="girl img-responsive" alt="" />
									</div>
								</div>
								
							</div>
							
							<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							</a>
							<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
								<i class="fa fa-angle-right"></i>
							</a>
						</div>
						
					</div>
				</div>
			</div>
		</section> -->
		<!--/slider-->
		
		<section>
			<div class="container">
				<div class="row">
					<div class="col-sm-3">
						<div class="left-sidebar">
							<h2>商品分類</h2>
							<div class="panel-group category-products" id="accordian"><!--category-productsr-->
								<!-- <div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
												<span class="badge pull-right"><i class="fa fa-plus"></i></span>
												Sportswear
											</a>
										</h4>
									</div>
									<div id="sportswear" class="panel-collapse collapse">
										<div class="panel-body">
											<ul>
												<li><a href="#">Nike </a></li>
												<li><a href="#">Under Armour </a></li>
												<li><a href="#">Adidas </a></li>
												<li><a href="#">Puma</a></li>
												<li><a href="#">ASICS </a></li>
											</ul>
										</div>
									</div>
								</div> -->
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=A">金紙</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=B">香品</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=C">蠟燭</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=D">油品</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=E">蓮花紙</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=F">紙紮</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=G">銅器</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=H">金爐</a></h4>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="goProduct.do?productType=I">小紙</a></h4>
									</div>
								</div>
							</div><!--/category-products-->
						
						</div>
					</div>
					
					<div class="col-sm-9 padding-right">
						<div class="features_items"><!--features_items-->
							<h2 class="title text-center">熱門商品</h2>
							<div class="col-sm-4" ng-repeat="item in hotProducts">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
											<img ng-src="{{item.imgPath || 'img/withoutImage.jpg'}}" style="width: 180px;height: 180px;" onError="this.onerror=null;this.src='img/withoutImage.jpg';">
											<h2 ng-show="item.salesPrice">NT. {{item.salesPrice}}</h2>
											<h2 ng-show="!item.salesPrice">請來店洽詢</h2>
											<a  href="javascript: void(0);" ng-click="openContents($index)"><p>{{item.productName}}</p></a>
											<a ng-disabled="!item.salesPrice" href="javascript: void(0);" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>加入購物車</a>
										</div>
										<div class="product-overlay" ng-show="item.salesPrice">
											<div class="overlay-content">
												<h2 ng-show="item.salesPrice">NT. {{item.salesPrice}}</h2>
												<h2 ng-hide="item.salesPrice">請來店洽詢</h2>
												<a href="javascript: void(0);" ng-click="openContents($index,0)"><p>{{item.productName}}</p></a>
												<a href="javascript: void(0);" class="btn btn-default add-to-cart" ng-click="addProduct($index,0)"><i class="fa fa-shopping-cart"></i>加入購物車</a>
											</div>
										</div>
										<img src="img/home/hot.png" class="new" alt="" />
									</div>
								</div>
							</div>
						</div><!--features_items-->
						
						<div class="recommended_items"><!--recommended_items-->
							<h2 class="title text-center">最新商品</h2>
							
							<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
								<div class="carousel-inner">
									<div class="item active">	
										<div class="col-sm-4" ng-repeat="firstItem in newFirstProducts">
											<div class="product-image-wrapper">
												<div class="single-products">
													<div class="productinfo text-center">
														<img ng-src="{{firstItem.imgPath || 'img/withoutImage.jpg'}}" style="width: 180px;height: 180px;" onError="this.onerror=null;this.src='img/withoutImage.jpg';">
														<h2 ng-show="firstItem.salesPrice">NT. {{firstItem.salesPrice}}</h2>
														<h2 ng-show="!firstItem.salesPrice">請來店洽詢</h2>
														<a href="javascript: void(0);" ng-click="openContents($index,1)"><p>{{firstItem.productName}}</p></a>
														<a ng-disabled="!firstItem.salesPrice" href="javascript: void(0);" class="btn btn-default add-to-cart" ng-click="addProduct($index,1)"><i class="fa fa-shopping-cart"></i>加入購物車</a>
													</div>
													
												</div>
											</div>
										</div>
									</div>
									<div class="item">	
										<div class="col-sm-4" ng-repeat="secondItem in newSecondProducts">
											<div class="product-image-wrapper">
												<div class="single-products">
													<div class="productinfo text-center">
														<img ng-src="{{secondItem.imgPath || 'img/withoutImage.jpg'}}" style="width: 180px;height: 180px;" onError="this.onerror=null;this.src='img/withoutImage.jpg';">
														<h2 ng-show="secondItem.salesPrice">NT. {{secondItem.salesPrice}}</h2>
														<h2 ng-show="!secondItem.salesPrice">請來店洽詢</h2>
														<a href="javascript: void(0);" ng-click="openContents($index,2)"><p>{{secondItem.productName}}</p></a>
														<a ng-disabled="!secondItem.salesPrice" href="javascript: void(0);" class="btn btn-default add-to-cart" ng-click="addProduct($index,2)"><i class="fa fa-shopping-cart"></i>加入購物車</a>
													</div>
													
												</div>
											</div>
										</div>
									</div>
								</div>
								 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
									<i class="fa fa-angle-left"></i>
								  </a>
								  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
									<i class="fa fa-angle-right"></i>
								  </a>			
							</div>
						</div><!--/recommended_items-->
						
					</div>
				</div>
			</div>
			<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-content">
					<ul class="list-inline item-details">
						<li><a href="http://themifycloud.com">ThemifyCloud</a></li>
						<li><a href="http://themescloud.org">ThemesCloud</a></li>
					</ul>
				</div>
			</div>
		</section>
		<!-- 商品Detail -->
		<section id="productDetail" style="display: none;">
			<div class="container">
				<div class="row">
					<div class="padding-right">
						<div class="product-details" ng-model="productDetail"><!--product-details-->
							<div class="col-sm-4">
								<div class="view-product">
									<img ng-src="{{productDetail.imgPath || 'img/withoutImage.jpg'}}" style="width: 350px;height: 350px;" onError="this.onerror=null;this.src='img/withoutImage.jpg';">
								</div>
							</div>
							<div class="col-sm-5">
								<div class="product-information"><!--/product-information-->
									<img src="img/product-details/new.jpg" class="newarrival" alt="" />
									<h2>{{productDetail.productName}}</h2>
									<p>No: {{productDetail.productNo}}</p>
									<span ng-show="productDetail.salesPrice">
										<span>NT. {{productDetail.salesPrice}}</span>
										<label>數量:</label>
										<input type="text" id="productDetailCount" value="{{productDetail.productCount || 1}}" />
										<button type="button" class="btn btn-fefault cart" ng-click="addProductDetail(productDetail)">
											<i class="fa fa-shopping-cart"></i>加入購物車
										</button>
									</span>
									<span ng-show="!productDetail.salesPrice">
										<span>請來店洽詢</span>
									</span>
									<p><b>產品描述:</b> {{productDetail.description}}</p>
								</div><!--/product-information-->
							</div>
						</div><!--/product-details-->
					</div>
				</div>
			</div>
			<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-content">
					<ul class="list-inline item-details">
						<li><a href="http://themifycloud.com">Ecommerce templates</a></li>
						<li><a href="http://themescloud.org">Ecommerce themes</a></li>
					</ul>
				</div>
			</div>
		</section>
	</section>
	
	
	
	<footer id="footer"><!--Footer-->
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="companyinfo">
							<h2><span>T</span>endebank</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
						</div>
					</div>
					<div class="col-sm-7">
						<!-- <div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="img/home/iframe1.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div> -->
					</div>
					<div class="col-sm-3">
						<div class="address">
							<img src="img/home/map.png" alt="" />
							<p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2016 Tendebank Inc. All rights reserved.</p>
					<p class="pull-right">Designed by <span><a target="_blank" href="http://www.edisondai.com">EdisonDai</a></span></p>
				</div>
			</div>
		</div>
		
	</footer><!--/Footer-->
	<div id="msg">已成功加入購物車！</div>
</body>
</html>