<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>订阅章节</title>
</head>

<body>
<header class="nav wrap">
   <a class="ico52 back" href="javascript:history.go(-1);"></a>
   <a href="#" style="color:#fff;">章节订阅</a>
   <a href="<%=path%>/" class="ico52 home"></a>
</header>
<div class="mod_content pb">
	<ul>
            <li class="gray">章节：第1282章 医生何求：误会</li>
            <li class="gray">字数：2030</li>
            <li class="gray">价格： <label class="orange">10</label> 小说币</li>
            <li class="gray">余额：  0小说币/0小说豆</li>
            <li class="gray">账户：嘿吥六球</li>
            <li class="gray"><input name="noshow" type="checkbox" value="" checked="checked" disabled="true">自动购买，下一章不再提醒。</li>
            <li><a class="btn block" href="/pay.html?ref=/book/14438/1647067"><font color="white">余额不足？其他充值»</font></a></li>
                                
                
	</ul>
</div>
<div class="mod_content c1">
	<ul class="gray small">
		<li class="orange">温馨提示：</li>
		<li>为了给您更好的阅读体验，如果您选择订阅，阅读本作品其他VIP章节将直接扣费，并不再提示该页面。</li>
	</ul>
</div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>
