package com.bzb.talentmarket.service;

import com.bzb.talentmarket.entity.RedGrandrecords;
import com.bzb.talentmarket.entity.TalentmarketMember;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author bzb
 * @Description: 会员中心业务接口
 * @date 2018/11/15 11:12
 */
public interface MemberCenterService {

    /**
     * 根据授权openid获取会员信息
     * @param openid
     * @return
     */
    TalentmarketMember getByAuthOpenid(String openid);

    /**
     * 采用静默授权重定向到会员中心页
     * @return
     */
    String getAuthRedirectUrl();

    /**
     * 获取红包发放记录列表
     * @param openid
     * @param pageNo 当前页
     * @Param pageSize 每页大小
     * @return
     */
    PageInfo<RedGrandrecords> getRedGrandRecodes(String openid, Integer pageNo, Integer pageSize);
}
