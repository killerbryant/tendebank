<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
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
	<!-- 彩色動態伸縮子選單 -->
	<link type="text/css" rel="stylesheet" href="css/color-menu.css">
	<link type="text/css" rel="stylesheet" href="css/tendebankStyle.css">
	<!-- NProgress -->
    <link href="css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="css/custom.css" rel="stylesheet">
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
    <!-- TDB JS -->
	<script src="js/tdb.manager.angular.js"></script>
	<script src="js/config.js"></script>
	<script type="text/javascript">
		$(function() {
			
		});
	</script>
</head>
<body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form id="manageLogin" action="loginAdmin.do" method="post">
              <h1>登入管理員帳戶</h1>
              <div>
                <input type="text" class="form-control" name="account" placeholder="請輸入管理員帳號" required="" />
              </div>
              <div>
                <input type="password" class="form-control" name="password" placeholder="請輸入管理員密碼" required="" />
              </div>
              <div>
                <a class="btn btn-default submit" href="javascript: void(0)" onclick="$('#manageLogin').submit();">登入</a>
                <a class="reset_pass" href="#">忘記密碼?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <!-- <p class="change_link">新增管理員?
                  <a href="#signup" class="to_register"> 建立管理員帳戶 </a>
                </p> -->

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-money"></i> Tendebank</h1>
                  <p>Copyright © 2016 Tendebank Inc. All rights reserved.</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>新建管理員</h1>
              <div>
                <input type="text" class="form-control" placeholder="請輸入帳戶" required="" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="請輸入Email" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="請輸入密碼" required="" />
              </div>
              <div>
                <a class="btn btn-default submit" href="index.html">註冊</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">已經是管理員?
                  <a href="#signin" class="to_register"> 登入 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-money"></i> Tendebank</h1>
                  <p>Copyright © 2016 Tendebank Inc. All rights reserved.</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
