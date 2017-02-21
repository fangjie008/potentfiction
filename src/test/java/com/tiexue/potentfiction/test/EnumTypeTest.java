package com.tiexue.potentfiction.test;

import org.junit.Test;

import com.tiexue.potentfiction.entity.EnumType;

public class EnumTypeTest {

	@Test
	public void TestEnum()
	{
		
		System.out.println(EnumType.BookStatus.FINISH.getIndex());
		System.out.println(EnumType.BookStatus.FINISH.getName());
		for (EnumType.BookStatus item : EnumType.BookStatus.values()) {
			System.out.println(item.getIndex());
			System.out.println(item.getName());
		}
	}

}
