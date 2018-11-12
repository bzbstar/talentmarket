package com.bzb.talentmarket.controller.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bzb.talentmarket.service.WxPushService;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.XmlUtils;

import sun.util.logging.resources.logging;

/**
 * 
 * @Description: 接受微信事件推送Controller
 * @author: BZB
 * @time:2018年11月11日 下午6:46:05
 */
@Controller
@RequestMapping("/wx")
public class WxPushController {
	
	private static final Logger log = LoggerFactory.getLogger(WxPushController.class);
	
	@Autowired
	private WxService wxService;

	@Autowired
	private WxPushService WxPushService;

	/**
	 * 检验微信签名222
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/push", method = RequestMethod.GET)
	public void checkWxSign(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter print;
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (signature != null && WxPushService.checkWxSign(signature, timestamp, nonce)) {
			try {
				print = response.getWriter();
				print.write(echostr);
				print.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**'
	 * 
	 * @Description:接受微信推送过来的消息并处理。关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
	    *  响应post请求，微信中消息和菜单交互都是采用post请求
	 * @param request
	 * @author: bzb
	 * @time:2018年11月11日 下午9:49:26
	 */
	@RequestMapping(value = "/push", method = RequestMethod.POST)
	public void executeMessage(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> params = null;
		try {
			params = XmlUtils.xmlToMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取微信事件推送xml转换Map异常");
		}
		
		try (PrintWriter out = response.getWriter()) {
			
			// 直接回复SUCCESS，防止微信重复请求
			out.println("success");
			
			wxService.executeMessge(params);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
