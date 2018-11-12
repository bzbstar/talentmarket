package com.bzb.talentmarket.utils;

/**
 * 
 * @Description:公共类
 * @author:bzb
 * @time:2018年11月11日 下午8:30:47
 */
public class CommonUtils {
	
	/**
	 * 
	 * @Description: Object对象转换为String
	 * @param obj
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月11日 下午8:31:35
	 */
	public static String objToStr(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}
}
