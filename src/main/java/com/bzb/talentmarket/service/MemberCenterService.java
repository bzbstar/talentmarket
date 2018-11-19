package com.bzb.talentmarket.service;

import com.bzb.talentmarket.bean.ResultModel;
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

    /**
     * 获取推荐人的openid
     * @param openid 推荐人的openid
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<TalentmarketMember> getRecommendMembers(String openid, Integer pageNo, Integer pageSize);

    /**
     * 授权成为总部经纪人
     * @param phone 手机号
     * @param wxid 微信号
     * @param isHead 是否总部经纪人, 0 不是， 1是
     * @return
     */
    ResultModel authToAgent(String openid, String phone, String wxid, Integer isHeader, String password);
}
