package com.tiexue.potentfiction.service;

import java.util.List;



import com.tiexue.potentfiction.entity.WxConsume;


public interface IWxConsumeService {

	    int deleteByPrimaryKey(Integer id);

	    int insert(WxConsume record);

	    int insertSelective(WxConsume record);

	    WxConsume selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(WxConsume record);

	    int updateByPrimaryKey(WxConsume record);
	    
	    List<WxConsume> getListByPage(int userId,int pageNo,int pageSize);

	  	Integer getCountByUserId(int userId);
}