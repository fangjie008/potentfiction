package com.tiexue.potentfiction.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.potentfiction.dto.PageUserDto;
import com.tiexue.potentfiction.dto.Pager;
import com.tiexue.potentfiction.dto.WxPayDto;
import com.tiexue.potentfiction.dto.WxUserDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxConstants;
import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.service.IWxPayService;
import com.tiexue.potentfiction.service.IWxUserService;
import com.tiexue.potentfiction.util.DateUtil;

import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.PayUtil;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.StreamUtils;
import weixin.popular.util.XMLConverUtil;

@Controller
@RequestMapping("/wxPay")
public class WxPayController {
	// 打印日志
	private Logger logger = Logger.getLogger(WxPayController.class);
	@Resource
	IWxPayService wxPayService;
	@Resource
	IWxUserService userSer;

	// 查询带分页的充值记录
	@RequestMapping("/index")
	public String getList(HttpServletRequest request,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token) {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		if (userIdStr != null && !userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			int pageNo = 0;
			if (pageNoStr != null && !pageNoStr.isEmpty()) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = 10;
			if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
			List<WxPay> wxPays = wxPayService.getListByPage(userId, pageNo, pageSize);
			List<WxPayDto> payDtos = wxPayFill(wxPays);
			request.setAttribute("wxpaylist", payDtos);
			// 获取充值记录总数
			Integer totalRecord = wxPayService.getCountByUserId(userId);
			// 获取分页数据
			Pager pagerModel = new Pager().getPager(pageNo, pageSize, totalRecord);
			request.setAttribute("pager", pagerModel);

			request.setAttribute("userId", userId);
		}

		return "/wxPay/index";
	}

	@RequestMapping(value = "getMoreList.do")
	@ResponseBody
	public String getMoreList(HttpServletRequest request,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token) {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		JSONObject getObj = new JSONObject();
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		if (userIdStr != null && !userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			int pageNo = 0;
			if (pageNoStr != null && !pageNoStr.isEmpty()) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = 10;
			if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
			List<WxPay> wxPays = wxPayService.getListByPage(userId, pageNo, pageSize);
			if (wxPays == null) {
				getObj.put("ok", false);
				getObj.put("msg", "没有数据");
			} else {
				getObj.put("OK", true);
				getObj.put("msg", "成功获取数据");
				getObj.put("WxPayData", wxPays);
			}
		} else {
			getObj.put("ok", false);
			getObj.put("msg", "参数错误");
		}

		return getObj.toString();
	}

	/**
	 * 充值 转到这个页面的时候,可能是来自用户直接点击充值,也可能是来自用户阅读中充值 这里需要记录来自哪里,如果来自阅读中我们需要输出cookie
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token) {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}

		// todo:从cookie或者session中判断用户是否登录,未登录转到登录界面
		if (userIdStr != null && !userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			WxUser userModel = userSer.selectByPrimaryKey(userId);
			WxUserDto userDto = userDtoFill(userModel);
			request.setAttribute("user", userDto);
		}

		// todo:如果充值来源于阅读中,我们需要将阅读信息保存到cookie中
		String bookid = request.getParameter("bookid");
		String chapterid = request.getParameter("chapterid");

		return "/wxPay/pay";
	}

	/*
	 * 1.用户选择充值渠道,方式,金额后,转到这里 2.首先拿到充值渠道,方式,金额,然后组织相关的参数--生成订单,将订单插入数据库中
	 * 3.参数组织好后请求微信统一下单接口,得到预付单id 4.拿到预付单id之后,再次组织参数(包含签名),将相关参数传递给前端页面
	 * 5.前端页面拿到参数后,发起H5充值js请求 6.等待接受微信充值成功回调--将相应小说币增加到用户数据 7.返回到cookie保存的充值前界面
	 */
	@RequestMapping("ipay_now")
	public String ipay_now(HttpServletRequest request, HttpServletResponse response) {
		// todo:判断是否登录,未登录转到登录接口

		// 先将逻辑全写在controller中,写完后拆分到对应service中
		String moneyStr = request.getParameter("money");
		String typeStr = request.getParameter("type");
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		Integer money = Integer.parseInt(moneyStr);
		Integer type = Integer.parseInt(typeStr);
		Integer bookId = Integer.parseInt(bookIdStr);
		Integer chapterId = Integer.parseInt(chapterIdStr);

		// todo:从用户登录信息获取用户openid
		WxUser user = new WxUser();

		// 调用统一下单接口
		String remoteIpAdd = request.getRemoteAddr();
		UnifiedorderResult unifiedorderResult = wxPayService.createUnifiedorder(user, type, money, bookId, chapterId,
				remoteIpAdd);

		String json = PayUtil.generateMchPayJsRequestJson(unifiedorderResult.getPrepay_id(), WxConstants.WxAppId,
				WxConstants.WxMch_Key);

		// 将json 传到jsp 页面
		request.setAttribute("json", json);

		// 转到支付发起js页面
		return "ipay_now";
	}

