<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>

<title>首页</title>
</head>

<body>
<header class="wrap"> <a id="logo" href="<%=path%>/" class="logo" title=""></a>
	<ul>
		<!-- <li><a href="/book/search.html">搜索</a></li>
		<li><a href="/top/tuijian.html">排行</a></li>
		<li><a href="/all.html">书库</a></li> -->
	</ul>
</header>
<dl class="user_area wrap">
	<dt><span class="ico32 user"></span>
	<c:if test="${pageUser!=null&&pageUser.id>0}">
	<a href="<%=path%>/wxUser/content">
		<label>${pageUser.name}&nbsp;&nbsp;</label>
		</a>
	</c:if>
	<c:if test="${pageUser==null||pageUser.id<=0}">
	     <a href="<%=path%>/wxUser/login">
		<label>登录</label>
		</a>
	</c:if>
	</dt>
	<dd><a href="<%=path%>/wxBookrack/list""><span class="ico32 bookcase"></span>书架</a></dd>
	<dd class="sp"></dd>
	<dd><a href="<%=path%>/wxPay/pay"><span class="ico32 pay"></span>充值</a></dd>
</dl>
<div class="mod_title mod_lastread">
	<h1>
		<ul>
			<li>	
		<c:if test="${bookrack.bookid>0&&bookrack.chapterid>0}">
		<label>继续阅读:</label>
		<a  href="<%=path %>/wxChapterSub/index?bookId=${bookrack.bookid}&chapterId=${bookrack.chapterid}">
		《${bookrack.bookname}》 ${bookrack.chaptertitle}</a>
		</c:if>
		<c:if test="${bookrack.bookid>0&&(bookrack.chapterid==null||bookrack.chapterid<=0)}">
		<label>继续阅读:</label>
		<a  href="<%=path %>/wxChapterSub/defualt?bookId=${bookrack.bookid}">
		《${bookrack.bookname}》</a>
		</c:if>
		<c:if test="${bookrack==null||bookrack.bookid<=0}">
		</c:if>
		</ul> 
	</h1>
</div>
<div class="mod_block"></div>
<div class="mod_title c0">
	<h1 class="f17"><i class="home-icon-tit home-icon-tit-b"></i> 本站必读</h1>
</div>
<div class="mod_content">
	<ul>
		<c:forEach items="${wxBooks}" var="books">
	      <li>
	        
	      	<a onclick="addbookrack('${books.id}','0')" href="<%=path%>/wxbook/detail?id=${books.id}"> 
	      	<img class="fn-left lazy" src="${books.coverImgs}" dataimg="${books.coverImgs}" alt="${books.name}">
			<div>
				<p>${books.name}</p>
				<p class="intro">${books.getIntr()}</p>
			</div>
			</a> 
	      </li>
	     </c:forEach>
	</ul>
</div>
<div class="mod_block"></div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
</html>

