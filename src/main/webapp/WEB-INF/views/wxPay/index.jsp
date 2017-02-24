<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="../static/css/wxpay/index.css" type="text/css" rel="stylesheet" />
<header class="nav wrap">
	<a class="ico52 back" href="javascript:;"></a>充值记录<a href="/" class="ico52 home"></a>
</header>

</head>
<script type="text/javascript" src="../static/js/jquery/jquery-1.10.2.min.js"></script>
<body>
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
</body>
</html>