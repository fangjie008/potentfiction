package com.tiexue.potentfiction.service;



import java.util.List;

import com.tiexue.potentfiction.dto.WxBookrackDto;
import com.tiexue.potentfiction.entity.WxBookrack;

public interface IWxBookrackService {
	
	    int deleteByPrimaryKey(Integer id);
	    
	    int insert(WxBookrack record);

	    int insertSelective(WxBookrack record);
	    
	    WxBookrack selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(WxBookrack record);
	    
	    int updateByPrimaryKey(WxBookrack record);
	    //根据图书Id获取最新书架
	    WxBookrack getModelByBookId(Integer bookId);
	    /**
	     * 根据图书Id获取书架列表
	     * @param userId 用户Id
	     * @param size  数据数量
	     * @return
	     */
	    List<WxBookrackDto> getListByUserId(Integer userId,Integer size);
}