package com.tiexue.potentfiction.entity;

public  class EnumType {


  /**
   *表WXbook的Status字段的枚举
   */
  public enum BookStatus
  {
	    NOTONLINE("未上线",1),
	    UPDATE("连载中", 2),
	    FINISH("完结", 3);
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
  


}
