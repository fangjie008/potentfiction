<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8"/>
<meta name='apple-mobile-web-app-capable' content='yes' />
<meta name='apple-mobile-web-app-status-bar-style' content='default' />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>Insert title here</title>
<style>
*{margin:0;pading:0}
body{
	background-color: #f9f8f8;
}
a{
	text-decoration: none;
}
.box-header{
	margin-bottom:15px; 
}

.box-header-title{
	height: 44px;
	background: #32c9ba;
	position: relative;
	top: 0;
	left: 0;
	line-height: 44px;
	width: 100%;
	text-align: center;
	color: white;
	font-size: 15px;
}

.box-header a{
	display: block;
	position: absolute;	
	top: 9px;
	background-image: url("http://cdn.xsm.meixiangdao.com/mobile/xsm/img/ico52.png");
	background-repeat: no-repeat;
	background-size: 26px auto;
	width: 26px;
	height: 26px;
	vertical-align: text-bottom;
}
.btn-headback{
	left: 11px;
}

.btn-headindex{
	background-position:  0 -26px;
	right: 11px;
}

.box-header-subtitle{
	height: 40px;
	border-bottom: 1px #e9eaeb solid;
	background-color: #FFF;
	line-height: 40px;
	padding-left: 10px;
}

.box-bookinfo{
	position: relative;
	padding: 0 10px;
}

.box-bookinfo img{
	width:90px;
	height:120px;
	border: 1px solid #eee;
	margin-bottom: 15px;
}

.box-bookinfo-text{
	position: absolute;
	top: 0px;
	left: 115px;
	right: 0;
	line-height: 25px;
	font-size: 15px;
}
.box-bookinfo-text dt{
	float: left;
	color: #b7b7b7;
}
.box-bookinfo-text dd{
	
}

.box-bookinfo-text dd a{
	color:#2897ed;
}

.box-action1{
	text-align: center;
	padding: 0 10px;
	margin-bottom: 15px;
}

.box-action1 a{
	display: inline-block;
	line-height: 40px;
	width: 48%;
	text-align: center;
	border-radius: 6px;
}

.box-action1 a.btn-action1{
	color: white;
	background: #2bb8a5;
}

.box-action1 a.btn-action2{
	border:1px solid #d4d4d4;
	background: white;  
	color: #313030;
}

.box-action1 a.btn-action3{
	border:1px solid #d4d4d4;
	background: #efefef;  
	color: #7d7d87;
}

.box-action2 a{
	display: block;
	line-height: 40px;
	text-align: center;
	border-radius: 6px;
	border:1px solid #d4d4d4;
	background: white;  
}

.box-action2{

	padding: 0 10px;
}

</style>
</head>
<body>
	<header class="box-header">
		<div class="box-header-title">
			<a href="#" class="btn-headback"></a>
			书籍信息
			<a href="#" class="btn-headindex"></a>
		</div>
		<div class="box-header-subtitle">
			${wxBook.getName()}
		</div>
	</header>
	
	
	
	<section>
		<div class="box-bookinfo">
			<img src="http://fm.xsm.meixiangdao.com/cover/77/14077-large.jpg"/>
			<dl class="box-bookinfo-text">
				<dt>专题：</dt>
				<dd><a href="#">言情小说迷</a></dd>

				<dt>类别：</dt>
				<dd><a href="#">现代言情</a>&nbsp;<a href="#">总裁豪门</a></dd>

				<dt>状态：</dt>
				<dd>已完结</dd>

				<dt>字数：</dt>
				<dd>${wxBook.getContentLen()}</dd>

				<dt>评论：</dt>
				<dd><span style="color:#ff7800">50</span>&nbsp;<a href="#">看评论 »</a></dd>
			</dl>

		</div>
		<div class="box-action1">
			<a href="#" class="btn-action1">免费阅读</a>&nbsp;
			<a href="#" class="btn-action2">加入书架</a>
		</div>

		<div class="box-action2">
			<a href="#">全部章节目录</a>
		</div>
	</section>


	<section>
	</section>


	<footer>
		
	</footer>
</body>
</html>