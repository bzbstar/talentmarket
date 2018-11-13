package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketGamerules;

public interface TalentmarketGamerulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketGamerules record);

    int insertSelective(TalentmarketGamerules record);

    TalentmarketGamerules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketGamerules record);

    int updateByPrimaryKey(TalentmarketGamerules record);

    /**
     * 获取华山路的半径映射范围
     * @return
     */
    TalentmarketGamerules getDistinceAndRed();
}