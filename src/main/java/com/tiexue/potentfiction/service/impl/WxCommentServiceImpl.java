package com.tiexue.potentfiction.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.entity.WxComment;
import com.tiexue.potentfiction.mapper.WxCommentMapper;
import com.tiexue.potentfiction.service.IWxCommentService;

@Service("wxCommnet")
public class WxCommentServiceImpl implements IWxCommentService {

	@Resource
	WxCommentMapper comment;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return comment.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxComment record) {
		return comment.insert(record);
	}

	@Override
	public int insertSelective(WxComment record) {
		return comment.insertSelective(record);
	}

	@Override
	public WxComment selectByPrimaryKey(Integer id) {
		return comment.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxComment record) {
		return comment.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(WxComment record) {
		return comment.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(WxComment record) {
		return comment.updateByPrimaryKey(record);
	}



}
