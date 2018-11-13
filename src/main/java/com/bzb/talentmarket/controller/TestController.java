package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.service.BaiduService;
import com.bzb.talentmarket.service.TestService;
import com.bzb.talentmarket.service.WxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @Autowired
    private BaiduService baiduService;

    @RequestMapping("/")
    public Map<String, Object> test() {
//        String qrcode = wxService.createQrcode("QR_LIMIT_STR_SCENE", "tuijianzhi", "jingjiren");

        String openid = "oqFhA1YfrunnhBasHmsdZ4KCLqok";
        Map<String, Object> result = baiduService.getLatAndLongByAddress("江苏省苏州市虎丘区华山路127号华山路人才市场");
        return result;
    }
    
}
