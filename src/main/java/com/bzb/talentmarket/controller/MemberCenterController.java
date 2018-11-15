package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.entity.RedGrandrecords;
import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.service.MemberCenterService;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author bzb
 * @Description: 会员中心Controller
 * @date 2018/11/15 10:50
 */
@Controller
@RequestMapping(value = "/memberCenter")
public class MemberCenterController {

    public static final Logger log = LoggerFactory.getLogger(MemberCenterController.class);

    @Autowired
    private WxService wxService;

    @Autowired
    private MemberCenterService memberCenterService;

    /**
     * 前往会员中心页面, 注意这里是通过微信授权过来的
     * @param code 微信网页授权code
     * @param  state 微信网页授权重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return
     */
    @RequestMapping(value = "/toMemberCenter", method = RequestMethod.GET)
    public String toMemberCenter(Model model, String code, String state) {

        // 通过code获取用户的openid
        Map<String, Object> accessTokenMap = wxService.getAccessTokenByCode(code);

        String openid = CommonUtils.objToStr(accessTokenMap.get("openid"));

        // 根据openid获取会员信息，并判断是否生成永久的二维码
        TalentmarketMember member = memberCenterService.getByAuthOpenid(openid);

        // 会员信息
        model.addAttribute("member", member);

        return "mobile/memberCenter";
    }

    /**
     * 采用静默授权去会员中心页
     * @return
     */
    @RequestMapping(value = "/authMemberCenter", method = RequestMethod.GET)
    public String authMemberCenter() {

        String redirectUrl = memberCenterService.getAuthRedirectUrl();
        return "redirect:" + redirectUrl;
    }

    /**
     * 随机红包收入明细
     * @param model
     * @param openid 粉丝openid
     * @param headimgurl 头像
     * @param qrcode 二维码
     * @return
     */
    @RequestMapping(value = "/toRedRecords", method = RequestMethod.GET)
    public String toRedRecords(Model model, String openid, String headimgurl, String qrcode) {
        model.addAttribute("openid", openid);
        model.addAttribute("headimgurl", headimgurl);
        model.addAttribute("qrcode", qrcode);
        return "mobile/redRecords";
    }

    /**
     * 获取红包领取记录，每次获取20条
     * @param openid
     * @return
     */
    @RequestMapping(value = "/redRecords", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<RedGrandrecords> getRedRecords(String openid, Integer pageNo, Integer pageSize) {
        PageInfo<RedGrandrecords> pageInfo = memberCenterService.getRedGrandRecodes(openid, pageNo, pageSize);
        return pageInfo;
    }

}