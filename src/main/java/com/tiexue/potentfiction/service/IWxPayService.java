package com.tiexue.potentfiction.service;

import java.util.List;

import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.entity.WxUser;

import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.UnifiedorderResult;

public interface IWxPayService {
	
	    int deleteByPrimaryKey(String ordernum);
	    
	    int insert(WxPay record);

	    int insertSelective(WxPay record);

	    WxPay selectByPrimaryKey(String ordernum);

	    int updateByPrimaryKeySelective(WxPay record);

	    int updateByPrimaryKey(WxPay record);
	    
		//根据userId获取支付记录
		List<WxPay> getListByPage(int userId,int pageNo,int pageSize);
		//获取总数
		Integer getCountByUserId(int userId);
		
		//统一下单接口
		UnifiedorderResult createUnifiedorder(WxUser wxUser, int type, int money, int bookId, int chapterId, String remoteAdd);

		//处理支付成功请求
		boolean handlePayNotify(MchPayNotify payNotify);
}
