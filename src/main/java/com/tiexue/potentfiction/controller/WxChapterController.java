package com.tiexue.potentfiction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxChapterDto;
import com.tiexue.potentfiction.dto.WxChapterNaviDto;
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
		int pageSize=2;
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
			List<WxChapterNaviDto> chapNaviDtos=wxChapterNaviDtoFill(bookId,totalRecord,pageSize,pageNo);
			request.setAttribute("wxBook", wxBookModel);
			request.setAttribute("bookId", bookId);
		    request.setAttribute("chapNaviDtos", chapNaviDtos);
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
	//绑定小说章节导航的数据
	private List<WxChapterNaviDto> wxChapterNaviDtoFill(int bookId,int totalRecord,int pageSize,int pageNo)
	{
		ArrayList<WxChapterNaviDto> resultData=new ArrayList<WxChapterNaviDto>();
		if(totalRecord<=0)
			return resultData;
		if(pageSize>=totalRecord){
			WxChapterNaviDto chapNavi=new WxChapterNaviDto();
			chapNavi.setName("1-"+pageSize+"章");
			chapNavi.setUrl("/wxChapter/index?bookId="+bookId+"&pageNo="+pageNo);
			chapNavi.setOrder(0);
			chapNavi.setIsActive(true);
			resultData.add(chapNavi);
		}
		else{
			int count=totalRecord/pageSize;
			if(totalRecord%pageSize>0)
				count++;
			for(int i=0;i<count;i++){
				int prePage=(pageSize*i+1);
				int lastPage=(pageSize*(i+1))>totalRecord?totalRecord:(pageSize*(i+1));
				WxChapterNaviDto chapNavi=new WxChapterNaviDto();
				chapNavi.setName(prePage+"-"+lastPage+"章");
				chapNavi.setUrl("/wxChapter/index?bookId="+bookId+"&pageNo="+pageSize*i);
				chapNavi.setOrder(i);
				chapNavi.setIsActive(i==pageNo?true:false);
				resultData.add(chapNavi);
			}
			
		}
			
		return resultData;
	}
}
