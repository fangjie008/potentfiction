package com.tiexue.potentfiction.service;



import com.tiexue.potentfiction.entity.WxChapterSub;

public interface IWxChapterSubService {

    int insert(WxChapterSub record);

    int insertSelective(WxChapterSub record);
    
    int updateByBookId(WxChapterSub record);

    //获取章节内容
    WxChapterSub selectByChapterId(Integer id);
}
