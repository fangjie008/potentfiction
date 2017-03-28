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
		<li><span data-money="1000" data-premium="50" data-type="1">10元(1000小说币)<br>
			<label>送50小说币</label></span></li>
		<li><span data-money="3000" data-premium="350" data-type="1">30元(3000小说币)<br>
			<label>送350小说币</label></span></li>
		<li><span data-money="5000" data-premium="600" data-type="1">50元(5000小说币)<br>
			<label>送600小说币</label></span></li>
		<li><span data-money="10000" data-premium="1500" data-type="1">100元(10000小说币)<br>
			<label>送1500小说币</label></span></li>
		
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
			<li style="color:red;font-size:20px;">首充优惠活动：
			</li>
			<li>用户第一次充值可获得小说币<span style="color:red;font-size:16px;">加倍</span>的奖励。即:充值10元即可获得2100小说币。以此类推。包年或半年也享受加倍奖励。即:包年可得两年会员。</li>
		</ul>
		<ul class="gray small">
			<li class="orange">温馨提示：
			</li>
			<li>包年只能直接购买，不可以用小说币兑换。</li>
			<li>包年时间是365天，半年是180天，重复购买时间会累加。</li>
			<li>充值阅读权限仅限本站使用
			  <!--  测试充值 -->
				<span class="testPay">&nbsp;&nbsp;<span data-money="1" data-premium="10" data-type="1">
				<label>&nbsp;</label></span>
				<span data-money="3" data-premium="10" data-type="1">
				<label>&nbsp;</label></span>
				</span> 
		   </li>
			<li>充值小说币为虚拟物品，不支持退款！</li>
		</ul>
	</div>

	 
<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
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
		
		
		$(".testPay ").click(function(){
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
