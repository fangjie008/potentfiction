<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>书架</title>
</head>

<body>
<header class="nav wrap">
	<a class="ico52 back" href="javascript:history.go(-1);"></a>我的书架<a href="<%=path%>/" class="ico52 home"></a>
</header>
<div class="panel-bar">
<ul class="btn_area fn-clear">
		<li><a class="btn  block " href="#">最近阅读</a></li>
		<li><a class="btn white block" href="#">收藏书架</a></li>
	</ul>
</div>
<div class="mod_tab_content shelf">
	<ul class="current">
	
	<c:forEach items="${bookracks}" var="racks">
			  <li id="${racks.getBookid() }">
		        <a href="<%=path%>/wxbook/detail?id=${racks.getBookid()}">
		      	<img  class="fn-left lazy" alt="" src="${racks.getCoverimgs()}">
		      	</a>
		      	<a  class="jxread" href="<%=path%>/wxbook/detail?id=${racks.getBookid()}">
		      	  <div>
		      	    <p class="nowrap">${racks.getBookname()}</p>
                    <p class="small nowrap">最后更新：<label>第1305章 医生何求</label></p>
                    <p class="small nowrap">阅读进度：<label class="ccode">3/1073 章节<br>继续阅读</label></p>
		      	 </div>
		      	</a>
		      	<span class="del" data-bid="${racks.getBookid() }"><span class="verticalAlign"></span><label>删除</label></span>
		      </li>
		</c:forEach>
        	
        	</ul>
</div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>

