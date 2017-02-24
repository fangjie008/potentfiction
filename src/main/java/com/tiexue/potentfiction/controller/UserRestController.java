package com.tiexue.potentfiction.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiexue.potentfiction.entity.User;
import com.tiexue.potentfiction.service.IUserService;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User toIndex(@PathVariable("id") String id) {
		if (StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException("id不能为空");
		}
		User user = this.userService.selectByPrimaryKey(id);
		return user;
	}

	@RequestMapping(value = "/selectAllUser", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Integer addUser(@RequestBody User user) {
		if (ObjectUtils.isEmpty(user)) {
			return 0;
		}
		return userService.insert(user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Integer delete(String id) {
		if (id == null) {
			return 0;
		}
		return userService.delete(id);
	}
}
