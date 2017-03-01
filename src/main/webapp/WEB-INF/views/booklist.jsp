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
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=path %>/static/css/booklist.css" type="text/css" rel="stylesheet" />
    
<title>小说站</title>
<base href="<%=basePath%>">
 </head>
  
  <body>
    <header>
    </header>
    <section>
	    <div>
	     <ul>
	     <c:forEach items="${wxBooks}" var="books">
	      <li>
	        <a href="<%=path%>/wxbook/detail?id=${books.id}">
	      	<img alt="" src="${books.coverImgs}">
	      	 <span>${books.name}</span>
	      	
	      	</a>
	      </li>
	     </c:forEach>
	     </ul>
	    </div>
    </section>
  </body>
</html>
