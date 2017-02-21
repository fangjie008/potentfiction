package com.tiexue.potentfiction.test;


import org.junit.Test;

import com.tiexue.potentfiction.entity.EnumType;



public class EnumTypeTest {

	@Test
	public void TestEnum()
	{
	    System.out.println("小说状态");
		for (EnumType.BookStatus item : EnumType.BookStatus.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("小说标识");
		for (EnumType.BookMark item : EnumType.BookMark.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("章节状态");
		for (EnumType.ChapterStatus item : EnumType.ChapterStatus.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("章节类型");
		for (EnumType.ChapterType item : EnumType.ChapterType.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("评论状态");
		for (EnumType.CommentStatus item : EnumType.CommentStatus.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("支付类型");
		for (EnumType.PayType item : EnumType.PayType.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("支付单位");
		for (EnumType.PayUnit item : EnumType.PayUnit.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("用户状态");
		for (EnumType.UserStatus item : EnumType.UserStatus.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
		System.out.println("用户类型");
		for (EnumType.UserType item : EnumType.UserType.values()) {
			System.out.println("index:"+item.getIndex()+".  name:"+item.getName());
		}
	}

}
