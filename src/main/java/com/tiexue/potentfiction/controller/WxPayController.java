package com.tiexue.potentfiction.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.potentfiction.dto.Pager;
import com.tiexue.potentfiction.dto.WxPayDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.service.IWxPayService;

@Controller
@RequestMapping("/wxPay")
public class WxPayController {
	// 打印日志
	private Logger logger = Logger.getLogger(WxPayController.class);
	@Resource
	IWxPayService wxPayService;

	//查询带分页的充值记录
	@RequestMapping("/index")
	public String getList(HttpServletRequest request){
		String userIdStr = request.getParameter("userId");
		String pageNoStr=request.getParameter("pageNo");
		String pageSizeStr=request.getParameter("pageSize");
		if (!userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			int pageNo = 0;
			if(pageNoStr!=null&&!pageNoStr.isEmpty()){
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = 10;
			if(pageSizeStr!=null&&!pageSizeStr.isEmpty()){
				pageSize = Integer.parseInt(pageSizeStr);
			}
			List<WxPay> wxPays = wxPayService.getListByPage(userId,pageNo,pageSize);
			List<WxPayDto> payDtos=wxPayFill(wxPays);
			request.setAttribute("wxpaylist", payDtos);
			//获取充值记录总数
			Integer totalRecord=wxPayService.getCountByUserId(userId);
			//获取分页数据
			Pager pagerModel= getPager(pageNo,pageSize,totalRecord);
			request.setAttribute("pager", pagerModel);
			
			request.setAttribute("userId", userId);
		}
		
		
		return "/wxPay/index";
	}
	
	@RequestMapping(value = "getMoreList.do")
	@ResponseBody
	public String getMoreList(HttpServletRequest request) {
		JSONObject getObj = new JSONObject();
		String userIdStr = request.getParameter("userId");
		String pageNoStr=request.getParameter("pageNo");
		String pageSizeStr=request.getParameter("pageSize");
		if (!userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			int pageNo = 0;
			if(pageNoStr!=null&&!pageNoStr.isEmpty()){
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = 10;
			if(pageSizeStr!=null&&!pageSizeStr.isEmpty()){
				pageSize = Integer.parseInt(pageSizeStr);
			}
			List<WxPay> wxPays = wxPayService.getListByPage(userId,pageNo,pageSize);
			if (wxPays == null) {
				getObj.put("ok", false);
				getObj.put("msg", "没有数据");
			} else {
				getObj.put("OK", true);
				getObj.put("msg", "成功获取数据");
				getObj.put("WxPayData", wxPays);
			}
		} else {
			getObj.put("ok", false);
			getObj.put("msg", "参数错误");
		}

		return getObj.toString();
	}
	
	private List<WxPayDto> wxPayFill(List<WxPay> wxpays)
	{
		List<WxPayDto> wxPayDtos=new ArrayList<WxPayDto>();
		if(wxpays!=null){
			for (WxPay pay : wxpays) {
				WxPayDto payDto=new WxPayDto();
				payDto.setId(pay.getId());
				payDto.setPayType(pay.getPayType());
				payDto.setPayTypeName(EnumType.PayType.get(pay.getPayType()));
				payDto.setAmount(pay.getAmount());
				payDto.setCount(pay.getCount());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				payDto.setCreateTime(sdf.format(pay.getCreateTime()));
				payDto.setUnit(pay.getUnit());
				payDto.setUnitName(EnumType.PayUnit.get(pay.getUnit()));
				wxPayDtos.add(payDto);
			}
		}
		return wxPayDtos;
	}
	//获取分页信息
	private Pager getPager(int pageNo,int pageSize,int totalRecord){
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager.setTotalRecord(totalRecord);
		int totalPage=0;
		totalPage=totalRecord/pageSize;
		int remNum=totalRecord%pageSize;
		if(remNum>0)
			totalPage+=1;
		pager.setTotalPage(totalPage);
		int prePage=0;
		if(pageNo>0)
			prePage=pageNo-1;
		pager.setPrePage(prePage);
		int nextPage=0;
		if(pageNo<totalPage)
			nextPage=pageNo+1;
		pager.setNextPage(nextPage);
		return pager;
	}

}
