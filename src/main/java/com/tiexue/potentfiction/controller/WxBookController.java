package com.tiexue.potentfiction.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxBookDto;
import com.tiexue.potentfiction.dto.WxPayDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.service.IWxBookService;

@Controller
@RequestMapping("wxbook")
public class WxBookController {
	// 日志
	private Logger logger = Logger.getLogger(WxBookController.class);

	@Resource
	IWxBookService wxBookService;

	@RequestMapping("detail")
	public String detailInfo(HttpServletRequest request) {
		String bookIdStr = request.getParameter("id");
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
			int bookId = Integer.parseInt(bookIdStr);
			WxBook wxBook = this.wxBookService.getModel(bookId);
			WxBookDto wxBookDto = toWxBookDto(wxBook);
			request.setAttribute("wxBook", wxBookDto);
		}

		return "bookdetail";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {

		   String status=EnumType.BookStatus_Finish+","+EnumType.BookStatus_Update;
			List<WxBook> wxBooks = this.wxBookService.getList(status,"ViewCount");
			List<WxBookDto> wxBookDtos = toWxBookListDto(wxBooks);
			request.setAttribute("wxBooks", wxBookDtos);
	
		return "/booklist";
	}

	private WxBookDto toWxBookDto(WxBook wxBook) {
		WxBookDto wxBookDto = new WxBookDto();
		if (wxBook != null) {
			wxBookDto.setId(wxBook.getId());
			wxBookDto.setName(wxBook.getName());
			wxBookDto.setCoverImgs(wxBook.getCoverImgs());
			wxBookDto.setStatus(EnumType.BookStatus.get(wxBook.getStatus()));
			wxBookDto.setContentLen(wxBook.getCotentLen());
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
				wxBookDto.setCoverImgs(book.getCoverImgs());
				wxBookDto.setStatus(EnumType.BookStatus.get(book.getStatus()));
				wxBookDto.setContentLen(book.getCotentLen());
				wxBookDtoList.add(wxBookDto);
			}
			
			
		}
		return wxBookDtoList;
	}

}