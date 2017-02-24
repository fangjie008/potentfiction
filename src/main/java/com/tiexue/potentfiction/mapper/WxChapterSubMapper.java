package com.tiexue.potentfiction.mapper;

import com.tiexue.potentfiction.entity.WxChapterSub;
import org.apache.ibatis.annotations.Insert;

public interface WxChapterSubMapper {
    @Insert({
        "insert into wxchaptersub (Id, Content)",
        "values (#{id,jdbcType=INTEGER}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(WxChapterSub record);

    int insertSelective(WxChapterSub record);
}