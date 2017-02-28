<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="mobile-agent" content="format=html5; url=http://user.xsm.meixiangdao.com/">
<meta http-equiv="mobile-agent" content="format=xhtml; url=http://user.xsm.meixiangdao.com/">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<% String path=request.getContextPath(); %>
<link href="<%=path %>/static/css/wxChapterSub/index.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<title>${bookName}</title>
</head>
<body>
	<header>
		<div>${wxChapterSub.title}</div>
	</header>
	<section>
	 	<div class="content">
	 	${wxChapterSub.content}
	 	</div>
	</section>
	<footer>
	<nav style="text-align: center">
<ul class="pagination" >
	
	<li>
		<c:if test="${wxChapterSub.preId>0 }">
		 <a class="btn btn-large" href="<%=path %>/wxChapterSub/index?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.preId}&bookName=${bookName}">上一章</a>
		</c:if>
		<c:if test="${wxChapterSub.preId<=0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一章</a>
		</c:if>
   </li>
   <li><a class="btn btn-large" href="<%=path %>/wxChapter/index?bookId=${wxChapterSub.bookId}&pageNo=0">目录</a></li>
	<li>
	<c:if test="${wxChapterSub.nextId>0 }">
		<a class="btn btn-large" href="<%=path %>/wxChapterSub/index?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.nextId}&bookName=${bookName}">下一章</a>
		</c:if>
		<c:if test="${wxChapterSub.nextId<=0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >下一章</a>
		</c:if>
	</li>
	
</ul>
</nav>
	</footer>
</body>
</html>