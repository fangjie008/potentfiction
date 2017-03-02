<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>消费记录</title>
</head>
<body>
	<header class="nav wrap">
		<a class="ico52 back" href="javascript:history.go(-1);"></a>消费记录<a href="<%=path%>/" class="ico52 home"></a>
	</header>
	<div class="mod_tab_cost">
		<!-- <ul>
			<li><a href="/member/trade/cost/0.html"><span class="prem">今天之前</span></a></li>
			<li>今天</li>
			<li></li>
		</ul> -->
	</div>
	<div class="mod_tab_content shelf">
		<ul class="current my_orderlist" id="cost">
			<li><span style="width: 16%;">小说币</span><span
				style="width: 55%;">消费内容</span><span style="width: 28%;">时间</span></li>
			<c:forEach items="${consumelist}" var="wxcons">
				<li>
				<span style="width: 16%;">${wxcons.getCostcoin()}</span> 
				<span style="width: 55%;">${wxcons.getCharptertitle()}</span>
					<span style="width: 28%;">${wxcons.getCreatetime()}</span><li>
			</c:forEach>
		</ul>
	</div>
	<ul id="pager" class="pager">
		<li class="four"><a class="btn white start"
			href="<%=path %>/wxConsume/index?userId=${userId }&pageNo=0&pageSize=${pager.pageSize }">第一页</a></li>
		<li class="four"><c:if test="${pager.prePage>=0 }">
				<a class="btn white prev" href="<%=path %>/wxConsume/index?userId=${userId }&pageNo=${pager.prePage }&pageSize=${pager.pageSize }">上一页</a>
			</c:if> <c:if test="${pager.prePage<0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><c:if test="${pager.nextPage>0 }">
				<a class="btn white prev"
					href="<%=path %>/wxConsume/index?userId=${userId }&pageNo=${pager.nextPage }&pageSize=${pager.pageSize }">下一页</a>
			</c:if> <c:if test="${pager.nextPage<=0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><a class="btn white end"
			href="<%=path %>/wxConsume/index?userId=${userId }&pageNo=${pager.lastPageNo }&pageSize=${pager.pageSize }">最末页</a></li>
	</ul>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>

