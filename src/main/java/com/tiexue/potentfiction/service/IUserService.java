package com.tiexue.potentfiction.service;

import java.util.List;

import com.tiexue.potentfiction.entity.User;

/**
 * Created by LittleXuan on 2015/10/17.
 */
public interface IUserService {
	List<User> getAll();

	User selectByPrimaryKey(String id);

	int insert(User muser);

	int update(User muser);

	int delete(String id);
}