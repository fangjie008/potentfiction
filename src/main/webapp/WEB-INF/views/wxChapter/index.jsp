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
<link href="<%=path %>/static/css/wxChapter/index.css" type="text/css" rel="stylesheet" />
<title> 
${wxBook.name}
</title>
</head>
<body>
<section>	
	<div>
		<ul>
		<c:forEach items="${chapNaviDtos }" var="navi">
			<li>
				<a href="<%=path%>${navi.url}" >${navi.name}</a>
			</li>
		</c:forEach>
		</ul>
	</div>
</section>
<section>
	<div class="menu">
		<ul>
		<c:forEach items="${wxChapters}" var="chapters">
		<li>
		<a href="<%=path%>/wxChapterSub/index?bookId=${wxBook.id}&chapterId=${chapters.id}&bookName=${wxBook.name}">
		 ${chapters.title}
		</a>
		</li>
		</c:forEach>
			
		</ul>
	</div>
</section>
</body>
</html>