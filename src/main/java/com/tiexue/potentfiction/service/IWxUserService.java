package com.tiexue.potentfiction.service;





import com.tiexue.potentfiction.entity.WxConsume;
import com.tiexue.potentfiction.entity.WxUser;

public interface IWxUserService {

    int deleteByPrimaryKey(Integer id);
    
    int insert(WxUser record);

    int insertSelective(WxUser record);


    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);


    int updateByPrimaryKeyWithBLOBs(WxUser record);

    int updateByPrimaryKey(WxUser record);
    
    /**
     * 更新小说币 添加消费记录
     * @param id
     * @param coin
     * @param updatetime
     * @return
     */
    boolean updateCoin(WxUser record,WxConsume cons);
    
    
 
}
