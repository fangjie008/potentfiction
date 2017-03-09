package com.tiexue.potentfiction.mapper;

import com.tiexue.potentfiction.entity.WxUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxUserMapper {
    @Delete({
        "delete from wxuser",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxuser (Id, Name, ",
        "HeaderIcon, Signature, ",
        "Pwd, Sex, City, ",
        "Province, UserType, ",
        "Coin, Deadline, ",
        "DeviceCode, Status, ",
        "Mobile, OpenId, ",
        "WeixinToken, Token, ",
        "LastActiveTime, CreateTime, ",
        "UpdateTime, AutoPurchase)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{headericon,jdbcType=VARCHAR}, #{signature,jdbcType=VARCHAR}, ",
        "#{pwd,jdbcType=VARCHAR}, #{sex,jdbcType=INTEGER}, #{city,jdbcType=VARCHAR}, ",
        "#{province,jdbcType=VARCHAR}, #{usertype,jdbcType=INTEGER}, ",
        "#{coin,jdbcType=INTEGER}, #{deadline,jdbcType=TIMESTAMP}, ",
        "#{devicecode,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{mobile,jdbcType=VARCHAR}, #{openid,jdbcType=VARCHAR}, ",
        "#{weixintoken,jdbcType=VARCHAR}, #{token,jdbcType=VARCHAR}, ",
        "#{lastactivetime,jdbcType=TIMESTAMP}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP}, #{autopurchase,jdbcType=LONGVARCHAR})"
    })
    int insert(WxUser record);

    int insertSelective(WxUser record);

    @Select({
        "select",
        "Id, Name, HeaderIcon, Signature, Pwd, Sex, City, Province, UserType, Coin, Deadline, ",
        "DeviceCode, Status, Mobile, OpenId, WeixinToken, Token, LastActiveTime, CreateTime, ",
        "UpdateTime, AutoPurchase",
        "from wxuser",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);

    @Update({
        "update wxuser",
        "set Name = #{name,jdbcType=VARCHAR},",
          "HeaderIcon = #{headericon,jdbcType=VARCHAR},",
          "Signature = #{signature,jdbcType=VARCHAR},",
          "Pwd = #{pwd,jdbcType=VARCHAR},",
          "Sex = #{sex,jdbcType=INTEGER},",
          "City = #{city,jdbcType=VARCHAR},",
          "Province = #{province,jdbcType=VARCHAR},",
          "UserType = #{usertype,jdbcType=INTEGER},",
          "Coin = #{coin,jdbcType=INTEGER},",
          "Deadline = #{deadline,jdbcType=TIMESTAMP},",
          "DeviceCode = #{devicecode,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=INTEGER},",
          "Mobile = #{mobile,jdbcType=VARCHAR},",
          "OpenId = #{openid,jdbcType=VARCHAR},",
          "WeixinToken = #{weixintoken,jdbcType=VARCHAR},",
          "Token = #{token,jdbcType=VARCHAR},",
          "LastActiveTime = #{lastactivetime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "AutoPurchase = #{autopurchase,jdbcType=LONGVARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(WxUser record);

    @Update({
        "update wxuser",
        "set Name = #{name,jdbcType=VARCHAR},",
          "HeaderIcon = #{headericon,jdbcType=VARCHAR},",
          "Signature = #{signature,jdbcType=VARCHAR},",
          "Pwd = #{pwd,jdbcType=VARCHAR},",
          "Sex = #{sex,jdbcType=INTEGER},",
          "City = #{city,jdbcType=VARCHAR},",
          "Province = #{province,jdbcType=VARCHAR},",
          "UserType = #{usertype,jdbcType=INTEGER},",
          "Coin = #{coin,jdbcType=INTEGER},",
          "Deadline = #{deadline,jdbcType=TIMESTAMP},",
          "DeviceCode = #{devicecode,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=INTEGER},",
          "Mobile = #{mobile,jdbcType=VARCHAR},",
          "OpenId = #{openid,jdbcType=VARCHAR},",
          "WeixinToken = #{weixintoken,jdbcType=VARCHAR},",
          "Token = #{token,jdbcType=VARCHAR},",
          "LastActiveTime = #{lastactivetime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxUser record);
}