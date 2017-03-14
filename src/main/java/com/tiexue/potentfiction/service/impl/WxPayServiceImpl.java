package com.tiexue.potentfiction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiexue.potentfiction.entity.EnumType;
import com.tiexue.potentfiction.entity.WxConstants;
import com.tiexue.potentfiction.entity.WxPay;
import com.tiexue.potentfiction.entity.WxUser;
import com.tiexue.potentfiction.mapper.WxPayMapper;
import com.tiexue.potentfiction.mapper.WxUserMapper;
import com.tiexue.potentfiction.service.IWxPayService;
import com.tiexue.potentfiction.util.DateUtil;

import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;

@Service("wxPayService")
public class WxPayServiceImpl implements IWxPayService {
	private static Logger _logger=Logger.getLogger(WxPayServiceImpl.class);
	@Resource
	private WxPayMapper wxPayMapper;
	@Resource
	private WxUserMapper wxUserMapper;
	@Override
	public List<WxPay> getListByPage(int userId, int beginRow, int pageSize) {
		return this.wxPayMapper.getListByPage(userId, beginRow, pageSize);
	}

	@Override
	public Integer getCountByUserId(int userId) {
		return this.wxPayMapper.getCountByUserId(userId);
	}

	@Override
	public int deleteByPrimaryKey(String ordernum) {
		return wxPayMapper.deleteByPrimaryKey(ordernum);
	}

	@Override
	public int insert(WxPay record) {
		return wxPayMapper.insert(record);
	}

	@Override
	public int insertSelective(WxPay record) {
		return wxPayMapper.insertSelective(record);
	}

	@Override
	public WxPay selectByPrimaryKey(String ordernum) {
		return wxPayMapper.selectByPrimaryKey(ordernum);
	}

	@Override
	public int updateByPrimaryKeySelective(WxPay record) {
		return wxPayMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(WxPay record) {
		return wxPayMapper.updateByPrimaryKey(record);
	}

	
	
	/**
	 * 统一下单 生成订单
	 */
	@Override
	public UnifiedorderResult createUnifiedorder(WxUser wxUser, int type, double money,int coin, int bookId, int chapterId,
			String remoteAdd) {
		// 充值的币
		if(type==1)
		{
		  coin = (int)money * 100+coin;
		}

		// 生成我们的订单
		WxPay wxPay = new WxPay();
		wxPay.setAmount(money);
		wxPay.setBookid(bookId);
		wxPay.setChapterid(chapterId);
		wxPay.setCount(coin);
		wxPay.setOpenid(wxUser.getOpenid());
		wxPay.setOrdernum(createOrderNum());
		wxPay.setOrderstatus(EnumType.OrderStatus_WaitPay); // todo:枚举, 待支付
		wxPay.setPaytype(type);
		wxPay.setUserid(wxUser.getId());
		wxPay.setPaychannel(EnumType.PayChannel_Wx); // todo:枚举,目前微信
		wxPay.setUnit(EnumType.PayUnit_Month);
		wxPay.setWxordernum("");
		wxPay.setCreatetime(DateUtil.fomatCurrentDate("yyyy-MM-dd HH:mm:ss"));
		// todo:将订单数据插入数据库中
        wxPayMapper.insert(wxPay);
        
		// 组织统一下单参数
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(WxConstants.WxAppId);
		unifiedorder.setMch_id(WxConstants.WxMch_Id);
		unifiedorder.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
		unifiedorder.setSign_type(WxConstants.WxMch_SignType);
		unifiedorder.setBody("五彩科技-小说充值");
		unifiedorder.setOut_trade_no(wxPay.getOrdernum());
		unifiedorder.setTotal_fee((String.valueOf(money)));
		unifiedorder.setSpbill_create_ip(remoteAdd);
		unifiedorder.setNotify_url(WxConstants.WxMch_NotifyUrl);
		unifiedorder.setTrade_type(WxConstants.WxMch_TradeType);
		unifiedorder.setOpenid(wxUser.getOpenid());

		UnifiedorderResult orderResult = PayMchAPI.payUnifiedorder(unifiedorder, WxConstants.WxMch_Key);

		return orderResult;
	}

	/**
	 * 统一下单 生成订单
	 */
	@Override
	@Transactional
	public boolean handlePayNotify(MchPayNotify payNotify) {
		try {
			String return_code = payNotify.getReturn_code();
			// 如果返回编码错误,返回
			if (null == return_code || return_code.isEmpty() || !return_code.equalsIgnoreCase("SUCCESS")) {
				return false;
			}
			// 支付结果
			String result_code = payNotify.getResult_code();
			// 如果返回编码错误,返回
			if (null == result_code || result_code.isEmpty() || !result_code.equalsIgnoreCase("SUCCESS")) {
				return false;
			}
			// openid
			String openid = payNotify.getOpenid();
			//原订单号
			String out_trade_no = payNotify.getOut_trade_no();
			//微信订单号
			String wxOrderNo = payNotify.getTransaction_id();
			//todo:开始执行给用户添加小说币的方法,同时订单标记为处理成功状态--需要事务处理
			//获取订单信息
			WxPay wxPayRecord= selectByPrimaryKey(out_trade_no);
			wxPayRecord.setOrderstatus(EnumType.OrderStatus_Success);
			wxPayRecord.setWxordernum(wxOrderNo);
		    int updateCount=updateByPrimaryKey(wxPayRecord);
		    if(updateCount<=0)
		    	return false;
		    //获取用户信息
		    WxUser wxUser= wxUserMapper.getModelByOpenId(openid);
		    wxUser.setCoin(wxUser.getCoin()+wxPayRecord.getCount());
		    int updateUser= wxUserMapper.updateByPrimaryKey(wxUser);
		    //事务回滚
		    if(updateUser<=0)
		    	updateUser=1/updateUser;
			return true;
		} catch (Exception e) {
			_logger.error("支付出错pay error："+e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 统一下单 生成订单
	 */
	@Override
	@Transactional
	public boolean testHandlePayNotify(String openid,String out_trade_no,String wxOrderNo) {
		try {
			
			//todo:开始执行给用户添加小说币的方法,同时订单标记为处理成功状态--需要事务处理
			//获取订单信息
			WxPay wxPayRecord= selectByPrimaryKey(out_trade_no);
			wxPayRecord.setOrderstatus(EnumType.OrderStatus_Success);
			wxPayRecord.setWxordernum(wxOrderNo);
		    int updateCount=updateByPrimaryKey(wxPayRecord);
		    if(updateCount<=0)
		    	return false;
		    //获取用户信息
		    WxUser wxUser= wxUserMapper.getModelByOpenId(openid);
		    wxUser.setCoin(wxUser.getCoin()+wxPayRecord.getCount());
		    int updateUser= wxUserMapper.updateByPrimaryKey(wxUser);
		    //事务回滚
		    if(updateUser<=0)
		    	updateUser=1/updateUser;
			return true;
		} catch (Exception e) {
			_logger.error("支付出错pay error："+e.getMessage());
			throw e;
		}
	}


	/**
	 * 生成订单编号 当前时间+随机字符串
	 * @return
	 */
	private static String createOrderNum() {
		String dateStr = DateUtil.date2StrDetail(new Date());
		String randStr = UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 8);
		return dateStr + randStr;
	}

}
