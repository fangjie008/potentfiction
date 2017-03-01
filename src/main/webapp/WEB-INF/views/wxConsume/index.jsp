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
<% 
   String path=request.getContextPath();
%>
<link href="<%=path %>/static/css/wxpay/index.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>

<header class="nav wrap">
	<a class="ico52 back" href="javascript:;"></a>消费记录<a href="/" class="ico52 home"></a>
</header>
<title>消费记录</title>
</head>
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<body>
<div>
<table class="table table-striped" style="text-align: center">
	<thead >
	  <tr>
		<th class="table_th">小说</th>
		<th class="table_th">消费章节</th>
		<th class="table_th">小说币</th>
		<th class="table_th">消费时间</th>
	   <tr>
	</thead>
	<tbody>
	 <c:forEach items="${consumelist}" var="wxcons">
	 	<tr>
	 	 <td >${wxcons.getBookname()}</td>
	 	 <td >${wxcons.getCharptertitle()}</td>
	 	 <td >${wxcons.getCostcoin()}</td>
	 	 <td >${wxcons.getCreatetime()}</td>
	 	</tr>
	 </c:forEach>
	</tbody>
</table>
</div>
<nav style="text-align: center">
<ul class="pagination" >
	<li><a class="btn btn-large" href="<%=path %>/wxcons/index?userId=${userId }&pageNo=0&pageSize=${pager.pageSize }">第一页</a></li>
	<li>
		<c:if test="${pager.prePage>=0 }">
		 <a class="btn btn-large" href="<%=path %>/wxcons/index?userId=${userId }&pageNo=${pager.prePage }&pageSize=${pager.pageSize }">上一页</a>
		</c:if>
		<c:if test="${pager.prePage<0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一页</a>
		</c:if>
   </li>
	<li>
	<c:if test="${pager.nextPage>0 }">
		<a class="btn btn-large" href="<%=path %>/wxcons/index?userId=${userId }&pageNo=${pager.nextPage }&pageSize=${pager.pageSize }">下一页</a>
		</c:if>
		<c:if test="${pager.nextPage<=0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一页</a>
		</c:if>
	</li>
	<li><a class="btn btn-large" href="<%=path %>/wxcons/index?userId=${userId }&pageNo=${pager.lastPageNo }&pageSize=${pager.pageSize }">最末页</a></li>
</ul>
</nav>
</body>
</html>