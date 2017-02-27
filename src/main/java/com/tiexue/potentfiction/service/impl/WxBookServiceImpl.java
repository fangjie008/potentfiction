package com.tiexue.potentfiction.service.impl;

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
	public WxBook getModel(int id) {
		return this.wxBookMapper.getModel(id);
	}

}
