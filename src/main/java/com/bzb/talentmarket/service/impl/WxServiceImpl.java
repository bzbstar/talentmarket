package com.bzb.talentmarket.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.bzb.talentmarket.bean.QrcodeScene;
import com.bzb.talentmarket.bean.wx.KfMessage;
import com.bzb.talentmarket.bean.wx.TextMessage;
import com.bzb.talentmarket.bean.wx.TransInfo;
import com.bzb.talentmarket.bean.wx.WxProperties;
import com.bzb.talentmarket.common.FinalData;
import com.bzb.talentmarket.entity.RedGrandrecords;
import com.bzb.talentmarket.entity.TalentmarketGamerules;
import com.bzb.talentmarket.entity.TalentmarketKf;
import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.exception.WxApiException;
import com.bzb.talentmarket.mapper.RedGrandrecordsMapper;
import com.bzb.talentmarket.mapper.TalentmarketEmployeeMapper;
import com.bzb.talentmarket.mapper.TalentmarketGamerulesMapper;
import com.bzb.talentmarket.mapper.TalentmarketKfMapper;
import com.bzb.talentmarket.mapper.TalentmarketMemberMapper;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.CommonUtils;
import com.bzb.talentmarket.utils.HttpUtils;
import com.bzb.talentmarket.utils.JsonUtils;
import com.bzb.talentmarket.utils.MakeOrderUtils;
import com.bzb.talentmarket.utils.MessageUtils;
import com.bzb.talentmarket.utils.SignUtils;
import com.bzb.talentmarket.utils.UniqueIdUtils;
import com.bzb.talentmarket.utils.XmlUtils;

import sun.management.snmp.util.SnmpNamedListTableCache;

/**
 * 
 * @Description:微信服务Service
 * @author:bzb
 * @time:2018年11月11日 下午8:10:50
 */
@Service
public class WxServiceImpl implements WxService {
	
	private static final Logger log = LoggerFactory.getLogger(WxServiceImpl.class);

	// 华山路人才市场经纬度， 腾讯高德地图显示
	private double hslLatitude = 31.3123300000; // 纬度
	private double hslLongitude = 120.5356100000; // 精度
	
	@Autowired
	private WxProperties wxProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SignUtils signUtils;

	@Autowired
	private TalentmarketMemberMapper memberMapper;

	@Autowired
	private TalentmarketGamerulesMapper gamerulesMapper;

	@Autowired
	private TalentmarketEmployeeMapper employeeMapper;
	
