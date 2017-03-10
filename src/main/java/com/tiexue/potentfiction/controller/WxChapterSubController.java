package com.tiexue.potentfiction.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiexue.potentfiction.dto.PageUserDto;
import com.tiexue.potentfiction.dto.ResultMsg;
import com.tiexue.potentfiction.dto.WxChapterSubDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxChapter;
import com.tiexue.potentfiction.entity.WxChapterSub;

import com.tiexue.potentfiction.service.IUserConsService;
import com.tiexue.potentfiction.service.IWxBookService;
import com.tiexue.potentfiction.service.IWxChapterService;
import com.tiexue.potentfiction.service.IWxChapterSubService;
import com.tiexue.potentfiction.service.IWxUserService;


@Controller
@RequestMapping("wxChapterSub")
public class WxChapterSubController {
	private static Logger logger=Logger.getLogger(WxChapterController.class);
	@Resource
	IWxChapterSubService chapterSubSer;
	@Resource
	IWxChapterService chapterService;
	@Resource
	IWxBookService bookService;
	@Resource
	IUserConsService userConsService;
	@Resource
	IWxUserService userSer;

	// 获取章节的内容信息
	@RequestMapping("/index")
	public String getContent(HttpServletRequest request, RedirectAttributes attr,
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		String bookName = "";
		int userId = 0;
		if (chapterIdStr != null && !chapterIdStr.isEmpty()) {
			int chapterId = Integer.parseInt(chapterIdStr);
			int bookId = 0;
			if (bookIdStr != null && !bookIdStr.isEmpty()) {
				bookId = Integer.parseInt(bookIdStr);
			}
			if (userIdStr != null && !userIdStr.isEmpty()) {
				userId = Integer.parseInt(userIdStr);
			}
			// 获取图书信息
			WxBook book = bookService.selectByPrimaryKey(bookId);
			if (book != null) {
				bookName = book.getName();
			}
			// 章节数据
			WxChapter chapterModel = chapterService.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
			if (chapterModel == null)
				return "wxChapterSub/index";
			// 付费章节操作
			if (chapterModel.getChaptertype() == 1) {
				ResultMsg resultMsg = userConsService.consDeal(userId, bookId, bookName, chapterModel);
				if (!resultMsg.getStatus()) {
					switch (resultMsg.getNum()) {
					case EnumType.ResultNum_Login:
						return "wxUser/login";
					case EnumType.ResultNum_Pay:
						attr.addAttribute("userId", userId);
						attr.addAttribute("chapterId", chapterId);
						attr.addAttribute("bookId", bookId);
						return "redirect:/WxPay/pay";
					case EnumType.ResultNum_Cons:
						attr.addAttribute("userId", userId);
						attr.addAttribute("chapterId", chapterId);
						attr.addAttribute("bookId", bookId);
						return "redirect:/wxConsume/subscribe";
					}
				}
				logger.error(resultMsg.getMsg());
			}
			// 获取章节信息
			WxChapterSubDto chapSubDto = getCahperDto(bookId, bookName, chapterId, chapterModel);
			request.setAttribute("wxChapterSub", chapSubDto);
		}
		return "wxChapterSub/index";
	}
	
	
	// // 付费后获取内容信息
	// @RequestMapping("/afterCons")
	// public String getContentAfterCons(HttpServletRequest
	// request,RedirectAttributes attr) throws UnsupportedEncodingException {
	// String userIdStr = request.getParameter("userId");
	// String bookIdStr = request.getParameter("bookId");
	// String chapterIdStr = request.getParameter("chapterId");
	// String bookName = "";
	// int userId = 0;
	// if (chapterIdStr != null && !chapterIdStr.isEmpty()) {
	// int chapterId = Integer.parseInt(chapterIdStr);
	// int bookId = 0;
	// if (bookIdStr != null && !bookIdStr.isEmpty()) {
	// bookId = Integer.parseInt(bookIdStr);
	// }
	// if (userIdStr != null && !userIdStr.isEmpty()) {
	// userId = Integer.parseInt(userIdStr);
	// }
	// // 获取图书信息
	// WxBook book = bookService.selectByPrimaryKey(bookId);
	// if (book != null) {
	// bookName = book.getName();
	// }
	// // 章节数据
	// WxChapter chapterModel = chapterService.selectByPrimaryKey(chapterId,
	// EnumType.ChapterStatus_OnLine);
	// if (chapterModel == null)
	// return "wxChapterSub/index";
	// // 获取章节信息
	// WxChapterSubDto chapSubDto = getCahperDto(bookName, chapterId,
	// chapterModel);
	// request.setAttribute("wxChapterSub", chapSubDto);
	// }
	// return "wxChapterSub/index";
	// }
	//

