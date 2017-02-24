package com.tiexue.potentfiction.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tiexue.potentfiction.entity.WxBook;

@Repository
public interface WxBookMapper {
	// 查询单条数据
	WxBook getModel(int id);

	// 查询列表数据
	List<WxBook> getList();
}
