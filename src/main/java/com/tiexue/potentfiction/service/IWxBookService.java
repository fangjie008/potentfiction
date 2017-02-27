package com.tiexue.potentfiction.service;

import com.tiexue.potentfiction.entity.WxBook;

public interface IWxBookService {
	/*
	 * 根据Id查询书籍信息数据
	 * **/
	WxBook getModel(int id);
}
