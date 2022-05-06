<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天地銀行香鋪</title>
<link rel="Shortcut Icon" type="image/x-icon" href="img/icon/tdb.ico" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		/*$('#inputMember').click(function(){
			$.ajax({
	            type: "POST",
	            url: "loginCheck",
	            data: $("#formMember").serialize(),
	            success: function(data) {
	            	console.log(data);
	            },
	            complete: function() {
	                console.log("${message}" + " YA")
	            }
	        });
		});*/

		// 登入清除資訊button
		$('#clearData').click(function(){
			$('#account').val("");
			$('#password').val("");
			$('#errorMsg').text("");
		});

		$('#resetBtn').click(function(){
				$.ajax({
		            type: "POST",
		            url: "resetMemberPassword.do",
		            data: $("#resetPwdMember").serialize(),
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
			});
		
		// div取代iframe
		$("[class^='sub-memu-'] li").click(function() {
			// 找出 li 中的超連結 href(#id)
			var sessionData = "${ sessionScope.member.account }";
			var _$this = $(this);
			var _clickTab = _$this.find('a').attr('href'); // 找到連結a中的target標籤值
			if("0" == _clickTab.search("mailto:")) { // 電子郵件的url
				_$this.find('a').click();
			} else if(_clickTab == "#") { // 未開放
				alert("此功能尚未開放！");
			} else if ("-1" == _clickTab.search("mailto:")) { // 已開放功能
				$.ajaxSetup({
					async : false
				});	      
				$.post(_clickTab, function(data) {
					// 
					if($.trim(data).length == 0){
						alert("請先登入會員！");
						window.location.href = "index.jsp";
					} else if(sessionData != null && sessionData != ""){
						$("#content").html(data);
						return;
					}else{
						var result = data.result;
						if(result == "limit") {
							alert("請先登入會員！");
						} else {
							$("#content").html(data);
						}
					}
				});
				return false;
			}
		});

		

		// 先取得相關選單元素及高度
		var $menuWrapper = $('#menu-wrapper'), $subMenuWwrapper = $menuWrapper
				.find('.sub-menu-wrapper').add($menuWrapper.find('.sub-menu')), _height = $subMenuWwrapper
				.height(), _animateSpeed = 200;

		// 先把 $subMenuWwrapper 的高度歸 0
		// 並把 .sub-menu ul 先往上移動隱藏
		var $subMenu = $subMenuWwrapper.height(0).find('.sub-menu ul').css({
			top : _height * -1
		});

		// 當滑鼠移入到 .main-menu ul li a 上時
		$('.main-menu ul li a').mouseover(
				function() {
					// 先取出被滑鼠移入的選單
					// 並取得該選單中第一個 span 的文字顏色
					var $this = $(this), $color = $this.find('span').css(
							'color'), _no = $this.parent().index();

					// 改變 $subMenuWwrapper 的顏色為 $color 並展開高度
					$subMenuWwrapper.css({
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
					$this.addClass('selected').parent().siblings().find(
							'a.selected').removeClass('selected');

					return false;
				});

		// 當滑鼠移出 $menuWrapper 後把 $subMenuWwrapper 的高度歸 0
		$menuWrapper.mouseleave(function() {
			$subMenuWwrapper.stop().animate({
				height : 0
			}, _animateSpeed);
		});
	});
</script>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

body {
	background-color: #fbde86;
}

div {
	/*border-width: 1px;
	border-style: solid;*/
	background-color: white;
}

#wrapper {
	width: 984px;
	margin: 0 auto;
}

#header {
	height: 160px;
	padding-top: 30px;
	background-color: #cd853f;
}

#mainNav {
	width: 220px;
	float: left;
}

#content {
	width: 760px;
	float: right;
}

#mainNav,#content {
	height:750px;
}

input{
	margin:5px;
}

#footer {
	clear: both;
	height: 80px;
	line-height: 80px;
	text-align: center;
	background-color: #cd853f;
}

/*.list-side {
	list-style: none;
	text-align: center;
	padding:5px;
}*/

p{
	color:#666;
	/*background-color:#FF9;
	border-top:#F60 8px solid;
	border-bottom:#39F 2px dashed;*/
	padding:10px 35px;
	line-height:20px;
	text-indent:2em;
}


img{
	display:block;
	margin:auto;
	padding:5px;
}

#member{
	padding:5px;
	margin:5px;
}

h3{
	background: url(img/right_h3.png) no-repeat left top;
  	height: 30px;
  	color: #555;
  	font-size: 100%;
  	overflow: hidden;
  	display: block;
  	font-weight: bold;
}

/* Menu */
#menu-wrapper .main-menu,#menu-wrapper .sub-menu {
	margin: 0 auto;
	width: 980px;
}

#menu-wrapper ul,#menu-wrapper ul li {
	margin: 0;
	padding: 0;
	list-style: none;
}

.main-menu ul li,.sub-menu ul li {
	float: left;
}

.main-menu ul li a {
	color: #000;
	display: block;
	margin-right: 50px;
	padding: 5px 5px 5px 0;
	text-decoration: none;
}

