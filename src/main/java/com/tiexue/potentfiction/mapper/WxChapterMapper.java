package com.tiexue.potentfiction.mapper;

import com.tiexue.potentfiction.entity.WxChapter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxChapterMapper {
    @Delete({
        "delete from wxchapter",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxchapter (Id, BookId, ",
        "Intro, SortOrder, ",
        "Title, ChapterType, ",
        "Pirce, Status, ContentLen, ",
        "CreateTime, UpdateTime, ",
        "Remark)",
        "values (#{id,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{intro,jdbcType=VARCHAR}, #{sortorder,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{chaptertype,jdbcType=INTEGER}, ",
        "#{pirce,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, #{contentlen,jdbcType=INTEGER}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{updatetime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(WxChapter record);

    int insertSelective(WxChapter record);

    @Select({
        "select",
        "Id, BookId, Intro, SortOrder, Title, ChapterType, Pirce, Status, ContentLen, ",
        "CreateTime, UpdateTime, Remark",
        "from wxchapter",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxChapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxChapter record);

    @Update({
        "update wxchapter",
        "set BookId = #{bookid,jdbcType=INTEGER},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "SortOrder = #{sortorder,jdbcType=INTEGER},",
          "Title = #{title,jdbcType=VARCHAR},",
          "ChapterType = #{chaptertype,jdbcType=INTEGER},",
          "Pirce = #{pirce,jdbcType=INTEGER},",
          "Status = #{status,jdbcType=INTEGER},",
          "ContentLen = #{contentlen,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxChapter record);
}