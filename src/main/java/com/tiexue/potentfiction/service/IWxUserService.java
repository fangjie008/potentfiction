package com.tiexue.potentfiction.service;



import com.tiexue.potentfiction.entity.WxUser;

public interface IWxUserService {

    int deleteByPrimaryKey(Integer id);
    
    int insert(WxUser record);

    int insertSelective(WxUser record);


    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);


    int updateByPrimaryKeyWithBLOBs(WxUser record);

    int updateByPrimaryKey(WxUser record);
}
