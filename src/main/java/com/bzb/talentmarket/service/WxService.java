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
	 * @time:2018年11月11日 下午10:01:34
	 */
	void executeMessge(Map<String, String> params);

	/**
	 * 调用接口获取用户基本信息
	 * @param openid
	 * @return
	 */
	TalentmarketMember getUserinfo(String openid);

	void pushTextMessage(String message);
}
