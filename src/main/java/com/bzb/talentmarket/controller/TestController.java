package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.service.BaiduService;
import com.bzb.talentmarket.service.TestService;
import com.bzb.talentmarket.service.WxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author bzb
 * @Description:
 * @date 2018/10/24 18:14
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private WxService wxService;

    @Autowired
    private TestService testService;

    @Autowired
    private BaiduService baiduService;

    @RequestMapping("/")
    public String test() {
        String qrcode = wxService.createQrcode("QR_LIMIT_STR_SCENE", "oqFhA1YfrunnhBasHmsdZ4KCLqok", "1");

//        String openid = "oqFhA1YfrunnhBasHmsdZ4KCLqok";
//        Map<String, Object> result = baiduService.getLatAndLongByAddress("江苏省苏州市虎丘区华山路127号华山路人才市场");
//        String longurl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHY8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyZURRaW9SRW9kMGwxMDAwMHcwN3AAAgStSOpbAwQAAAAA";
//        String shorturl = wxService.shorturl(longurl);
//        wxService.getkflist();

//        wxService.addKf("", "客服001");

//        wxService.sendTextMessage("oqFhA1YfrunnhBasHmsdZ4KCLqok", "你好，我\n<a href='http://www.baidu.com'>百度</a>");
        return "mobile/memberCenter";
    }
    
}
