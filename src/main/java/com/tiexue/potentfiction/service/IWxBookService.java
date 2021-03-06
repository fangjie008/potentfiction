package com.tiexue.potentfiction.service;

import java.util.List;


import com.tiexue.potentfiction.entity.WxBook;

public interface IWxBookService {
	
    int deleteByPrimaryKey(Integer id);   
    int insert(WxBook record);

    int insertSelective(WxBook record);

    WxBook selectByPrimaryKey(Integer id);
    
    List<WxBook> getList(String strWhere,String orderStr,Integer size);

    int updateByPrimaryKeySelective(WxBook record);
    int updateByPrimaryKey(WxBook record);
}
