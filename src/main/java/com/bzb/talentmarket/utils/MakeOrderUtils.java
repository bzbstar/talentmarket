package com.bzb.talentmarket.utils;

import java.util.UUID;

/**
 * @ClassName: MakeOrderUtils
 * @CreateTime 2015年9月13日 下午4:51:02
 * @author : bzb
 * @Description: 订单号生成工具，生成非重复订单号
 *
 */
public class MakeOrderUtils {

	/**
	 * 
	 * @Description:生成唯一的订单号
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月17日 下午8:30:31
	 */
	public static String generateOrderno() {
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		System.out.println(hashCodeV);
		return DateUtils.nowFormatNoPartition() + String.format("%014d", hashCodeV);
	}

	public static void main(String[] args) {
		int i = (int) (Math.random() * 8998) + 1000 + 1;
		System.out.println((MakeOrderUtils.generateOrderno()).length());
	}
}
