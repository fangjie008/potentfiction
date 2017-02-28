package com.tiexue.potentfiction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tiexue.potentfiction.entity.WxBook;

@Repository
public interface WxBookMapper {
	// 查询单条数据
	WxBook getModel(int id);

	@Select({" select * from wxbook where Status in (#{status}) order by #{orderStr} desc"})
	List<WxBook> getList(@Param("status")String status,@Param("orderStr")String orderStr);
}
