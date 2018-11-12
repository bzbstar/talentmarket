package com.bzb.talentmarket.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bzb.talentmarket.common.FinalData;
import com.bzb.talentmarket.exception.WxApiException;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.CommonUtils;

import sun.management.resources.agent;

/**
 * 
 * @Description:微信服务Service
 * @author:bzb
 * @time:2018年11月11日 下午8:10:50
 */
@Service
public class WxServiceImpl implements WxService {
	
	private static final Logger log = LoggerFactory.getLogger(WxServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${wx_appid}")
	private String appid;

	@Value("${wx_secret}")
	private String secret;

	@Override
	public String getAccessToken(String appid, String secret) {
		
		// 请求参数
		Map<String, String> params = new HashMap<>();
		params.put("appid", appid);
		params.put("secret", secret);
		
		
		// 发送GET请求
		@SuppressWarnings("unchecked")
		Map<String, Object> result = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}", 
				Map.class, params);
		
		if (!result.containsKey("access_token")) {
			log.error("调用微信接口获取Access_Token失败，reason is：errcode={}, errmsg={}", 
					result.get("errcode"), result.get("errmsg"));
			throw new WxApiException("调用微信接口获取Access_Token失败");
		}
		
		return CommonUtils.objToStr(result.get("access_token"));
	}

	@Override
	public String getAccessToken() {
		return getAccessToken(appid, secret);
	}

	@Override
	public String createQrcode(String actionName, String openid, String uid) {
		
		// 获取AccessToken
		String accessToken = getAccessToken();
		
		// 场景字符串
		Map<String, Object> scene_str = new HashMap<>();
		scene_str.put("scene_str", openid + "-" + uid);
		
		// 场景值
		Map<String, Object> scene = new HashMap<>();
		scene.put("scene", scene_str);
		
		// 请求参数
		Map<String, Object> params = new HashMap<>();
		params.put("expire_seconds", "2592000"); // 30天
		params.put("action_name", actionName);
		params.put("action_info", scene);
		
		// 发送请求
		@SuppressWarnings("unchecked")
		Map<String, Object> result = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken, 
				params, Map.class);
		
		if (!result.containsKey("ticket")) {
			throw new WxApiException("调用微信接口获取二维码失败，reason is");
		}
		
		// 获取二维码票据
		String ticket = CommonUtils.objToStr(result.get("ticket"));
		
		try {
			return "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(ticket, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void executeMessge(Map<String, String> params) {
		log.info("处理微信发送过来的消息， map={}", params);
		
		String fromUserName = params.get("FromUserName");//消息来源用户标识
		String toUserName = params.get("ToUserName");//消息目的用户标识
		String msgType = params.get("MsgType"); //消息类型
		String event = params.get("Event"); // 事件类型
		
		if (FinalData.Wx.MSGTYPE_EVENT.equals(msgType)) { // 事件类型
			
			if (FinalData.Wx.EVENT_SUBSCIBE.equals(event)) { // 订阅事件
				
				// 不存在则插入，将对应的openid的订阅状态改为已关注
				log.info("我是订阅事件");
			} else if (FinalData.Wx.EVENT_UNSUBSCRIBE.equals(event)) { // 取消订阅
				// 将对应的openid的订阅状态改为取消关注，该粉丝已死
			}
			
		}
	}
	
	

	
}