.sub-menu-wrapper {
	height: 28px;
	clear: left;
	background-color: #f90;
	border-top: 5px solid #f90; /* 預設使用跟 .c1 一樣的顏色 */
}

.sub-menu {
	height: 28px;
	position: relative;
	overflow: hidden;
}

.sub-menu ul {
	position: absolute;
}

.sub-menu ul li a {
	color: #fff;
	display: block;
	margin-right: 20px;
	padding-top: 2px;
	text-decoration: none;
}
/* 自訂子選單的位置 */
.sub-menu .sub-memu-1 {
	left: 50px;
}
.sub-menu .sub-memu-2 {
	left: 170px;
}
.sub-menu .sub-memu-3 {
	left: 265px;
}
.sub-menu .sub-memu-4 {
	left: 300px;
}
.sub-menu .sub-memu-5 {
	left: 365px;
}
/* 自訂每一個選單的顏色 */
.main-menu ul li.m1 span,.main-menu ul li.m1 a.selected {
	color: #f90;
}
.main-menu ul li.m2 span,.main-menu ul li.m2 a.selected {
	color: #09c;
}
.main-menu ul li.m3 span,.main-menu ul li.m3 a.selected {
	color: #3c0;
}
.main-menu ul li.m4 span,.main-menu ul li.m4 a.selected {
	color: #f6f;
}
.main-menu ul li.m5 span,.main-menu ul li.m5 a.selected {
	color: #b22222;
}
.main-menu ul li.m6 span,.main-menu ul li.m6 a.selected {
	color: #483d8b;
}

/*ul li{  
	list-style-type:none;  
}*/

</style>
</head>
<body>
	<!---->
	<div id="wrapper">
		<div class="top" id="header">
			<a href="login.do"><img alt="天地銀行香鋪" src="img/tdb_logo.png"
				style="margin-top: 5px; margin-left: 5px;" /></a>
			<div id="menu-wrapper">
				<div class="main-menu-wrapper">
					<div class="main-menu">
						<ul>
							<li class="m1"><a href="#"><span>關</span>於天地</a></li>
							<li class="m2"><a href="#"><span>商</span>品專區</a></li>
							<li class="m3"><a href="#"><span>購</span>物車</a></li>
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
							<li><a href="#">金紙</a></li>
							<li><a href="#">香品</a></li>
							<li><a href="#">蠟燭</a></li>
							<li><a href="#">油品</a></li>
							<li><a href="#">蓮花紙</a></li>
							<li><a href="#">紙紮</a></li>
							<li><a href="#">銅器</a></li>
							<li><a href="#">金爐</a></li>
							<li><a href="#">小紙</a></li>
						</ul>
						<ul class="sub-memu-2">
							<li><a href="goShoppingCartOrder.do">購物車查詢</a></li>
							<li><a href="#">結帳</a></li>
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
		<div class="side" id="mainNav">
			<div id="member">
				<c:choose>
					<c:when test="${ not empty sessionScope.member.lastname and not empty sessionScope.member.firstname }">
					    ${ sessionScope.member.lastname }${ sessionScope.member.firstname } 你好！
					    <a href="logout">登出</a>
					</c:when>
					<c:when test="${ not empty sessionScope.member.account }">
					    ${ sessionScope.member.account } 你好！
					    <a href="logout">登出</a>
					</c:when>
					<c:otherwise>
					    <form id="loginMember" action="login.do" method="post">
							帳號:<input type="text" id="account" name="account" placeholder="請輸入帳號" size="15" />
							<font id="errorMsg" color="red" size="1">
								
							</font>
							<br>
							密碼:<input type="password" id="password" name="password" placeholder="請輸入密碼" size="15" />
							<br>
							<input type="checkbox" id="rememberMe" name="rememberMe" value="true" />記住我
							<br>
							<!-- <input id="inputMember" type="button" value="登入" /> -->
							<input type="submit" value="登入" />
							<input id="clearData" type="button" value="清除" />
							<!-- <s:reset value="清除"/> -->
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="main" id="content">
			重新設定帳號密碼
			<form id="resetPwdMember"  action="" method="post">
				帳號:<input type="text" id="memberAccount" name="account" placeholder="請輸入帳號" size="30" readonly value="${ account }" />
				<br>
				密碼:<input type="password" id="memberPassword" name="password" placeholder="請輸入密碼" size="20" />
				<br>
				在一次輸入密碼:<input type="password" id="originalPassword" name="originalPassword" placeholder="請輸入密碼" size="20" />
				<br>
				<input type="hidden" id="email" name="email"  readonly value="${ email }"/>
				<input type="hidden" id="resetPassword" name="resetPassword" readonly value="${ resetPwd }" />
				<input id="resetBtn" type="button" value="更改密碼" />
			</form>
		</div>
		<div class="bottom" id="footer">天地銀行香鋪 版權所有 (02)2975-4793~4 三重埔河邊北街68巷5號 CopyRight 2015</div>
	</div>
</body>
</html>
