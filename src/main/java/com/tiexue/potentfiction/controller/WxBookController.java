package com.tiexue.potentfiction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.tiexue.potentfiction.dto.PageUserDto;
import com.tiexue.potentfiction.dto.WxBookDto;
import com.tiexue.potentfiction.dto.WxBookrackDto;
import com.tiexue.potentfiction.dto.bookrackCookieDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxBookrack;
import com.tiexue.potentfiction.entity.WxChapter;
import com.tiexue.potentfiction.service.IWxBookService;
import com.tiexue.potentfiction.service.IWxBookrackService;
import com.tiexue.potentfiction.service.IWxChapterService;
import com.tiexue.potentfiction.service.IWxUserService;

@Controller
@RequestMapping("/wxbook")
public class WxBookController {
	// 日志
	private Logger logger = Logger.getLogger(WxBookController.class);

	@Resource
	IWxBookService wxBookService;
	
	@Resource
	IWxBookrackService bookrackService;
	//获取章节信息的服务
	@Resource
	IWxChapterService wxChapterService;
	
	@Resource
	IWxUserService userSer;
	
	/**
	 * 首页入口
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request
			,@CookieValue(value ="defaultbookrack",required = true, defaultValue = "")String rackCookie
			,@CookieValue(value ="wx_gzh_token",required = true, defaultValue = "")String wx_gzh_token) {
		String userIdStr="";
		try {
			logger.error("获取 wx_gzh_token："+wx_gzh_token);
			if(wx_gzh_token!=""){
				PageUserDto pageUser= userSer.getPageUserDto(wx_gzh_token);
				if(pageUser!=null){
				 userIdStr=pageUser.getId();
				 request.setAttribute("pageUser", pageUser);
				}
				logger.error("获取 pageUser.getId："+userIdStr);
			}
			String status = EnumType.BookStatus_Finish + "," + EnumType.BookStatus_Update;
			List<WxBook> wxBooks = this.wxBookService.getList(status, "ViewCount",40);
			List<WxBookDto> wxBookDtos = toWxBookListDto(wxBooks);
			request.setAttribute("wxBooks", wxBookDtos);
			WxBookrack rack=new WxBookrack();
			if (userIdStr != null && !userIdStr.isEmpty()) {
				int userId = Integer.parseInt(userIdStr);
			    rack = bookrackService.getModelByUserId(userId);
			}else{
				rack=getBookrackByCookie(rackCookie);
			}
			request.setAttribute("bookrack", rack);
		} catch (Exception e) {
			logger.error("首页获取数据异常"+e.getMessage());
		}
		return "/booklist";
	}

	
	@RequestMapping("/detail")
	public String detailInfo(HttpServletRequest request,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token) {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("id");
		int bookId=0;
		int userId=0;
		String bookName="";
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
		    bookId = Integer.parseInt(bookIdStr);
			WxBook wxBook = this.wxBookService.selectByPrimaryKey(bookId);
			WxBookDto wxBookDto = toWxBookDto(wxBook);
			request.setAttribute("wxBook", wxBookDto);
			if (userIdStr != null && !userIdStr.isEmpty()) {
			    userId = Integer.parseInt(userIdStr);
				WxBookrack rack = bookrackService.getModelByBookId(userId, bookId);
				request.setAttribute("bookrack", rack);
			} else {

			}
			if(wxBook!=null){
				bookName=wxBook.getName();
			}

		}
		//保存书架
        if(bookId>0&&userId>0){
        	saveBookrack(bookId,userId,bookName);
        }
		return "bookdetail";
	}
	
	/**
	 * 根据cookie获取收藏的书架
	 * @param rackCookie
	 * @return
	 */
	private WxBookrack getBookrackByCookie(String rackCookie){
		WxBookrack rack = new WxBookrack();
		 List<bookrackCookieDto> cookies=JSON.parseArray(rackCookie, bookrackCookieDto.class);
		 if(cookies!=null&&cookies.size()>0){
				 WxChapter curChap = null;
				 WxBook book= wxBookService.selectByPrimaryKey(cookies.get(cookies.size()-1).getBookid());
				 if(cookies.get(cookies.size()-1).getChapterid()>0){
					 curChap=wxChapterService.selectByPrimaryKey(cookies.get(cookies.size()-1).getChapterid(), EnumType.ChapterStatus_OnLine);
				 }
				 rack = bookrackDtoFill(book,curChap);
		 }
		 return rack;
	}
	
	
	private WxBookrack bookrackDtoFill(WxBook book,WxChapter curChap){
		WxBookrack rack = new WxBookrack();
		if (book != null) {
			rack.setBookid(book.getId());
			rack.setBookname(book.getName());
			rack.setLocation(0);
			rack.setUserid(0);
		}
		if (curChap != null) {
			rack.setChapterid(curChap.getId());
			rack.setChaptertitle(curChap.getTitle());
		}

		return rack;
	}
	
	
	private WxBookDto toWxBookDto(WxBook wxBook) {
		WxBookDto wxBookDto = new WxBookDto();
		if (wxBook != null) {
			wxBookDto.setId(wxBook.getId());
			wxBookDto.setName(wxBook.getName());
			wxBookDto.setCoverImgs(wxBook.getCoverimgs());
			wxBookDto.setTag(wxBook.getTag());
			wxBookDto.setStatus(EnumType.BookStatus.get(wxBook.getStatus()));
			if(wxBook.getContentlen()!=null){
				if(wxBook.getContentlen()>10000){
					String conlen=(wxBook.getContentlen()/10000+wxBook.getContentlen()%10000*0.0001)+"万";
					wxBookDto.setContentLen(conlen);
				}
				else{
					wxBookDto.setContentLen(wxBook.getContentlen()+"");
				}
			}
			
			wxBookDto.setIntr(wxBook.getIntr());
		}
		return wxBookDto;
	}
	
	private List<WxBookDto> toWxBookListDto(List<WxBook> wxBooks) {
		ArrayList<WxBookDto> wxBookDtoList = new ArrayList<WxBookDto>();
		if (wxBooks != null) {
			for(WxBook book:wxBooks){
				WxBookDto wxBookDto=new WxBookDto();
				wxBookDto.setId(book.getId());
				wxBookDto.setName(book.getName());
				wxBookDto.setTag(book.getTag());
				wxBookDto.setCoverImgs(book.getCoverimgs());
				wxBookDto.setStatus(EnumType.BookStatus.get(book.getStatus()));
				if(book.getContentlen()!=null){
					if(book.getContentlen()>10000){
						String conlen=(book.getContentlen()/10000+book.getContentlen()%10000*0.0001)+"万";
						wxBookDto.setContentLen(conlen);
					}
					else{
						wxBookDto.setContentLen(book.getContentlen()+"");
					}
				}
				wxBookDto.setIntr(book.getIntr());
				wxBookDtoList.add(wxBookDto);
			}
			
			
		}
		return wxBookDtoList;
	}

	/**
	 * 用户阅读小说时直接加到书架中
	 * @param bookId
	 * @param userId
	 */
	private void saveBookrack(int bookId,int userId,String bookName){
		bookrackService.saveBookrack(userId, bookId, bookName, 0, "");
	}
}
