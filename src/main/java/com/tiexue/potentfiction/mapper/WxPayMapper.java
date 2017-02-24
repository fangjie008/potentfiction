package com.tiexue.potentfiction.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tiexue.potentfiction.entity.WxPay;

@Repository
public interface WxPayMapper {
	//新增数据
	Boolean insert(WxPay wxPay);
	//更新数据
	Boolean update(WxPay wxPay);
	//根据id获取数据
	WxPay getModel(int id);
	//根据userId获取支付记录
	List<WxPay> getListByPage(int userId,int pageNo,int pageSize);
	//获取总数
	Integer getCountByUserId(int userId);
}
