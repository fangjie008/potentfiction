package com.tiexue.potentfiction.entity;

public class WxConstants {
	// 微信验证 token
	public static final String WxToken = "txmobilekey";

	// 微信网页授权state
	public static final String WxOauthState = "ok";

	// 微信公众号Appid
	public static final String WxAppId = "wxbfa39e44977aead2";

	// 微信公众号AppKey
	public static final String WxAppSecret = "d37aa936620b14325b151f74d40f7211";

	// 微信网页授权回调地址
	public static final String WxRedirectUrl = "http://wx.top657.cn/wxUser/wxoauthcallback";

	// 微信网页授权语言编码
	public static final String WxSnsLang = "zh_CN";

	// 微信商户支付相关
	public static final String WxMch_Id = "";

	// 微信商户支付秘钥
	public static final String WxMch_Key = "";

	// 微信商户支付后回调url
	public static final String WxMch_NotifyUrl = "http://wx.top657.cn/wxPay/wxpaycallback";

	// 签名方式
	public static final String WxMch_SignType = "MD5";

	// 交易发起方式
	public static final String WxMch_TradeType = "JSAPI";

}