	@Autowired
	private RedGrandrecordsMapper redMapper;

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
		return getAccessToken(wxProperties.getAppid(), wxProperties.getSecret());
	}

	@Override
	public String createQrcode(String actionName, String openid) {
		
		// 获取AccessToken
		String accessToken = getAccessToken();
		
		// 场景字符串
		Map<String, Object> scene_str = new HashMap<>();
		scene_str.put("scene_str", openid);

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
		
		// TODO, 消息的排重问题待解决
		
		log.info("处理微信发送过来的消息， map={}", params);
		
		// 给微信的返回信息
//		String returnMessage = "success";
		
		String fromUserName = params.get("FromUserName"); //消息来源用户标识
//		String toUserName = params.get("ToUserName"); //消息目的用户标识， 公众号
		String msgType = params.get("MsgType"); //消息类型
		String event = params.get("Event"); // 事件类型
		String scene = CommonUtils.objToStr(params.get("EventKey")); // 扫码场景值
		String ip = CommonUtils.objToStr(params.get("ip"));
		
		if (FinalData.Wx.MSGTYPE_EVENT.equals(msgType)) { // 事件类型
			
			if (FinalData.Wx.EVENT_SUBSCIBE.equals(event)) { // 订阅事件，调用微信接口获取用户的基本信息
				log.info("关注事件推送，openid={}", fromUserName);
				
				// 关注事件，插入粉丝信息，经纪人为总部经纪人
				subscibe(fromUserName);
				
				if (params.containsKey("Ticket")) { // 扫码关注事件
					
					scanQrcode(fromUserName, FinalData.Wx.EVENT_SUBSCIBE, scene, ip);
				} else { // 其他关注事件,如直接搜索公众号关注
					
				}
				
				
			} else if (FinalData.Wx.EVENT_UNSUBSCRIBE.equals(event)) { // 取消订阅
				log.info("取消关注事件推送， openid={}", fromUserName);
				unsubscibe(fromUserName);
			} else if (FinalData.Wx.EVENT_LOCATION.equals(event)) { // 上报地理位置事件
				
				Double latitude = Double.parseDouble(CommonUtils.objToStr(params.get("Latitude")));
				Double longitude = Double.parseDouble(CommonUtils.objToStr(params.get("Longitude")));
				Double precision = Double.parseDouble(CommonUtils.objToStr(params.get("Precision")));

				// 如果用户不开启定位，则插入null，不获取奖励
				log.info("地理位置上报, openid={}", fromUserName);
				location(latitude, longitude, precision, fromUserName);
			} else if (FinalData.Wx.EVENT_SCAN.equals(event)) { // 已关注时的扫码推送
				log.info("已关注的扫码推送");
				
				scanQrcode(fromUserName, FinalData.Wx.EVENT_SCAN, scene, ip);
			}
			
		} else if (FinalData.Wx.MSGTYPE_TEXT.equals(msgType)) { // 文本消息
			
			// 粉丝发送过来的文本消息
			String content = params.get("Content");
			
			// 文本消息转发到客服
//			returnMessage = sendMessageToKf(fromUserName);
			
//			forwardTextMessage(fromUserName, content); 
			
			// 给经济人发送消息
			sendMessageToAgent(fromUserName);
		}
		
	}
	
	/**
	 * 
	 * @Description:给经纪人发送消息
	 * @param openid
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月22日 下午10:07:05
	 */
	private void sendMessageToAgent(String openid) {
		log.info("给经纪人发送消息");
		
		// 获取粉丝信息
		TalentmarketMember fans = memberMapper.getByOpenid(openid);
		if (fans == null) {
			log.error("发送消息异常，粉丝不存在， this is a bug");
			throw new WxApiException("发送消息异常，粉丝不存在");
		}
		
		if (fans.getIsagent() != FinalData.Member.ISAGENT_FAN) { // 经纪人不发送消息
			return;
		}
		
		String agentOpenid = fans.getAgentopenid();
		if (StringUtils.hasText(agentOpenid)) { // 经纪人不存在，不发送消息
			log.error("发送消息失败，经纪人不存在");
			return;
		}
		
		// 给经纪人发送消息
		
		// 给经纪人发送客服文本消息
		
		StringBuilder content = new StringBuilder();
		content.append("粉丝给你发送新的消息了，请注意查收！\n\n")
		.append("<a href='url?openid='")
		.append(openid)
		.append(">详情</a>");
		
		sendTextMessage(agentOpenid, content.toString());
		
		// TODO, 插入消息表
	}

	/**
	 * 
	 * @Description:转发粉丝发送过来的文本消息
	 * @param openid 粉丝的openid
	 * @param content 粉丝发送的内容
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月17日 上午11:51:55
	 */
	private String forwardTextMessage(String openid, String content) {
		log.info("转发粉丝发送过来的内容,openid={},content={}", openid, content);
		
		// 获取粉丝openid对应的客服账号
		TalentmarketMember member = memberMapper.getByOpenid(openid);
		
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(wxProperties.getWxid());
		textMessage.setToUserName(member.getAgentopenid());
		textMessage.setMsgType("text");
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setContent(content);
		String returnMessage = MessageUtils.textMessageToXml(textMessage);
		log.info("回复内容textMessage={}", returnMessage);
		return returnMessage;
	}

	/**
	 * 
	 * @Description:发送客服消息
	 * @param openid
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月16日 下午10:57:28
	 */
	private String sendMessageToKf(String openid) {
		log.info("发送客服消息");
		
		// 获取粉丝openid对应的客服账号
		TalentmarketMember member = memberMapper.getByOpenid(openid);
		
		KfMessage kfMessage = new KfMessage();
		kfMessage.setToUserName(openid);
		kfMessage.setFromUserName(wxProperties.getWxid());
		kfMessage.setCreateTime(CommonUtils.getTimestamp());
		kfMessage.setMsgType(FinalData.Wx.MSGTYPE_TRANSFER_CUSTOMER_SERVICE);
		kfMessage.setTransInfo(new TransInfo(member.getAgentopenid()));
		
		String returnMessage = XmlUtils.toXML(kfMessage);
		log.info("回复的客服消息，returnMsg={}", returnMessage);
		return returnMessage;
	}

	
	public String getQrcodeScene(String scene, String eventType) {
		// 获取扫码场景值
		if (FinalData.Wx.EVENT_SUBSCIBE.equals(eventType)) { // 未关注扫码，场景值带qrscene_前缀
			scene = scene.replaceAll("qrscene_", "");
		}
		log.info("scene={}", scene);
		return scene;
	}
	
	/**
	 * 扫描事件处理
	 * @param openid 粉丝openid
	 * @param eventType 事件类型，未关注扫描和关注扫描
	 * @param scene 扫描场景值，格式为openid
	 * @param ip
	 */
	private void scanQrcode(String openid, String eventType, String scene, String ip) {

		// 扫码场景值
		String refereeOpenid = getQrcodeScene(scene, eventType);

		// 推荐人为自己，直接返回
		if (openid.equals(refereeOpenid)) {
			log.info("推荐人为自己，直接返回");
			return;
		}

		// 获取当前粉丝信息
		TalentmarketMember member = memberMapper.getByOpenid(openid);
		if (member == null) {
			log.error("粉丝不存在，不可能，关注的时候就存上了，这是一个bug");
			throw new WxApiException("粉丝不存在，不可能，关注的时候就存上了，这是一个bug");
		}

		if (FinalData.Member.ISAGENT_FAN != member.getIsagent()) { // 粉丝为经纪人，直接返回
			log.info("贡献的粉丝为经纪人，无效粉丝");
			return;
		}

		// 判断是否已领取，同一个人只能领取一次红包
		if (FinalData.Member.REDSTATUS_DRAWED == member.getRedStatus()) { // 已领取
			log.info("粉丝openid={}已领取过红包了", openid);
			return;
		}

		// 获取经纪人信息
		TalentmarketMember agent = getAgentByRefereeOpenid(refereeOpenid);

		// 普通粉丝，推送经纪人消息
		if (agent != null) {
			sendAgentMessage(openid, agent);
		}

		// 半径映射范围
		TalentmarketGamerules gamerules = gamerulesMapper.getDistinceAndRed();

		// 判断是否在高新区的半径映射范围内，防止全国刷单
		if (!checkDistince(member.getGeoglatitude(), member.getGeoglongitude(), gamerules.getDistance())) {
			return;
		}

		// 给推荐者发送现金红包，经纪人不发送
		grandCashRed(openid, refereeOpenid, gamerules.getMaxred(), agent == null ? "" : agent.getOpenid(), ip);

	}

	/**
	 * 根据推荐者获取经纪人信息
	 * @param refereeOpenid 推荐者的openid
	 * @return
	 */
	private TalentmarketMember getAgentByRefereeOpenid(String refereeOpenid) {

		// 推荐者信息
		TalentmarketMember referee = memberMapper.getByOpenid(refereeOpenid);

		String agentOpenid = null; // 经纪人编号
		if (FinalData.Member.ISAGENT_FAN == referee.getIsagent()) { // 普通粉丝
			agentOpenid = referee.getAgentopenid();
			if (StringUtils.hasText(agentOpenid)) { // 经纪人为空不是总部经纪人不存在，请尽快绑定
				return memberMapper.getByOpenid(agentOpenid);
			}
		} else { // 经纪人
			return  referee;
		}
		return null;
	}

	/**
	 * 更新粉丝状态为已领取
	 * @param openid 粉丝openid
	 * @param refereeOpenid 推荐人的openid
	 */
	private void updateFansRedstatus(String openid, String refereeOpenid, String agentOpenid) {
		log.info("更新粉丝领取状态");
		TalentmarketMember fans = new TalentmarketMember();
		fans.setOpenid(openid);
		fans.setFopenid(refereeOpenid); // 推荐人
		fans.setAgentopenid(agentOpenid); // 经纪人id
		fans.setRedStatus((byte) FinalData.Member.REDSTATUS_DRAWED);
		fans.setUpddate(new Date());
		memberMapper.updateByOpenid(fans);
	}

	/**
	 * 推送经济人消息
	 * @param openid 粉丝openid
	 * @param agent 经纪人
	 */
	private void sendAgentMessage(String openid, TalentmarketMember agent) {
		log.info("推送经纪人消息");

		String phone = agent.getPhone();
		String wxid = agent.getWxid();

		StringBuilder content = new StringBuilder();
		content.append("感谢您关注\"华山路人才市场\"\n\n")
				.append("我是您的专属经纪人：").append(agent.getNickname())
		.append("\n");
		if (phone.equals(wxid)) {
			content.append("电话/微信：" + phone).append("\n");
		} else {
			content.append("电话：" + phone).append("\n微信：").append(wxid).append("\n");
		}
		content.append("请随时与我联系");

		sendTextMessage(openid, content.toString());
	}


	/**
	 * 给推荐者发放现金红包
	 * @param openid 粉丝的openid
	 * @param refereeOpenid 推荐人的openid
	 * @param maxred 随机红包最大值
	 * @param agentOpenid 经纪人的openid
	 * @param ip ip地址，现金红包接口需要用到
	 */
	@Transactional
	public void grandCashRed(String openid, String refereeOpenid, int maxred, String agentOpenid, String ip) {
		log.info("发放现金红包");

		// 更新粉丝为已领取
		updateFansRedstatus(openid, refereeOpenid, agentOpenid);

		// 计算现金红包金额
		double randMoney = CommonUtils.randomDigits(maxred);
		long redMoney = (long) randMoney * 100; // 转换为分

		// 推荐者信息
		TalentmarketMember referee = memberMapper.getByOpenid(refereeOpenid);

		// 是否经纪人
		Integer isagent = (int) referee.getIsagent();
		boolean isfans = isagent == FinalData.Member.ISAGENT_FAN; // 是否普通粉丝

		// 更新推荐人的统计相关信息
		memberMapper.updateFans(refereeOpenid, isfans ? redMoney : 0);

		if (!isfans) { // 普通粉丝才发送红包
			return;
		}

		// 调用微信转账接口发送现金红包
//		grantCashbonus(refereeOpenid, redMoney, ip);
		payToChange(refereeOpenid, referee.getNickname(), 300, "扫码推荐", ip);

		// 插入领取红包记录
		RedGrandrecords red = new RedGrandrecords();
		red.setUid(UniqueIdUtils.getUUID());
		red.setDraweruid(referee.getOpenid());
		red.setName(referee.getNickname());
		red.setMoney(redMoney);
		red.setSourceOpenid(openid);
		red.setSource((byte) FinalData.Member.REDMONEY_SOURCE_SCAN_RECOMMEND);
		Date now = new Date();
		red.setCredate(now);
		red.setUpddate(now);
		redMapper.insertSelective(red);

		// 给推荐者发送红包消息
		StringBuilder content = new StringBuilder();
		content.append("感谢你对\"华山路人才市场\"的大力关注！\n\n")
				.append("恭喜你获得红包：").append(randMoney)
				.append("元\n\n")
				.append("这是送您的一点点心意，请注意查收");
		sendTextMessage(refereeOpenid, content.toString());
	}



	/**
	 * 校验粉丝是否在华山路人才市场的半径映射范围内
	 * @param geoglatitude 粉丝地理纬度
	 * @param geoglongitude 粉丝地理经度
	 * @param distince 半径映射范围
	 * @return boolean true 在映射范围内 false 不在映射范围内
	 */
	private boolean checkDistince(Double geoglatitude, Double geoglongitude, int distince) {

		if (distince == 0) { // 0表示不限制
			return true;
		}

		// 获取当前粉丝距离华山路人才市场的距离
		double fansDistince = CommonUtils.getDistance(geoglatitude, geoglongitude, hslLatitude, hslLongitude);
		if (fansDistince > distince) { // 超出映射范围
			log.info("超出映射范围");
			return false;
		}

		return true;
	}

	/**
	 * 用户上报地理位置
	 * @param latitude 地理纬度
	 * @param longitude 地理经度
	 * @param precision 地理精度
	 * @param openid
	 */
	private void location(Double latitude, Double longitude, Double precision, String openid) {

		// 更新粉丝的地理位置

		TalentmarketMember member = new TalentmarketMember();
		member.setOpenid(openid);
		member.setGeoglatitude(latitude);
		member.setGeoglongitude(longitude);
		member.setGeogprecision(precision);
		member.setUpddate(new Date());
		memberMapper.updateByOpenid(member);
	}

	/**
	 * 取消订阅， 更新粉丝订阅状态为取消
	 * @param openid 粉丝的openid
	 */
	private void unsubscibe(String openid) {
		// 将对应的openid的订阅状态改为取消关注，该粉丝已死
		TalentmarketMember member = new TalentmarketMember();
		member.setOpenid(openid);
		Date now = new Date();
		member.setUpddate(now);
		member.setSubscribedate(System.currentTimeMillis() / 1000); // 取消订阅时间，单位秒
		member.setSubscribeStatus((byte) FinalData.Member.SUBSCIBE_NO);
		memberMapper.updateByOpenid(member);
	}

	/**
	 * 订阅事件处理, 新增或更新用户信息
	 * @param openid 粉丝的openid
	 */
	private void subscibe(String openid) {

		// 调用微信接口获取用户的基本信息
		TalentmarketMember wxUser = getUserinfo(openid);

		// 当前系统时间
		Date now = new Date();

		// 判断粉丝是否存在
		TalentmarketMember member = memberMapper.getByOpenid(openid);
		
		if (member == null) { // 新增
			wxUser.setUid(UniqueIdUtils.getUUID());
			
			TalentmarketMember header = memberMapper.getHeaderAgent();
			if (header == null) { 
				log.error("总部经纪人不存在，this is a bug， 请尽快绑定");
			}
			wxUser.setAgentopenid(header == null ? "" : header.getOpenid()); // 经纪人为总部经纪人
			
			wxUser.setCredate(now);
			wxUser.setUpddate(now);
			memberMapper.insertSelective(wxUser);
		} else { // 更新， 经纪人不允许修改
			wxUser.setUpddate(now);
			memberMapper.updateByOpenid(wxUser);
		}
	}

	@Override
	public TalentmarketMember getUserinfo(String openid) {
		log.info("根据openid={}获取用户的基本信息", openid);
		if (!StringUtils.hasText(openid)) {
			throw new WxApiException("调用微信接口获取用户基本信息失败，reason is openid 为空");
		}

		String accessToken = getAccessToken();

		// 参数
		// 接口地址https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		String userJson = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/user/info?access_token={ACCESS_TOKEN}&openid={OPENID}",
				String.class, accessToken, openid);
		log.info("userinfo={}", userJson);

		// 将json转换为Map
		Map<String, Object> userMap = JsonUtils.fromJson(userJson, Map.class);

		// 赋值会员粉丝
		TalentmarketMember member = new TalentmarketMember();
		member.setOpenid(openid);
		member.setNickname(CommonUtils.objToStr(userMap.get("nickname")));
		member.setNickname(CommonUtils.objToStr(userMap.get("nickname")));
		member.setSex(Byte.parseByte(CommonUtils.objToStr(userMap.get("sex"))));
		member.setYuyan(CommonUtils.objToStr(userMap.get("language")));
		member.setCountry(CommonUtils.objToStr(userMap.get("country")));
		member.setProvince(CommonUtils.objToStr(userMap.get("province")));
		member.setCity(CommonUtils.objToStr(userMap.get("city")));
		member.setHeadimgurl(CommonUtils.objToStr(userMap.get("headimgurl")));
		member.setSubscribedate(Long.parseLong(CommonUtils.objToStr(userMap.get("subscribe_time"))));
		member.setRemark(CommonUtils.objToStr(userMap.get("remark")));
		// 标签列表暂无
		member.setGroupid(Integer.parseInt(CommonUtils.objToStr(userMap.get("groupid"))));
		member.setSubscribeScene(CommonUtils.objToStr(userMap.get("subscribe_scene")));
		member.setQrScene(Integer.parseInt(CommonUtils.objToStr(userMap.get("qr_scene"))));
		member.setQrSceneStr(CommonUtils.objToStr(userMap.get("qr_scene_str")));
		member.setSubscribeStatus(Byte.parseByte(CommonUtils.objToStr(userMap.get("subscribe"))));
		return member;
	}

	@Override
	public void pushTextMessage(String message) {

	}

	@Override
	public String shorturl(String longurl) {
		log.info("将长链接转换为短链接， longurl={}", longurl);

		String accessToken = getAccessToken();

		// 参数
		Map<String, String> params = new HashMap<>();
		params.put("action", "long2short");
		params.put("long_url", longurl);

		Map<String, Object> resultMap = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken,
				params, Map.class);
		if (resultMap == null || Integer.parseInt(CommonUtils.objToStr(resultMap.get("errcode"))) != 0) {
			log.error("调用微信长连接转换为短链接异常，reason is {}" + resultMap);
			throw new WxApiException("调用微信长连接转换为短链接异常，reason is " + resultMap.toString());
		}

		String shorturl = CommonUtils.objToStr(resultMap.get("short_url"));
		log.info("转换为的短链接为={}", shorturl);
		return shorturl;
	}


	@Override
	public Map<String, Object> getkflist() {

		log.info("获取所有的客服列表");
		// https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN

		String accessToken = getAccessToken();

		Map<String, Object> kfs = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + accessToken, Map.class);
		log.info("客服列表，kdlist={}", kfs);

		return kfs;
	}

	@Override
	public void addKf(String kfPre, String nickname) {
		log.info("添加客服账号");

		String accessToken = getAccessToken();

		// 请求参数
		Map<String, Object> params = new HashMap<>();
		params.put("kf_account",  kfPre + "@" + wxProperties.getWxid());
		params.put("nickname", nickname);

		Map<String, Object> resultMap = restTemplate.postForObject("https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + accessToken,
				params, Map.class);

		if (resultMap == null || Integer.parseInt(CommonUtils.objToStr(resultMap.get("errcode"))) != 0) {
			throw new WxApiException("调用微信新增客服接口异常，reason is " + resultMap.toString());
		}
	}

	@Override
	public void sendTextMessage(String openid, String content) {
		log.info("发送文本消息");

		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getAccessToken();

		Map<String, Object> text= new HashMap<>();
		text.put("content", content);

		// 请求参数
		Map<String, Object> params = new HashMap<>();
		params.put("touser", openid);
		params.put("msgtype", "text");
		params.put("text", text);

		Map<String, Object> result = restTemplate.postForObject(url, params, Map.class);
		if (result == null || Integer.parseInt(CommonUtils.objToStr(result.get("errcode"))) != 0) {
			log.error("调用微信发送文本消息客服接口失败， reason is {}", result);
			throw new WxApiException("调用微信发送文本消息客服接口失败， reason is " + result.toString());
		}
	}

	@Override
	public Map<String, Object> getAccessTokenByCode(String code) {
		log.info("通过授权code获取opedid, code={}", code);

		String resultJson = restTemplate.getForObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code", String.class,
				wxProperties.getAppid(), wxProperties.getSecret(), code);
		log.info("根据code获取openid返回结果， result={}", resultJson);

		Map<String, Object> result = JsonUtils.fromJson(resultJson, Map.class);

		if (result == null || result.containsKey("errcode")) {
			throw new WxApiException("根据授权code获取openid失败");
		}

		return result;
	}

	@Override
	public String getAuthRedirectUrl(String scope, String state, String url) {

		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("微信授权url编码错误");
			e.printStackTrace();
		}

		StringBuilder redirectUrl = new StringBuilder();
		redirectUrl.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
				.append("appid=").append(wxProperties.getAppid())
				.append("&redirect_uri=").append(url)
				.append("&response_type=code")
				.append("&scope=").append(scope)
				.append("&state=").append(state)
				.append("#wechat_redirect");
		log.info("授权地址， redirectUrl={}", redirectUrl);
		return redirectUrl.toString();
	}

	@Override
	public void payToChange(String openid, String username, long money, String desc, String ip) {
		log.info("企业付款到零钱");
		
		// 参数
		SortedMap<Object, Object> params = new TreeMap<>();
		params.put("mch_appid", wxProperties.getAppid()); // 商户账号appid
		params.put("mchid", wxProperties.getMchid());// 商户号
		// 微信支付分配的终端设备号, 非必填
		params.put("nonce_str", UniqueIdUtils.getUUID()); // 随机字符串
		params.put("partner_trade_no", MakeOrderUtils.generateOrderno()); // 商户订单号，需保持唯一性(只能是字母或者数字，不能包含有其他字符)
		params.put("openid", openid); // 粉丝的openid
		params.put("check_name", FinalData.Wx.CHECK_NAME_NO_CHECK); // 不校验真实姓名 
		params.put("re_user_name", username); // 收款用户真实姓名。 如果check_name设置为FORCE_CHECK，则必填用户真实姓名
		params.put("amount", money); // 企业付款金额，单位为分 
		params.put("desc", desc); // 企业付款备注，必填。
		params.put("spbill_create_ip", ip); // 	该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。
		// 签名
		params.put("sign", signUtils.creatWxSign(params));
		
		// map转换为xml
		String xmlParams = XmlUtils.toXml(params);
		
		String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
		String resultStr = HttpUtils.posts(url, xmlParams);
		log.info("企业付款返回结果result={}", resultStr);
		
		// xml转Map
		Map<String, String> result = null;
		try {
			result = XmlUtils.xmlToMap(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("xml转换为map异常");
		}
		if (resultStr == null || "FAIL".equals(result.get("return_code"))) {
			log.error("调用微信接口-企业付款到零钱失败，reason is {}", result);
		}
		log.info("付款返回result={}", result);
	}

	@Override
	public void grantCashbonus(String openid, long money, String ip) {
		log.info("发放现金红包");
		
		// 参数
		SortedMap<Object, Object> params = new TreeMap<>();
		params.put("nonce_str", UniqueIdUtils.getUUID()); // 随机字符串
		params.put("mch_billno", MakeOrderUtils.generateOrderno()); // 商户订单号（每个订单号必须唯一。取值范围：0~9，a~z，A~Z），接口根据商户订单号支持重入，如出现超时可再调用。
		params.put("mch_id", wxProperties.getMchid());// 商户号
		params.put("wxappid", wxProperties.getAppid()); // 公众账号appid
		params.put("send_name", "华山路人才市场"); // 红包发送者名称
		params.put("re_openid", openid); // 粉丝的openid
//		params.put("total_amount", money); // 红包发放金额，单位分, 100分
		params.put("total_amount", 100); 
		params.put("total_num", 1); // 	红包发放总人数
		params.put("wishing", "感谢参与扫码推荐活动，祝您生活愉快"); // 红包祝福语
		params.put("client_ip", ip); // 调用接口的机器Ip地址
		params.put("act_name", "扫码推荐活动"); // 活动名称
		params.put("remark", "扫码推荐好友越多，奖励越丰厚"); // 备注信息
		params.put("scene_id", "PRODUCT_1"); // 场景id
		// 签名
		params.put("sign", signUtils.creatWxSign(params));
		
		// map转换为xml
		String xmlParams = XmlUtils.toXml(params);
		
		String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
		String resultStr = HttpUtils.posts(url, xmlParams);
		log.info("企业付款返回结果result={}", resultStr);
		
		Map<String, String> result = null;
		try {
			result = XmlUtils.xmlToMap(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("xml转换为map异常");
		}
		if (resultStr == null || "FAIL".equals(result.get("return_code"))) {
			log.error("调用微信接口-企业付款到零钱失败，reason is {}", result);
			throw new WxApiException("调用微信接口发放现金红包失败，reason is " + result);
		}
		log.info("付款返回result={}", result);
	}
	
	
}
