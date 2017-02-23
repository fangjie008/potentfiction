package com.tiexue.potentfiction.service.impl;

import java.util.List;

import javax.annotation.Resource;

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
	public List<WxPay> getList(int userId) {
		return this.wxPayMapper.getList(userId);
	}

}
