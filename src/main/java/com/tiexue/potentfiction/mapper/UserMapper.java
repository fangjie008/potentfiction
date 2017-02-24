package com.tiexue.potentfiction.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tiexue.potentfiction.entity.User;

@Repository
public interface UserMapper {

	int deleteByPrimaryKey(String id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> getAll();
}
