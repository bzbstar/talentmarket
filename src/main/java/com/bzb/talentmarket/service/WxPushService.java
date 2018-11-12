package com.bzb.talentmarket.service;

public interface WxPushService {
	
	/**
	 * 
	 * @Description:验证微信签名
	 * @param sign 微信签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return String
	 * @author: bzb
	 * @time:2018年11月11日 下午6:58:10
	 */
	boolean checkWxSign(String sign, String timestamp, String nonce);
}
