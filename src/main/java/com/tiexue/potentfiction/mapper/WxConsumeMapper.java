package com.tiexue.potentfiction.mapper;

import com.tiexue.potentfiction.entity.WxConsume;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxConsumeMapper {
    @Delete({
        "delete from wxconsume",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxconsume (Id, UserId, ",
        "CostCoin, BookId, ",
        "BookName, CharpterId, ",
        "CharpterTitle, CreateTime)",
        "values (#{id,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{costcoin,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{bookname,jdbcType=VARCHAR}, #{charpterid,jdbcType=INTEGER}, ",
        "#{charptertitle,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(WxConsume record);

    int insertSelective(WxConsume record);

    @Select({
        "select",
        "Id, UserId, CostCoin, BookId, BookName, CharpterId, CharpterTitle, CreateTime",
        "from wxconsume",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxConsume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxConsume record);

    @Update({
        "update wxconsume",
        "set UserId = #{userid,jdbcType=INTEGER},",
          "CostCoin = #{costcoin,jdbcType=INTEGER},",
          "BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "CharpterId = #{charpterid,jdbcType=INTEGER},",
          "CharpterTitle = #{charptertitle,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxConsume record);
}