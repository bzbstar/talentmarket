package com.bzb.talentmarket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description:时间工具类
 * @author:bzb
 * @time:2018年11月17日 下午7:59:26
 */
public class DateUtils {
	
	/**
	 * 
	 * @Description:日期格式化
	 * @param date
	 * @param partern 格式化字符串
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月17日 下午8:02:02
	 */
	public static String dateFormat(Date date, String partern) {
		SimpleDateFormat sdf = new SimpleDateFormat(partern);
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @Description: 格式化当前时间，格式为yyyy-MM-dd HH:mm:ss
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月17日 下午8:03:04
	 */
	public static String nowFormat() {
		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 
	 * @Description: 格式化当前时间，格式为yyyyMMddHHmmss
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月17日 下午8:03:04
	 */
	public static String nowFormatNoPartition() {
		return dateFormat(new Date(), "yyyyMMddHHmmss");
	}
}
