<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>充值</title>
</head>

<body>
<input type="hidden" id="bookid" name="bookid" value="${bookid }">
<input type="hidden" id="chapterid" name="chapterid" value="${chapterid }">
	<header class="nav wrap">
		<a class="ico52 back" href="javascript:history.go(-1);"></a>充值小说币<a
			href="<%=path%>/" class="ico52 home"></a>
	</header>
	<div class="mod_content panel c1  "
		style="padding-top: 8px; padding-bottom: 2px;">
		<ul class="gray">
			<li>充值账号：<label class="orange">${user.name }</label><br>
				用户余额：<label class="orange">${user.coin}</label>小说币
			</li>
		</ul>
	</div>
	<div class="mod_content gray c1 ">
		<h1 style="border: none">选择充值渠道</h1>
		<ul class="channellist channellistnew" style="width: 100%;">
			<a href="#">
				<li class="paychannel" style="width: 100%"><span
					class="current btn-paychannel" data-channel="13"> <img
						src="<%=path%>/static/image/user/weixin.png" alt=""
						class="charge_wx"> <img
						src="<%=path%>/static/image/user/orangeTriangle.png" alt=""
						class="charge_wayImg"> | 微信支付
				</span></li>
			</a>
		</ul>
	</div>
	<ul class="pay_money moneylist margin-top-10">
		<li><span data-money="3000" data-premium="100" data-type="1">30元(3000小说币)<br>
			<label>送100小说豆</label></span></li>
		<li><span data-money="5000" data-premium="500" data-type="1">50元(5000小说币)<br>
			<label>送500小说豆</label></span></li>
		<li><span data-money="10000" data-premium="1500" data-type="1">100元(10000小说币)<br>
			<label>送1500小说豆</label></span></li>
		<li><span data-money="20000" data-premium="3300" data-type="1">200元(20000小说币)<br>
			<label>送3300小说豆</label></span></li>
		
	</ul>
	<ul class="pay_money moneylist">
		<h1 style="padding-left: 10px; margin-top: 10px;">
			<font color="red">↓↓包年更划算，全站作品免费看🔥</font>
		</h1>
		<li><span class="" data-money="36500" data-premium="12"
			data-type="2">365元（包年）<br>
			<label>全站作品免费看</label></span></li>
		<li><span data-money="29800" data-premium="6" data-type="2">298元（半年）<br>
			<label>全站作品免费看</label></span></li>
	</ul>
	<div class="mod_content c1 gray">
		<ul class="gray small">
			<li class="orange">温馨提示：</li>
			<li>包年只能直接购买，不可以用小说币兑换。</li>
			<li>包年时间是365天，半年是180天，重复购买时间会累加。</li>
			<li>支付宝充值支持借记卡、信用卡、支付宝余额支付</li>
			<li>充值阅读权限仅限本站使用</li>
			<li>充值小说币为虚拟物品，不支持退款！</li>
			<li>若充值遇到问题，<a
				href="#">点此留言</a> 或查看 <a
				href="#">帮助中心</a></li>
		</ul>
	</div>
	<script>
		$(".pay_money li").click(function(){
			var bookid=$("#bookid").val();
			var chapterid=$("#chapterid").val();
			var span = $(this).find("span");
			var money = span.attr("data-money");
			var premium = span.attr("data-premium");
			var type = span.attr("data-type");
			
			var form = $("<form></form>",{ 
				'method':'post', 
				'action':'ipay_now', 
				'style':'display:none' 
				}).appendTo($("body")); 
			
			form.append($("<input>",{'type':'hidden','name':'money','value':money})); 
			form.append($("<input>",{'type':'hidden','name':'premium','value':premium})); 
			form.append($("<input>",{'type':'hidden','name':'type','value':type})); 
			form.append($("<input>",{'type':'hidden','name':'bookid','value':bookid})); 
			form.append($("<input>",{'type':'hidden','name':'chapterid','value':chapterid})); 
			form.submit();
		});
	</script>
</body>
</html>