	// 获取章节的内容信息
	@RequestMapping("/defualt")
	public String getContentByBookId(HttpServletRequest request, RedirectAttributes attr,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("bookId");
		String bookName = "";
		int userId = 0;
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
			int bookId = Integer.parseInt(bookIdStr);
			int chapterId = 0;
			// 获取图书信息
			WxBook book = bookService.selectByPrimaryKey(bookId);
			if (book != null) {
				bookName = book.getName();
			}
			if (userIdStr != null && !userIdStr.isEmpty()) {
				userId = Integer.parseInt(userIdStr);
			}

			// 获取第一章数据
			WxChapter chapterModel = chapterService.getFirstChapter(bookId, EnumType.ChapterStatus_OnLine);
			if (chapterModel != null) {
				chapterId = chapterModel.getId();
				// 付费章节操作
				if (chapterModel.getChaptertype() == 1) {
					ResultMsg resultMsg = userConsService.consDeal(userId, bookId, bookName, chapterModel);
					if (!resultMsg.getStatus()) {
						switch (resultMsg.getNum()) {
						case EnumType.ResultNum_Login:
							return "wxUser/login";
						case EnumType.ResultNum_Pay:
							return "WxPay/pay";
						case EnumType.ResultNum_Cons:
							return "wxConsume/subscribe";
						}
					}
					logger.error(resultMsg.getMsg());
				}
			}
			// 获取章节信息
			WxChapterSubDto chapSubDto = getCahperDto(bookId, bookName, chapterId, chapterModel);
			request.setAttribute("wxChapterSub", chapSubDto);
		}
		return "wxChapterSub/index";
	}
	

	/**
	 * 获取章节内容信息
	 * @param bookName
	 * @param chapterId
	 * @param chapterModel
	 * @return
	 */
	private WxChapterSubDto getCahperDto(int bookId,String bookName, int chapterId,WxChapter chapterModel) {
		int preId = 0;
		int nextId = 0;
		// 章节内容数据
		WxChapterSub chapSub = chapterSubSer.selectByChapterId(chapterId);
		// 上一章数据
		WxChapter preChap = chapterService.getPreChapter(bookId,chapterId, EnumType.ChapterStatus_OnLine);
		// 下一章数据
		WxChapter nextChap = chapterService.getNextChapter(bookId,chapterId, EnumType.ChapterStatus_OnLine);
		if (preChap != null) {
			preId = preChap.getId();
		}
		if (nextChap != null) {
			nextId = nextChap.getId();
		}
		WxChapterSubDto chapSubDto = wxChapterSubDtoFill(chapSub, chapterModel, preId, nextId);
		chapSubDto.setBookName(bookName);
		return chapSubDto;
	}


	

	
	private WxChapterSubDto wxChapterSubDtoFill(WxChapterSub chapSub,WxChapter chapterModel,int preId,int nextId){
		WxChapterSubDto chapSubDto=new WxChapterSubDto();
		if(chapterModel!=null){
			
		    chapSubDto.setTitle(chapterModel.getTitle());
			chapSubDto.setBookId(chapterModel.getBookid());
		}
		if(chapSub!=null){
			chapSubDto.setId(chapSub.getId());
			chapSubDto.setContent(chapSub.getContent());
		}
		chapSubDto.setPreId(preId);
		chapSubDto.setNextId(nextId);
		return chapSubDto;
	}


}
