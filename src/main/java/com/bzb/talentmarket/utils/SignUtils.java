package com.bzb.talentmarket.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bzb.talentmarket.bean.wx.WxProperties;

/**
 * 
 * @Description:签名工具类
 * @author:bzb
 * @time:2018年11月17日 下午6:35:51
 */
@Component
public class SignUtils {

	private static final Logger log = LoggerFactory.getLogger(SignUtils.class);

	@Autowired
	private WxProperties wxProperties;

	/**
	 * @param characterEncoding 编码格式 utf-8
	 */
	public String creatWxSign(String characterEncoding, SortedMap<Object, Object> parameters) {

		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k).append("=").append(v).append("&");
			}
		}

		// 复制签名代码的人，需要把该常量改成自己商务号的key值。原因是Api规定了签名必须加上自己的key值哦
		sb.append("key=").append(wxProperties.getApisecret());
		String sign = MD5Utils.MD5(sb.toString()).toUpperCase();
		log.info("签名sign={}", sign);
		return sign;
	}

	public String creatWxSign(SortedMap<Object, Object> parameters) {
		return creatWxSign("UTF-8", parameters);
	}
}
