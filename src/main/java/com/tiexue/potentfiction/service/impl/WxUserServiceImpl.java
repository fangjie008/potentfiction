package com.tiexue.potentfiction.service.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiexue.potentfiction.controller.WxBookController;
import com.tiexue.potentfiction.entity.WxConsume;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.mapper.WxUserMapper;
import com.tiexue.potentfiction.service.IWxUserService;
@Service("wxUserService")
public class WxUserServiceImpl implements IWxUserService{
	// 日志
	private Logger logger = Logger.getLogger(WxUserServiceImpl.class);
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

	/**
	 * 更新操作 
	 */
	@Override
	@Transactional
	public boolean updateCoin(WxUser record, WxConsume cons) {
		boolean resUpdate=false;
		try {
			userMapper.updateByPrimaryKey(record);
		    consSerImpl.insert(cons);
		    resUpdate=true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			//必须抛出异常 否则事务就不能正常执行
			throw e;
		}
		return resUpdate;
	}

	
	
	
}
