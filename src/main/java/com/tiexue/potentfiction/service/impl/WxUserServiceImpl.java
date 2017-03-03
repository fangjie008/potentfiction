package com.tiexue.potentfiction.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.entity.WxConsume;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.mapper.WxUserMapper;
import com.tiexue.potentfiction.service.IWxUserService;
@Service("wxUserService")
public class WxUserServiceImpl implements IWxUserService{

	@Resource
	WxUserMapper userMapper;
	@Resource
	WxConsumeServiceImpl consSerImpl;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxUser record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(WxUser record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public WxUser selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxUser record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(WxUser record) {
		return userMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(WxUser record) {
		return userMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateCoin(WxUser record, WxConsume cons) {
		int resUpdate= userMapper.updateCoin(record.getId(),record.getCoin(), record.getUpdatetime());
		consSerImpl.insert(cons);
		return resUpdate;
	}

	
	
	
}
