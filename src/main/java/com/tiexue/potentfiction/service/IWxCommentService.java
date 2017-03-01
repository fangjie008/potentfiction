package com.tiexue.potentfiction.service;


import com.tiexue.potentfiction.entity.WxComment;

public interface IWxCommentService {
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(WxComment record);

	    int insertSelective(WxComment record);

	    WxComment selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(WxComment record);

	    int updateByPrimaryKeyWithBLOBs(WxComment record);

	    int updateByPrimaryKey(WxComment record);
}
