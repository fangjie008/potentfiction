package com.tiexue.potentfiction.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiexue.potentfiction.entity.WxBook;

public interface IWxBookService {
	/*
	 * 根据Id查询书籍信息数据
	 * **/
	WxBook getModel(int id);
	
	//获取书籍信息列表
	List<WxBook> getList(String status,String orderStr);
}
