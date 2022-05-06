<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Chomo" />
<link rel="start" href="http://www.14px.com" title="Home" />
<title>div仿框架布局 - iframe无法适应高度的bug也一起被修复了</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

html {
	height: 100%;
	overflow: hidden;
	background: #fff;
}

body {
	height: 100%;
	overflow: hidden;
	background: #fff;
}

div {
	background: #f60;
	line-height: 1.6;
}

.top {
	position: absolute;
	left: 10px;
	top: 10px;
	right: 10px;
	height: 50px;
}

.side {
	position: absolute;
	left: 10px;
	top: 70px;
	bottom: 70px;
	width: 200px;
}

.main {
	position: absolute;
	left: 220px;
	top: 70px;
	bottom: 70px;
	right: 10px;
}

.bottom {
	position: absolute;
	left: 10px;
	bottom: 10px;
	right: 10px;
	height: 50px;
}

.main iframe {
	width: 100%;
	height: 100%;
}
/*---ie6---*/
html {
	*padding: 70px 10px;
}

.top {
	*height: 50px;
	*margin-top: -60px;
	*margin-bottom: 10px;
	*position: relative;
	*top: 0;
	*right: 0;
	*bottom: 0;
	*left: 0;
}

.side {
	*height: 100%;
	*float: left;
	*width: 200px;
	*position: relative;
	*top: 0;
	*right: 0;
	*bottom: 0;
	*left: 0;
}

.main {
	*height: 100%;
	*margin-left: 210px;
	_margin-left: 207px;
	*position: relative;
	*top: 0;
	*right: 0;
	*bottom: 0;
	*left: 0;
}

.bottom {
	*height: 50px;
	*margin-top: 10px;
	*position: relative;
	*top: 0;
	*right: 0;
	*bottom: 0;
	*left: 0;
}
</style>
<script language="JavaScript" type="text/javascript">

</script>
</head>
<body>
	<div class="top">看，亲爱的，iframe无法适应高度的bug也一起被修复了。不过这个修复也可以想想其他的办法：）办法就在本文中，有兴趣的朋友可以自己摸索。</div>
	<div class="side">
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br /> <br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
	</div>
	<div class="main">
		<iframe frameborder="0" src="http://www.tendebank.com.tw/"></iframe>
	</div>
	<div class="bottom"></div>
</body>
</html>
