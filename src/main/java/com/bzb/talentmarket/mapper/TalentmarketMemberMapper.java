package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketMember;

public interface TalentmarketMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketMember record);

    int insertSelective(TalentmarketMember record);

    TalentmarketMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketMember record);

    int updateByPrimaryKey(TalentmarketMember record);
}