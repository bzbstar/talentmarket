package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketMember;

import java.util.List;

public interface TalentmarketMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketMember record);

    int insertSelective(TalentmarketMember record);

    TalentmarketMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketMember record);

    int updateByPrimaryKey(TalentmarketMember record);

    /**
     * 根据openid获取会员信息
     * @param openid
     * @return
     */
    TalentmarketMember getByOpenid(String openid);

    /**
     * 根据openid更新会员信息
     * @param member
     * @return
     */
    int updateByOpenid(TalentmarketMember member);

    /**
     * 更新推荐人的相关粉丝信息
     * @param presenterOpenid
     * @param randMoney, 单位分
     * @return
     */
    int updateFans(String presenterOpenid, long randMoney);

    /**
     * 获取推荐者的粉丝列表
     * @param openid
     * @return
     */
    List<TalentmarketMember> getRecommendMembersByOpenid(String openid);
}