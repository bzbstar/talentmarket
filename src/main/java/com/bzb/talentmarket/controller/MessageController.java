package com.bzb.talentmarket.controller;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.entity.MessageAccept;
import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.mapper.TalentmarketMemberMapper;
import com.bzb.talentmarket.service.MessageService;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.CommonUtils;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private WxService wxService;
	
	@Autowired
	private TalentmarketMemberMapper memberMapper;
	
	// 获取该粉丝的前10条消息记录
	@RequestMapping(value = "/messageAccepts", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getMessageAccepts(String openid, String agentOpenid) {
		Map<String, Object> messageMap = messageService.getMessages(openid, agentOpenid);
		return new ResultModel(true, "success", messageMap);
	}
	
	/**
	 * 
	 * @Description:经纪人给粉丝回复消息
	 * @param openid 粉丝的openid
	 * @param agentOpenid 经纪人的openid
	 * @param content 消息内容，目前只支持文字
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月23日 下午8:59:23
	 */
	@RequestMapping(value = "/sendMessageToFans", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel sendMessageToFans(String openid, String agentOpenid, String content) {
		return messageService.sendMessageToFans(openid, agentOpenid, content);
	}
	
	/**
	     * 前往会员中心页面, 注意这里是通过微信授权过来的
	* @param code 微信网页授权code
	* @param  state 微信网页授权重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	* @return
	*/
	@RequestMapping(value = "/toMessageCenter", method = RequestMethod.GET)
	public String toMemberCenter(Model model, String code, String state) {
	
		// 通过code获取用户的openid
		Map<String, Object> accessTokenMap = wxService.getAccessTokenByCode(code);
		
		// 经纪人的openid
		String agentOpenid = CommonUtils.objToStr(accessTokenMap.get("openid"));
		
		System.out.println("state" + state);
		// 粉丝的openid
		model.addAttribute("fans", memberMapper.getByOpenid(state));
		
		model.addAttribute("agent", memberMapper.getByOpenid(agentOpenid));
		
		return "mobile/messageCenter";
	}
}
