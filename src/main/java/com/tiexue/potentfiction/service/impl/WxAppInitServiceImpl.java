package com.tiexue.potentfiction.service.impl;

import javax.annotation.PostConstruct;

import com.tiexue.potentfiction.entity.WxConstants;

import weixin.popular.support.TicketManager;
import weixin.popular.support.TokenManager;

public class WxAppInitServiceImpl {
	
	/*
	 * 容器启动时执行的初始化方法
	 * */
	@PostConstruct
	public void init() {
		//定时拉取token
		TokenManager.init(WxConstants.WxAppId, WxConstants.WxAppSecret);
		
		//定时拉取jsapi_token
		TicketManager.init(WxConstants.WxAppId);
	}
}
