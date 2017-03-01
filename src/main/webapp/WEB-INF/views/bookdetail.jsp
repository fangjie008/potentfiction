<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="mobile-agent" content="format=html5; url=http://user.xsm.meixiangdao.com/">
<meta http-equiv="mobile-agent" content="format=xhtml; url=http://user.xsm.meixiangdao.com/">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<%
String path = request.getContextPath();
%>
<link href="<%=path %>/static/css/bookdetail.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<title>	${wxBook.getName()}</title>
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
	<input type="hidden" id="bookid" name="bookid" value="${wxBook.getId()}">
	<input type="hidden" id="bookname" name="bookname" value="${wxBook.getName()}">
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
			<a href="<%=path %>/wxChapterSub/defualt?bookId=${wxBook.getId()}" class="btn-action1">免费阅读</a>&nbsp;
			<a href="#" id="btn-addbookrack" class="btn-action2">加入书架</a>
		</div>

		<div class="box-action2">
			<a href="<%=path %>/wxChapter/index?bookId=${wxBook.getId()}">全部章节目录</a>
		</div>
	</section>


	<section>
	</section>


	<footer>
		
	</footer>
</body>
</html>
<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/bookdetail.js"></script>

<script type="text/javascript">
	$("#btn-addbookrack").click(function(){
		var text=$("#btn-addbookrack").html();
		if(text=="已添加"){
			return;
		}
		var bookid=$("#bookid").val();
		var bookname=$("#bookname").val();
		var userid=getCookie("wx_userid");
		if(userid==undefined||userid==""){
			userid=3;
		}
		var postData={'bookId':bookid,'bookName':bookname,'userId':userid}
		$.ajax({
			url:"<%=path%>/wxBookrack/addBookrack",
			data:postData,
			type:"post",
			dataType:"json",
			success:function(res){
				$("#btn-addbookrack").prop("disabled","disabled");
				$("#btn-addbookrack").html("已添加");
				console.log(res.msg);
			},
			error:function(res){
				//alert("失败");
			}
		});
	});
</script>