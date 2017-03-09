package com.tiexue.potentfiction.controller;


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
import com.tiexue.potentfiction.dto.WxUserDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.service.IWxPayService;
import com.tiexue.potentfiction.service.IWxUserService;
import com.tiexue.potentfiction.util.DateUtil;

@Controller
@RequestMapping("/wxPay")
public class WxPayController {
	// 打印日志
	private Logger logger = Logger.getLogger(WxPayController.class);
	@Resource
	IWxPayService wxPayService;
	@Resource
	IWxUserService userSer;
	
	//查询带分页的充值记录
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
			List<WxPay> wxPays = wxPayService.getListByPage(userId,pageNo,pageSize);
			List<WxPayDto> payDtos=wxPayFill(wxPays);
			request.setAttribute("wxpaylist", payDtos);
			//获取充值记录总数
			Integer totalRecord=wxPayService.getCountByUserId(userId);
			//获取分页数据
			Pager pagerModel=new Pager().getPager(pageNo,pageSize,totalRecord);;
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
		if (userIdStr != null && !userIdStr.isEmpty()) {
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
	
	/**
	 * 充值
	 * @param request
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request){
		String userIdStr = request.getParameter("userId");
		if (userIdStr != null && !userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			WxUser userModel= userSer.selectByPrimaryKey(userId);
			 WxUserDto userDto=	userDtoFill(userModel);
			 request.setAttribute("user", userDto);
		}
		return "/wxPay/pay";
	}
	
	/**
	 * 支付失败
	 * @param request
	 * @return
	 */
	@RequestMapping("/errorpay")
	public String error(HttpServletRequest request){
		return "/wxPay/errorpay";
	}
	
	private List<WxPayDto> wxPayFill(List<WxPay> wxpays)
	{
		List<WxPayDto> wxPayDtos=new ArrayList<WxPayDto>();
		if(wxpays!=null){
			for (WxPay pay : wxpays) {
				WxPayDto payDto=new WxPayDto();
				payDto.setOrdernum(pay.getOrdernum());
				payDto.setPaytype(pay.getPaytype());
				payDto.setPaytypeName(EnumType.PayType.get(pay.getPaytype()));
				payDto.setAmount(pay.getAmount());
				payDto.setCount(pay.getCount());
				payDto.setCreatetime(DateUtil.date2Str(pay.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
				payDto.setUnit(pay.getUnit());
				payDto.setUnitName(EnumType.PayUnit.get(pay.getUnit()));
				wxPayDtos.add(payDto);
			}
		}
		return wxPayDtos;
	}
	
	
 	private WxUserDto userDtoFill(WxUser user){
		WxUserDto userDto=new WxUserDto();
		if(user!=null){
			userDto.setId(user.getId());
			userDto.setAutopurchase(user.getAutopurchase());
			userDto.setCoin(user.getCoin());
			userDto.setCreatetime(DateUtil.date2Str(user.getCreatetime()));
			userDto.setDeadline(DateUtil.date2Str(user.getDeadline()));
			userDto.setDevicecode(user.getDevicecode());
			userDto.setHeadericon(user.getHeadericon());
			userDto.setLastactivetime(DateUtil.date2Str(user.getLastactivetime()));
			userDto.setMobile(user.getMobile());
			userDto.setName(user.getName());
			userDto.setPwd(user.getPwd());
			userDto.setSignature(user.getSignature());
			userDto.setStatus(user.getStatus());
			userDto.setStatusStr(EnumType.UserStatus.get(user.getStatus()));
			userDto.setToken(user.getToken());
			userDto.setUpdatetime(DateUtil.date2Str(user.getUpdatetime()));
			userDto.setUsertype(user.getUsertype());
			userDto.setUsertypestr(EnumType.UserType.get(user.getUsertype()));
			userDto.setOpenid(user.getOpenid());
			userDto.setWeixintoken(user.getWeixintoken());
			
		}
		return userDto;
	}

}
