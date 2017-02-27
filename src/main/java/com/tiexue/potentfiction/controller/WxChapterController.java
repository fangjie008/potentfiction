package com.tiexue.potentfiction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxChapterDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxChapter;
import com.tiexue.potentfiction.service.IWxBookService;
import com.tiexue.potentfiction.service.IWxChapterService;

@Controller
@RequestMapping("wxChapter")
public class WxChapterController {
	private static Logger logger=Logger.getLogger(WxChapterController.class);
	//获取章节信息的服务
	@Resource
	IWxChapterService wxChapterService;
	//获取小说信息的服务
	@Resource
	IWxBookService wxBook;
	//获取章节列表信息
	@RequestMapping("/index")
	public String getList(HttpServletRequest request) {
		int pageSize=20;
		String bookIdStr = request.getParameter("bookId");
		String pageNoStr = request.getParameter("pageNo");
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
			int bookId = 0;
			bookId = Integer.parseInt(bookIdStr);
			int pageNo = 0;
			pageNo = Integer.parseInt(pageNoStr);
			List<WxChapter> wxChapters = wxChapterService.selectByBookId(bookId, EnumType.ChapterStatus_OnLine, pageNo,
					pageSize);
			WxBook wxBookModel=wxBook.getModel(bookId);
			int totalRecord=wxChapterService.getCountByBookId(bookId, EnumType.ChapterStatus_OnLine);
			request.setAttribute("wxChapters",wxChapterDtoFill(wxChapters));
			request.setAttribute("wxBook", wxBookModel);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("bookId", bookId);
			request.setAttribute("pageNo", pageNo);
		}

		return "/wxChapter/index";
	}
	
	private List<WxChapterDto> wxChapterDtoFill(List<WxChapter> wxChapters){
		ArrayList<WxChapterDto> resultData=new ArrayList<WxChapterDto>();
		if(wxChapters!=null){
			for(WxChapter chap: wxChapters){
				WxChapterDto chaDto=new WxChapterDto();
				chaDto.setId(chap.getId());
				chaDto.setBookid(chap.getBookid());
				chaDto.setTitle(chap.getTitle());
				chaDto.setContentlen(chap.getContentlen());
				chaDto.setPirce(chap.getPirce());
				chaDto.setSortorder(chap.getSortorder());
				chaDto.setChaptertype(chap.getChaptertype());
				chaDto.setChaptertypeName(EnumType.ChapterType.get(chap.getChaptertype()));
				resultData.add(chaDto);
			}
		}
		return resultData;
	}
	
	
}
