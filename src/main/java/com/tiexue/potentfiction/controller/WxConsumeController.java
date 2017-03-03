package com.tiexue.potentfiction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.potentfiction.dto.Pager;
import com.tiexue.potentfiction.dto.WxConsumeDto;
import com.tiexue.potentfiction.dto.WxPayDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxChapter;
import com.tiexue.potentfiction.entity.WxConsume;
import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.service.IUserConsService;
import com.tiexue.potentfiction.service.IWxBookService;
import com.tiexue.potentfiction.service.IWxChapterService;
import com.tiexue.potentfiction.service.IWxConsumeService;
import com.tiexue.potentfiction.service.IWxUserService;
import com.tiexue.potentfiction.util.DateUtil;

@Controller
@RequestMapping("wxConsume")
public class WxConsumeController {

	@Resource
	IWxConsumeService consumeService;
	@Resource
	IWxChapterService chapterService;
	@Resource
	IWxBookService bookService;
	@Resource
	IWxUserService userService;
	@Resource
	IUserConsService userConsService;
	
	@RequestMapping("/index")
	public String getList(HttpServletRequest request){
		String userIdStr = request.getParameter("userId");
		String pageNoStr=request.getParameter("pageNo");
		String pageSizeStr=request.getParameter("pageSize");
		if (userIdStr!=null&&!userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			int pageNo = 0;
			if(pageNoStr!=null&&!pageNoStr.isEmpty()){
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = 10;
			if(pageSizeStr!=null&&!pageSizeStr.isEmpty()){
				pageSize = Integer.parseInt(pageSizeStr);
			}
			List<WxConsume> wxConsumes = consumeService.getListByPage(userId,pageNo,pageSize);
			List<WxConsumeDto> wxConsumeDtos=wxConsumeFill(wxConsumes);
			request.setAttribute("consumelist", wxConsumeDtos);
			//获取充值记录总数
			Integer totalRecord=consumeService.getCountByUserId(userId);
			//获取分页数据
			Pager pagerModel=new Pager().getPager(pageNo,pageSize,totalRecord);;
			request.setAttribute("pager", pagerModel);
			request.setAttribute("userId", userId);
		}
		return "wxConsume/index";
	}

	/**
	 * 订阅章节页面
	 * 
	 * @return
	 */
	@RequestMapping("subscribe")
	public String getSubscribe(HttpServletRequest request) {
		String userIdStr = request.getParameter("userId");
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		int chapterId = 0;
		int bookId = 0;
		int userId = 0;
		if (chapterIdStr != null && !chapterIdStr.isEmpty())
			chapterId = Integer.parseInt(chapterIdStr);
		if (bookIdStr != null && !bookIdStr.isEmpty())
			bookId = Integer.parseInt(bookIdStr);
		if (userIdStr != null && !userIdStr.isEmpty())
			userId = Integer.parseInt(userIdStr);
		// 章节数据
		WxChapter chapterModel = chapterService.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
		// 用户信息
		WxUser userModel = userService.selectByPrimaryKey(userId);
		//图书信息
		WxBook bookModel=bookService.selectByPrimaryKey(bookId);
		request.setAttribute("chapter", chapterModel);
		request.setAttribute("user", userModel);
		request.setAttribute("book", bookModel);
		return "wxConsume/subscribe";
	}
	/**
	 * 处理消费订单
	 * @param request
	 * @return
	 */
	@RequestMapping("handleOrder")
	@ResponseBody
	public String handleOrder(HttpServletRequest request){
		JSONObject getObj=new JSONObject();
		String userIdStr = request.getParameter("userId");
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		String autopayStr = request.getParameter("autopay");
		int chapterId = 0;
		int bookId = 0;
		int userId = 0;
		boolean autoPay=true;
		if (chapterIdStr != null && !chapterIdStr.isEmpty())
			chapterId = Integer.parseInt(chapterIdStr);
		if (bookIdStr != null && !bookIdStr.isEmpty())
			bookId = Integer.parseInt(bookIdStr);
		if (userIdStr != null && !userIdStr.isEmpty())
			userId = Integer.parseInt(userIdStr);
		if (autopayStr != null && !autopayStr.isEmpty()){
			autoPay = (Integer.parseInt(autopayStr)>0)?true:false;
		}
		Boolean result= userConsService.consumeRecord(userId, bookId, chapterId,autoPay);
		getObj.put("ok", result);
		return getObj.toString();
	}
	

	private List<WxConsumeDto> wxConsumeFill(List<WxConsume> wxConsumes)
	{
			List<WxConsumeDto> wxConsumeDtos=new ArrayList<WxConsumeDto>();
			if(wxConsumes!=null){
				for (WxConsume cons : wxConsumes) {
					WxConsumeDto consDto=new WxConsumeDto();
					consDto.setId(cons.getId());
					consDto.setBookid(cons.getBookid());
					consDto.setBookname(cons.getBookname());
					consDto.setCharpterid(cons.getCostcoin());
					consDto.setCharptertitle(cons.getCharptertitle());
					consDto.setCostcoin(cons.getCostcoin());
					consDto.setUserid(consDto.getUserid());
					consDto.setCreatetime(DateUtil.date2Str(cons.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
					wxConsumeDtos.add(consDto);
				}
			}
			return wxConsumeDtos;
	}
}
