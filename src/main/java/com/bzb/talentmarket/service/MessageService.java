package com.bzb.talentmarket.service;

import java.util.List;
import java.util.Map;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.entity.MessageAccept;

public interface MessageService {
	
	/**
	 * 
	 * @Description: 获取粉丝发送的5条信息和经纪人回复的2条信息
	 * @param openid 粉丝openid
	 * @param agentOpenid 经纪人的openid
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月23日 下午8:50:05
	 */
	Map<String, Object> getMessages(String openid, String agentOpenid);
	
	/**
	 * 
	 * @Description:经纪人给粉丝回复消息
	 * @param openid 粉丝的openid
	 * @param agentOpenid 经纪人的openid
	 * @param content 消息内容，目前只支持文字
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月23日 下午9:00:36
	 */
	ResultModel sendMessageToFans(String openid, String agentOpenid, String content);

}
