package com.tiexue.potentfiction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.entity.User;
import com.tiexue.potentfiction.mapper.UserMapper;
import com.tiexue.potentfiction.service.IUserService;



/**
 * Created by LittleXuan on 2015/10/17.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper muserMapper;

	public UserMapper getMuserMapper() {
		return muserMapper;
	}

	@Autowired
	public void setMuserMapper(UserMapper muserMapper) {
		this.muserMapper = muserMapper;
	}

	@Override
	public List<User> getAll() {

		return muserMapper.getAll();
	}

	@Override
	public int insert(User muser) {

		return muserMapper.insert(muser);
	}

	@Override
	public int update(User muser) {

		return muserMapper.updateByPrimaryKey(muser);
	}

	@Override
	public int delete(String id) {

		return muserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User selectByPrimaryKey(String id) {

		return muserMapper.selectByPrimaryKey(id);
	}
}