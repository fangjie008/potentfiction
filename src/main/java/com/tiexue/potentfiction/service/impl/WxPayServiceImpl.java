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
	public List<WxPay> getListByPage(int userId,int beginRow,int pageSize) {
		return this.wxPayMapper.getListByPage(userId,beginRow,pageSize);
	}

	@Override
	public Integer getCountByUserId(int userId) {
		return this.wxPayMapper.getCountByUserId(userId);
	}

	@Override
	public int deleteByPrimaryKey(String ordernum) {
		return wxPayMapper.deleteByPrimaryKey(ordernum);
	}

	@Override
	public int insert(WxPay record) {
		return wxPayMapper.insert(record);
	}

	@Override
	public int insertSelective(WxPay record) {
		return wxPayMapper.insertSelective(record);
	}

	@Override
	public WxPay selectByPrimaryKey(String ordernum) {
		return wxPayMapper.selectByPrimaryKey(ordernum);
	}

	@Override
	public int updateByPrimaryKeySelective(WxPay record) {
		return wxPayMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(WxPay record) {
		return wxPayMapper.updateByPrimaryKey(record);
	}





}
