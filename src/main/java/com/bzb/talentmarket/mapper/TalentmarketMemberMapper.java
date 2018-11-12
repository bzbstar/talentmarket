package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketMember;

import java.lang.reflect.Member;

public interface TalentmarketMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketMember record);

    int insertSelective(TalentmarketMember record);

    TalentmarketMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketMember record);

    int updateByPrimaryKey(TalentmarketMember record);

    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     */
    TalentmarketMember getByOpenid(String openid);

    /**
     * 根据openid更新用户信息
     * @param member
     * @return
     */
    int updateByOpenid(TalentmarketMember member);
}