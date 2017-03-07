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

import com.tiexue.potentfiction.entity.WxConstants;
import com.tiexue.potentfiction.service.IWxCallbackService;

@Controller
@RequestMapping("wxcallback")
public class WxCallbackController {
	// 日志
	private Logger logger = Logger.getLogger(WxCallbackController.class);

	@Resource
	IWxCallbackService wxCallbackService;

	@RequestMapping("check")
	public String check(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		boolean ret = wxCallbackService.checkSignature(signature, timestamp, nonce);
		if (ret == true) {
			return nonce;
		}
		return "";
	}

}
