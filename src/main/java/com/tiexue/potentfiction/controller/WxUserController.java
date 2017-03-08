package com.tiexue.potentfiction.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxUserDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxConstants;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.service.IWxUserService;
import com.tiexue.potentfiction.util.DateUtil;

import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

@Controller
@RequestMapping("wxUser")
public class WxUserController {

	// 微信token
	static SnsToken wxSnsToken = null;
	static weixin.popular.bean.user.User wxSnsUser = null;

	@Resource
	IWxUserService userSer;

	@RequestMapping("/content")
	public String getModel(HttpServletRequest request, Integer userId) {
		WxUser userModel = userSer.selectByPrimaryKey(userId);
		WxUserDto userDtoModel = userDtoFill(userModel);
		request.setAttribute("user", userDtoModel);
		return "wxUser/index";
	}

	// 转到用户登录界面,记录来源refer,将refer保存到cookie里面,用于登录后的返回
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String refer = request.getHeader("Refer");
		if (null != refer && !refer.isEmpty()) {
			Cookie _refCookie = new Cookie("_ref", refer); // 创建一个Cookie对象，并将用户名保存到Cookie对象中
			_refCookie.setMaxAge(3600); // 设置Cookie的过期之前的时间，单位为秒
			response.addCookie(_refCookie); // 通过response的addCookie()方法将此Cookie对象保存到客户端的Cookie中
		}
		return "wxUser/login";
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
			userDto.setWeixinid(user.getWeixinid());
			userDto.setWeixintoken(user.getWeixinid());

		}
		return userDto;
	}

	/*
	 * 开始执行微信登录逻辑 OAuth2.0开始 页面跳转到
	 * open.weixin.qq.com/connect/oauth2/authorize(指定回调地址) 回调地址收到code -->
	 * 根据code换取access_token --> 根据access_token请求userinfo信息
	 * 请求到userinfo信息之后,将userinfo信息进行保存(数据库不存在openid插入)
	 * 将用户登录信息写入cookie及session,判断cookie中的_ref页面跳转回登录前网址,完成登录流程
	 */
	@RequestMapping("/wxlogindo")
	public String wxLoginDo(HttpServletRequest request, HttpServletResponse response) {
		String oauthUrl = SnsAPI.connectOauth2Authorize(WxConstants.WxAppId, WxConstants.WxRedirectUrl, true,
				WxConstants.WxOauthState);
		return "redirect:" + oauthUrl;
	}

	/*
	 * 微信公众号授权回调页面,微信会返回code和state 拿到Code和state以后我们发起调用请求access_token请求
	 * 拿到access_token及openid后,我们发起请求, 请求微信的用户基础信息,包括昵称,性别等
	 * 请求到用户信息后保存用户信息,用户转到登录前页面
	 */
	@RequestMapping("wxoauthcallback")
	public String wxOAuthCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!state.equalsIgnoreCase(WxConstants.WxOauthState)) {
			throw new Exception("state error");
		}
		// 获取access_token及openid等信息
		wxSnsToken = SnsAPI.oauth2AccessToken(WxConstants.WxAppId, WxConstants.WxAppSecret, code);

		// 根据access_token及openid等信息请求用户信息
		wxSnsUser = SnsAPI.userinfo(wxSnsToken.getAccess_token(), wxSnsToken.getOpenid(), WxConstants.WxSnsLang);

		// todo:生成登录cookie写到客户端,保存session
		Cookie token_cookie = new Cookie("wx_gzh_token", URLEncoder.encode("i_am_login", "UTF-8") ); // 创建一个Cookie对象，并将用户名保存到Cookie对象中
		token_cookie.setMaxAge(3600); // 设置Cookie的过期之前的时间，单位为秒
		response.addCookie(token_cookie); // 通过response的addCookie()方法将此Cookie对象

		// 接下来跳转到一个专门处理登录后逻辑的页面
		return "redirect:wxloginhandle";
	}

	/*
	 * 专门用来处理登录后逻辑的页面
	 * 
	 */
	@RequestMapping("wxloginhandle")
	public void wxLoginHandle(HttpServletRequest request, HttpServletResponse response) {
		// todo:判断是否登录,理论上到这里都是登录后的

		// todo:跳转到登录前页面

		// 这里是测试输出
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("YourOpenId:").append(wxSnsUser.getOpenid()).append(System.lineSeparator());
			sb.append("YourName:").append(wxSnsUser.getNickname()).append(System.lineSeparator());
			sb.append("YourCity:").append(wxSnsUser.getCity()).append(System.lineSeparator());
			sb.append("YourCountry:").append(wxSnsUser.getCountry()).append(System.lineSeparator());
			sb.append("YourHeadimgurl:").append(wxSnsUser.getHeadimgurl()).append(System.lineSeparator());
			sb.append("YourSex:").append(wxSnsUser.getSex()).append(System.lineSeparator());
			response.getWriter().println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
