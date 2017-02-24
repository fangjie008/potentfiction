package com.tiexue.potentfiction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.mapper.WxPayMapper;
import com.tiexue.potentfiction.service.IWxPayService;

@Service("wxPayService")
public class WxPayServiceImpl implements IWxPayService {

	@Resource
	private WxPayMapper wxPayMapper;
	
	@Override
	public Boolean insert(WxPay wxPay) {
		return this.wxPayMapper.insert(wxPay);
	}

	@Override
	public Boolean update(WxPay wxPay) {
		return this.wxPayMapper.update(wxPay);
	}

	@Override
	public WxPay getModel(int id) {
		return this.wxPayMapper.getModel(id);
	}

	@Override
	public List<WxPay> getListByPage(@Param("userId")int userId,@Param("beginRow")int beginRow,@Param("pageSize")int pageSize) {
		return this.wxPayMapper.getListByPage(userId,beginRow,pageSize);
	}

	@Override
	public Integer getCountByUserId(@Param("userId")int userId) {
		return this.wxPayMapper.getCountByUserId(userId);
	}

}
