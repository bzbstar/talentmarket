package com.bzb.talentmarket.service;

import java.util.Map;

/**
 * @author bzb
 * @Description: 调用百度地图接口Service
 * @date 2018/11/13 16:53
 */
public interface BaiduService {

    /**
     * 根据地图获取经纬度
     * @param address
     * @return
     */
    Map<String, Object> getLatAndLongByAddress(String address);
}
