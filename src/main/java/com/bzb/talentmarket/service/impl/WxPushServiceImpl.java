package com.bzb.talentmarket.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bzb.talentmarket.bean.wx.WxProperties;
import com.bzb.talentmarket.service.WxPushService;

/**
 * 
 * @Description: 微信推送业务层
 * @author:Administrator
 * @time:2018年11月11日 下午6:49:02
 */
@Service
public class WxPushServiceImpl implements WxPushService {
	
	 @Autowired
	 private WxProperties wxProperties;

	@Override
	public boolean checkWxSign(String sign, String timestamp, String nonce) {
		
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		String[] arr = new String[] { wxProperties.getToken(), timestamp, nonce };
		Arrays.sort(arr);

		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(sign.toUpperCase()) : false;
	}

	private static String byteToStr(byte[] byteArray) {
		StringBuilder strDigest = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			strDigest.append(byteToHexStr(byteArray[i]));
		}
		return strDigest.toString();
	}

	/**
	 * @Description:将字节转换为十六进制字符串
	 * @param mByte 字节数组
	 * @return
	 * String
	 * @author: bzb
	 * @time:2018年11月11日 下午7:12:12
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
