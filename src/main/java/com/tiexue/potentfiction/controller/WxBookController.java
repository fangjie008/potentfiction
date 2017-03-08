package com.tiexue.potentfiction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxBookDto;
import com.tiexue.potentfiction.dto.WxBookrackDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxBookrack;
import com.tiexue.potentfiction.service.IWxBookService;
import com.tiexue.potentfiction.service.IWxBookrackService;

@Controller
@RequestMapping("/wxbook")
public class WxBookController {
	// 日志
	private Logger logger = Logger.getLogger(WxBookController.class);

	@Resource
	IWxBookService wxBookService;
	
	@Resource
	IWxBookrackService bookrackService;
	
	/**
	 * 首页入口
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {

		try {
			String userIdStr = request.getParameter("userId");
			String status = EnumType.BookStatus_Finish + "," + EnumType.BookStatus_Update;
			List<WxBook> wxBooks = this.wxBookService.getList(status, "ViewCount");
			List<WxBookDto> wxBookDtos = toWxBookListDto(wxBooks);
			request.setAttribute("wxBooks", wxBookDtos);
			if (userIdStr != null && !userIdStr.isEmpty()) {
				int userId = Integer.parseInt(userIdStr);
				WxBookrack rack = bookrackService.getModelByUserId(userId);
				request.setAttribute("bookrack", rack);
			}
		} catch (Exception e) {
			logger.error("首页获取数据异常"+e.getMessage());
		}
		return "/booklist";
	}

	
	@RequestMapping("/detail")
	public String detailInfo(HttpServletRequest request) {
		String bookIdStr = request.getParameter("id");
		String userIdStr = request.getParameter("userId");
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
			int bookId = Integer.parseInt(bookIdStr);
			WxBook wxBook = this.wxBookService.selectByPrimaryKey(bookId);
			WxBookDto wxBookDto = toWxBookDto(wxBook);
			request.setAttribute("wxBook", wxBookDto);
			if (userIdStr != null && !userIdStr.isEmpty()) {
				int userId = Integer.parseInt(userIdStr);
				WxBookrack rack = bookrackService.getModelByBookId(userId, bookId);
				request.setAttribute("bookrack", rack);
			}

		}

		return "bookdetail";
	}
	
	
	private WxBookDto toWxBookDto(WxBook wxBook) {
		WxBookDto wxBookDto = new WxBookDto();
		if (wxBook != null) {
			wxBookDto.setId(wxBook.getId());
			wxBookDto.setName(wxBook.getName());
			wxBookDto.setCoverImgs(wxBook.getCoverimgs());
			wxBookDto.setTag(wxBook.getTag());
			wxBookDto.setStatus(EnumType.BookStatus.get(wxBook.getStatus()));
			wxBookDto.setContentLen(wxBook.getContentlen());
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
				wxBookDto.setContentLen(book.getContentlen());
				wxBookDto.setIntr(book.getIntr());
				wxBookDtoList.add(wxBookDto);
			}
			
			
		}
		return wxBookDtoList;
	}

}
