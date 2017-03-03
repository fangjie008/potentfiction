package com.tiexue.potentfiction.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.potentfiction.dto.ResultMsg;
import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxBook;
import com.tiexue.potentfiction.entity.WxChapter;
import com.tiexue.potentfiction.entity.WxConsume;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.service.IUserConsService;

/**
 * 用户消费操作
 * @author 
 *
 */
@Service("userCons")
public class UserConsServiceImpl implements IUserConsService {

	@Resource
	WxUserServiceImpl userSerImpl;
	@Resource
	WxConsumeServiceImpl consSerImpl;
	@Resource
	WxBookServiceImpl bookSerImpl;
	@Resource
	WxChapterServiceImpl charpterSerImpl;
	
	public ResultMsg consDeal(int userId, int bookId, String bookName, WxChapter chapterModel) {
		ResultMsg resultMsg = new ResultMsg();
		// 用户信息
		WxUser userModel = userSerImpl.selectByPrimaryKey(userId);
		if (userModel == null) {
			resultMsg.setStatus(false);
			resultMsg.setMsg("跳转到登录页面");
			resultMsg.setNum(EnumType.ResultNum_Login);
			return resultMsg;
		}
		int count = consSerImpl.judgeConsume(userId, chapterModel.getId());
		// 未消费
		if (count > 0) {
			resultMsg.setStatus(true);
			resultMsg.setMsg("已付费");
			return resultMsg;
		}
		// 是否自动付费
		if (!getAutoPurchase(userModel, bookId)) {
			resultMsg.setStatus(false);
			resultMsg.setMsg("跳转到扣费页面");
			resultMsg.setNum(EnumType.ResultNum_Cons);
			return resultMsg;
		}
		// 包年用户
		if (userModel.getDeadline().getTime() > new Date().getTime()) {
			resultMsg.setStatus(true);
			resultMsg.setMsg("包年用户");
			return resultMsg;
		}
		// 余额不足
		if (userModel.getCoin() < chapterModel.getPirce()) {
			resultMsg.setStatus(false);
			resultMsg.setMsg("跳转到充值页面");
			resultMsg.setNum(EnumType.ResultNum_Pay);
			return resultMsg;
		}
		else {
			// 添加消费信息
			WxConsume cons = new WxConsume();
			cons.setBookid(bookId);
			cons.setBookname(bookName);
			cons.setCharpterid(chapterModel.getId());
			cons.setCharptertitle(chapterModel.getTitle());
			cons.setCostcoin(chapterModel.getPirce());
			cons.setUserid(userId);
			cons.setCreatetime(new Date());
			// 更新小说币
			userModel.setCoin(userModel.getCoin() - chapterModel.getPirce());
			userModel.setUpdatetime(new Date());
			// 更新小说币并增加记录
			int dealRes= userSerImpl.updateCoin(userModel, cons);
			if(dealRes>0){
			resultMsg.setStatus(true);
			resultMsg.setMsg("支付成功");
			}
			else{
				resultMsg.setStatus(false);
				resultMsg.setMsg("自动扣款失败跳转到扣费页面");
				resultMsg.setNum(EnumType.ResultNum_Cons);
			}
		}
		return resultMsg;
	}
	
	
	public boolean consumeRecord(int userId, int bookId, int chapterId,boolean autoPay) {
		// 用户信息
		WxUser userModel = userSerImpl.selectByPrimaryKey(userId);
		// 获取图书信息
		WxBook book = bookSerImpl.selectByPrimaryKey(bookId);
		// 章节数据
		WxChapter chapterModel = charpterSerImpl.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
		int count = consSerImpl.judgeConsume(userId, chapterModel.getId());
		// 未消费
		if (count > 0) {
			return true;
		}
		WxConsume cons = new WxConsume();
		cons.setBookid(bookId);
		cons.setBookname(book.getName());
		cons.setCharpterid(chapterModel.getId());
		cons.setCharptertitle(chapterModel.getTitle());
		cons.setCostcoin(chapterModel.getPirce());
		cons.setUserid(userId);
		cons.setCreatetime(new Date());
		// 更新小说币
		userModel.setCoin(userModel.getCoin() - chapterModel.getPirce());
		userModel.setUpdatetime(new Date());
		//自动付费
		if(autoPay)
		{
			String bookIds=userModel.getAutopurchase();
			if(bookIds!=null&&!bookIds.isEmpty()){
				bookIds+=','+bookId;
			}else{
				bookIds=bookId+"";
			}
			userModel.setAutopurchase(bookIds);
		}
		// 更新小说币并增加记录
		int dealRes = userSerImpl.updateCoin(userModel, cons);
		if (dealRes > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 验证用户阅读的小说是否自动付费
	 * @param userId
	 * @param bookId
	 * @return
	 */

	private Boolean getAutoPurchase(WxUser userModel,Integer bookId){
		Boolean result=false;
		if(userModel!=null&&!userModel.getAutopurchase().isEmpty()){
			String[] bookIds=userModel.getAutopurchase().split(",");
			if(bookIds!=null&&bookIds.length>0){
				for (String id : bookIds) {
					if(id.equals(bookId.toString())){
						result=true;
						break;
					}
				}
			}
		}
		return result;
	}

}
