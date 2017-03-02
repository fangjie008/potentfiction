package com.tiexue.potentfiction.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.potentfiction.dto.WxUserDto;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.service.IWxUserService;
import com.tiexue.potentfiction.util.DateUtil;

@Controller
@RequestMapping("wxUser")
public class WxUserController {

	@Resource
	IWxUserService userSer;
	@RequestMapping("/content")
	public String getModel(HttpServletRequest request,Integer userId){
		WxUser userModel= userSer.selectByPrimaryKey(userId);
		WxUserDto userDtoModel= userDtoFill(userModel);
		request.setAttribute("user", userDtoModel);
		return "wxUser/index";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		return "wxUser/login";
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
			userDto.setWeixinid(user.getWeixinid());
			userDto.setWeixintoken(user.getWeixinid());
			
		}
		return userDto;
	}
	
}
