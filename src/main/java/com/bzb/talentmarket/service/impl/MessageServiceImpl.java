package com.bzb.talentmarket.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.common.FinalData;
import com.bzb.talentmarket.entity.MessageAccept;
import com.bzb.talentmarket.entity.MessageReply;
import com.bzb.talentmarket.mapper.MessageAcceptMapper;
import com.bzb.talentmarket.mapper.MessageReplyMapper;
import com.bzb.talentmarket.service.MessageService;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.UniqueIdUtils;

@Service
public class MessageServiceImpl implements MessageService {
	
	public static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private MessageAcceptMapper messageAcceptMapper;
	
	@Autowired
	private MessageReplyMapper messageReplyMapper;
	
	@Autowired
	private WxService wxService;

	@Override
	public Map<String, Object> getMessages(String openid, String agentOpenid) {
		log.info("获取最新的5条消息");
		
		
		List<MessageAccept> accepts = messageAcceptMapper.getNewFiveMessages(openid, FinalData.Message.TABLENAME_PRE_ACCEPT + agentOpenid);
		
		List<MessageReply> replays = messageReplyMapper.getNewTwoMessages(openid, FinalData.Message.TABLENAME_PRE_REPLY + agentOpenid);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("accepts", accepts);
		resultMap.put("replys", replays);
		return resultMap;
	}

	@Override
	public ResultModel sendMessageToFans(String openid, String agentOpenid, String content) {
		log.info("经纪人给粉丝回复消息");
		
		// 参数校验
		if (!StringUtils.hasText(openid)) {
			return new ResultModel("要回复的粉丝不存在");
		}
		if (!StringUtils.hasText(agentOpenid)) {
			return new ResultModel("经纪人不存在");
		}
		if (!StringUtils.hasText(content)) {
			return new ResultModel("不能回复空消息");
		}
		
		// 调用微信客服消息接口回复消息
		wxService.sendTextMessage(openid, content);
		
		// 插入消息回复表
		MessageReply replay = new MessageReply();
		replay.setUid(UniqueIdUtils.getUUID());
		replay.setSendOpenid(agentOpenid);
		replay.setToOpenid(openid);
		replay.setContent(content);
		replay.setStatus((byte) FinalData.Message.STATUS_UNREAD);
		Date now = new Date();
		replay.setCredate(now);
		replay.setUpddate(now);
		replay.setTableName(FinalData.Message.TABLENAME_PRE_REPLY + agentOpenid);
		messageReplyMapper.insertSelective(replay);
		
		return new ResultModel(true, "success");
	}

}
