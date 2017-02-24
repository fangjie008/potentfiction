package com.tiexue.potentfiction.dto;

public class WxPayDto {

	    //充值Id
		private Integer id;
		//充值类型
		private Integer payType; 
		//充值类型名称
		private String payTypeName; 
		//充值金额
		private Double amount;
		//小说币
		private Integer Count;   
		//充值时间
		private String createTime;
		//单位
		private Integer unit;
		//单位对应名称
		private String unitName;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getPayType() {
			return payType;
		}
		public void setPayType(Integer payType) {
			this.payType = payType;
		}
		public String getPayTypeName() {
			return payTypeName;
		}
		public void setPayTypeName(String payTypeName) {
			this.payTypeName = payTypeName;
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
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public Integer getUnit() {
			return unit;
		}
		public void setUnit(Integer unit) {
			this.unit = unit;
		}
		public String getUnitName() {
			return unitName;
		}
		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

		
}
