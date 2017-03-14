<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String pathFooter = request.getContextPath();
String basePathFooter = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathFooter+"/";
%>
<footer>
	<p style="line-height: 20px"> 
	<a href="<%=pathFooter%>/">首页</a><span class="sp"></span>
	<a href="<%=pathFooter%>/wxBookrack/list?userId=3" title="书架">书架</a>
	<span class="sp"></span><a href="#">客服</a>
	<span class="sp"></span><a href="#">帮助</a> 
	</p>
	<p style="line-height: 22px;"> 
	<span class="small">©2016 五彩小说城</span> <span class="small">
	京ICP备16021213号-1</span> </p>
</footer>