package com.tiexue.potentfiction.service;

import java.util.List;


import com.tiexue.potentfiction.entity.WxChapter;

public interface IWxChapterService  {
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(WxChapter record);

	    int insertSelective(WxChapter record);

	    WxChapter selectByPrimaryKey(Integer id,Integer status);
	    
	    List<WxChapter> selectByBookId(Integer bookId,Integer status,Integer pageNo,Integer pageSize);

	    int updateByPrimaryKeySelective(WxChapter record);

	    int updateByPrimaryKey(WxChapter record);
	    
	    int getCountByBookId(Integer bookId,Integer status);
	    
	    //获取上一章内容
	    WxChapter getPreChapter(Integer chapterId,Integer status);
	    
	    //获取下一章内容
	    WxChapter getNextChapter(Integer chapterId,Integer status);
}

