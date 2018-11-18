package com.bzb.talentmarket.service;

import com.bzb.talentmarket.entity.TalentmarketMember;

import java.util.Map;

public interface WxService {
	
	/**
	 * 
	 * @Description:获取全局唯一的接口调用凭证
	 * @param appid 用户唯一凭证
	 * @param secret 用户唯一密钥
	 * @return String Access_Token，有效期为7200s。应该统一缓存此凭证，前期暂时调用接口获取。
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月11日 下午8:14:09
	 */
	String getAccessToken(String appid, String secret);
	
	String getAccessToken();
	
	/**
	 * 
	 * @Description:获取带参数的公众号二维码
	 * @param actionName 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，
	 * QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
	 * @param openid 推荐者的openid
	 * @param uid 经纪人的uid
	 * @return
	 * @author: bzb
	 * @time:2018年11月11日 下午8:43:08
	 */
	String createQrcode(String actionName, String openid, String uid);
	
	/**
	 * 
	 * @Description:处理微信推送的消息
	 * @param params
	 * @exception:
	 * @author: bzb
	 * @return String 回复给微信的消息
	 * @time:2018年11月11日 下午10:01:34
	 */
	String executeMessge(Map<String, String> params);

	/**
	 * 调用接口获取用户基本信息
	 * @param openid
	 * @return
	 */
	TalentmarketMember getUserinfo(String openid);

	void pushTextMessage(String message);

	/**
	 * 将一条长链接转成短链接。
	 * @param longurl 长链接
	 * @return
	 */
	String shorturl(String longurl);

	/**
	 * 获取所有的客服列表
	 * @return
	 */
	Map<String, Object> getkflist();

	/**
	 * 添加客服账号，
	 * @param kfAccount 完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
	 * @param nickname 客服昵称，最长16个字
	 */
	void addKf(String kfAccount, String nickname);

	/**
	 * 客服消息，发送文本消息
	 * @param content 文本内容
	 */
	void sendTextMessage(String openid, String content);

	/**
	 * 根据授权code获取网页授权凭证，和全局的接口调用凭证不一样
	 * @param code
	 * @return
	 */
	Map<String, Object> getAccessTokenByCode(String code);

	/**
	 * 获取微信授权地址
	 * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @param redirectUri 重定向的url，需进行url编码
	 * @return
	 */
	String getAuthRedirectUrl(String scope, String state, String redirectUri);
	
	/**
	 * 
	 * @Description:企业付款到零钱
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月17日 下午6:00:10
	 */
	void payToChange(String openid, String username, long money, String desc, String ip);
	
	/**
	 * 
	 * @Description:发送现金红包
	 * @param openid 粉丝的opneid
	 * @param money 单位分
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月18日 下午7:35:25
	 */
	void grantCashbonus(String openid, long money, String ip);
}
