package com.tiexue.potentfiction.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.tiexue.potentfiction.dto.PageUserDto;
import com.tiexue.potentfiction.service.IWxUserService;

public class WxUserTest {

	@Resource
	IWxUserService userSer;
	@Test
    public void getCookie()
    {
//		PageUserDto pageUser= userSer.getPageUserDto("40ED6BBB79F3C262FEE0759AA9E726DB");
//		System.out.println(pageUser.getId());
    }
}
