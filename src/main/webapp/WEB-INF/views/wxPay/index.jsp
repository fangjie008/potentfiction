<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>充值记录</title>
</head>
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<body>
<header class="nav wrap">
	<a class="ico52 back" href="javascript:history.go(-1);"></a>充值记录<a href="<%=path%>/" class="ico52 home"></a>
</header>
<div class="mod_tab_content shelf">
	<ul class="current my_orderlist" id="pay">
        <li><span>金额</span><span>充值类型</span><span>小说币</span><span>时间</span></li>
        <c:forEach items="${wxpaylist}" var="wxpay">
	 	<li>
	 	 <span>${wxpay.amount}</span>
	 	 
	 	 <span>${wxpay.payTypeName}</span>
	 	 <c:if test="${wxpay.payType==1 }">
	 	 <span>${wxpay.count}</span>
	 	 </c:if>
	 	 <c:if test="${wxpay.payType==2 }">
	 	  <span>${wxpay.count}${wxpay.unitName}</span>
	 	 </c:if>
	 	 <span>${wxpay.createTime}</span>
	 	</li>
	 </c:forEach>
    </ul>
</div>
<ul id="pager" class="pager">
      <li class="four"><a class="btn white start"
			href="<%=path %>/wxPay/index?userId=${userId }&pageNo=0&pageSize=${pager.pageSize }">第一页</a></li>
		<li class="four"><c:if test="${pager.prePage>=0 }">
				<a class="btn white prev" href="<%=path %>/wxPay/index?userId=${userId }&pageNo=${pager.prePage }&pageSize=${pager.pageSize }">上一页</a>
			</c:if> <c:if test="${pager.prePage<0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><c:if test="${pager.nextPage>0 }">
				<a class="btn white prev"
					href="<%=path %>/wxPay/index?userId=${userId }&pageNo=${pager.nextPage }&pageSize=${pager.pageSize }">下一页</a>
			</c:if> <c:if test="${pager.nextPage<=0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><a class="btn white end"
			href="<%=path %>/wxPay/index?userId=${userId }&pageNo=${pager.lastPageNo }&pageSize=${pager.pageSize }">最末页</a></li>
</ul>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>