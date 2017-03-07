package com.tiexue.potentfiction.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiexue.potentfiction.entity.WxConstants;
import com.tiexue.potentfiction.service.IWxCallbackService;

@Controller
@RequestMapping("wxcallback")
public class WxCallbackController {
	// 日志
	private Logger logger = Logger.getLogger(WxCallbackController.class);

	@Resource
	IWxCallbackService wxCallbackService;

	@RequestMapping(name="check", method={RequestMethod.GET}, produces="application/json;charset=UTF-8")
	public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		boolean ret = wxCallbackService.checkSignature(signature, timestamp, nonce);
		if (ret == true) {
			response.getWriter().print(echostr);
		}
		response.getWriter().print("error");
	}

}
