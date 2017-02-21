package com.tiexue.potentfiction.entity;

public class EnumType {

	// 小说状态
	public enum BookStatus {
		NOTONLINE("未上线", 0), UPDATE("连载中", 1), FINISH("完结", 2),DELETE("删除",3);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private BookStatus(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (BookStatus c : BookStatus.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}

	// 小说标识
	public enum BookMark {
		NORMAL("正常", 0),RECOMMENT("推荐",1),HOT("热门",2),ESSENCE("精华",3);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private BookMark(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
		public static String getName(int index)
		{
			for (BookMark c : BookMark.values()) {
				if(c.getIndex()==index){
					return c.name;
				}
			}
			return "";
		}

	}
	
	//章节类型
	public enum ChapterType{
		FREE("免费",0),PAY("付费",1);
		private String name;
		private int index;
		private ChapterType(String name,int index)
		{
			this.name=name;
			this.index=index;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public static String getName(int index)
		{
			for(ChapterType c :ChapterType.values()){
				if(c.getIndex()==index){
					return c.name;
				}
			}
			return null;
		}
	}
	
	//章节状态
	public enum ChapterStatus{
		
		NOTONLINE("未上线",0),DELETE("删除",1),ONLINE("上线",2);
		
		private String name;
		private int index;
		
		private ChapterStatus(String name,int index){
			this.name=name;
			this.index=index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
		public static String getName(int index){
			for(ChapterStatus c:ChapterStatus.values()){
				if(c.getIndex()==index){
					return c.name;
				}
			}
			return null;
		}
	}
	
	

	//评论状态
	public enum CommentStatus{
		
		NORMAL("正常",0),DELETE("删除",1);
		
		private String name;
		private int index;
		
		private CommentStatus(String name,int index){
			this.name=name;
			this.index=index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
		public static String getName(int index){
			for(CommentStatus c:CommentStatus.values()){
				if(c.getIndex()==index){
					return c.name;
				}
			}
			return null;
		}
	}
	
	// 充值类型
	public enum PayType {

		NOVELCURR("小说币", 0), TIME("包月(年)", 1);

		private String name;
		private int index;

		private PayType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public static String getName(int index) {
			for (PayType c : PayType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
	}
		
	//包年(月)充值的单位
	public enum PayUnit {

		YEAR("", 0), MONTH("月", 1),QUARTER("季度",2);

		private String name;
		private int index;

		private PayUnit(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public static String getName(int index) {
			for (PayUnit c : PayUnit.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
	}
	
	// 用户类型
	public enum UserType {

		NORMAL("普通用户", 0), VIP("VIP用户", 1);
		private String name;
		private int index;

		private UserType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public static String getName(int index) {
			for (UserType c : UserType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
	}
			
	// 用户类型
	public enum UserStatus {

		NORMAL("正常", 0), DELETE("删除", 1);
		private String name;
		private int index;

		private UserStatus(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public static String getName(int index) {
			for (UserStatus c : UserStatus.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
	}
			
}
