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
<link href="<%=path %>/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<link href="<%=path %>/static/css/wxBookrack/index.css" type="text/css" rel="stylesheet" />
<title>书架</title>
</head>
<body>
<div>
收藏书架
</div>
<div>
	<ul>
		<c:forEach items="${bookracks}" var="racks">
			 <li>
		        <a href="<%=path%>/wxbook/detail?id=${racks.getBookid()}">
		      	<img alt="" src="${racks.getCoverimgs()}">
		      	 <span>${racks.getBookname()}</span>
		      	
		      	</a>
		      </li>
		</c:forEach>
	</ul>
</div>

</body>
</html>