	// 重复通知过滤
	private static ExpireKey expireKey = new DefaultExpireKey();

	/*
	 * 微信支付成功,回调此接口
	 * 
	 */
	@RequestMapping("wxpaycallback")
	public void wxPayCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取请求数据
		String xmlData = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
		// 将XML转为MAP,确保所有字段都参与签名验证
		Map<String, String> mapData = XMLConverUtil.convertToMap(xmlData);
		// 转换数据对象
		MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class, xmlData);
		// 已处理 去重
		if (expireKey.exists(payNotify.getTransaction_id())) {
			return;
		}
		// 签名验证
		if (SignatureUtil.validateSign(mapData, WxConstants.WxMch_Key)) {
			expireKey.add(payNotify.getTransaction_id());

			// 调用处理支付逻辑
			wxPayService.handlePayNotify(payNotify);

			// 返回给微信后台的回应
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("SUCCESS");
			baseResult.setReturn_msg("OK");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());

		} else {
			// 返回微信后台的回应
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR_Sign");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}
	}

	/*
	 * 支付结果跳转页面
	 */
	@RequestMapping("payresult")
	public String payResult(HttpServletRequest request) {
		// todo:在这里处理支付成功的逻辑,跳转到原来阅读地址,或者到首页等

		return "pay_result";
	}

	/**
	 * 支付失败
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/errorpay")
	public String error(HttpServletRequest request) {
		return "/wxPay/errorpay";
	}

	private List<WxPayDto> wxPayFill(List<WxPay> wxpays) {
		List<WxPayDto> wxPayDtos = new ArrayList<WxPayDto>();
		if (wxpays != null) {
			for (WxPay pay : wxpays) {
				WxPayDto payDto = new WxPayDto();
				payDto.setOrdernum(pay.getOrdernum());
				payDto.setPaytype(pay.getPaytype());
				payDto.setPaytypeName(EnumType.PayType.get(pay.getPaytype()));
				payDto.setAmount(pay.getAmount());
				payDto.setCount(pay.getCount());
				payDto.setCreatetime(DateUtil.date2Str(pay.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
				payDto.setUnit(pay.getUnit());
				payDto.setUnitName(EnumType.PayUnit.get(pay.getUnit()));
				wxPayDtos.add(payDto);
			}
		}
		return wxPayDtos;
	}

	private WxUserDto userDtoFill(WxUser user) {
		WxUserDto userDto = new WxUserDto();
		if (user != null) {
			userDto.setId(user.getId());
			userDto.setAutopurchase(user.getAutopurchase());
			userDto.setCoin(user.getCoin());
			userDto.setCreatetime(DateUtil.date2Str(user.getCreatetime()));
			userDto.setDeadline(DateUtil.date2Str(user.getDeadline()));
			userDto.setDevicecode(user.getDevicecode());
			userDto.setHeadericon(user.getHeadericon());
			userDto.setLastactivetime(DateUtil.date2Str(user.getLastactivetime()));
			userDto.setMobile(user.getMobile());
			userDto.setName(user.getName());
			userDto.setPwd(user.getPwd());
			userDto.setSignature(user.getSignature());
			userDto.setStatus(user.getStatus());
			userDto.setStatusStr(EnumType.UserStatus.get(user.getStatus()));
			userDto.setToken(user.getToken());
			userDto.setUpdatetime(DateUtil.date2Str(user.getUpdatetime()));
			userDto.setUsertype(user.getUsertype());
			userDto.setUsertypestr(EnumType.UserType.get(user.getUsertype()));
			userDto.setOpenid(user.getOpenid());
			userDto.setWeixintoken(user.getWeixintoken());

		}
		return userDto;
	}

}
