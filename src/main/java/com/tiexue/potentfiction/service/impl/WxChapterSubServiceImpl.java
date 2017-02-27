package com.tiexue.potentfiction.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.entity.WxChapterSub;
import com.tiexue.potentfiction.mapper.WxChapterSubMapper;
import com.tiexue.potentfiction.service.IWxChapterSubService;

@Service("wxChapterSubService")
public class WxChapterSubServiceImpl implements IWxChapterSubService {
	@Resource
	private WxChapterSubMapper wxChapterSubMapper;
	
	@Override
	public int insert(WxChapterSub record) {
		return wxChapterSubMapper.insert(record);
	}

	@Override
	public int insertSelective(WxChapterSub record) {
		return wxChapterSubMapper.insertSelective(record);
	}

	@Override
	public int updateByBookId(WxChapterSub record) {
		return wxChapterSubMapper.updateByBookId(record);
	}

	@Override
	public WxChapterSub selectByChapterId(Integer id) {
		return wxChapterSubMapper.selectByChapterId(id);
	}

}
