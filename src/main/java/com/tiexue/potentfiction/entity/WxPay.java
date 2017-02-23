package com.tiexue.potentfiction.entity;

import java.io.Serializable;
import java.util.Date;

public class WxPay implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//充值Id
	private Integer id;
	//对应用户Id
	private Integer userId; 
	//充值类型
	private Integer payType; 
	//充值金额
	private Double amount;
	//小说币
	private Integer Count;   
	//充值时间
	private Date CreateTime;
	//单位
	private Integer Unit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getCount() {
		return Count;
	}
	public void setCount(Integer count) {
		Count = count;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Integer getUnit() {
		return Unit;
	}
	public void setUnit(Integer unit) {
		Unit = unit;
	}

	
	
	
}
