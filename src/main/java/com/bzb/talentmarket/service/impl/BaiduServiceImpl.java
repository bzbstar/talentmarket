package com.bzb.talentmarket.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bzb.talentmarket.service.BaiduService;

/**
 * @author bzb
 * @Description:
 * @date 2018/11/13 16:54
 */
@Service
public class BaiduServiceImpl implements BaiduService {

    private static final Logger log = LoggerFactory.getLogger(BaiduServiceImpl.class);

    @Value("${baidu_AK}")
    private String AK;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, Object> getLatAndLongByAddress(String address) {
//        String url = "http://api.map.baidu.com/geocoder/v2/?address=北京市海淀区上地十街10号&output=json&ak=您的ak&callback=showLocation //GET请求";
        String result = restTemplate.getForObject("http://api.map.baidu.com/geocoder/v2/?address={address}&output=json&ak={ak}"
                , String.class, address, AK);
        log.info("百度地图返回值，result={}", result);
        return new HashMap<>();
    }
}
