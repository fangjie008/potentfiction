package com.tiexue.potentfiction.controller;

import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.service.IWxPayService;

@Controller
@RequestMapping("/wxPay")
public class WxPayController {
	// 打印日志
	private Logger logger = Logger.getLogger(WxPayController.class);
	@Resource
	IWxPayService wxPayService;

	@RequestMapping(value = "getList.do")
	@ResponseBody
	public String getList(HttpServletRequest request) {
		JSONObject getObj = new JSONObject();
		try {
			String userIdStr = request.getParameter("userId");
			if (!userIdStr.isEmpty()) {
				int userId = Integer.parseInt(userIdStr);

				List<WxPay> wxPays = wxPayService.getList(userId);
				if (wxPays == null) {
					getObj.put("ok", false);
					getObj.put("msg", "没有数据");
				} else {
					getObj.put("OK", true);
					getObj.put("msg", "成功获取数据");
					getObj.put("WxPayData", wxPays);
				}
			}
			else{
				getObj.put("ok", false);
				getObj.put("msg", "参数错误");
			}
			

		} catch (Exception e) {
			getObj.put("ok", false);
			getObj.put("msg", "数据异常");
			logger.error(e.toString());
		}

		return getObj.toString();
	}

}
