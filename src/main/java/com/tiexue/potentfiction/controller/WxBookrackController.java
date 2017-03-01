package com.tiexue.potentfiction.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.potentfiction.dto.WxBookrackDto;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxBookrack;
import com.tiexue.potentfiction.service.IWxBookService;
import com.tiexue.potentfiction.service.IWxBookrackService;

@Controller
@RequestMapping("wxBookrack")
public class WxBookrackController {

	@Resource
	IWxBookrackService bookrackService;
	@Resource
	IWxBookService bookService;

	@RequestMapping("addBookrack")
	@ResponseBody
	public String insertBookrack(HttpServletRequest request, Integer bookId, String bookName, Integer userId)
			throws UnsupportedEncodingException {
		JSONObject getObj = new JSONObject();
		WxBookrack rack;
		if (bookId > 0) {
			rack = bookrackService.getModelByBookId(bookId);
			if (rack != null && rack.getBookid() > 0) {
				getObj.put("ok", false);
				getObj.put("msg", "已收藏此书");
			} else {
				if (bookName == null) {
					WxBook book = bookService.selectByPrimaryKey(bookId);
					if (book != null)
						bookName = book.getName();
				}

				Date time = new Date();
				rack = new WxBookrack();
				rack.setBookid(bookId);
				rack.setBookname(bookName);
				rack.setUserid(userId);
				rack.setChapterid(0);
				rack.setLocation(0);
				rack.setCreatetime(time);
				rack.setChaptertitle("");
				int res = bookrackService.insert(rack);
				getObj.put("ok", res > 0 ? true : false);
				getObj.put("msg", "收藏成功");
			}
		}
		return getObj.toString();
	}
	
	//获取书架信息
	@RequestMapping("list")
	public String getBookrackList(HttpServletRequest request,Integer userId){
		List<WxBookrackDto> racks=bookrackService.getListByUserId(userId, 20);
		request.setAttribute("bookracks", racks);
		return "/wxBookrack/index";
		
	}
}
