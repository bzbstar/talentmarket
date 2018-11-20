package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.service.TestService;
import com.bzb.talentmarket.service.WxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/")
    public String test(String kfPre, String nickname, HttpServletRequest request) {
//        String qrcode = wxService.createQrcode("QR_LIMIT_STR_SCENE", "oqFhA1YfrunnhBasHmsdZ4KCLqok", "1");

        String openid = "oqFhA1eV7EgpcQU46U2AEO1QVLO0";
        
//        Map<String, Object> result = baiduService.getLatAndLongByAddress("江苏省苏州市虎丘区华山路127号华山路人才市场");
//        String longurl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHY8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyZURRaW9SRW9kMGwxMDAwMHcwN3AAAgStSOpbAwQAAAAA";
//        String shorturl = wxService.shorturl(longurl);
//        wxService.getkflist();

//        wxService.addKf("", "客服001");

//        wxService.sendTextMessage("oqFhA1YfrunnhBasHmsdZ4KCLqok", "你好，我\n<a href='http://www.baidu.com'>百度</a>");
    	
//    	wxService.addKf(kfPre, nickname);
        String username = "白志斌";
        long money = 100;
        String desc = "扫码推荐";
        String ip = request.getLocalAddr();
//       wxService.grantCashbonus(openid, money, ip);
        return "mobile/authAgent";
    }
    
}
