package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketChoreboy;

public interface TalentmarketChoreboyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketChoreboy record);

    int insertSelective(TalentmarketChoreboy record);

    TalentmarketChoreboy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketChoreboy record);

    int updateByPrimaryKey(TalentmarketChoreboy record);
}