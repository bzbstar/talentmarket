package com.bzb.talentmarket.utils;

import java.math.BigDecimal;

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

	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 通过经纬度获取距离(单位：米)
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2,
									 double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s*1000;
		return s;
	}

	/**
	 * 生成随机小数，(0,max)之间的随机小数, 并保留两位。0元的则返回0.1
	 * @param max 最大值
	 * @return
	 */
	public static double randomDigits(int max) {
		double random = Math.random()*max;
		return random == 0 ? 0.1 : new BigDecimal(random).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}
	
	/**
	 * 
	 * @Description:获取当前的时间戳，单位s
	 * @author: bzb
	 * @time:2018年11月16日 下午11:00:49
	 */
	public static long getTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	public static void main(String[] args) {
		double distance = getDistance(121.491909,31.233234,121.411994,31.206134);
		System.out.println(randomDigits(3));
	}
}
