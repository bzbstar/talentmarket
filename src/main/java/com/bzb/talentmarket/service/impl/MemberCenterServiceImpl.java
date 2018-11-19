package com.bzb.talentmarket.service.impl;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.common.FinalData;
import com.bzb.talentmarket.entity.RedGrandrecords;
import com.bzb.talentmarket.entity.TalentmarketMember;
import com.bzb.talentmarket.exception.WxApiException;
import com.bzb.talentmarket.mapper.RedGrandrecordsMapper;
import com.bzb.talentmarket.mapper.TalentmarketGamerulesMapper;
import com.bzb.talentmarket.mapper.TalentmarketMemberMapper;
import com.bzb.talentmarket.service.MemberCenterService;
import com.bzb.talentmarket.service.WxService;
import com.bzb.talentmarket.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.export.ganglia.GangliaMetricsExportAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author bzb
 * @Description: 会员中心业务实现类
 * @date 2018/11/15 11:12
 */
@Service
public class MemberCenterServiceImpl implements MemberCenterService {

    public static final Logger log = LoggerFactory.getLogger(MemberCenterServiceImpl.class);

    @Autowired
    private TalentmarketMemberMapper memberMapper;

    @Autowired
    private RedGrandrecordsMapper redGrandrecordsMapper;

    @Autowired
    private WxService wxService;

    @Value("${membercenter_url}")
    private String memberCenterUrl;
    
    private TalentmarketGamerulesMapper gamerulesMapper;

    @Override
    public TalentmarketMember getByAuthOpenid(String openid) {

        // 获取会员信息
        TalentmarketMember member = memberMapper.getByOpenid(openid);
        if (member == null) {
            throw new WxApiException("从数据库获取会员信息出错，this is a bug, 会员信息应该在关注公众号的时候就存上去了");
        }

        String qrcode = member.getQrcode();

        if (!StringUtils.hasText(qrcode)) { // 推荐二维码为空，生成永久二维码
            qrcode = wxService.createQrcode("QR_LIMIT_SCENE", openid, member.getKfaccount());

            // 转换为短链接
            qrcode = wxService.shorturl(qrcode);

            // 更新会员推荐二维码
            TalentmarketMember updateMember = new TalentmarketMember();
            updateMember.setOpenid(openid);
            updateMember.setQrcode(qrcode);
            updateMember.setUpddate(new Date());
            memberMapper.updateByOpenid(updateMember);

        }

        member.setQrcode(qrcode);

        return member;
    }

    @Override
    public String getAuthRedirectUrl() {
        String url = wxService.getAuthRedirectUrl("snsapi_base ", "123", memberCenterUrl);
        return url;
    }

    @Override
    public PageInfo<RedGrandrecords> getRedGrandRecodes(String openid, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<RedGrandrecords> redGrandrecords = redGrandrecordsMapper.getByOpenid(openid);

        PageInfo<RedGrandrecords> pageInfo = new PageInfo<>(redGrandrecords);

        return pageInfo;
    }

    @Override
    public PageInfo<TalentmarketMember> getRecommendMembers(String openid, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        List<TalentmarketMember> recommendMembers = memberMapper.getRecommendMembersByOpenid(openid);

        PageInfo<TalentmarketMember> pageInfo = new PageInfo<>(recommendMembers);

        return pageInfo;
    }

    @Override
    public ResultModel authToAgent(String openid, String phone, String wxid, Integer isHeader, 
    		String password) {
        log.info("授权成为经纪人");

        ResultModel resultModel = checkAuthAgent(phone, wxid, isHeader, password);
        if (resultModel != null) {
            return resultModel;
        }

        // 根据openid更新经纪人状态
        TalentmarketMember member = new TalentmarketMember();
        member.setOpenid(openid);
        member.setPhone(phone); // 手机号
        member.setWxid(wxid); // 微信号
        member.setUpddate(new Date());
        
        // 是否总部经纪人
        isHeader = FinalData.Member.HEADER_AGENT == isHeader ? 
        		FinalData.Member.HEADER_AGENT : FinalData.Member.COMMON_AGENT;
        
        memberMapper.updateByOpenid(member);
        return new ResultModel(true, "SUCCESS");
    }

    /**
     * 授权成为经纪人,参数校验
     * @param phone
     * @param wxid
     * @param isHead
     * @return
     */
    private ResultModel checkAuthAgent(String phone, String wxid, Integer isHeader, String password) {

        if (!StringUtils.hasText(phone)) {
            return new ResultModel(false, "请填写手机号");
        }

        if (!StringUtils.hasText(wxid)) {
            return new ResultModel(false, "请填写微信号");
        }

        if (isHeader == null || (isHeader != FinalData.Member.HEADER_AGENT && isHeader != FinalData.Member.COMMON_AGENT)) {
            return new ResultModel(false, "请填写正确的经纪人类型");
        }
        
        if (isHeader == FinalData.Member.HEADER_AGENT) { // 总部经纪人则校验密码
			String headerPwd = gamerulesMapper.getHeaderPassword();
			if (!headerPwd.equals(MD5Utils.MD5(password))) { // md5加密后比较
				return new ResultModel(false, "总部经纪人密码错误");
			}
		}
        return null;
    }
}
