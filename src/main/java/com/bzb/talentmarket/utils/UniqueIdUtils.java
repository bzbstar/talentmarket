package com.bzb.talentmarket.utils;
import java.util.UUID;
public class UniqueIdUtils {
	
	//获取UUID
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
