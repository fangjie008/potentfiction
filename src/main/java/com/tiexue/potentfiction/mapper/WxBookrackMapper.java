package com.tiexue.potentfiction.mapper;

import com.tiexue.potentfiction.entity.WxBookrack;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxBookrackMapper {
    @Delete({
        "delete from wxbookrack",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxbookrack (Id, BookId, ",
        "BookName, ChapterId, ",
        "ChapterTitle, Location, ",
        "UserId, CreateTime)",
        "values (#{id,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{bookname,jdbcType=VARCHAR}, #{chapterid,jdbcType=INTEGER}, ",
        "#{chaptertitle,jdbcType=VARCHAR}, #{location,jdbcType=INTEGER}, ",
        "#{userid,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(WxBookrack record);

    int insertSelective(WxBookrack record);

    @Select({
        "select",
        "Id, BookId, BookName, ChapterId, ChapterTitle, Location, UserId, CreateTime",
        "from wxbookrack",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxBookrack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxBookrack record);

    @Update({
        "update wxbookrack",
        "set BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "ChapterId = #{chapterid,jdbcType=INTEGER},",
          "ChapterTitle = #{chaptertitle,jdbcType=VARCHAR},",
          "Location = #{location,jdbcType=INTEGER},",
          "UserId = #{userid,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxBookrack record);
}