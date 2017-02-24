package com.tiexue.potentfiction.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiexue.potentfiction.entity.User;
import com.tiexue.potentfiction.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService muserService;

	@RequestMapping(value = "/listUser")
	public String listUser(HttpServletRequest request) {

		List<User> list = muserService.getAll();
		request.setAttribute("userlist", list);
		return "listUser";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(User muser) {

		String id = UUID.randomUUID().toString();
		muser.setId(id);
		muserService.insert(muser);
		return "redirect:/user/listUser";
	}

	@RequestMapping(value = "/deleteUser")
	public String deleteUser(String id) {

		muserService.delete(id);
		return "redirect:/user/listUser";
	}

	@RequestMapping(value = "/updateUserUI")
	public String updateUserUI(String id, HttpServletRequest request) {

		User muser = muserService.selectByPrimaryKey(id);
		request.setAttribute("user", muser);
		return "updateUser";
	}

	@RequestMapping(value = "/updateUser")
	public String updateUser(User muser) {

		muserService.update(muser);
		return "redirect:/user/listUser";
	}
}

