<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
   String path=request.getContextPath();
%>
<link href="<%=path %>/static/css/wxpay/index.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>

<header class="nav wrap">
	<a class="ico52 back" href="javascript:;"></a>充值记录<a href="/" class="ico52 home"></a>
</header>

</head>
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<body>
<div>
<table id="wxpaylist">
	<thead>
	  <tr>
		<th>充值金额</th>
		<th>充值类型</th>
		<th>小说币</th>
		<th>充值时间</th>
	   <tr>
	</thead>
	<tbody>
	 <c:forEach items="${wxpaylist}" var="wxpay">
	 	<tr>
	 	 <td >${wxpay.amount}</td>
	 	 
	 	 <td >${wxpay.payTypeName}</td>
	 	 <c:if test="${wxpay.payType==1 }">
	 	 <td >${wxpay.count}</td>
	 	 </c:if>
	 	 <c:if test="${wxpay.payType==2 }">
	 	  <td >${wxpay.count}${wxpay.unitName}</td>
	 	 </c:if>
	 	 <td >${wxpay.createTime}</td>
	 	</tr>
	 </c:forEach>
	</tbody>
</table>
</div>
<div>
<ul id="pager" class="pager">
	<li><a class="btn btn-large" href="<%=path %>/wxPay/index?userId=${userId }&pageNo=0&pageSize=${pager.pageSize }">第一页</a></li>
	<li>
		<c:if test="${pager.prePage>0 }">
		 <a class="btn btn-large" href="<%=path %>/wxPay/index?userId=${userId }&pageNo=${pager.prePage }&pageSize=${pager.pageSize }">上一页</a>
		</c:if>
		<c:if test="${pager.prePage<=0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一页</a>
		</c:if>
   </li>
	<li>
	<c:if test="${pager.nextPage>0 }">
		<a class="btn btn-large" href="<%=path %>/wxPay/index?userId=${userId }&pageNo=${pager.nextPage }&pageSize=${pager.pageSize }">下一页</a>
		</c:if>
		<c:if test="${pager.nextPage<=0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一页</a>
		</c:if>
	</li>
	<li><a class="btn btn-large" href="<%=path %>/wxPay/index?userId=${userId }&pageNo=${pager.totalPage }&pageSize=${pager.pageSize }">最末页</a></li>
</ul>
</div>
</body>
</html>