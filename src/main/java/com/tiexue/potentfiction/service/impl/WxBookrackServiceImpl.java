package com.tiexue.potentfiction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.dto.WxBookrackDto;
import com.tiexue.potentfiction.entity.WxBookrack;
import com.tiexue.potentfiction.mapper.WxBookrackMapper;
import com.tiexue.potentfiction.service.IWxBookrackService;

@Service("wxBookrack")
public class WxBookrackServiceImpl implements IWxBookrackService {

	@Resource
	WxBookrackMapper bookrackMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookrackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxBookrack record) {
		return bookrackMapper.insert(record);
	}

	@Override
	public int insertSelective(WxBookrack record) {
		return bookrackMapper.insertSelective(record);
	}

	@Override
	public WxBookrack selectByPrimaryKey(Integer id) {
		return bookrackMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxBookrack record) {
		return bookrackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxBookrack record) {
		return bookrackMapper.updateByPrimaryKey(record);
	}

	@Override
	public WxBookrack getModelByBookId(Integer bookId) {
		return bookrackMapper.getModelByBookId(bookId);
	}


	@Override
	public List<WxBookrackDto> getListByUserId(Integer userId, Integer size) {
		return bookrackMapper.getListByUserId(userId, size);
	}

}
