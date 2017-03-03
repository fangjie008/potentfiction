package com.tiexue.potentfiction.service;

import com.tiexue.potentfiction.dto.ResultMsg;
import com.tiexue.potentfiction.entity.WxChapter;

public interface IUserConsService {
	
	/**
	 * 处理章节消费
	 * @param userId
	 * @param bookId
	 * @param bookName
	 * @param chapterModel
	 * @return 
	 */
	ResultMsg consDeal(int userId, int bookId, String bookName, WxChapter chapterModel);
	/**
	 * 直接付费
	 * @param userId
	 * @param bookId
	 * @param chapterId
	 * @return
	 */
    boolean consumeRecord(int userId, int bookId, int chapterId,boolean autoPay);
}
