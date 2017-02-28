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

<% String path=request.getContextPath(); %>
<link href="<%=path %>/static/css/wxChapter/index.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<title> 
${wxBook.name}
</title>
</head>
<body>
<section>	
	<div>
		<ul>
		<c:forEach items="${chapNaviDtos }" var="navi">
			<li>
				<a href="<%=path%>${navi.url}" >${navi.name}</a>
			</li>
		</c:forEach>
		</ul>
	</div>
</section>
<section>
	<div class="menu">
		<ul>
		<c:forEach items="${wxChapters}" var="chapters">
		<li>
		<a href="<%=path%>/wxChapterSub/index?bookId=${wxBook.id}&chapterId=${chapters.id}&bookName=${wxBook.name}">
		 ${chapters.title}
		</a>
		</li>
		</c:forEach>
			
		</ul>
	</div>
</section>
<nav style="text-align: center">
<ul class="pagination" >
	<li><a class="btn btn-large" href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=0">第一页</a></li>
	<li>
		<c:if test="${pager.prePage>=0 }">
		 <a class="btn btn-large" href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.prePage }">上一页</a>
		</c:if>
		<c:if test="${pager.prePage<0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一页</a>
		</c:if>
   </li>
	<li>
	<c:if test="${pager.nextPage>0 }">
		<a class="btn btn-large" href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.nextPage }">下一页</a>
		</c:if>
		<c:if test="${pager.nextPage<=0 }">
		 <a class="btn btn-large" href="#"  disabled="disabled" >上一页</a>
		</c:if>
	</li>
	<li><a class="btn btn-large" href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.lastPageNo }">最末页</a></li>
</ul>
</nav>
<div>跳转至
 <input type="hidden" id="totalPage" value="${pager.totalPage }">
 <input type="hidden" id="pageSize" value="${pager.pageSize }">
 <input type="hidden" id="preUrl" value="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=">
 <input id="jump_page" name="p" value="${jumpPage }">页 
 <a class="btn btn-large" href="#" onclick="jumpPage()">跳转</a>
 </div>
</body>
</html>
<script type="text/javascript">
	function jumpPage(){
		var jumpPage=$("#jump_page").val();
		var totalPage=$("#totalPage").val();
		var pageSize=$("#pageSize").val();
		var preUrl=$("#preUrl").val();
		
		if(isNaN(jumpPage)||jumpPage<=0){
			alert("请输入大于0的数字");
			return;
			}
		if(jumpPage>totalPage){
			alert("超出最大页数");
		}
		else{
			var pageNo=(jumpPage-1)*pageSize;
			window.location.href=preUrl+pageNo+"&jumpPage="+jumpPage;
		}
	}
</script>