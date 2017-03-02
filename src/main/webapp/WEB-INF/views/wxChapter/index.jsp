<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<link href="<%=path %>/static/css/wxChapter/index.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/static/css/wxChapter/fenye.css" type="text/css" rel="stylesheet" />
<title>${wxBook.name}</title>
</head>
<body>
	<header class="nav wrap">
		<a class="ico52" href="<%=path%>/wxbook/detail?id=${bookId}"></a>
		<div class="header-center">${wxBook.name}</div>
		<a href="<%=path%>/" class="ico52 home"></a>
	</header>
	<div id="zjlb">
		<div class="fenye">
			<div class="fy">
				<div id="spagebg" style="height: 1135px; opacity: 0; display: none;"></div>
				<div class="spage">
					<a class="xbk this tb">1 - 20章</a>
				</div>
			</div>
			<div class="desc">
				<span><a href="#"
					class="dise r2">倒序</a></span>
			</div>
			<div class="cc">
			<ul class="menuList" style="display: none">
		<c:forEach items="${chapNaviDtos }" var="navi">
			<li><a class="chapter" href="<%=path%>${navi.url}">${navi.name}</a></li>
		</c:forEach>
	</ul>
			</div>
		</div>
	</div>
		<ul class="menu">
		<c:forEach items="${wxChapters}" var="chapters">
		<li>
		<a class="chapter" href="<%=path%>/wxChapterSub/index?bookId=${wxBook.id}&chapterId=${chapters.id}">
		 ${chapters.title}
		</a>
		<span
				class="fn-right c999">免费</span>
		</li>
		</c:forEach>
			
		</ul>

	<ul id="pager" class="pager">

		<li class="four"><a class="btn white start"
			href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=0">第一页</a></li>
		<li class="four"><c:if test="${pager.prePage>=0 }">
				<a class="btn white prev"
					href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.prePage }">上一页</a>
			</c:if> <c:if test="${pager.prePage<0 }">
				<a class="btn white prev" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><c:if test="${pager.nextPage>0 }">
				<a class="btn white next"
					href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.nextPage }">下一页</a>
			</c:if> <c:if test="${pager.nextPage<=0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><a class="btn white end"
			href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.lastPageNo }">最末页</a></li>
	</ul>
	<div style="background: none repeat scroll 0 0 #F9F8F8; padding-left: 10px; padding-bottom: 10px">
		跳转至 <input type="hidden" id="totalPage" value="${pager.totalPage }">
		<input type="hidden" id="pageSize" value="${pager.pageSize }">
		<input type="hidden" id="preUrl"
			value="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=">
		<input id="jump_page"
			style="width: 40px; border: none; border-bottom: 1px solid #ccc; text-align: center"
			type="text" name="p" value="${jumpPage }">页 <input
			id="jump_to" onclick="jumpPage()" class="btn white"
			style="height: 35px; line-height: 35px" type="button" name="jumpto"
			value="跳转">
	</div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript">
	function jumpPage() {
		var jumpPage = $("#jump_page").val();
		var totalPage = $("#totalPage").val();
		var pageSize = $("#pageSize").val();
		var preUrl = $("#preUrl").val();

		if (isNaN(jumpPage) || jumpPage <= 0) {
			alert("请输入大于0的数字");
			return;
		}
		if (jumpPage > totalPage) {
			alert("超出最大页数");
		} else {
			var pageNo = (jumpPage - 1) * pageSize;
			window.location.href = preUrl + pageNo + "&jumpPage=" + jumpPage;
		}
	}
</script>
</html>





