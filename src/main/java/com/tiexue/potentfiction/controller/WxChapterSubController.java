package com.tiexue.potentfiction.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxChapterSubDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxChapter;
import com.tiexue.potentfiction.entity.WxChapterSub;
import com.tiexue.potentfiction.service.IWxChapterService;
import com.tiexue.potentfiction.service.IWxChapterSubService;

@Controller
@RequestMapping("wxChapterSub")
public class WxChapterSubController {
	private static Logger logger=Logger.getLogger(WxChapterController.class);
	@Resource
	IWxChapterSubService chapterSubSer;
	@Resource
	IWxChapterService chapterService;
	
	//获取章节的内容信息
	@RequestMapping("/index")
	public String getContent(HttpServletRequest request) throws UnsupportedEncodingException{
		String bookIdStr=request.getParameter("bookId");
		String chapterIdStr=request.getParameter("chapterId");
		String bookName=new String(request.getParameter("bookName").getBytes("ISO-8859-1"),"UTF-8");
		if(chapterIdStr!=null&&!chapterIdStr.isEmpty()){
			int preId=0;
			int nextId=0;
			int bookId=Integer.parseInt(bookIdStr);
			int chapterId=Integer.parseInt(chapterIdStr);
			//章节内容数据
			WxChapterSub chapSub=chapterSubSer.selectByChapterId(chapterId);
			//章节数据
			WxChapter chapterModel=chapterService.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
			//上一章数据
			WxChapter preChap=chapterService.getPreChapter(chapterId, EnumType.ChapterStatus_OnLine);
			//下一章数据
			WxChapter nextChap=chapterService.getNextChapter(chapterId, EnumType.ChapterStatus_OnLine);
		    if(preChap!=null){
		    	preId=preChap.getId();
		    }
		    if(nextChap!=null){
		    	nextId=nextChap.getId();
		    }
		    WxChapterSubDto chapSubDto= wxChapterSubDtoFill(chapSub,chapterModel,preId,nextId);
		    request.setAttribute("wxChapterSub", chapSubDto);
		    request.setAttribute("bookName", bookName);
		}
		return "wxChapterSub/index";
	}
	
	private WxChapterSubDto wxChapterSubDtoFill(WxChapterSub chapSub,WxChapter chapterModel,int preId,int nextId){
		WxChapterSubDto chapSubDto=new WxChapterSubDto();
		chapSubDto.setId(chapSub.getId());
	
		if(chapterModel!=null){
		    chapSubDto.setTitle(chapterModel.getTitle());
			chapSubDto.setBookId(chapterModel.getBookid());
		}
		chapSubDto.setContent(chapSub.getContent());
		chapSubDto.setPreId(preId);
		chapSubDto.setNextId(nextId);
		return chapSubDto;
	}
}
