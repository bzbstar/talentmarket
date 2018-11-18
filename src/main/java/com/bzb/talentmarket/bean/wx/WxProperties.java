package com.bzb.talentmarket.bean.wx;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @Description:微信配置实体类
 * @author:bzb
 * @time:2018年11月17日 下午9:39:43
 */
@Configuration
@PropertySource(value = "classpath:wx.properties")
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

	// 微信token配置
	private String token;
	private String appid;
	private String secret;
	private String wxid;

	// 微信支付

	// 商户号
	private String mchid;

	private String apisecret;

	@Override
	public String toString() {
		return "WxProperties [token=" + token + ", appid=" + appid + ", secret=" + secret + ", wxid=" + wxid
				+ ", mchid=" + mchid + ", apisecret=" + apisecret + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getApisecret() {
		return apisecret;
	}

	public void setApisecret(String apisecret) {
		this.apisecret = apisecret;
	}
}
