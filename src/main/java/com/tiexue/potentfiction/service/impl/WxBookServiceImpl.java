package com.tiexue.potentfiction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.mapper.WxBookMapper;
import com.tiexue.potentfiction.service.IWxBookService;

@Service("wxBookService")
public class WxBookServiceImpl implements IWxBookService {

	@Resource
	private WxBookMapper wxBookMapper;
	

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.wxBookMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxBook record) {
		return this.wxBookMapper.insert(record);
	}

	@Override
	public int insertSelective(WxBook record) {
		return this.insertSelective(record);
	}

	@Override
	public WxBook selectByPrimaryKey(Integer id) {
		return this.selectByPrimaryKey(id);
	}
	@Override
	public List<WxBook> getList(String status, String orderStr) {
		return this.wxBookMapper.getList(status, orderStr);
	}
	@Override
	public int updateByPrimaryKeySelective(WxBook record) {
		return this.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(WxBook record) {
		return this.updateByPrimaryKey(record);
	}

}
