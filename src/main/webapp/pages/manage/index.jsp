<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%@include file="/taglib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>天地銀行香鋪-管理頁面</title>
	<link rel="Shortcut Icon" type="image/x-icon" href="img/icon/tdb.ico" /><!-- Bootstrap CSS -->
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
	<link type="text/css" rel="stylesheet" href="css/tendebankStyle.css">
	<!-- NProgress -->
    <link href="css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="css/custom.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="css/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="css/bootstrap-progressbar-3.3.4.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="css/jqvmap.css" rel="stylesheet"/>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="css/jasny-bootstrap.css">
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
	<!-- Latest compiled and minified JavaScript -->
	<script src="js/jasny-bootstrap.js"></script>
    <!-- Bootstrap select JS -->
    <script src="js/nya-bs-select.js"></script>
    <!-- TDB JS -->
	<script src="js/tdb.manager.angular.js"></script>
	<script src="js/config.js"></script>
	<script type="text/javascript">
		$(function() {
			
			// div取代iframe
			$("[class^='nav child_menu'] li").click(function() {
				// 找出 li 中的超連結 href(#id)
				var _$this = $(this);
				var _clickTab = _$this.find('a').attr('href'); // 找到連結a中的target標籤值
				if (_clickTab == "#") {
					alert("尚未開放此功能");
				} else {
					angular.element(document).injector().invoke(function($compile, $http, $rootScope) {
		                $http.post(_clickTab).success(function (data) {
		                	data = $(data);
	                        $("#manage-content").html(data);
	                        $compile(data)($rootScope);
			            });
		            });
				}
				
				return false;
			});
			
		});
	</script>
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="manage.do" class="site_title"><i class="fa fa-money"></i> <span>Tendebank</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_pic">
                <img src="img/male.png" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>歡迎,</span>
                <h2>${sessionScope.manager.account}</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>一般</h3>
                <ul class="nav side-menu">
                   <li><a><i class="fa fa-edit"></i> 訂單管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="goManageOrdersByNotDeliver.do">本月未出貨訂單</a></li>
                      <li><a href="goManageOrdersByDeliver.do">本月已出貨訂單</a></li>
                      <li><a href="goManageHistoryOrdersByNotDeliver.do">歷史未出貨訂單</a></li>
                      <li><a href="goManageHistoryOrdersByDeliver.do">歷史已出貨訂單</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-home"></i> 產品管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="addProduct.do">新增產品</a></li>
                      <li><a href="#">產品列表</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> 會員管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="#">會員列表</a></li>
                      <li><a href="#">會員黑名單</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> 廠商管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="addVendor.do">新增廠商</a></li>
                      <li><a href="#">廠商列表</a></li>
                      <li><a href="#">廠商黑名單</a></li>
                    </ul>
                  </li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="設定">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="全螢幕">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="鎖定">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="登出" href="logoutAdmin.do">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="img/male.png" alt="">${ sessionScope.manager.account }
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:;"> 管理員資料</a></li>
                    <li><a href="logoutAdmin.do"><i class="fa fa-sign-out pull-right"></i> 登出</a></li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div id="manage-content" class="right_col" role="main" ng-controller="contentAdminController">
          
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Tendebank - Design by <a href="#">Edison</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>
    <!-- FastClick -->
    <script src="js/fastclick.js"></script>
    <!-- NProgress -->
    <script src="js/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="js/Chart.js"></script>
    <!-- gauge.js -->
    <script src="js/gauge.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="js/bootstrap-progressbar.js"></script>
    <!-- iCheck -->
    <script src="js/icheck.js"></script>
    <!-- Skycons -->
    <script src="js/skycons.js"></script>
    <!-- Flot -->
    <script src="js/jquery.flot.js"></script>
    <script src="js/jquery.flot.pie.js"></script>
    <script src="js/jquery.flot.time.js"></script>
    <script src="js/jquery.flot.stack.js"></script>
    <script src="js/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="js/jquery.flot.orderBars.js"></script>
    <script src="js/jquery.flot.spline.js"></script>
    <script src="js/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="js/date.js"></script>
    <!-- JQVMap -->
    <script src="js/jquery.vmap.js"></script>
    <script src="js/jquery.vmap.world.js"></script>
    <script src="js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="js/moment.js"></script>
    <script src="js/daterangepicker.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="js/custom.js"></script>
  </body>
</html>
