package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.service.TestService;
import com.bzb.talentmarket.service.WxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bzb
 * @Description:
 * @date 2018/10/24 18:14
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private WxService wxService;

    @Autowired
    private TestService testService;

    @Value("${wos}")
    private String bzb;

    @RequestMapping("/")
    public String test() {
//        String qrcode = wxService.createQrcode("QR_LIMIT_SCENE", "dfdddddddddddddddddddddddddddddddddddddddddddd", "uid");
        return bzb;
    }
    
}
