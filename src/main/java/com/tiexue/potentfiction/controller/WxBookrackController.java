package com.tiexue.potentfiction.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.service.IWxBookrackService;

@Controller
@RequestMapping("wxBookrack")
public class WxBookrackController {

	@Resource
	IWxBookrackService bookrackService;
}
